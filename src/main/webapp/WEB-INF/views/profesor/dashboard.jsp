 <%@ page contentType="text/html;charset=UTF-8" language="java" %>

 <jsp:include page="/WEB-INF/views/fragments/_header.jsp"/>
  <main>
    <div class="container">
        <nav class="nav-dashboard">
            <ul>
                <li><a href="${pageContext.request.contextPath}/profesor/cursos">📕 CURSOS</a></li>
                <li><a href="${pageContext.request.contextPath}/profesor/contenidos">📚 CONTENIDO</a></li>
                <li><a href="${pageContext.request.contextPath}/logout">👨 ESTUDIANTES</a></li>
                <li><a href="${pageContext.request.contextPath}/profesor/analisis">📉 ANALISIS</a>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/profesor/analisis">INSCRIPCIONES</a></li>
                    <li><a href="${pageContext.request.contextPath}/profesor/analisis">VALORACION</a></li>
                </ul>
                </li>
            </ul>
        </nav>
    </div>
    <section>
        <p>section</p>
    </section>
        <!-- contenido de la página -->
  </main>

<jsp:include page="/WEB-INF/views/fragments/_footer.jsp"/>