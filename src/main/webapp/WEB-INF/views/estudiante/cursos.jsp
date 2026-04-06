<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, org.practica.dto.CursoDTO" %>

<jsp:include page="/WEB-INF/views/fragments/_header.jsp"/>
<jsp:include page="/WEB-INF/views/fragments/_nav-estudiante.jsp"/>
<style>
  table th, table td { padding: 12px 15px; }
  .emoji { font-size: 1.1rem; }
  .ya-inscrito { color: green; font-weight: bold; }
</style>
<main style="padding: 30px;">
  <div style="max-width: 1100px; margin: 0 auto;">

    <div class="d-flex justify-content-between align-items-center mb-4">
      <h2>Cursos disponibles</h2>
    </div>

    <%
      String inscripcion = request.getParameter("inscripcion");
      if ("ok".equals(inscripcion)) {
    %>
      <div class="alert alert-success">Inscripción realizada correctamente.</div>
    <% } else if ("vacio".equals(inscripcion)) { %>
      <div class="alert alert-warning">Selecciona al menos un curso para inscribirte.</div>
    <% } %>

    <form method="post" action="${pageContext.request.contextPath}/estudiante/curso">
      <table class="table table-lg table-striped table-hover table-bordered align-middle">
        <thead class="table-dark">
        <tr>
          <th>Inscribirse</th>
          <th>ID</th>
          <th>Título</th>
          <th>Descripción</th>
          <th>Duración</th>
          <th>Nivel</th>
          <th>Profesor</th>
        </tr>
        </thead>
        <tbody>
        <%
          List<CursoDTO> cursos = (List<CursoDTO>) request.getAttribute("cursos");
          List<Integer> inscritos = (List<Integer>) request.getAttribute("inscritos");
          if (cursos != null) { for (CursoDTO c : cursos) {
            boolean yaInscrito = inscritos != null && inscritos.contains(c.getId());
        %>
        <tr>
          <td class="text-center">
            <% if (yaInscrito) { %>
              <span class="ya-inscrito">✔ Inscrito</span>
            <% } else { %>
              <input type="checkbox" name="cursoId" value="<%= c.getId() %>"/>
            <% } %>
          </td>
          <td><%= c.getId() %></td>
          <td><%= c.getTitulo() %></td>
          <td><%= c.getDescripcion() %></td>
          <td><%= c.getDuracion() %></td>
          <td><%= c.getNivel() %></td>
          <td><%= c.getNombreProfesor() %></td>
        </tr>
        <% } } %>
        </tbody>
      </table>
      <button type="submit" class="btn btn-primary">Inscribirse en los seleccionados</button>
    </form>
  </div>
</main>

<jsp:include page="/WEB-INF/views/fragments/_footer.jsp"/>