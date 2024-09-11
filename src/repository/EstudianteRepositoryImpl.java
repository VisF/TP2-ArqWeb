package repository;

import java.util.List;

import Modelo.Estudiante;

public class EstudianteRepositoryImpl implements EstudianteRepository{
	public static EstudianteRepositoryImpl instance = new EstudianteRepositoryImpl();
	
	private EstudianteRepositoryImpl() {
		
	}
	public static EstudianteRepositoryImpl get_instance() {
		return instance;
	}
	
	@Override
	public Estudiante findById(Integer id) {
		return RepositoryFactory.getEntity_manager().find(Estudiante.class, id);
	}

	@Override
	public List<Estudiante> findAll() {
		return RepositoryFactory.getEntity_manager().createQuery(
				"SELECT e FROM Estudiante e", Estudiante.class).getResultList();
	}

	@Override
	public Estudiante save(Estudiante estudiante) {
		RepositoryFactory.getEntity_manager().getTransaction().begin();
		if(estudiante.getId() == null) {
			RepositoryFactory.getEntity_manager().persist(estudiante);
			RepositoryFactory.getEntity_manager().getTransaction().commit();
			RepositoryFactory.cerrar_conexion();
			return estudiante;
		}
		RepositoryFactory.getEntity_manager().merge(estudiante);
		RepositoryFactory.getEntity_manager().getTransaction().commit();
		RepositoryFactory.cerrar_conexion();
		
		return estudiante;
	}

	@Override
	public void delete(Estudiante estudiante) {
		RepositoryFactory.getEntity_manager().remove(estudiante);
		
	}

}
