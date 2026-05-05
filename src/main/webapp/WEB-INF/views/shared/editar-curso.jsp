<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="org.practica.model.AreasInteres" %>
<%@ page import="java.util.List" %>
<%@ page import="org.practica.model.Curso" %>

<jsp:include page="/WEB-INF/views/fragments/_header.jsp"/>
<jsp:include page="${navFragment}"/>

<%
  Curso curso = (Curso)  request.getAttribute("curso");
  List<AreasInteres> todasLasAreas = (List<AreasInteres>) request.getAttribute("areasInteres");
%>

<main style="padding: 30px;">
  <div style="max-width: 600px; margin: 0 auto;">
    <h2>Editar Curso</h2>

    <form action="${pageContext.request.contextPath}${formAction}" method="post">
      <input type="hidden" name="accion" value="actualizar"/>
      <input type="hidden" name="id" value="<%= curso.getId() %>"/>

      <%-- Campos comunes --%>
      <div class="mb-3">
        <label class="form-label">Titulo</label>
        <input type="text" name="titulo" class="form-control"
               value="<%= curso.getTitulo() %>" required/>
      </div>
      <div class="mb-3">
        <label class="form-label">Descripción</label>
        <input type="text" name="descripcion" class="form-control" value="<%= curso.getDescripcion()%>" required/>
      </div>
      <div class="row">
        <div class="col mb-3">
          <label class="form-label">Duración</label>
          <input type="number" name="duracion" class="form-control"
                 value="<%= curso.getDuracion() %>" required/>
        </div>
        <div class="col mb-3">
          <label class="form-label">Nivel</label>
          <input type="number" name="nivel" class="form-control"
                 value="<%= curso.getNivel() %>" required/>
        </div>
      </div>
      <div class="mb-3">

      <%-- Campos exclusivos del estudiante --%>
        <%
                Curso e = (Curso) curso;
                List<Integer> ids= new ArrayList<>();
                if (e.getAreasInteres() != null) {
                    for(AreasInteres area : e.getAreasInteres()){
                        ids.add(area.getId());
                    }
                }
            %>
      <hr class="my-3"/>



      </div>

      <div class="mb-3">
        <label class="form-label">Áreas de interés</label>
        <div class="d-flex flex-wrap gap-2">
          <% for (AreasInteres area : todasLasAreas){%>
          <div class="form-check form-check-inline">
            <input class="form-check-input" type="checkbox" name="areasInteres" id="area-<%=e.getId()%>" value="<%=area.getId()%>" <%= ids.contains(area.getId()) ? "checked": "" %> >
            <label class="form-check-label" for="<%= area.getId()%>"><%=area.getDescripcion() %></label>
          </div>
          <%}%>
        </div>
      </div>
  </div>


  <button type="submit" class="btn btn-primary w-100">Guardar cambios</button>
  </form>
  </div>
</main>

<jsp:include page="/WEB-INF/views/fragments/_footer.jsp"/>