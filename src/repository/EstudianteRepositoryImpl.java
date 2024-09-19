package repository;

import java.sql.SQLException;
import java.util.List;

import Modelo.Estudiante;
import jakarta.persistence.NoResultException;

@SuppressWarnings("unused")
/**
 * EstudianteRepositoryImpl es una implementación del repositorio para la entidad Estudiante.
 * Proporciona las operaciones CRUD y algunas búsquedas específicas usando JPA.
 */
public class EstudianteRepositoryImpl implements EstudianteRepository {
	// Instancia única de la clase (patrón singleton)
	public static EstudianteRepositoryImpl instance = new EstudianteRepositoryImpl();
	
	/**
     * Constructor privado para asegurar el patrón Singleton.
     */
	private EstudianteRepositoryImpl() {
		
	}
	
	/**
     * Obtiene la instancia única de EstudianteRepositoryImpl.
     *
     * @return La instancia única de EstudianteRepositoryImpl.
     */
	public static EstudianteRepositoryImpl get_instance() {
		return instance;
	}
	
	 /**
     * Busca una entidad Estudiante por su identificador.
     *
     * @param id El identificador de la entidad Estudiante a buscar.
     * @return La entidad Estudiante encontrada o null si no existe.
     */
	@Override
	public Estudiante findById(Integer id) {
		return RepositoryFactory.getEntity_manager().find(Estudiante.class, id);
	}

	 /**
     * Recupera todas las entidades Estudiante de la base de datos.
     *
     * @return Una lista de todas las entidades Estudiante.
     */
	@Override
	public List<Estudiante> findAll() {
		return RepositoryFactory.getEntity_manager().createQuery(
				"SELECT e FROM Estudiante e", Estudiante.class).getResultList();
	}

	/** CONSULTA A
     * Guarda o actualiza una entidad Estudiante en la base de datos.
     * Si la entidad es nueva (id es null), genera un número de libreta único y la persiste.
     * Si ya existe, la actualiza (merge).
     *
     * @param estudiante La entidad Estudiante a guardar.
     * @return La entidad Estudiante guardada o actualizada.
     */
	@Override
	public Estudiante save(Estudiante estudiante) {
		RepositoryFactory.getEntity_manager().getTransaction().begin();
		//RepositoryFactory.getEntity_manager_factory();
		if(estudiante.getId() == null) {
			estudiante.setNroLibreta(generarNroLibretaUnico());
			RepositoryFactory.getEntity_manager().persist(estudiante);
			RepositoryFactory.getEntity_manager().getTransaction().commit();
			//RepositoryFactory.cerrar_conexion();
			return estudiante;
		}
		RepositoryFactory.getEntity_manager().merge(estudiante);
		RepositoryFactory.getEntity_manager().getTransaction().commit();
		//RepositoryFactory.cerrar_conexion();
		
		return estudiante;
	}

	 /**
     * Elimina una entidad Estudiante de la base de datos.
     *
     * @param estudiante La entidad Estudiante a eliminar.
     */
	@Override
	public void delete(Estudiante estudiante) {
		RepositoryFactory.getEntity_manager().remove(estudiante);
		
	}
	
	/** CONSULTA E
     * Busca estudiantes por su género.
     *
     * @param genero El género de los estudiantes a buscar.
     * @return Una lista de estudiantes que coinciden con el género.
     */
	public List<Estudiante> getEstudianteByGenero(char genero){
		return  RepositoryFactory.getEntity_manager().createQuery(
				"SELECT e FROM Estudiante e WHERE e.genero = :genero", Estudiante.class)
				.setParameter("genero", genero)
				.getResultList();
	}
	
	  /** CONSULTA D
     * Busca un estudiante por su número de matrícula.
     *
     * @param matricula El número de matrícula del estudiante.
     * @return La entidad Estudiante encontrada o null si no existe.
     */
	public Estudiante getEstudianteByMatricula(int matricula) {
		Estudiante est = new Estudiante();
		try{
		est =  RepositoryFactory.getEntity_manager().createQuery(
				"SELECT e FROM Estudiante e WHERE e.nroLibreta = :nroLibreta", Estudiante.class)
				.setParameter("nroLibreta", matricula)
				.getSingleResult();
		}
		catch (NoResultException nre){
		}
		return est;
	}
	
	/** CONSULTA C
     * Recupera todos los estudiantes ordenados por ciudad de residencia.
     *
     * @return Una lista de estudiantes ordenados por ciudad de residencia.
     */
	public List<Estudiante> getEstudianteByCiudad(){
		return  RepositoryFactory.getEntity_manager().createQuery(
				"SELECT e FROM Estudiante e ORDER BY e.ciudadDeResidencia", Estudiante.class).getResultList();
	}
	
	 /**
     * Genera un número de libreta único para un nuevo estudiante.
     *
     * @return El número de libreta generado.
     */
	private int generarNroLibretaUnico() {
		return getUltimoNumeroLibreta() + 1;
	}
	
	/**
     * Obtiene el último número de libreta utilizado.
     *
     * @return El último número de libreta o 0 si no existen estudiantes.
     */
	public int getUltimoNumeroLibreta() {
		Integer ultimoNroLibreta =  RepositoryFactory.getEntity_manager().createQuery(
				"SELECT MAX(e.nroLibreta) FROM Estudiante e", Integer.class)
				.getSingleResult();
		
		return (ultimoNroLibreta != null) ? ultimoNroLibreta : 0;
	}

}
