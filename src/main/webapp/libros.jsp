
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.app.logica.Libro" %>
<%@ page import="com.app.logica.Autor" %>

<%
    List<Libro> libros =
        (List<Libro>) request.getAttribute("libros");

    List<Autor> autores =
        (List<Autor>) request.getAttribute("autores");

    Libro libroEditar =
        (Libro) request.getAttribute("libroEditar");

    boolean editando = libroEditar != null;
%>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BiblioTech | Libros</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/estilos.css">
</head>

<body>

<header>
    <h1>📚 Biblio<span>Tech</span></h1>
    <p>Administración de libros</p>
</header>

<nav>
    <a href="${pageContext.request.contextPath}/index.jsp">Inicio</a>
    <a href="${pageContext.request.contextPath}/autores">Autores</a>
    <a href="${pageContext.request.contextPath}/libros" class="active">Libros</a>
</nav>

<main class="container">

    <h2>Gestión de libros</h2>

    <p class="subtitle">
        Registrá, consultá y administrá los libros de tu biblioteca.
    </p>

    <% if (request.getParameter("mensaje") != null) { %>
        <p class="mensaje">Operación realizada correctamente.</p>
    <% } %>

    <% if (request.getAttribute("error") != null) { %>
        <p class="error">
            <%= request.getAttribute("error") %>
        </p>
    <% } %>

    <div class="card">

        <h2><%= editando ? "Modificar libro" : "Registrar nuevo libro" %></h2>

        <form action="${pageContext.request.contextPath}/libros"
              method="post">

            <input type="hidden"
                   name="accion"
                   value="<%= editando ? "actualizar" : "crear" %>">

            <% if (editando) { %>
                <input type="hidden"
                       name="id"
                       value="<%= libroEditar.getId() %>">
            <% } %>

            <label>Título:</label>
            <input type="text"
                   name="titulo"
                   value="<%= editando ? libroEditar.getTitulo() : "" %>"
                   required>

            <label>Género:</label>
            <input type="text"
                   name="genero"
                   value="<%= editando ? libroEditar.getGenero() : "" %>"
                   required>

            <label>Año de publicación:</label>
            <input type="number"
                   name="anio"
                   min="1"
                   value="<%= editando ? libroEditar.getAnioPublicacion() : "" %>"
                   required>

            <label>Autor:</label>

            <select name="autorId" required>

                <option value="">Seleccionar autor</option>

                <% if (autores != null) {
                    for (Autor a : autores) {

                        boolean seleccionado = editando
                            && libroEditar.getAutor() != null
                            && libroEditar.getAutor().getId() == a.getId();
                %>

                    <option value="<%= a.getId() %>"
                            <%= seleccionado ? "selected" : "" %>>
                        <%= a.getNombre() %>
                    </option>

                <%  }
                   } %>

            </select>

            <button type="submit" class="btn btn-gold">
                <%= editando ? "Guardar cambios" : "Registrar libro" %>
            </button>

            <% if (editando) { %>
                <a href="${pageContext.request.contextPath}/libros"
                   class="btn btn-secundario">
                    Cancelar
                </a>
            <% } %>

        </form>

    </div>

    <div class="card">

        <h2>Libros registrados</h2>

        <div class="tabla-contenedor">

            <table>

                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Título</th>
                        <th>Género</th>
                        <th>Año</th>
                        <th>Autor</th>
                        <th>Acciones</th>
                    </tr>
                </thead>

                <tbody>

                <% if (libros != null && !libros.isEmpty()) {
                    for (Libro l : libros) {
                %>

                    <tr>
                        <td><%= l.getId() %></td>
                        <td><%= l.getTitulo() %></td>
                        <td><%= l.getGenero() %></td>
                        <td><%= l.getAnioPublicacion() %></td>

                        <td>
                            <%= l.getAutor() != null
                                ? l.getAutor().getNombre()
                                : "Sin autor" %>
                        </td>

                        <td class="acciones">

                            <a class="btn btn-editar"
                               href="${pageContext.request.contextPath}/libros?accion=editar&id=<%= l.getId() %>">
                                Editar
                            </a>

                            <form action="${pageContext.request.contextPath}/libros"
                                  method="post"
                                  onsubmit="return confirm('¿Eliminar este libro?');">

                                <input type="hidden"
                                       name="accion"
                                       value="eliminar">

                                <input type="hidden"
                                       name="id"
                                       value="<%= l.getId() %>">

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
                        <td colspan="6">
                            Todavía no hay libros registrados.
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
