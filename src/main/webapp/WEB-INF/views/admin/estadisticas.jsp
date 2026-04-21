<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.practica.dto.CursoAnalisisDTO, org.practica.dto.CursoParticipacionDTO, java.util.List" %>

<jsp:include page="/WEB-INF/views/fragments/_header.jsp"/>
<jsp:include page="/WEB-INF/views/fragments/_nav-admin.jsp"/>

<main style="padding: 30px;">
    <div style="max-width: 900px; margin: 0 auto;">
        <h2>Estadísticas</h2>

        <% if ("usuarios".equals(request.getAttribute("seccion"))) { %>

        <h4 class="mt-4 mb-3">Usuarios registrados</h4>
        <div class="row g-3">

            <div class="col-md-4">
                <div class="card text-center shadow-sm">
                    <div class="card-body">
                        <div style="font-size: 2.5rem; font-weight: bold; color: #0d6efd;">
                            <%= request.getAttribute("totalEstudiantes") %>
                        </div>
                        <div class="card-title mt-1">Estudiantes</div>
                    </div>
                </div>
            </div>

            <div class="col-md-4">
                <div class="card text-center shadow-sm">
                    <div class="card-body">
                        <div style="font-size: 2.5rem; font-weight: bold; color: #198754;">
                            <%= request.getAttribute("totalProfesores") %>
                        </div>
                        <div class="card-title mt-1">Profesores</div>
                    </div>
                </div>
            </div>

            <div class="col-md-4">
                <div class="card text-center shadow-sm">
                    <div class="card-body">
                        <div style="font-size: 2.5rem; font-weight: bold; color: #6c757d;">
                            <%= request.getAttribute("totalAdmins") %>
                        </div>
                        <div class="card-title mt-1">Administradores</div>
                    </div>
                </div>
            </div>

        </div>

        <% } else if ("cursos".equals(request.getAttribute("seccion"))) { %>

        <h4 class="mt-4 mb-3">Cursos activos</h4>

        <div class="card text-center shadow-sm d-inline-block px-5 py-3 mb-4">
            <div style="font-size: 2.5rem; font-weight: bold; color: #0d6efd;">
                <%= request.getAttribute("totalCursos") %>
            </div>
            <div class="card-title mt-1">Total de cursos registrados</div>
        </div>

        <% } else if ("inscripciones".equals(request.getAttribute("seccion"))) {
            List<CursoAnalisisDTO> inscripciones = (List<CursoAnalisisDTO>) request.getAttribute("inscripciones");
        %>

        <h4 class="mt-4 mb-3">Inscripciones</h4>

        <div class="card text-center shadow-sm d-inline-block px-5 py-3 mb-4">
            <div style="font-size: 2.5rem; font-weight: bold; color: #0d6efd;">
                <%= request.getAttribute("totalInscripciones") %>
            </div>
            <div class="card-title mt-1">Total inscripciones</div>
        </div>

        <table class="table table-striped table-bordered mt-3">
            <thead class="table-dark">
                <tr>
                    <th>Curso</th>
                    <th class="text-center">Estudiantes inscritos</th>
                </tr>
            </thead>
            <tbody>
                <% for (CursoAnalisisDTO c : inscripciones) { %>
                <tr>
                    <td><%= c.getTitulo() %></td>
                    <td class="text-center"><%= c.getNumInscritos() %></td>
                </tr>
                <% } %>
            </tbody>
        </table>

        <% } else if ("participacion".equals(request.getAttribute("seccion"))) {
            List<CursoParticipacionDTO> participacion = (List<CursoParticipacionDTO>) request.getAttribute("participacion");
        %>

        <h4 class="mt-4 mb-3">Participación y valoración por curso</h4>

        <table class="table table-striped table-bordered mt-2">
            <thead class="table-dark">
                <tr>
                    <th>Curso</th>
                    <th class="text-center">Media valoración (0-5)</th>
                    <th class="text-center">Progreso medio (%)</th>
                </tr>
            </thead>
            <tbody>
                <% for (CursoParticipacionDTO c : participacion) { %>
                <tr>
                    <td><%= c.getTitulo() %></td>
                    <td class="text-center"><%= c.getMediaValoracion() %></td>
                    <td class="text-center"><%= c.getProgresoMedio() %> %</td>
                </tr>
                <% } %>
            </tbody>
        </table>

        <% } %>
    </div>
</main>

<jsp:include page="/WEB-INF/views/fragments/_footer.jsp"/>
