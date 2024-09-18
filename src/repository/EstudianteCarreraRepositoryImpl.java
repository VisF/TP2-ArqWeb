package repository;

import java.util.List;

import Modelo.Carrera;
import Modelo.Estudiante;
import Modelo.EstudianteCarrera;
import Modelo.EstudianteCarreraId;

public class EstudianteCarreraRepositoryImpl implements EstudianteCarreraRepository{
public static EstudianteCarreraRepositoryImpl instance = new EstudianteCarreraRepositoryImpl();
	
	private EstudianteCarreraRepositoryImpl() {
		
	}
	public static EstudianteCarreraRepositoryImpl get_instance() {
		return instance;
	}
	
	@Override
	public EstudianteCarrera findById(EstudianteCarreraId id) {
		return RepositoryFactory.getEntity_manager().find(EstudianteCarrera.class, id);
	}

	@Override
	public List<EstudianteCarrera> findAll() {
		return RepositoryFactory.getEntity_manager().createQuery(
				"SELECT e FROM EstudianteCarrera e", EstudianteCarrera.class).getResultList();
	}

	@Override
	public EstudianteCarrera save(EstudianteCarrera estudianteCarrera) {
		RepositoryFactory.getEntity_manager().getTransaction().begin();
		EstudianteCarreraId id = new EstudianteCarreraId(
				estudianteCarrera.getEstudiante().getId(),
				estudianteCarrera.getCarrera().getId());
		//TO DO
		EstudianteCarrera existe = RepositoryFactory.getEntity_manager().find(EstudianteCarrera.class, id);
		if(existe == null) {
			RepositoryFactory.getEntity_manager().persist(estudianteCarrera);
			RepositoryFactory.getEntity_manager().getTransaction().commit();
			//RepositoryFactory.cerrar_conexion();
			return estudianteCarrera;
		}
		RepositoryFactory.getEntity_manager().merge(estudianteCarrera);
		RepositoryFactory.getEntity_manager().getTransaction().commit();
		//RepositoryFactory.cerrar_conexion();
		
		return estudianteCarrera;
	}

	@Override
	public void delete(EstudianteCarrera EstudianteCarrera) {
		RepositoryFactory.getEntity_manager().remove(EstudianteCarrera);
		
	}
	
	public List<Carrera> getCarrerasConAlumnosInscriptos(){
		return  RepositoryFactory.getEntity_manager().createQuery(
				
				"SELECT c "
				+ "FROM Carrera c JOIN c.estudiantes ec "
				+ "GROUP BY c.id "
				+ " ORDER BY COUNT(ec.carrera) DESC ", Carrera.class)
				.getResultList();
	}
	
	public List<Estudiante> getEstudiantesByCiudad(Carrera carrera, String ciudad){
		return  RepositoryFactory.getEntity_manager().createQuery(
				"SELECT e "
				+ "FROM Estudiante e JOIN e.carreras ec "
				+ "JOIN ec.carrera c "
				+ "WHERE c.nombre = :nombreCarrera "
				+ " AND e.ciudadDeResidencia = :ciudad ", Estudiante.class)
				.setParameter("nombreCarrera", carrera.getNombre())
				.setParameter("ciudad", ciudad)
				.getResultList();
		/*
		 * tendria q mostrar el nombre d ela carrera tmb?
		 * */
	}
	

}
