package dto;

import java.io.Serializable;
import java.time.LocalDate;

public class EstudianteDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2771089775738619762L;
	private String nombre;
	private String apellido;
	private LocalDate fechaNac;
	private char genero;
	private int dni;
	private String ciudadDeResidencia;
	private int nroLibreta;
	
	
	public EstudianteDTO(String nombre, String apellido, LocalDate fechaNac, char genero, int dni,
			String ciudadDeResidencia, int nroLibreta) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNac = fechaNac;
		this.genero = genero;
		this.dni = dni;
		this.ciudadDeResidencia = ciudadDeResidencia;
		this.nroLibreta = nroLibreta;
	}

	public String getNombre() {
		return nombre;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public LocalDate getFechaNac() {
		return fechaNac;
	}
	
	public char getGenero() {
		return genero;
	}
	
	public int getDni() {
		return dni;
	}
	
	public String getCiudadDeResidencia() {
		return ciudadDeResidencia;
	}
	
	public int getNroLibreta() {
		return nroLibreta;
	}

	@Override
	public String toString() {
		return "EstudianteDTO [nombre=" + nombre + ", apellido=" + apellido + ", fechaNac=" + fechaNac + ", genero="
				+ genero + ", dni=" + dni + ", ciudadDeResidencia=" + ciudadDeResidencia + ", nroLibreta=" + nroLibreta
				+ "]";
	}
	
	
}
