package api.model;

public class Response {
	
	private String nombre;
	
	private Float diametro;
	
	private String velocidad;
	
	private String fecha;
	
	private String planeta;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Float getDiametro() {
		return diametro;
	}

	public void setDiametro(Float diametro) {
		this.diametro = diametro;
	}

	public String getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(String velocidad) {
		this.velocidad = velocidad;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getPlaneta() {
		return planeta;
	}

	public void setPlaneta(String planeta) {
		this.planeta = planeta;
	}
	

}
