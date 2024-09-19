package repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * RepositoryFactory es una clase singleton que gestiona la creación de repositorios
 * y la conexión con la base de datos mediante JPA.
 */
public class RepositoryFactory {
	public static final String DERBY = "Derby";
	public static final String MYSQL = "MySQL";
	// Instancia única de la clase
	private static RepositoryFactory instance = null;
	// EntityManagerFactory y EntityManager para gestionar las conexiones
	private static EntityManagerFactory entity_manager_factory;
	private static EntityManager entity_manager;

	 /**
     * Constructor privado para implementar el patrón Singleton.
     */
	private RepositoryFactory() {
	}

	 /**
     * Obtiene la instancia única de RepositoryFactory.
     * Si no existe, se crea y se inicializa la conexión a la base de datos.
     *
     * @param unidad_persistencia El nombre de la unidad de persistencia (por ejemplo, Derby o MySQL).
     * @return La instancia única de RepositoryFactory.
     */
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

