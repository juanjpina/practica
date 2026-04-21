 <%@ page contentType="text/html;charset=UTF-8" language="java" %>

 <jsp:include page="/WEB-INF/views/fragments/_header.jsp"/>
<main>
    <div class="container">

        <nav class="nav-dashboard">
            <ul>
                <li><a href="${pageContext.request.contextPath}/estudiante/curso">📕 CURSOS</a></li>
                <li><a href="${pageContext.request.contextPath}/estudiante/contenidos">📚 CONTENIDO</a></li>
                <li><a href="${pageContext.request.contextPath}/estudiante/contenidos">🏆 VALORACION</a></li>
                <li><a href="${pageContext.request.contextPath}/estudiante/favoritos">🏅 FAVORITOS</a></li>
                <li><a href="${pageContext.request.contextPath}/logout">⚙ MI CUENTA</a></li>
            </ul>
        </nav>

    </div>
    <section>
<%--        <jsp:include page="/WEB-INF/views/estudiante/cursos.jsp"/>--%>
    </section>
        <!-- contenido de la página -->
</main>

     <jsp:include page="/WEB-INF/views/fragments/_footer.jsp"/>