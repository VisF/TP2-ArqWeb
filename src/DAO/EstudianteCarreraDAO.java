package DAO;

import java.util.List;

import Modelo.EstudianteCarrera;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class EstudianteCarreraDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(EstudianteCarrera estudianteCarrera) {
        entityManager.persist(estudianteCarrera);
    }

    public EstudianteCarrera findById(Long id) {
        return entityManager.find(EstudianteCarrera.class, id);
    }

    public List<EstudianteCarrera> findAll() {
        return entityManager.createQuery("FROM EstudianteCarrera", EstudianteCarrera.class).getResultList();
    }

    public void update(EstudianteCarrera estudianteCarrera) {
        entityManager.merge(estudianteCarrera);
    }

    public void delete(EstudianteCarrera estudianteCarrera) {
        entityManager.remove(entityManager.contains(estudianteCarrera) ? estudianteCarrera : entityManager.merge(estudianteCarrera));
    }
}