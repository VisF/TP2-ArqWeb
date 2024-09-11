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
	private int id;
	@Column    
	private String nombre;
	@OneToMany(mappedBy = "carrera", cascade = CascadeType.ALL)
	private List<EstudianteCarrera> estudiantes;
}
