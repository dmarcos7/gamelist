package modelo;

import java.util.ArrayList;

public class Juego {
	private Long id;
	private String titulo;
	private String sinopsis;
	private int num_trofeos;
	private int puntuacion;
	private Genero genero;
	private ArrayList<Trofeo> trofeos;
	
	
	public Juego(Long id, String titulo, String sinopsis, int num_trofeos, int puntuacion, Genero genero) {
		
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getSinopsis() {
		return sinopsis;
	}


	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}


	public int getNum_trofeos() {
		return num_trofeos;
	}


	public void setNum_trofeos(int num_trofeos) {
		this.num_trofeos = num_trofeos;
	}


	public int getPuntuacion() {
		return puntuacion;
	}


	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}


	public Genero getGenero() {
		return genero;
	}


	public void setGenero(Genero genero) {
		this.genero = genero;
	}


	public ArrayList<Trofeo> getTrofeos() {
		return trofeos;
	}


	public void setTrofeos(ArrayList<Trofeo> trofeos) {
		this.trofeos = trofeos;
	}
	
	

}
