
<%@ page import="java.util.List" %>
<%@ page import="com.app.logica.Usuario" %>

<%
    List<Usuario> usuarios =
        (List<Usuario>) request.getAttribute("usuarios");

    Usuario usuarioEditar =
        (Usuario) request.getAttribute("usuarioEditar");

    boolean editando = usuarioEditar != null;
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Gestión de usuarios</title>
    <link rel="stylesheet" href="css/estilos.css">
</head>

<body>

<div class="container">

    <h1>Gestión de usuarios</h1>
    <p>Administración de usuarios de la biblioteca</p>

    <% if (request.getParameter("mensaje") != null) { %>
        <p class="mensaje">Operación realizada correctamente.</p>
    <% } %>

    <% if (request.getAttribute("error") != null) { %>
        <p class="error">
            <%= request.getAttribute("error") %>
        </p>
    <% } %>

    <div class="card">

        <h2><%= editando ? "Editar usuario" : "Registrar usuario" %></h2>

        <form action="<%= request.getContextPath() %>/usuarios"
              method="POST">

            <input type="hidden"
                   name="accion"
                   value="<%= editando ? "actualizar" : "crear" %>">

            <% if (editando) { %>
                <input type="hidden"
                       name="id"
                       value="<%= usuarioEditar.getId() %>">
            <% } %>

            <label>Nombre</label>
            <input type="text" name="nombre"
                   value="<%= editando ? usuarioEditar.getNombre() : "" %>"
                   required>

            <label>Apellido</label>
            <input type="text" name="apellido"
                   value="<%= editando ? usuarioEditar.getApellido() : "" %>"
                   required>

            <label>DNI</label>
            <input type="text" name="dni"
                   value="<%= editando ? usuarioEditar.getDni() : "" %>"
                   required>

            <label>Email</label>
            <input type="email" name="email"
                   value="<%= editando ? usuarioEditar.getEmail() : "" %>"
                   required>

            <label>Teléfono</label>
            <input type="text" name="telefono"
                   value="<%= editando ? usuarioEditar.getTelefono() : "" %>">

            <button type="submit" class="btn btn-gold">
                <%= editando ? "Guardar cambios" : "Registrar usuario" %>
            </button>

            <% if (editando) { %>
                <a href="<%= request.getContextPath() %>/usuarios"
                   class="btn">
                    Cancelar
                </a>
            <% } %>

        </form>

    </div>

    <div class="card">

        <h2>Usuarios registrados</h2>

        <table>

            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>DNI</th>
                    <th>Email</th>
                    <th>Teléfono</th>
                    <th>Acciones</th>
                </tr>
            </thead>

            <tbody>

            <% if (usuarios != null && !usuarios.isEmpty()) {
                for (Usuario u : usuarios) { %>

                <tr>
                    <td><%= u.getId() %></td>
                    <td><%= u.getNombre() %></td>
                    <td><%= u.getApellido() %></td>
                    <td><%= u.getDni() %></td>
                    <td><%= u.getEmail() %></td>
                    <td><%= u.getTelefono() == null ? "" : u.getTelefono() %></td>

                    <td>
                        <a class="btn"
                           href="<%= request.getContextPath() %>/usuarios?accion=editar&id=<%= u.getId() %>">
                            Editar
                        </a>

                        <form action="<%= request.getContextPath() %>/usuarios"
                              method="POST"
                              style="display:inline;">

                            <input type="hidden"
                                   name="accion"
                                   value="eliminar">

                            <input type="hidden"
                                   name="id"
                                   value="<%= u.getId() %>">

                            <button type="submit"
                                    class="btn"
                                    onclick="return confirm('¿Eliminar este usuario?')">
                                Eliminar
                            </button>

                        </form>
                    </td>
                </tr>

            <%  }
               } else { %>

                <tr>
                    <td colspan="7">Todavía no hay usuarios registrados.</td>
                </tr>

            <% } %>

            </tbody>
        </table>

    </div>

    <a href="<%= request.getContextPath() %>/index.jsp"
       class="btn">
        Volver al inicio
    </a>

</div>

</body>
</html>
