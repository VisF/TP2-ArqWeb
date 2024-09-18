package repository;

import java.sql.SQLException;
import java.util.List;

import Modelo.Estudiante;
import jakarta.persistence.NoResultException;

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

	@Override
	public void delete(Estudiante estudiante) {
		RepositoryFactory.getEntity_manager().remove(estudiante);
		
	}
	
	public List<Estudiante> getEstudianteByGenero(char genero){
		return  RepositoryFactory.getEntity_manager().createQuery(
				"SELECT e FROM Estudiante e WHERE e.genero = :genero", Estudiante.class)
				.setParameter("genero", genero)
				.getResultList();
	}
	
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
	
	public List<Estudiante> getEstudianteByCiudad(){
		return  RepositoryFactory.getEntity_manager().createQuery(
				"SELECT e FROM Estudiante e ORDER BY e.ciudadDeResidencia", Estudiante.class).getResultList();
	}
	
	private int generarNroLibretaUnico() {
		return getUltimoNumeroLibreta() + 1;
	}
	
	public int getUltimoNumeroLibreta() {
		Integer ultimoNroLibreta =  RepositoryFactory.getEntity_manager().createQuery(
				"SELECT MAX(e.nroLibreta) FROM Estudiante e", Integer.class)
				.getSingleResult();
		
		return (ultimoNroLibreta != null) ? ultimoNroLibreta : 0;
	}

}
