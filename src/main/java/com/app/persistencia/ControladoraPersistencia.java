
package com.app.persistencia;

import java.util.List;
import com.app.persistencia.UsuarioJpaController;

import com.app.logica.Usuario;
import com.app.logica.Autor;
import com.app.logica.Libro;

public class ControladoraPersistencia {

    private AutorJpaController autorJpa =
            new AutorJpaController();

    private LibroJpaController libroJpa =
            new LibroJpaController();

    UsuarioJpaController usuarioJpa = new UsuarioJpaController();
    // -------- AUTORES --------

    public void crearAutor(Autor autor) {
        autorJpa.crear(autor);
    }

    public Autor buscarAutorPorId(int id) {
        return autorJpa.buscarPorId(id);
    }

    public List<Autor> buscarTodosLosAutores() {
        return autorJpa.buscarTodos();
    }

    public void editarAutor(Autor autor) {
        autorJpa.editar(autor);
    }

    public void eliminarAutor(int id) {
        autorJpa.destruir(id);
    }

    // -------- LIBROS --------

    public void crearLibro(Libro libro) {
        libroJpa.crear(libro);
    }

    public Libro buscarLibroPorId(int id) {
        return libroJpa.buscarPorId(id);
    }

    public List<Libro> buscarTodosLosLibros() {
        return libroJpa.buscarTodos();
    }

    public void editarLibro(Libro libro) {
        libroJpa.editar(libro);
    }

    public void eliminarLibro(int id) {
        libroJpa.destruir(id);
    }
    
 // USUARIOS

    public void crearUsuario(Usuario usuario) {
        usuarioJpa.crearUsuario(usuario);
    }

    public void editarUsuario(Usuario usuario) {
        usuarioJpa.editarUsuario(usuario);
    }

    public Usuario buscarUsuarioPorId(int id) {
        return usuarioJpa.buscarUsuarioPorId(id);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioJpa.listarUsuarios();
    }

    public void eliminarUsuario(int id) {
        usuarioJpa.eliminarUsuario(id);
    }

    // -------- CONSULTAS --------

    public List<Libro> buscarLibrosPorAutor(int idAutor) {
        return libroJpa.buscarPorAutor(idAutor);
    }

    public Long contarLibrosPorAutor(int idAutor) {
        return libroJpa.contarLibrosPorAutor(idAutor);
    }
}
