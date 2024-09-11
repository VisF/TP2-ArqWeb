package DAO;

import java.util.List;

import javax.persistence.PersistenceContext;

import Modelo.Carrera;
import jakarta.persistence.EntityManager;


public class CarreraDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Carrera carrera) {
        entityManager.persist(carrera);
    }

    public Carrera findById(Long id) {
        return entityManager.find(Carrera.class, id);
    }

    public List<Carrera> findAll() {
        return entityManager.createQuery("FROM Carrera", Carrera.class).getResultList();
    }

    public void update(Carrera carrera) {
        entityManager.merge(carrera);
    }

    public void delete(Carrera carrera) {
        entityManager.remove(entityManager.contains(carrera) ? carrera : entityManager.merge(carrera));
    }
}
