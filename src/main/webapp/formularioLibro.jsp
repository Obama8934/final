
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>

<%@ page import="com.app.logica.Autor" %>

<%@ page import="com.app.logica.Libro" %>

<%
    Libro libro = (Libro) request.getAttribute("libro");
    List<Autor> autores =
        (List<Autor>) request.getAttribute("autores");
    boolean editar = libro != null;
    int autorSeleccionado = editar && libro.getAutor() != null
        ? libro.getAutor().getId() : -1;
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Formulario de libro</title>
    <link rel="stylesheet" href="css/estilos.css">
</head>
<body>
    <h1><%= editar ? "Editar libro" : "Nuevo libro" %></h1>

    <form action="libros" method="post">
        <input type="hidden" name="accion"
               value="<%= editar ? "actualizar" : "crear" %>">

        <% if (editar) { %>
            <input type="hidden" name="id"
                   value="<%= libro.getId() %>">
        <% } %>

        <label>Título:</label>
        <input type="text" name="titulo" required maxlength="150"
               value="<%= editar ? libro.getTitulo() : "" %>">

        <label>Género:</label>
        <input type="text" name="genero" required maxlength="60"
               value="<%= editar ? libro.getGenero() : "" %>">

        <label>Año de publicación:</label>
        <input type="number" name="anio" required min="1" max="2100"
               value="<%= editar ? libro.getAnioPublicacion() : "" %>">

        <label>Autor:</label>
        <select name="autorId" required>
            <option value="">Seleccionar autor</option>

            <% if (autores != null) {
                for (Autor a : autores) { %>
                    <option value="<%= a.getId() %>"
                        <%= autorSeleccionado == a.getId()
                            ? "selected" : "" %>>
                        <%= a.getNombre() %>
                    </option>
            <%  }
               } %>
        </select>

        <button type="submit">Guardar</button>
    </form>

    <p><a href="libros">Cancelar</a></p>
</body>
</html>
