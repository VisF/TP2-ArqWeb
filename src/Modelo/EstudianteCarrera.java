package Modelo;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class EstudianteCarrera {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "idestudiante", nullable = false)
	private Estudiante estudiante;

	@ManyToOne
	@JoinColumn(name = "idcarrera", nullable = false)
	private Carrera carrera;
	@Column
	private LocalDate fechainicio;
	@Column
	private LocalDate fechafin;
	
	public EstudianteCarrera() {
		
	}

	public EstudianteCarrera(Estudiante estudiante, Carrera carrera, LocalDate fechainicio) {
		super();
		this.estudiante = estudiante;
		this.carrera = carrera;
		this.fechainicio = fechainicio;
	}
	
	public int getId() {
		return id;
	}

	public Estudiante getEstudiante() {
		return estudiante;
	}
	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}
	public Carrera getCarrera() {
		return carrera;
	}
	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}
	public LocalDate getFechainicio() {
		return fechainicio;
	}
	public void setFechainicio(LocalDate fechainicio) {
		this.fechainicio = fechainicio;
	}
	public LocalDate getFechafin() {
		return fechafin;
	}
	public void setFechafin(LocalDate fechafin) {
		this.fechafin = fechafin;
	}
	
	
	
}
