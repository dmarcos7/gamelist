package modelo;

public class Plataforma {
	
	private int id;
	private String nombre;
	
	
	public Plataforma() {
		
	}
	
	public Plataforma(int id, String nombre) {
		setId(id);
		setNombre(nombre);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	

}
