<%@ page import="org.practica.model.Curso" %>
<%@ page import="org.practica.model.Contenido" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/WEB-INF/views/fragments/_header.jsp"/>

<main>
    <jsp:include page="/WEB-INF/views/fragments/_nav-estudiante.jsp"/>

    <div style="max-width: 900px; margin: 30px auto; padding: 0 20px;">
        <h2 class="mb-4">Mis Contenidos</h2>

        <%
            List<Curso> cursos = (List<Curso>) request.getAttribute("cursos");
            Map<Integer, Integer> progresoPorCurso = (Map<Integer, Integer>) request.getAttribute("progresoPorCurso");
            Map<Integer, Integer> valoracionPorCurso = (Map<Integer, Integer>) request.getAttribute("valoracionPorCurso");
            Map<Integer, Boolean> favoritosPorCurso = (Map<Integer, Boolean>) request.getAttribute("favoritosPorCurso");
            if (cursos == null || cursos.isEmpty()) {
        %>
            <p>No estás inscrito en ningún curso.</p>
        <%
            } else {
                for (Curso curso : cursos) {
                    int total = curso.getContenidos() != null ? curso.getContenidos().size() : 0;
                    int completados = progresoPorCurso.getOrDefault(curso.getId(), 0);
                    int porcentaje = total > 0 ? (completados * 100) / total : 0;
                    int valoracion = valoracionPorCurso.getOrDefault(curso.getId(), 0);
                    boolean esFavorito = favoritosPorCurso.getOrDefault(curso.getId(), false);
        %>
        <div class="card mb-4">
            <div class="card-header">
                <h4><%= curso.getTitulo() %></h4>
                <p class="mb-0"><%= curso.getDescripcion() %></p>
                <div class="mt-2">
                    <small><%= completados %> / <%= total %> contenidos completados</small>
                    <div class="progress mt-1" style="height: 8px;">
                        <div class="progress-bar" role="progressbar"
                             style="width: <%= porcentaje %>%;"
                             aria-valuenow="<%= porcentaje %>" aria-valuemin="0" aria-valuemax="100"></div>
                    </div>
                </div>
                <div class="d-flex align-items-center gap-3 mt-2">
                    <form method="post" action="${pageContext.request.contextPath}/estudiante/contenidos">
                        <input type="hidden" name="cursoId" value="<%= curso.getId() %>">
                        <small>Tu valoración: </small>
                        <% for (int i = 1; i <= 5; i++) { %>
                            <button type="submit" name="valoracion" value="<%= i %>"
                                    class="btn btn-sm <%= valoracion >= i ? "btn-warning" : "btn-outline-warning" %>"
                                    style="padding: 2px 6px;">★</button>
                        <% } %>
                    </form>
                    <form method="post" action="${pageContext.request.contextPath}/estudiante/contenidos">
                        <input type="hidden" name="cursoId" value="<%= curso.getId() %>">
                        <input type="hidden" name="accion" value="favorito">
                        <button type="submit" class="btn btn-sm <%= esFavorito ? "btn-danger" : "btn-outline-danger" %>">
                            <%= esFavorito ? "♥ Favorito" : "♡ Añadir favorito" %>
                        </button>
                    </form>
                </div>
            </div>
            <div class="card-body">
                <%
                    List<Contenido> contenidos = curso.getContenidos();
                    if (contenidos == null || contenidos.isEmpty()) {
                %>
                    <p>Este curso no tiene contenidos aún.</p>
                <%
                    } else {
                        for (Contenido c : contenidos) {
                %>
                <div class="d-flex justify-content-between align-items-center mb-2">
                    <span><strong><%= c.getOrden() %>.</strong> <%= c.getTitulo() %> <em>(<%= c.getTipo() %>)</em></span>
                    <a href="${pageContext.request.contextPath}/estudiante/contenidos?action=acceder&contenidoId=<%= c.getId() %>"
                       class="btn btn-sm btn-primary">Acceder</a>
                </div>
                <%
                        }
                    }
                %>
            </div>
        </div>
        <%
                }
            }
        %>
    </div>
</main>

<jsp:include page="/WEB-INF/views/fragments/_footer.jsp"/>
