<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, org.practica.model.Curso" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Cursos disponibles</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<header>
    <div class="container d-flex justify-content-between align-items-center py-3">
        <span class="fw-bold fs-5">MiApp</span>
        <a href="${pageContext.request.contextPath}/login" class="btn btn-primary">Acceder</a>
    </div>
</header>

<main class="container py-5">
    <h1 class="mb-2">Cursos disponibles</h1>
    <p class="text-muted mb-4">Explora nuestro catálogo. Regístrate o inicia sesión para inscribirte.</p>

    <div class="row row-cols-1 row-cols-md-3 g-4">
        <%
            List<Curso> cursos = (List<Curso>) request.getAttribute("cursos");
            if (cursos != null && !cursos.isEmpty()) {
                for (Curso c : cursos) {
        %>
        <div class="col">
            <div class="card h-100 shadow-sm">
                <div class="card-body">
                    <h5 class="card-title"><%= c.getTitulo() %></h5>
                    <p class="card-text text-muted"><%= c.getDescripcion() %></p>
                </div>
                <div class="card-footer d-flex justify-content-between text-secondary small">
                    <span>Duración: <%= c.getDuracion() %>h</span>
                    <span>Nivel: <%= c.getNivel() %></span>
                </div>
            </div>
        </div>
        <%
                }
            } else {
        %>
        <div class="col-12">
            <p class="text-muted">No hay cursos disponibles de momento.</p>
        </div>
        <% } %>
    </div>
</main>

<footer class="text-center text-muted py-4 mt-5 border-top">
    <p>MiApp &copy; 2026</p>
</footer>

</body>
</html>
