package repository;

import java.util.List;

import dto.CarreraReporteDTO;
import Modelo.Carrera;
import Modelo.Estudiante;
import Modelo.EstudianteCarrera;
import Modelo.EstudianteCarreraId;

/**
 * EstudianteCarreraRepositoryImpl es una implementación del repositorio para la entidad EstudianteCarrera.
 * Gestiona las relaciones entre estudiantes y carreras usando JPA a través del EntityManager.
 */
public class EstudianteCarreraRepositoryImpl implements EstudianteCarreraRepository {
	// Instancia única de la clase (patrón singleton)
	public static EstudianteCarreraRepositoryImpl instance = new EstudianteCarreraRepositoryImpl();


	/**
	 * Constructor privado para asegurar el patrón Singleton.
	 */
	private EstudianteCarreraRepositoryImpl() {
		
	}
	
	 /**
     * Obtiene la instancia única de EstudianteCarreraRepositoryImpl.
     *
     * @return La instancia única de EstudianteCarreraRepositoryImpl.
     */
	public static EstudianteCarreraRepositoryImpl get_instance() {
		return instance;
	}
	
	/**
     * Busca una entidad EstudianteCarrera por su identificador compuesto.
     *
     * @param id El identificador compuesto de la relación EstudianteCarrera.
     * @return La entidad EstudianteCarrera encontrada o null si no existe.
     */
	@Override
	public EstudianteCarrera findById(EstudianteCarreraId id) {
		return RepositoryFactory.getEntity_manager().find(EstudianteCarrera.class, id);
	}

	 /**
     * Recupera todas las entidades EstudianteCarrera de la base de datos.
     *
     * @return Una lista de todas las entidades EstudianteCarrera.
     */
	@Override
	public List<EstudianteCarrera> findAll() {
		return RepositoryFactory.getEntity_manager().createQuery(
				"SELECT e FROM EstudianteCarrera e", EstudianteCarrera.class).getResultList();
	}

	/**
     * Guarda o actualiza una entidad EstudianteCarrera en la base de datos.
     * Si la relación no existe, la persiste.
     * Si ya existe, la actualiza (merge).
     *
     * @param estudianteCarrera La entidad EstudianteCarrera a guardar.
     * @return La entidad EstudianteCarrera guardada o actualizada.
     */
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

	 /**
     * Elimina una entidad EstudianteCarrera de la base de datos.
     *
     * @param estudianteCarrera La entidad EstudianteCarrera a eliminar.
     */
	@Override
	public void delete(EstudianteCarrera EstudianteCarrera) {
		RepositoryFactory.getEntity_manager().remove(EstudianteCarrera);
		
	}
	
	
	/** CONSULTA B ALTERNATIVA
     * Guarda (matricula) un estudiante en una carrera. 
     * Si no existe la relacion, la persiste.
     * Si ya existe, la actualiza.
     *
     * @param carrera La carrera en la que se desea matricular al estudiante.
     * @param estudiante El estudiante que deseamos matricular.
     * @return La relacion entre el estudiante y la carrera.
     
	public EstudianteCarrera matricularEstudianteEnCarrera(Estudiante estudiante, Carrera carrera) {
	    // Crear el objeto EstudianteCarrera que representa la relación entre estudiante y carrera
	    EstudianteCarrera estudianteCarrera = new EstudianteCarrera();
	    estudianteCarrera.setEstudiante(estudiante);
	    estudianteCarrera.setCarrera(carrera);
	    
	    RepositoryFactory.getEntity_manager().getTransaction().begin();
	    
	    // Verificar si ya existe la relación (matrícula)
	    EstudianteCarreraId id = new EstudianteCarreraId(estudiante.getId(), carrera.getId());
	    EstudianteCarrera existe = RepositoryFactory.getEntity_manager().find(EstudianteCarrera.class, id);
	    
	    if (existe == null) {
	        RepositoryFactory.getEntity_manager().persist(estudianteCarrera);
	    } else {
	        RepositoryFactory.getEntity_manager().merge(estudianteCarrera);
	    }
	    RepositoryFactory.getEntity_manager().getTransaction().commit();

	    return estudianteCarrera;
	}*/


}
