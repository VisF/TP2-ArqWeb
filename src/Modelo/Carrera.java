package Modelo;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;

@Entity
public class Carrera {
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column    
	private String nombre;
	@OneToMany(mappedBy = "carrera", cascade = CascadeType.ALL)
	private List<EstudianteCarrera> estudiantes;
	public Integer getId() {
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
	public List<EstudianteCarrera> getEstudiantes() {
		return estudiantes;
	}
	public void setEstudiantes(List<EstudianteCarrera> estudiantes) {
		this.estudiantes = estudiantes;
	}
	
}
