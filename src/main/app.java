package main;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import Modelo.Estudiante;
import conection.ConnectionFactory;
import repository.EstudianteRepositoryImpl;
import repository.RepositoryFactory;

public class app {

	public static void main(String[] args) {
		RepositoryFactory.getInstance(RepositoryFactory.MYSQL);
		
		EstudianteRepositoryImpl repoEstudiante = RepositoryFactory.get_repositorio_estudiante();

		//EstudianteCarreraRepositoryImpl repoEstCar = RepositoryFactory.get_repositorio_EsCar();
		
		
		Estudiante es = new Estudiante("Paula" , "Sabatini", LocalDate.now() , 'F', 2345, "Chacabuco",  null);
		repoEstudiante.save(es);
		Estudiante es2 = new Estudiante("Laura" , "Martinez", LocalDate.now() , 'F', 2345, "Zamora",  null);
		repoEstudiante.save(es2);
		Estudiante es3 = new Estudiante("Anastacia" , "Lopez", LocalDate.now() , 'F', 2345, "Loberia",  null);
		repoEstudiante.save(es3);
		
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
		RepositoryFactory.cerrar_conexion();
	}

}
