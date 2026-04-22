<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div style="max-width:1100px; margin: 0 auto;">
    <nav class="nav-dashboard">
        <ul>
            <li><a href="${pageContext.request.contextPath}/admin/cursos">📕 CURSOS</a></li>
            <li><a href="${pageContext.request.contextPath}/admin/contenidos">📚 CONTENIDO</a></li>
            <li><a href="${pageContext.request.contextPath}/admin/usuarios">👨 GESTIÓN USUARIOS</a></li>
            <li><a href="${pageContext.request.contextPath}/admin/estadisticas">📰 ESTADÍSTICAS</a>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/admin/estadisticas?seccion=usuarios">USUARIOS REGISTRADOS</a></li>
                    <li><a href="${pageContext.request.contextPath}/admin/estadisticas?seccion=cursos">CURSOS ACTIVOS</a></li>
                    <li><a href="${pageContext.request.contextPath}/admin/estadisticas?seccion=inscripciones">INSCRIPCIONES</a></li>
                    <li><a href="${pageContext.request.contextPath}/admin/estadisticas?seccion=participacion">PARTICIPACIÓN Y VALORACIÓN</a></li>
                </ul>
            </li>
            <li><a href="#">⚙ CONFIGURACIÓN</a>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/admin/areas">ÁREAS DE INTERÉS</a></li>
                </ul>
            </li>
        </ul>
    </nav>
</div>