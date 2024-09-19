package repository;

import java.util.List;

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
	
	 /** CONSULTA F
     * Recupera las carreras con el mayor número de estudiantes inscritos.
     *
     * @return Una lista de carreras con estudiantes inscritos, ordenada por cantidad de inscripciones.
     */
	public List<Carrera> getCarrerasConAlumnosInscriptos(){
		return  RepositoryFactory.getEntity_manager().createQuery(
				
				"SELECT c "
				+ "FROM Carrera c JOIN c.estudiantes ec "
				+ "GROUP BY c.id "
				+ " ORDER BY COUNT(ec.carrera) DESC ", Carrera.class)
				.getResultList();
	}
	
	/** CONSULTA G
     * Recupera los estudiantes de una carrera específica que residen en una ciudad dada.
     *
     * @param carrera La carrera de la que se desea obtener estudiantes.
     * @param ciudad La ciudad de residencia de los estudiantes.
     * @return Una lista de estudiantes que residen en la ciudad especificada.
     */
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
