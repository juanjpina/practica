<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="mb-3">
    <label class="form-label">Titulo *</label>
    <input type="text" name="titulo" class="form-control"
            required/>
</div>
<div class="mb-3">
    <label class="form-label">Descripción *</label>
    <input type="text" name="descripcion" class="form-control" required/>
</div>
<div class="row">
    <div class="col mb-3">
        <label class="form-label">Duración *</label>
        <input type="number" name="duracion" class="form-control"
                required/>
    </div>
    <div class="col mb-3">
        <label class="form-label">Nivel *</label>
        <input type="number" name="nivel" class="form-control"
                required/>
    </div>
</div>
<div class="mb-3">
    <label class="form-label">Áreas de interés</label>
    <div class="d-flex flex-wrap gap-2">
        <%
            java.util.List<?> areasInteres = (java.util.List<?>) request.getAttribute("areasInteres");
            if (areasInteres != null) {
                for (Object obj : areasInteres) {
                    org.practica.model.AreasInteres area = (org.practica.model.AreasInteres) obj;
        %>
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="checkbox" name="areasInteres"
                   id="area-<%= area.getId() %>" value="<%= area.getId() %>">
            <label class="form-check-label"
                   for="area-<%= area.getId() %>"><%= area.getDescripcion() %>
            </label>
        </div>
        <%
                }
            }
        %>
    </div>
</div>