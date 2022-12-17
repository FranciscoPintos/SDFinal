package py.una.entidad;
import java.util.ArrayList;
import java.util.List;

public class Persona {

	Long cedula;
	String nombre;
	String apellido;
	String chapa;
	String marca;
	Double monto;
	
	List<String> asignaturas;
	
	public Persona(){
		asignaturas = new ArrayList<String>();
	}

	public Persona(Long pcedula, String pnombre, String papellido,String chapa, String marca,Double monto){
		this.cedula = pcedula;
		this.nombre = pnombre;
		this.apellido = papellido;
		this.chapa=chapa;
		this.marca=marca;
		this.monto=monto;
		
		asignaturas = new ArrayList<String>();
	}
	
	public Long getCedula() {
		return cedula;
	}

	public void setCedula(Long cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public List<String> getAsignaturas() {
		return asignaturas;
	}

	public void setAsignaturas(List<String> asignaturas) {
		this.asignaturas = asignaturas;
	}

	public String getChapa() {
		return chapa;
	}

	public void setChapa(String chapa) {
		this.chapa = chapa;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

}
