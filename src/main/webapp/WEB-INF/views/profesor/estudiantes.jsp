<%@ page import="org.practica.dto.EstudianteProgresoDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/WEB-INF/views/fragments/_header.jsp"/>
<jsp:include page="/WEB-INF/views/fragments/_nav-profesor.jsp"/>

<main>
    <div style="max-width: 1000px; margin: 30px auto; padding: 0 20px;">
        <h2 class="mb-4">👨 Estudiantes</h2>

        <%
            List<EstudianteProgresoDTO> estudiantes = (List<EstudianteProgresoDTO>) request.getAttribute("estudiantes");
            if (estudiantes == null || estudiantes.isEmpty()) {
        %>
            <p>No hay estudiantes inscritos en tus cursos.</p>
        <%
            } else {
        %>
        <table class="table table-bordered table-hover">
            <thead class="table-dark">
                <tr>
                    <th>Estudiante</th>
                    <th>Email</th>
                    <th>Curso</th>
                    <th class="text-center">Progreso</th>
                </tr>
            </thead>
            <tbody>
                <% for (EstudianteProgresoDTO dto : estudiantes) { %>
                <tr>
                    <td><%= dto.getNombre() %> <%= dto.getApellidos() %></td>
                    <td><%= dto.getEmail() %></td>
                    <td><%= dto.getCursoTitulo() %></td>
                    <td style="min-width: 160px;">
                        <div class="d-flex align-items-center gap-2">
                            <div class="progress flex-grow-1" style="height: 8px;">
                                <div class="progress-bar" role="progressbar"
                                     style="width: <%= dto.getPorcentaje() %>%;"
                                     aria-valuenow="<%= dto.getPorcentaje() %>"
                                     aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                            <small><%= dto.getCompletados() %>/<%= dto.getTotal() %></small>
                        </div>
                    </td>
                </tr>
                <% } %>
            </tbody>
        </table>
        <% } %>
    </div>
</main>

<jsp:include page="/WEB-INF/views/fragments/_footer.jsp"/>
