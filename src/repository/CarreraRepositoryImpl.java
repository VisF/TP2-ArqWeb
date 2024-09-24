package repository;

import java.util.List;

import DTO.CarreraReporteDTO;
import Modelo.Carrera;

/**
 * CarreraRepositoryImpl es una implementación del repositorio para la entidad Carrera.
 * Proporciona las operaciones CRUD utilizando JPA a través del EntityManager.
 */
public class CarreraRepositoryImpl implements CarreraRepository {
	// Instancia única de la clase (patrón singleton)
	public static CarreraRepositoryImpl instance = new CarreraRepositoryImpl();
	
	 /**
     * Constructor privado para asegurar el patrón Singleton.
     */
	private CarreraRepositoryImpl() {
	}
	
	/**
     * Obtiene la instancia única de CarreraRepositoryImpl.
     *
     * @return La instancia única de CarreraRepositoryImpl.
     */
	public static CarreraRepositoryImpl get_instance() {
		return instance;
	}
	
	 /**
     * Busca una entidad Carrera por su identificador.
     *
     * @param id El identificador de la entidad Carrera a buscar.
     * @return La entidad Carrera encontrada o null si no existe.
     */
	@Override
	public Carrera findById(Integer id) {
		return RepositoryFactory.getEntity_manager().find(Carrera.class, id);
	}

	  /**
     * Recupera todas las entidades Carrera de la base de datos.
     *
     * @return Una lista de todas las entidades Carrera.
     */
	@Override
	public List<Carrera> findAll() {
		return RepositoryFactory.getEntity_manager().createQuery(
				"SELECT c FROM Carrera c", Carrera.class).getResultList();
	}

	/**
     * Guarda o actualiza una entidad Carrera en la base de datos.
     * Si la entidad es nueva (id es null), la persiste.
     * Si ya existe, la actualiza (merge).
     *
     * @param carrera La entidad Carrera a guardar.
     * @return La entidad Carrera guardada o actualizada.
     */
	@Override
	public Carrera save(Carrera carrera) {
		RepositoryFactory.getEntity_manager().getTransaction().begin();
		if(carrera.getId() == null) {
			RepositoryFactory.getEntity_manager().persist(carrera);
			RepositoryFactory.getEntity_manager().getTransaction().commit();
			//RepositoryFactory.cerrar_conexion();
			return carrera;
		}
		RepositoryFactory.getEntity_manager().merge(carrera);
		RepositoryFactory.getEntity_manager().getTransaction().commit();
		//RepositoryFactory.cerrar_conexion();
		
		return carrera;
	}

	/**
     * Elimina una entidad Carrera de la base de datos.
     *
     * @param carrera La entidad Carrera a eliminar.
     */
	@Override
	public void delete(Carrera carrera) {
		RepositoryFactory.getEntity_manager().remove(carrera);	
	}
	
	
	public List<CarreraReporteDTO> generarReporteCarreras() {
	    return RepositoryFactory.getEntity_manager().createQuery(
	        "SELECT new CarreraReporteDTO(c.nombre, YEAR(ec.fechaInicio), " +
	        "COUNT(ec), " +
	        "(SELECT COUNT(ece) FROM EstudianteCarrera ece WHERE ece.carrera.id = c.id AND YEAR(ece.fechaFin) = YEAR(ec.fechaInicio))) " +
	        "FROM Carrera c JOIN c.estudiantes ec " +
	        "GROUP BY c.nombre, YEAR(ec.fechaInicio) " +
	        "ORDER BY c.nombre ASC, YEAR(ec.fechaInicio) ASC", CarreraReporteDTO.class)
	        .getResultList();
	}
	
}
