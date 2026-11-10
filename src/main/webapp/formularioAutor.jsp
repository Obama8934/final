
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.app.logica.Autor" %>

<%
    Autor autor = (Autor) request.getAttribute("autor");
    boolean editar = autor != null;
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Formulario de autor</title>
    <link rel="stylesheet" href="css/estilos.css">
</head>
<body>
    <h1><%= editar ? "Editar autor" : "Nuevo autor" %></h1>

    <form action="autores" method="post">
        <input type="hidden" name="accion"
               value="<%= editar ? "actualizar" : "crear" %>">

        <% if (editar) { %>
            <input type="hidden" name="id"
                   value="<%= autor.getId() %>">
        <% } %>

        <label>Nombre:</label>
        <input type="text" name="nombre"
               required maxlength="100"
               value="<%= editar ? autor.getNombre() : "" %>">

        <label>Nacionalidad:</label>
        <input type="text" name="nacionalidad"
               required maxlength="60"
               value="<%= editar ? autor.getNacionalidad() : "" %>">

        <button type="submit">Guardar</button>
    </form>

    <p><a href="autores">Cancelar</a></p>
</body>
</html>
