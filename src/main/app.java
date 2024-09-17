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

		
		Estudiante es = new Estudiante("Matias" , "Shedden", LocalDate.now() , 'M', 2345, "Chaves", 2, null);
		repoEstudiante.save(es);
		Estudiante es2 = new Estudiante("Matias" , "Shedden", LocalDate.now() , 'M', 2345, "Necochea", 2, null);
		repoEstudiante.save(es2);
		Estudiante es3 = new Estudiante("Matias" , "Chedden", LocalDate.now() , 'M', 2345, "Necochea", 2, null);
		repoEstudiante.save(es3);
	
		List<Estudiante> lista = repoEstudiante.getEstudianteByCiudad();
		
		for(Estudiante e : lista) {
			System.out.println(e.getCiudadDeResidencia());
		}
		
		RepositoryFactory.cerrar_conexion();
	}

}
