
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BiblioTech | Inicio</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
</head>

<body>

<header>
    <h1>📚 Biblio<span>Tech</span></h1>
    <p>Sistema de Gestión Bibliotecaria</p>
</header>

<nav>
    <a href="${pageContext.request.contextPath}/index.jsp" class="active">Inicio</a>
    <a href="${pageContext.request.contextPath}/autores">Autores</a>
    <a href="${pageContext.request.contextPath}/libros">Libros</a>
</nav>

<main class="container">

    <section class="bienvenida">
        <h2>¡Bienvenido a BiblioTech!</h2>
        <p class="subtitle">
            Administrá tu biblioteca y descubrí los libros de cada autor.
        </p>
    </section>

    <div class="cards">

        <div class="card">
            <div class="icono">✍️</div>
            <h3>Gestión de autores</h3>
            <p>
                Registrá nuevos autores, consultá sus datos,
                modificá su información y eliminá registros.
            </p>
            <a href="${pageContext.request.contextPath}/autores" class="btn">
                Ver autores
            </a>
        </div>

        <div class="card">
            <div class="icono">📖</div>
            <h3>Gestión de libros</h3>
            <p>
                Agregá libros, consultá sus datos y relacioná
                cada libro con su autor correspondiente.
            </p>
            <a href="${pageContext.request.contextPath}/libros" class="btn">
                Ver libros
            </a>
        </div>

    </div>

    <section class="card relacion">
        <h2>🔗 Relación entre libros y autores</h2>

        <p>
            En nuestra biblioteca cada libro pertenece a un autor.
            De esta manera podemos conocer qué libros escribió
            cada autor y mantener toda la información organizada.
        </p>

        <div class="relacion-diagrama">
            <div class="diagrama-item">
                <span>✍️</span>
                <strong>Autor</strong>
                <small>Registra sus datos</small>
            </div>

            <div class="flecha">1 ───── N</div>

            <div class="diagrama-item">
                <span>📚</span>
                <strong>Libros</strong>
                <small>Un autor puede tener varios libros</small>
            </div>
        </div>
    </section>

</main>

<footer>
    <p>© 2026 BiblioTech | Sistema de Gestión Bibliotecaria</p>
</footer>

</body>
</html>
