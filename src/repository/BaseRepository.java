package repository;

import java.util.List;

public interface BaseRepository<T, ID> {
	T findById(ID id);

	List<T> findAll();

	T save(T entity);

	void delete(T entity);
}
