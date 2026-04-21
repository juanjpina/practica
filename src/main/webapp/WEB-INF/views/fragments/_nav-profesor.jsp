<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div style="max-width:1100px; margin: 0 auto;">
    <nav class="nav-dashboard">
        <ul>
            <li><a href="${pageContext.request.contextPath}/profesor/cursos">📕 CURSOS</a></li>
            <li><a href="${pageContext.request.contextPath}/profesor/contenidos">📚 CONTENIDO</a></li>
            <li><a href="${pageContext.request.contextPath}/profesor/estudiantes">👨 ESTUDIANTES</a></li>
            <li><a href="${pageContext.request.contextPath}/profesor/analisis">📉 ANÁLISIS</a>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/profesor/analisis">INSCRIPCIONES</a></li>
                    <li><a href="${pageContext.request.contextPath}/profesor/analisis">VALORACIÓN</a></li>
                </ul>
            </li>
        </ul>
    </nav>
</div>