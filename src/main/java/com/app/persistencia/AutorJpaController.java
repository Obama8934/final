
package com.app.persistencia;

import java.util.List;

import com.app.logica.Autor;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class AutorJpaController {

    private EntityManagerFactory emf;

    public AutorJpaController() {
        emf = Persistence.createEntityManagerFactory("unidadBiblioteca");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // CREAR
    public void crear(Autor autor) {
        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(autor);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    // BUSCAR POR ID
    public Autor buscarPorId(int id) {
        EntityManager em = getEntityManager();

        try {
            return em.find(Autor.class, id);
        } finally {
            em.close();
        }
    }

    // LISTAR TODOS
    public List<Autor> buscarTodos() {
        EntityManager em = getEntityManager();

        try {
            return em.createQuery(
                "SELECT a FROM Autor a", Autor.class
            ).getResultList();
        } finally {
            em.close();
        }
    }

    // EDITAR
    public void editar(Autor autor) {
        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();
            em.merge(autor);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    // ELIMINAR
    public void destruir(int id) {
        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();

            Autor autor = em.find(Autor.class, id);

            if (autor != null) {
                em.remove(autor);
            }

            em.getTransaction().commit();
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
}
