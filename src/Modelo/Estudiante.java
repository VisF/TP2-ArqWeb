package Modelo;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;


@Entity
public class Estudiante {
	@Id
	private int id;
	@Column
	private String nombre;
	@Column
	private String apellido;
	@Column
	private Date fechaNac;
	@Column
	private char genero;
	@Column
	private int dni;
	@Column
	private String ciudadDeResidencia;
	@Column
	private int nroLibreta;
	@ManyToMany //En carrera (mappedBy = "carreraSet")
	private List<Carrera> carreras;
	
	public Estudiante(String nombre, String apellido, Date fechaNac, char genero, int dni, String ciudadDeResidencia,
			int nroLibreta, List<Carrera> carreras) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNac = fechaNac;
		this.genero = genero;
		this.dni = dni;
		this.ciudadDeResidencia = ciudadDeResidencia;
		this.nroLibreta = nroLibreta;
		this.carreras = carreras;
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
	public char getGenero() {
		return genero;
	}
	public void setGenero(char genero) {
		this.genero = genero;
	}
	public String getCiudadDeResidencia() {
		return ciudadDeResidencia;
	}
	public void setCiudadDeResidencia(String ciudadDeResidencia) {
		this.ciudadDeResidencia = ciudadDeResidencia;
	}
	public List<Carrera> getCarreras() {
		List<Carrera> nueva = new ArrayList<>(carreras);
		return nueva;
	}
	public void setCarreras(Carrera carrera) {
		this.carreras.add(carrera);
	}
	public int getEdad() {
		LocalDate fecha = fechaNac.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate fechaActual = LocalDate.now();
		return Period.between(fecha, fechaActual).getYears();
	}
	public int getDni() {
		return dni;
	}
	public int getNroLibreta() {
		return nroLibreta;
	}
	
}
