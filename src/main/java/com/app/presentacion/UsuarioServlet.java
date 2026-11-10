
package com.app.presentacion;

import com.app.logica.Usuario;
import com.app.logica.ControladoraLogica;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/usuarios")
public class UsuarioServlet extends HttpServlet {

    private ControladoraLogica control = new ControladoraLogica();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if ("nuevo".equals(accion)) {
            request.getRequestDispatcher("/usuarios.jsp")
                   .forward(request, response);
            return;
        }

        if ("editar".equals(accion)) {
            int id = Integer.parseInt(request.getParameter("id"));

            Usuario usuario = control.buscarUsuarioPorId(id);

            request.setAttribute("usuarioEditar", usuario);
            request.getRequestDispatcher("/usuarios.jsp")
                   .forward(request, response);
            return;
        }

        listar(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String accion = request.getParameter("accion");

        try {
            if ("crear".equals(accion)) {

                Usuario usuario = obtenerUsuario(request);

                control.crearUsuario(usuario);

            } else if ("actualizar".equals(accion)) {

                Usuario usuario = obtenerUsuario(request);

                usuario.setId(
                    Integer.parseInt(request.getParameter("id"))
                );

                control.editarUsuario(usuario);

            } else if ("eliminar".equals(accion)) {

                int id = Integer.parseInt(request.getParameter("id"));

                control.eliminarUsuario(id);
            }

            response.sendRedirect(
                request.getContextPath() + "/usuarios?mensaje=ok"
            );

        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            listar(request, response);
        }
    }

    private Usuario obtenerUsuario(HttpServletRequest request) {

        Usuario usuario = new Usuario();

        usuario.setNombre(request.getParameter("nombre"));
        usuario.setApellido(request.getParameter("apellido"));
        usuario.setDni(request.getParameter("dni"));
        usuario.setEmail(request.getParameter("email"));
        usuario.setTelefono(request.getParameter("telefono"));

        return usuario;
    }

    private void listar(HttpServletRequest request,
                        HttpServletResponse response)
            throws ServletException, IOException {

        List<Usuario> usuarios = control.listarUsuarios();

        request.setAttribute("usuarios", usuarios);

        request.getRequestDispatcher("/usuarios.jsp")
               .forward(request, response);
    }
}
