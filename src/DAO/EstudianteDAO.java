package DAO;

import java.util.List;

import Modelo.Estudiante;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class EstudianteDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Estudiante estudiante) {
        entityManager.persist(estudiante);
    }

    public Estudiante findById(Long id) {
        return entityManager.find(Estudiante.class, id);
    }

    public List<Estudiante> findAll() {
        return entityManager.createQuery("FROM Estudiante", Estudiante.class).getResultList();
    }

    public void update(Estudiante estudiante) {
        entityManager.merge(estudiante);
    }

    public void delete(Estudiante estudiante) {
        entityManager.remove(entityManager.contains(estudiante) ? estudiante : entityManager.merge(estudiante));
    }
}