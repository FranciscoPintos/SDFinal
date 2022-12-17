package py.una.server.udp;


import java.io.*;
import java.net.*;
import java.util.*;

import jdk.jfr.internal.Logger;
import py.una.entidad.Persona;
import py.una.entidad.PersonaJSON;

class UDPClient {
    Logger logger;
    public static void main(String a[]) throws Exception {

        ArrayList<Persona> lista = new ArrayList<>();
        System.out.println("Francisco Pintos - CI 5726591");
        // Datos necesario
        String direccionServidor = "127.0.0.1";

        if (a.length > 0) {
            direccionServidor = a[0];
        }

        int puertoServidor = 9876;
        while (true){
            try {

                BufferedReader inFromUser =
                        new BufferedReader(new InputStreamReader(System.in));

                DatagramSocket clientSocket = new DatagramSocket();

                InetAddress IPAddress = InetAddress.getByName(direccionServidor);
                System.out.println("Intentando conectar a = " + IPAddress + ":" + puertoServidor + " via UDP...");

                byte[] sendData = new byte[1024];
                byte[] receiveData = new byte[1024];

                System.out.print("Ingrese Tipo operacion");
                String operacion = inFromUser.readLine();
                String datoPaquete = null;
                switch (Integer.parseInt(operacion)) {
                    case (1):
                        System.out.print("Ingrese el número de cédula (debe ser numérico): ");
                        String strcedula = inFromUser.readLine();
                        Long cedula = 0L;
                        try {
                            cedula = Long.parseLong(strcedula);
                        } catch (Exception e1) {

                        }

                        System.out.print("Ingrese el nombre: ");
                        String nombre = inFromUser.readLine();
                        System.out.print("Ingrese el apellido: ");
                        String apellido = inFromUser.readLine();
                        System.out.print("Ingrese el nro de chapa: ");
                        String chapa = inFromUser.readLine();
                        System.out.print("Ingrese la marca: ");
                        String marca = inFromUser.readLine();
                        System.out.print("Ingrese el monto: ");
                        String monto = inFromUser.readLine();

                        Persona p = new Persona(cedula, nombre, apellido, chapa, marca, Double.parseDouble(monto));
                        lista.add(p);
                        datoPaquete = PersonaJSON.objetoString(p);
                        sendData = datoPaquete.getBytes();
                        break;
                    case (2):
                        double maximo = 0;
                        int indices = 0;
                        for(int i= 0; i< lista.size(); i++){
                            if(lista.get(i).getMonto() > maximo){
                                maximo = lista.get(i).getMonto();
                                indices = i;
                            }

                        }
//                        System.out.println("El cliente con mayor monto es: " + lista.get(indices));
                        System.out.println("El cliente con mayor monto es: " + lista.get(indices).getNombre() + " " + lista.get(indices).getApellido() + " con un monto de: " + lista.get(indices).getMonto());
                        break;
                    default:
                        System.out.println("Opcion no valida");
                        break;


                }


                System.out.println("Enviar " + datoPaquete + " al servidor. (" + sendData.length + " bytes)");
                DatagramPacket sendPacket =
                        new DatagramPacket(sendData, sendData.length, IPAddress, puertoServidor);

                clientSocket.send(sendPacket);

                DatagramPacket receivePacket =
                        new DatagramPacket(receiveData, receiveData.length);

                System.out.println("Esperamos si viene la respuesta.");

                //Vamos a hacer una llamada BLOQUEANTE entonces establecemos un timeout maximo de espera
                clientSocket.setSoTimeout(10000);

                try {
                    // ESPERAMOS LA RESPUESTA, BLOQUENTE
                    clientSocket.receive(receivePacket);

                    String respuesta = new String(receivePacket.getData());
                    Persona presp = PersonaJSON.stringObjeto(respuesta.trim());

                    InetAddress returnIPAddress = receivePacket.getAddress();
                    int port = receivePacket.getPort();

                    System.out.println("Respuesta desde =  " + returnIPAddress + ":" + port);
//                System.out.println("Asignaturas: ");

                    for (String tmp : presp.getAsignaturas()) {
                        System.out.println(" -> " + tmp);
                    }


                } catch (SocketTimeoutException ste) {

                    System.out.println("TimeOut: El paquete udp se asume perdido.");
                }
                clientSocket.close();
            } catch (UnknownHostException ex) {
                System.err.println(ex);
            } catch (IOException ex) {
                System.err.println(ex);
            }
        }

    }
} 

