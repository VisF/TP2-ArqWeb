package repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class RepositoryFactory {
	public static final String DERBY = "Derby";
	public static final String MYSQL = "MySQL";
	private static RepositoryFactory instance = null;
	private static EntityManagerFactory entity_manager_factory;
	private static EntityManager entity_manager;

	private RepositoryFactory() {
	}

	public static RepositoryFactory getInstance(String unidad_persistencia) {
		if (instance == null) {
			entity_manager_factory = Persistence.createEntityManagerFactory(unidad_persistencia);
			entity_manager = entity_manager_factory.createEntityManager();
			instance = new RepositoryFactory();
			return instance;
		}
		return instance;
	}

	public static EntityManagerFactory getEntity_manager_factory() {
		return entity_manager_factory;
	}

	public static EntityManager getEntity_manager() {
		return entity_manager;
	}
	
	public static void cerrar_conexion() {
		RepositoryFactory.getEntity_manager().close();
		RepositoryFactory.getEntity_manager_factory().close();
	}
	
	public static EstudianteRepositoryImpl get_repositorio_estudiante() {
		return EstudianteRepositoryImpl.get_instance();
	}
	
	public static CarreraRepositoryImpl get_repositorio_carrera() {
		return CarreraRepositoryImpl.get_instance();
	}
	
	public static EstudianteCarreraRepositoryImpl get_repositorio_estudiante_carrera() {
		return EstudianteCarreraRepositoryImpl.get_instance();
	}
/*
	public static CarreraRepositoryImpl get_repositorio_jugador() {
		return CarreraRepositoryImpl.get_instance();
	}
*/
}

