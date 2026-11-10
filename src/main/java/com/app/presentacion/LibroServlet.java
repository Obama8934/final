
package com.app.presentacion;

import com.app.logica.Autor;
import com.app.logica.Libro;
import com.app.logica.ControladoraLogica;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/libros")
public class LibroServlet extends HttpServlet {

    private ControladoraLogica controladora;

    @Override
    public void init() {
        controladora = new ControladoraLogica();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        try {
            String accion = request.getParameter("accion");

            if ("nuevo".equals(accion)) {
                cargarAutores(request);

                request.getRequestDispatcher("/libros.jsp")
                       .forward(request, response);
                return;
            }

            if ("editar".equals(accion)) {
                int id = Integer.parseInt(request.getParameter("id"));

                Libro libro = controladora.buscarLibroPorId(id);

                if (libro == null) {
                    response.sendRedirect(request.getContextPath()
                            + "/libros?mensaje=noEncontrado");
                    return;
                }

                request.setAttribute("libroEditar", libro);
                cargarAutores(request);

                List<Libro> libros = controladora.listarLibros();
                request.setAttribute("libros", libros);

                request.getRequestDispatcher("/libros.jsp")
                       .forward(request, response);
                return;
            }

            List<Libro> libros = controladora.listarLibros();

            request.setAttribute("libros", libros);

            // Cargar los autores para el menú desplegable
            cargarAutores(request);

            request.getRequestDispatcher("/libros.jsp")
                   .forward(request, response);

        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath()
                    + "/libros?mensaje=idInvalido");

        } catch (Exception e) {
            e.printStackTrace();

            request.setAttribute("error",
                    "No se pudieron cargar los libros: " + e.getMessage());

            request.getRequestDispatcher("/error.jsp")
                   .forward(request, response);
        }
    }

    private void cargarAutores(HttpServletRequest request) {
        List<Autor> autores = controladora.listarAutores();
        request.setAttribute("autores", autores);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String accion = request.getParameter("accion");

        try {

            if ("crear".equals(accion)) {

                String titulo = request.getParameter("titulo");
                String genero = request.getParameter("genero");

                int anio = Integer.parseInt(
                        request.getParameter("anio"));

                int idAutor = Integer.parseInt(
                        request.getParameter("autorId"));

                Libro libro = new Libro();

                libro.setTitulo(titulo);
                libro.setGenero(genero);
                libro.setAnioPublicacion(anio);

                controladora.crearLibro(libro, idAutor);

            } else if ("actualizar".equals(accion)) {

                int id = Integer.parseInt(
                        request.getParameter("id"));

                String titulo = request.getParameter("titulo");
                String genero = request.getParameter("genero");

                int anio = Integer.parseInt(
                        request.getParameter("anio"));

                int idAutor = Integer.parseInt(
                        request.getParameter("autorId"));

                Libro libro = new Libro();

                libro.setId(id);
                libro.setTitulo(titulo);
                libro.setGenero(genero);
                libro.setAnioPublicacion(anio);

                controladora.editarLibro(libro, idAutor);

            } else if ("eliminar".equals(accion)) {

                int id = Integer.parseInt(
                        request.getParameter("id"));

                controladora.eliminarLibro(id);
            }

            response.sendRedirect(request.getContextPath()
                    + "/libros?mensaje=ok");

        } catch (NumberFormatException e) {

            request.setAttribute("error",
                    "El año, el ID del libro o el autor no son válidos.");

            request.getRequestDispatcher("/error.jsp")
                   .forward(request, response);

        } catch (IllegalArgumentException e) {

            request.setAttribute("error", e.getMessage());

            request.getRequestDispatcher("/error.jsp")
                   .forward(request, response);

        } catch (Exception e) {

            e.printStackTrace();

            request.setAttribute("error",
                    "No se pudo realizar la operación: " + e.getMessage());

            request.getRequestDispatcher("/error.jsp")
                   .forward(request, response);
        }
    }
}
