package modelo;

public class Trofeo {
	private Long id;
	private String nombre;
	private String descripcion;
	private boolean conseguido;
	
	public Trofeo() {
		
	}
	
	public Trofeo(Long id, String nombre, String descripcion, boolean conseguido) {
		setId(id);
		setNombre(nombre);
		setDescripcion(descripcion);
		setConseguido(conseguido);
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isConseguido() {
		return conseguido;
	}

	public void setConseguido(boolean conseguido) {
		this.conseguido = conseguido;
	}
	
	

}
