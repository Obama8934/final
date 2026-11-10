
package com.app.persistencia;

import java.util.List;

import com.app.logica.Libro;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class LibroJpaController {

    private EntityManagerFactory emf;

    public LibroJpaController() {
        emf = Persistence.createEntityManagerFactory("unidadBiblioteca");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // CREAR
    public void crear(Libro libro) {
        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(libro);
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
    public Libro buscarPorId(int id) {
        EntityManager em = getEntityManager();

        try {
            return em.find(Libro.class, id);
        } finally {
            em.close();
        }
    }

    // LISTAR TODOS
    public List<Libro> buscarTodos() {
        EntityManager em = getEntityManager();

        try {
            return em.createQuery(
                "SELECT l FROM Libro l", Libro.class
            ).getResultList();
        } finally {
            em.close();
        }
    }

    // EDITAR
    public void editar(Libro libro) {
        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();
            em.merge(libro);
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

            Libro libro = em.find(Libro.class, id);

            if (libro != null) {
                em.remove(libro);
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

    // BUSCAR LIBROS POR AUTOR
    public List<Libro> buscarPorAutor(int idAutor) {
        EntityManager em = getEntityManager();

        try {
            return em.createQuery(
                "SELECT l FROM Libro l WHERE l.autor.id = :id",
                Libro.class
            )
            .setParameter("id", idAutor)
            .getResultList();

        } finally {
            em.close();
        }
    }

    // CONTAR LIBROS DE UN AUTOR
    public Long contarLibrosPorAutor(int idAutor) {
        EntityManager em = getEntityManager();

        try {
            return em.createQuery(
                "SELECT COUNT(l) FROM Libro l WHERE l.autor.id = :id",
                Long.class
            )
            .setParameter("id", idAutor)
            .getSingleResult();

        } finally {
            em.close();
        }
    }
}
