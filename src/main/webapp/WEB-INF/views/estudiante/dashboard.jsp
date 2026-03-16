 <%@ page contentType="text/html;charset=UTF-8" language="java" %>

 <jsp:include page="/WEB-INF/views/fragments/_header.jsp"/>
<main>
    <div class="container">

        <nav class="nav-dashboard">
            <ul>
                <li><a href="${pageContext.request.contextPath}/logout">📕 CURSOS</a></li>
                <li><a href="${pageContext.request.contextPath}/logout">📚 CONTENIDO</a></li>
                <li><a href="${pageContext.request.contextPath}/logout">📋 PROGRESO</a></li>
                <li><a href="${pageContext.request.contextPath}/logout">🏆 VALORACION</a></li>
                <li><a href="${pageContext.request.contextPath}/logout">🏅 FAVORITOS</a></li>
                <li><a href="${pageContext.request.contextPath}/logout">⚙ MI CUENTA</a></li>
            </ul>
        </nav>

    </div>
    <section>

      <p>section</p>

    </section>
        <!-- contenido de la página -->
</main>

     <jsp:include page="/WEB-INF/views/fragments/_footer.jsp"/>