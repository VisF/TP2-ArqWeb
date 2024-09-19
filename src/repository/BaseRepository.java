package repository;

import java.util.List;

/**
 * BaseRepository es una interfaz genérica que define las operaciones CRUD básicas
 * para la gestión de entidades en una base de datos.
 *
 * @param <T> El tipo de la entidad que será manejada.
 * @param <ID> El tipo del identificador de la entidad.
 */
public interface BaseRepository<T, ID> {
	T findById(ID id);

	List<T> findAll();

	T save(T entity);

	void delete(T entity);
}
