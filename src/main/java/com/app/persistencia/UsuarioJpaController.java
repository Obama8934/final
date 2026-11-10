
package com.app.persistencia;

import com.app.logica.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class UsuarioJpaController {

    private EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("unidadBiblioteca");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // CREAR
    public void crearUsuario(Usuario usuario) {
        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
        } finally {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            em.close();
        }
    }

    // EDITAR
    public void editarUsuario(Usuario usuario) {
        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();
            em.merge(usuario);
            em.getTransaction().commit();
        } finally {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            em.close();
        }
    }

    // BUSCAR POR ID
    public Usuario buscarUsuarioPorId(int id) {
        EntityManager em = getEntityManager();

        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    // LISTAR TODOS
    public List<Usuario> listarUsuarios() {
        EntityManager em = getEntityManager();

        try {
            return em.createQuery(
                "SELECT u FROM Usuario u ORDER BY u.apellido, u.nombre",
                Usuario.class
            ).getResultList();
        } finally {
            em.close();
        }
    }

    // ELIMINAR
    public void eliminarUsuario(int id) {
        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();

            Usuario usuario = em.find(Usuario.class, id);

            if (usuario != null) {
                em.remove(usuario);
            }

            em.getTransaction().commit();
        } finally {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            em.close();
        }
    }
}
