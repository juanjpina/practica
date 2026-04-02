<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div style="max-width:1100px; margin: 0 auto;">
    <nav class="nav-dashboard">
        <ul>
            <li><a href="${pageContext.request.contextPath}/profesor/cursos">📕 CURSOS</a></li>
            <li><a href="${pageContext.request.contextPath}/admin/usuarios">👨 GESTIÓN USUARIOS</a></li>
            <li><a href="${pageContext.request.contextPath}/logout">📰 ESTADÍSTICAS</a>
            <ul>
                    <li><a href="#">USUARIOS REGISTRADOS</a></li>
                    <li><a href="#">CURSOS ACTIVOS</a></li>
                    <li><a href="#">INSCRIPCIONES</a></li>
                    <li><a href="#">PARTICIPACIÓN Y VALORACIÓN</a></li>
                </ul>
            </li>
            <li><a href="#">⚙ CONFIGURACIÓN</a></li>
        </ul>
    </nav>
</div>