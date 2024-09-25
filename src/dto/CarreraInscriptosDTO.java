package dto;

import java.io.Serializable;

public class CarreraInscriptosDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6900645078818224657L;
	private String carrera;
	private long inscriptos;
	
	
	
	public CarreraInscriptosDTO(String carrera, long inscriptos)  {
		super();
		this.carrera = carrera;
		this.inscriptos = inscriptos;
	}

	public String getCarrera() {
		return carrera;
	}
	
	public long getInscriptos() {
		return inscriptos;
	}

	@Override
	public String toString() {
		return "CarreraInscriptosDTO [carrera=" + carrera + ", inscriptos=" + inscriptos + "]";
	}
	
}
