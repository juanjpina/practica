<%@ page import="org.practica.dto.CursoAnalisisDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/WEB-INF/views/fragments/_header.jsp"/>
<jsp:include page="/WEB-INF/views/fragments/_nav-profesor.jsp"/>

<main>
    <div style="max-width: 900px; margin: 30px auto; padding: 0 20px;">
        <h2 class="mb-4">📉 Análisis de cursos</h2>

        <%
            List<CursoAnalisisDTO> analisis = (List<CursoAnalisisDTO>) request.getAttribute("analisis");
            if (analisis == null || analisis.isEmpty()) {
        %>
            <p>No tienes cursos asignados.</p>
        <%
            } else {
        %>
        <table class="table table-bordered table-hover">
            <thead class="table-dark">
                <tr>
                    <th>Curso</th>
                    <th class="text-center">Estudiantes inscritos</th>
                    <th class="text-center">Valoración media</th>
                </tr>
            </thead>
            <tbody>
                <% for (CursoAnalisisDTO dto : analisis) { %>
                <tr>
                    <td><%= dto.getTitulo() %></td>
                    <td class="text-center"><%= dto.getNumInscritos() %></td>
                    <td class="text-center">
                        <% if (dto.getMediaValoracion() == 0) { %>
                            <span class="text-muted">Sin valoraciones</span>
                        <% } else { %>
                            <%= dto.getMediaValoracion() %> ★
                        <% } %>
                    </td>
                </tr>
                <% } %>
            </tbody>
        </table>
        <% } %>
    </div>
</main>

<jsp:include page="/WEB-INF/views/fragments/_footer.jsp"/>
