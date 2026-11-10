
package com.app.presentacion;

import com.app.logica.Autor;
import com.app.logica.ControladoraLogica;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/autores")
public class AutorServlet extends HttpServlet {

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

            // Si se seleccionó editar
            if ("editar".equals(accion)) {

                int id = Integer.parseInt(request.getParameter("id"));

                Autor autor = controladora.buscarAutorPorId(id);

                if (autor == null) {
                    response.sendRedirect(request.getContextPath()
                            + "/autores?mensaje=noEncontrado");
                    return;
                }

                // El JSP espera este atributo
                request.setAttribute("autorEditar", autor);
            }

            // Cargar siempre la lista de autores
            List<Autor> autores = controladora.listarAutores();
            request.setAttribute("autores", autores);

            // Mostrar siempre autores.jsp
            request.getRequestDispatcher("/autores.jsp")
                   .forward(request, response);

        } catch (NumberFormatException e) {

            response.sendRedirect(request.getContextPath()
                    + "/autores?mensaje=idInvalido");

        } catch (Exception e) {

            e.printStackTrace();

            request.setAttribute("error",
                    "No se pudieron cargar los autores.");

            request.getRequestDispatcher("/error.jsp")
                   .forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String accion = request.getParameter("accion");

        try {

            if ("crear".equals(accion)) {

                String nombre = request.getParameter("nombre");
                String nacionalidad = request.getParameter("nacionalidad");

                Autor autor = new Autor();

                controladora.crearAutor(autor);

            } else if ("actualizar".equals(accion)) {

                int id = Integer.parseInt(request.getParameter("id"));

                String nombre = request.getParameter("nombre");
                String nacionalidad = request.getParameter("nacionalidad");

                Autor autor = new Autor();
                autor.setId(id);
                autor.setNombre(nombre);
                autor.setNacionalidad(nacionalidad);

                // La Controladora recibe un objeto Autor
                controladora.editarAutor(autor);

            } else if ("eliminar".equals(accion)) {

                int id = Integer.parseInt(request.getParameter("id"));

                controladora.eliminarAutor(id);

            }

            response.sendRedirect(request.getContextPath()
                    + "/autores?mensaje=ok");

        } catch (NumberFormatException e) {

            response.sendRedirect(request.getContextPath()
                    + "/autores?mensaje=idInvalido");

        } catch (IllegalArgumentException e) {

            request.setAttribute("error", e.getMessage());

            request.getRequestDispatcher("/error.jsp")
                   .forward(request, response);

        } catch (Exception e) {

            e.printStackTrace();

            request.setAttribute("error",
                    "No se pudo realizar la operación. Verificá los datos.");

            request.getRequestDispatcher("/error.jsp")
                   .forward(request, response);
        }
    }
}
