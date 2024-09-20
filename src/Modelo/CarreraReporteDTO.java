package Modelo;

public class CarreraReporteDTO {
	
    private String nombreCarrera;
    private int anio;
    private long cantidadInscriptos;
    private long cantidadEgresados;

    public CarreraReporteDTO(String nombreCarrera, int anio, long cantidadInscriptos, long cantidadEgresados) {
        this.nombreCarrera = nombreCarrera;
        this.anio = anio;
        this.cantidadInscriptos = cantidadInscriptos;
        this.cantidadEgresados = cantidadEgresados;
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

