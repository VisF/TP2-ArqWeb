package main;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import dto.CarreraInscriptosDTO;
import dto.CarreraReporteDTO;
import dto.EstudianteDTO;
import Modelo.Carrera;
import Modelo.Estudiante;
import Modelo.EstudianteCarrera;
import repository.CarreraRepositoryImpl;
import repository.EstudianteCarreraRepositoryImpl;
import repository.EstudianteRepositoryImpl;
import repository.RepositoryFactory;

public class app {

	public static void main(String[] args)  {
		RepositoryFactory.getInstance(RepositoryFactory.MYSQL);
		

		//EstudianteCarreraRepositoryImpl repoEstCar = RepositoryFactory.get_repositorio_EsCar();
		
		
		//generarDatos();
		
		testEnConsola();
		
		RepositoryFactory.cerrar_conexion();
		
		
		
	}

	private static void testEnConsola() {
		List<EstudianteDTO> lista2 = getEstudiantesPorGenero('F');
		System.out.println("\nESTUDIANTES POR GENERO\n");
		for(EstudianteDTO e : lista2) {
			System.out.println("\t"+e);
		}
		

		EstudianteDTO estudiante = getEstudiantePorMatricula(2);
		System.out.println("\nESTUDIANTE POR MATRICULA\n");
		System.out.println("\t"+estudiante);		
		
		
		List<CarreraInscriptosDTO> carreras = getCarrerasOrdenadasPorInscriptos();
		System.out.println("\nCARRERAS CON ALUMNOS ORDENADAS\n");
		for(CarreraInscriptosDTO cc : carreras) {
			System.out.println("\t"+cc);
		}
		
		List<EstudianteDTO> lista3 = getEstudiantesOrdenadosPorCiudad();
		System.out.println("\nESTUDIANTES POR CIUDAD\n");
		for(EstudianteDTO ee : lista3) {
			System.out.println("\t"+ee );
		}
		
		List<EstudianteDTO> lista4 = getEstudiantesDeCarreraOrdenadosPorCiudad("Zamora", "TUDAI");
		System.out.println("\nESTUDIANTES DE CARRERA Y CIUDAD\n");
		for(EstudianteDTO ee : lista4) {
			System.out.println("\t"+ee );
		}
		
		List<CarreraReporteDTO> listaDTO = getReporteCarreras();
		System.out.println("\nCARRERAS\n");
		for(CarreraReporteDTO crdto : listaDTO) {
			System.out.println("\t"+crdto);
		}
	}

	private static void generarDatos() {
		
		EstudianteRepositoryImpl repoEstudiante = RepositoryFactory.get_repositorio_estudiante();
		CarreraRepositoryImpl repoCarrera = RepositoryFactory.get_repositorio_carrera();
		EstudianteCarreraRepositoryImpl repoEstCarr = RepositoryFactory.get_repositorio_estudiante_carrera();
		
		Estudiante es = new Estudiante("Paula" , "Sabatini", LocalDate.now() , 'F', 2345, "Chacabuco",  null);
		Estudiante es2 = new Estudiante("Laura" , "Martinez", LocalDate.now() , 'F', 2345, "Zamora",  null);
		Estudiante es3 = new Estudiante("Anastacia" , "Lopez", LocalDate.now() , 'F', 2345, "Loberia",  null);
		Estudiante es4 = new Estudiante("Juan" , "Lopez", LocalDate.now() , 'M', 2345, "Loberia",  null);
		Estudiante es5 = new Estudiante("Pedro" , "Lopez", LocalDate.now() , 'M', 2345, "Loberia",  null);
		Estudiante es6 = new Estudiante("Laura" , "Garcia", LocalDate.now() , 'F', 2345, "Zamora",  null);
		
		
		repoEstudiante.save(es);
		repoEstudiante.save(es2);
		repoEstudiante.save(es3);
		repoEstudiante.save(es4);
		repoEstudiante.save(es5);
		repoEstudiante.save(es6);
		
		
		/*CARRERAS*/
		
		Carrera c1 = new Carrera("TUDAI");
		Carrera c2 = new Carrera("Tecnicatura en Bioingenieria");
		
		repoCarrera.save(c1);
		repoCarrera.save(c2);
		
		
		/*ESTUDIANTE - CARRERA*/
		EstudianteCarrera ec = new EstudianteCarrera(es, c1,  LocalDate.now());
		EstudianteCarrera ec2 = new EstudianteCarrera(es2, c1,  LocalDate.now().minusYears(10));
		EstudianteCarrera ec3 = new EstudianteCarrera(es3, c1,  LocalDate.now().minusMonths(4));
		EstudianteCarrera ec4 = new EstudianteCarrera(es4, c2,  LocalDate.now());
		EstudianteCarrera ec5 = new EstudianteCarrera(es5, c2,  LocalDate.now());
		
		repoEstCarr.save(ec);
		repoEstCarr.save(ec2);
		repoEstCarr.save(ec3);
		repoEstCarr.save(ec4);
		repoEstCarr.save(ec5);
	}
	
	public static List<EstudianteDTO> getEstudiantesOrdenadosPorCiudad(){
		EstudianteRepositoryImpl repoEstudiante = RepositoryFactory.get_repositorio_estudiante();
		return repoEstudiante.getEstudianteOrderByCiudad();
	}
	
	public static EstudianteDTO getEstudiantePorMatricula(int matricula) {
		EstudianteRepositoryImpl repoEstudiante = RepositoryFactory.get_repositorio_estudiante();
		return repoEstudiante.getEstudianteByMatricula(matricula);
	}
	
	public static List<EstudianteDTO> getEstudiantesPorGenero(char genero){
		EstudianteRepositoryImpl repoEstudiante = RepositoryFactory.get_repositorio_estudiante();
		return repoEstudiante.getEstudianteByGenero(genero);
	}
	
	public static List<CarreraInscriptosDTO> getCarrerasOrdenadasPorInscriptos(){
		CarreraRepositoryImpl repoCarrera = RepositoryFactory.get_repositorio_carrera();
		return repoCarrera.getCarrerasConAlumnosInscriptos();
	}
	
	public static List<EstudianteDTO> getEstudiantesDeCarreraOrdenadosPorCiudad(String ciudad, String carrera){
		EstudianteRepositoryImpl repoEstudiante = RepositoryFactory.get_repositorio_estudiante();
		return repoEstudiante.getEstudiantesByCiudad(carrera, ciudad);
	}
	
	public static List<CarreraReporteDTO> getReporteCarreras(){
		CarreraRepositoryImpl repoCarrera = RepositoryFactory.get_repositorio_carrera();
		return repoCarrera.generarReporteCarreras();
	}

}
