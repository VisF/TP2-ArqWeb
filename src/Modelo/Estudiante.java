package Modelo;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity
public class Estudiante {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
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
	@OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL)
	private List<EstudianteCarrera> carreras;
	
	public Estudiante(String nombre, String apellido, Date fechaNac, char genero, int dni, String ciudadDeResidencia,
			int nroLibreta, List<EstudianteCarrera> carreras) {
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
	
	
	//TODO
	public List<Carrera> getCarreras() {
		List<Carrera> nueva = new ArrayList<>();
		return nueva;
	}
	
	
	
	public Estudiante clone() {
        try {
            Estudiante cloned = (Estudiante) super.clone();
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
	
	public void setCarreras(Carrera carrera) {
		Estudiante copia = clone();
		EstudianteCarrera nueva = new EstudianteCarrera(copia, carrera, LocalDate.now());
		carreras.add(nueva);
	}
	public int getEdad() {
		LocalDate fecha = fechaNac.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate fechaActual = LocalDate.now();
		return Period.between(fecha, fechaActual).getYears();
	}
	public int getDni() {
		return dni;
	}
	
	public Integer getId() {
		return id;
	}

	public int getNroLibreta() {
		return nroLibreta;
	}
	
}
