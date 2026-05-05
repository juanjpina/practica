<%@ page import="org.practica.model.Curso" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/WEB-INF/views/fragments/_header.jsp"/>
<jsp:include page="/WEB-INF/views/fragments/_nav-estudiante.jsp"/>

<main>
    <div style="max-width: 900px; margin: 30px auto; padding: 0 20px;">
        <h2 class="mb-4">🏅 Mis Favoritos</h2>

        <%
            List<Curso> favoritos = (List<Curso>) request.getAttribute("favoritos");
            if (favoritos == null || favoritos.isEmpty()) {
        %>
            <p>No tienes cursos marcados como favoritos.</p>
        <%
            } else {
                for (Curso curso : favoritos) {
        %>
        <div class="card mb-3">
            <div class="card-body d-flex justify-content-between align-items-center">
                <div>
                    <h5 class="mb-1"><%= curso.getTitulo() %></h5>
                    <p class="mb-0 text-muted"><%= curso.getDescripcion() %></p>
                </div>
                <a href="${pageContext.request.contextPath}/estudiante/contenidos" class="btn btn-primary btn-sm">Ver contenidos</a>
            </div>
        </div>
        <%
                }
            }
        %>
    </div>
</main>

<jsp:include page="/WEB-INF/views/fragments/_footer.jsp"/>
