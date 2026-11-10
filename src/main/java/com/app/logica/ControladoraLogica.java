
package com.app.logica;

import java.util.List;

import com.app.logica.Usuario;

import com.app.persistencia.ControladoraPersistencia;

public class ControladoraLogica {

    private ControladoraPersistencia controlPersis =
            new ControladoraPersistencia();

    // ========== AUTORES ==========

    public void crearAutor(Autor autor) throws Exception {

        validarAutor(autor);

        controlPersis.crearAutor(autor);
    }

    public void editarAutor(Autor autor) throws Exception {

        validarAutor(autor);

        if (controlPersis.buscarAutorPorId(autor.getId()) == null) {
            throw new Exception("El autor no existe.");
        }

        controlPersis.editarAutor(autor);
    }

    public Autor buscarAutorPorId(int id) {
        return controlPersis.buscarAutorPorId(id);
    }

    public List<Autor> listarAutores() {
        return controlPersis.buscarTodosLosAutores();
    }

    public void eliminarAutor(int id) throws Exception {

        Autor autor = controlPersis.buscarAutorPorId(id);

        if (autor == null) {
            throw new Exception("El autor no existe.");
        }

        Long cantidad = controlPersis.contarLibrosPorAutor(id);

        if (cantidad > 0) {
            throw new Exception(
                "No se puede eliminar el autor porque tiene libros asociados."
            );
        }

        controlPersis.eliminarAutor(id);
    }

    // ========== LIBROS ==========

    public void crearLibro(Libro libro, int idAutor) throws Exception {

        validarLibro(libro);

        Autor autor = controlPersis.buscarAutorPorId(idAutor);

        if (autor == null) {
            throw new Exception("El autor seleccionado no existe.");
        }

        libro.setAutor(autor);

        controlPersis.crearLibro(libro);
    }

    public void editarLibro(Libro libro, int idAutor) throws Exception {

        validarLibro(libro);

        if (controlPersis.buscarLibroPorId(libro.getId()) == null) {
            throw new Exception("El libro no existe.");
        }

        Autor autor = controlPersis.buscarAutorPorId(idAutor);

        if (autor == null) {
            throw new Exception("El autor seleccionado no existe.");
        }

        libro.setAutor(autor);

        controlPersis.editarLibro(libro);
    }

    public Libro buscarLibroPorId(int id) {
        return controlPersis.buscarLibroPorId(id);
    }

    public List<Libro> listarLibros() {
        return controlPersis.buscarTodosLosLibros();
    }

    public void eliminarLibro(int id) throws Exception {

        Libro libro = controlPersis.buscarLibroPorId(id);

        if (libro == null) {
            throw new Exception("El libro no existe.");
        }

        controlPersis.eliminarLibro(id);
    }

    // ========== BUSCADOR ==========

    public List<Libro> buscarLibrosPorAutor(int idAutor)
            throws Exception {

        if (controlPersis.buscarAutorPorId(idAutor) == null) {
            throw new Exception("El autor no existe.");
        }

        return controlPersis.buscarLibrosPorAutor(idAutor);
    }

    // ========== VALIDACIONES ==========

    private void validarAutor(Autor autor) throws Exception {

        if (autor == null) {
            throw new Exception("Los datos del autor son obligatorios.");
        }

        if (autor.getNombre() == null ||
            autor.getNombre().trim().isEmpty()) {

            throw new Exception("El nombre del autor es obligatorio.");
        }

        if (autor.getNacionalidad() == null ||
            autor.getNacionalidad().trim().isEmpty()) {

            throw new Exception("La nacionalidad es obligatoria.");
        }
    }

    private void validarLibro(Libro libro) throws Exception {

        if (libro == null) {
            throw new Exception("Los datos del libro son obligatorios.");
        }

        if (libro.getTitulo() == null ||
            libro.getTitulo().trim().isEmpty()) {

            throw new Exception("El título del libro es obligatorio.");
        }

        if (libro.getGenero() == null ||
            libro.getGenero().trim().isEmpty()) {

            throw new Exception("El género es obligatorio.");
        }

        if (libro.getAnioPublicacion() <= 0) {
            throw new Exception("El año de publicación no es válido.");
        }
    }
    
 // CREAR USUARIO
    public void crearUsuario(Usuario usuario) {
        if (usuario.getNombre() == null || usuario.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }

        if (usuario.getApellido() == null || usuario.getApellido().isBlank()) {
            throw new IllegalArgumentException("El apellido es obligatorio");
        }

        if (usuario.getDni() == null || usuario.getDni().isBlank()) {
            throw new IllegalArgumentException("El DNI es obligatorio");
        }

        if (usuario.getEmail() == null || usuario.getEmail().isBlank()) {
            throw new IllegalArgumentException("El email es obligatorio");
        }

        controlPersis.crearUsuario(usuario);
    }

    // EDITAR USUARIO
    public void editarUsuario(Usuario usuario) {
        if (usuario.getId() <= 0) {
            throw new IllegalArgumentException("ID de usuario inválido");
        }

        if (usuario.getNombre() == null || usuario.getNombre().isBlank()
                || usuario.getApellido() == null || usuario.getApellido().isBlank()
                || usuario.getDni() == null || usuario.getDni().isBlank()
                || usuario.getEmail() == null || usuario.getEmail().isBlank()) {
            throw new IllegalArgumentException("Completá todos los campos obligatorios");
        }

        controlPersis.editarUsuario(usuario);
    }

    // BUSCAR USUARIO
    public Usuario buscarUsuarioPorId(int id) {
        return controlPersis.buscarUsuarioPorId(id);
    }

    // LISTAR USUARIOS
    public List<Usuario> listarUsuarios() {
        return controlPersis.listarUsuarios();
    }

    // ELIMINAR USUARIO
    public void eliminarUsuario(int id) {
        controlPersis.eliminarUsuario(id);
    }
}
