package DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Modelo.Estudiante;
import Modelo.EstudianteCarrera;
import repository.EstudianteCarreraRepositoryImpl;
import repository.RepositoryFactory;

public class CarreraReporteDTO implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombreCarrera;
    private int anio;
    private Long cantidadInscriptos;
    private long cantidadEgresados;

    
    public CarreraReporteDTO(String nombreCarrera, int anio, Long insc, long egres) {
    	this.nombreCarrera = nombreCarrera;
    	this.anio = anio;
    	this.cantidadEgresados = egres;
    	this.cantidadInscriptos = insc;
    }

    public String getNombreCarrera() {
    	
        return nombreCarrera;
    }

    public int getAnio() {
        return anio;
    }

    public long getCantidadInscriptos() {
        return cantidadInscriptos;
    }

    public long getCantidadEgresados() {
        return cantidadEgresados;
    }
}

