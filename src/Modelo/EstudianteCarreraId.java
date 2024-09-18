package Modelo;

import java.io.Serializable;
import java.util.Objects;

public class EstudianteCarreraId implements Serializable{
	
	private Integer estudiante;
	private Integer carrera;
	
	public EstudianteCarreraId() {
		
	}
	public EstudianteCarreraId(Integer estudiante, Integer carrera) {
		this.estudiante = estudiante;
		this.carrera = carrera;
	}
	public Integer getId() {
		return (Integer) hashCode();
	}
	public Integer getEstudiante() {
		return estudiante;
	}
	public void setEstudiante(int estudiante) {
		this.estudiante = estudiante;
	}
	public Integer getCarrera() {
		return carrera;
	}
	public void setCarrera(int carrera) {
        this.carrera = carrera;
    }
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EstudianteCarreraId that = (EstudianteCarreraId) o;
        return Objects.equals(estudiante, that.estudiante) && 
               Objects.equals(carrera, that.carrera);
    }

    @Override
    public int hashCode() {
        return Objects.hash(estudiante, carrera);
    }
	

}
