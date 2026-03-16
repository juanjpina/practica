 <%@ page contentType="text/html;charset=UTF-8" language="java" %>

 <jsp:include page="/WEB-INF/views/fragments/_header.jsp"/>
  <main>
    <div class="container">

              <nav class="nav-dashboard">
                  <ul>

                      <li><a href="${pageContext.request.contextPath}/logout"> 👨 GESTION USUARIOS</a></li>

  <li><a href="${pageContext.request.contextPath}/logout"> 📰 INFORMACION ESTADISTICA</a>
  <ul>
    <li><a href="${pageContext.request.contextPath}/logout"> USUARIOS REGISTRADOS</a></li>
    <li><a href="${pageContext.request.contextPath}/logout"> CURSOS ACTIVOS</a></li>
    <li><a href="${pageContext.request.contextPath}/logout"> INSCRIPCIONES</a></li>
    <li><a href="${pageContext.request.contextPath}/logout"> PARTICIPACION Y VALORACION</a></li>
  </ul>
  </li>
<li><a href="${pageContext.request.contextPath}/logout">⚙ CONFIGURACION</a></li>

                  </ul>
              </nav>

  </div>
      <section>

  <p>section</p>

    </section>
        <!-- contenido de la página -->
  </main>

     <jsp:include page="/WEB-INF/views/fragments/_footer.jsp"/>