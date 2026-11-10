
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.app.logica.Autor" %>

<%
    List<Autor> autores =
        (List<Autor>) request.getAttribute("autores");

    Autor autorEditar =
        (Autor) request.getAttribute("autorEditar");

    boolean editando = autorEditar != null;
%>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>BiblioTech | Autores</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/estilos.css">
</head>

<body>

<header>
    <h1>📚 Biblio<span>Tech</span></h1>
    <p>Administración de autores</p>
</header>

<nav>
    <a href="${pageContext.request.contextPath}/index.jsp">Inicio</a>
    <a href="${pageContext.request.contextPath}/autores"
       class="active">Autores</a>
    <a href="${pageContext.request.contextPath}/libros">Libros</a>
</nav>

<main class="container">

    <h2>Gestión de autores</h2>

    <p class="subtitle">
        Registrá, consultá y administrá los autores de tu biblioteca.
    </p>

    <% if (request.getParameter("mensaje") != null) { %>
        <p class="mensaje">
            Operación realizada correctamente.
        </p>
    <% } %>

    <% if (request.getAttribute("error") != null) { %>
        <p class="error">
            <%= request.getAttribute("error") %>
        </p>
    <% } %>

    <!-- FORMULARIO PARA CREAR O MODIFICAR AUTORES -->

    <div class="card">

        <h2>
            <%= editando ? "Modificar autor" : "Registrar nuevo autor" %>
        </h2>

        <form action="${pageContext.request.contextPath}/autores"
              method="post">

            <input type="hidden"
                   name="accion"
                   value="<%= editando ? "actualizar" : "crear" %>">

            <% if (editando) { %>

                <input type="hidden"
                       name="id"
                       value="<%= autorEditar.getId() %>">

            <% } %>

            <label for="nombre">Nombre:</label>

            <input type="text"
                   id="nombre"
                   name="nombre"
                   placeholder="Nombre del autor"
                   value="<%= editando ? autorEditar.getNombre() : "" %>"
                   required>

            <label for="nacionalidad">Nacionalidad:</label>

            <input type="text"
                   id="nacionalidad"
                   name="nacionalidad"
                   placeholder="Nacionalidad del autor"
                   value="<%= editando ? autorEditar.getNacionalidad() : "" %>"
                   required>

            <button type="submit" class="btn btn-gold">
                <%= editando ? "Guardar cambios" : "Registrar autor" %>
            </button>

            <% if (editando) { %>

                <a href="${pageContext.request.contextPath}/autores"
                   class="btn btn-secundario">
                    Cancelar
                </a>

            <% } %>

        </form>

    </div>

    <!-- TABLA DE AUTORES -->

    <div class="card">

        <h2>Autores registrados</h2>

        <div class="tabla-contenedor">

            <table>

                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Nacionalidad</th>
                        <th>Acciones</th>
                    </tr>
                </thead>

                <tbody>

                <% if (autores != null && !autores.isEmpty()) {

                    for (Autor a : autores) {
                %>

                    <tr>

                        <td><%= a.getId() %></td>

                        <td><%= a.getNombre() %></td>

                        <td><%= a.getNacionalidad() %></td>

                        <td class="acciones">

                            <a class="btn btn-editar"
                               href="${pageContext.request.contextPath}/autores?accion=editar&id=<%= a.getId() %>">
                                Editar
                            </a>

                            <form action="${pageContext.request.contextPath}/autores"
                                  method="post"
                                  onsubmit="return confirm('¿Eliminar este autor?');">

                                <input type="hidden"
                                       name="accion"
                                       value="eliminar">

                                <input type="hidden"
                                       name="id"
                                       value="<%= a.getId() %>">

                                <button type="submit"
                                        class="btn btn-eliminar">
                                    Eliminar
                                </button>

                            </form>

                        </td>

                    </tr>

                <%  }

                   } else { %>

                    <tr>
                        <td colspan="4">
                            Todavía no hay autores registrados.
                        </td>
                    </tr>

                <% } %>

                </tbody>

            </table>

        </div>

    </div>

    <a href="${pageContext.request.contextPath}/index.jsp"
       class="btn btn-secundario">
        Volver al inicio
    </a>

</main>

<footer>
    <p>© 2026 BiblioTech | Sistema de Gestión Bibliotecaria</p>
</footer>

</body>
</html>
