package main;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import Modelo.Carrera;
import Modelo.Estudiante;
import Modelo.EstudianteCarrera;
import conection.ConnectionFactory;
import repository.CarreraRepositoryImpl;
import repository.EstudianteCarreraRepositoryImpl;
import repository.EstudianteRepositoryImpl;
import repository.RepositoryFactory;

public class app {

	public static void main(String[] args)  {
		RepositoryFactory.getInstance(RepositoryFactory.MYSQL);
		
		EstudianteRepositoryImpl repoEstudiante = RepositoryFactory.get_repositorio_estudiante();

		//EstudianteCarreraRepositoryImpl repoEstCar = RepositoryFactory.get_repositorio_EsCar();
		
		
		Estudiante es = new Estudiante("Paula" , "Sabatini", LocalDate.now() , 'F', 2345, "Chacabuco",  null);
		Estudiante es2 = new Estudiante("Laura" , "Martinez", LocalDate.now() , 'F', 2345, "Zamora",  null);
		Estudiante es3 = new Estudiante("Anastacia" , "Lopez", LocalDate.now() , 'F', 2345, "Loberia",  null);
		/*
		repoEstudiante.save(es);
		repoEstudiante.save(es2);
		repoEstudiante.save(es3);
		*/
		/*
		List<Estudiante> lista = repoEstudiante.getEstudianteByCiudad();
		
		for(Estudiante e : lista) {
			System.out.println(e.getNombre() +" " +  e.getApellido() + " " + e.getCiudadDeResidencia());
		}
		
		List<Estudiante> lista2 = repoEstudiante.getEstudianteByGenero('F');
		
		for(Estudiante e : lista2) {
			System.out.println(e.getNombre() +" " +  e.getApellido() + " " + e.getGenero());
		}
		Estudiante estudiante = repoEstudiante.getEstudianteByMatricula(8);
		System.out.println(estudiante.getNombre() + " " + estudiante.getApellido());
		
		*/
		
		
		/*CARRERAS*/
		
		CarreraRepositoryImpl repoCarrera = RepositoryFactory.get_repositorio_carrera();
		Carrera c1 = new Carrera("TUDAI");
		Carrera c2 = new Carrera("Tecnicatura en Bioingenieria");
		
		repoCarrera.save(c1);
		repoCarrera.save(c2);
		
		
		/*ESTUDIANTE - CARRERA*/
		EstudianteCarreraRepositoryImpl repoEstCarr = RepositoryFactory.get_repositorio_estudiante_carrera();
		EstudianteCarrera ec = new EstudianteCarrera(es, c1,  LocalDate.now());
		repoEstCarr.save(ec);
		
		System.out.println(ec.getFechainicio().getYear());
		
		List<Carrera> carreras = repoEstCarr.getCarrerasConAlumnosInscriptos();
		for(Carrera cc : carreras) {
			System.out.println(cc.getNombre());
		}
		
		List<Estudiante> lista2 = repoEstCarr.getEstudiantesByCiudad(c1, "Loberia");
		for(Estudiante ee : lista2) {
			System.out.println(ee.getNombre() + ee.getCiudadDeResidencia() );
		}
		
		RepositoryFactory.cerrar_conexion();
		
		
	}

}
