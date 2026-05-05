<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, org.practica.model.Curso" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Cursos disponibles</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<header>
    <div class="container">
        <nav>
            <ul>
                <li><a href="${pageContext.request.contextPath}/docs/javadoc/index.html" target="_blank">JavaDoc</a></li>
                <li><a href="${pageContext.request.contextPath}/docs/memoria.pdf" target="_blank">Memória</a></li>
                <li><a href="${pageContext.request.contextPath}/login">Acceder</a></li>
                <li><a href="${pageContext.request.contextPath}/registro">Registrarse</a></li>
            </ul>
        </nav>
    </div>
</header>

<main>
    <div style="max-width: 1200px; margin: 0 auto; padding: 40px 20px;">
        <h2 style="margin-bottom: 8px;">Cursos disponibles</h2>
        <p style="color: #666; margin-bottom: 32px;">Explora nuestro catálogo. Inicia sesión para inscribirte.</p>

        <div style="display: grid; grid-template-columns: repeat(3, 1fr); gap: 24px;">
            <%
                List<Curso> cursos = (List<Curso>) request.getAttribute("cursos");
                if (cursos != null && !cursos.isEmpty()) {
                    for (Curso c : cursos) {
            %>
            <div style="background: rgba(255,255,255,0.6); border-radius: 10px; padding: 24px; box-shadow: 0 2px 8px rgba(0,0,0,0.08);">
                <h5 style="font-weight: 700; margin-bottom: 10px;"><%= c.getTitulo() %></h5>
                <p style="color: #555; font-size: 0.9rem; margin-bottom: 16px;"><%= c.getDescripcion() %></p>
                <div style="display: flex; justify-content: space-between; font-size: 0.8rem; color: #777;">
                    <span>⏱ <%= c.getDuracion() %>h</span>
                    <span>📊 Nivel <%= c.getNivel() %></span>
                </div>
            </div>
            <%
                    }
                } else {
            %>
            <p style="color: #666;">No hay cursos disponibles de momento.</p>
            <% } %>
        </div>
    </div>
</main>

<jsp:include page="/WEB-INF/views/fragments/_footer.jsp"/>
