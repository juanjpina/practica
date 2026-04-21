<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.practica.model.Usuario, org.practica.model.Estudiante" %>
<%@ page import="org.practica.model.AreasInteres" %>
<%@ page import="java.util.ArrayList, java.util.List" %>

<jsp:include page="/WEB-INF/views/fragments/_header.jsp"/>
<jsp:include page="/WEB-INF/views/fragments/_nav-estudiante.jsp"/>

<%
    Usuario usuario = (Usuario) request.getAttribute("usuario");
    List<AreasInteres> todasLasAreas = (List<AreasInteres>) request.getAttribute("areasInteres");
    Estudiante e = (Estudiante) usuario;
    List<Integer> areasSeleccionadas = new ArrayList<>();
    for (AreasInteres area : e.getAreasInteres()) {
        areasSeleccionadas.add(area.getId());
    }
    boolean guardado = "ok".equals(request.getParameter("guardado"));
%>

<main style="padding: 30px;">
    <div style="max-width: 600px; margin: 0 auto;">
        <h2>Mi Cuenta</h2>

        <% if (guardado) { %>
            <div class="alert alert-success">Datos actualizados correctamente.</div>
        <% } %>

        <form action="${pageContext.request.contextPath}/estudiante/perfil" method="post">
            <input type="hidden" name="rol" value="ESTUDIANTE"/>

            <div class="mb-3">
                <label class="form-label">Email</label>
                <input type="email" name="email" class="form-control"
                       value="<%= usuario.getEmail() %>" required/>
            </div>
            <div class="mb-3">
                <label class="form-label">Contraseña <small class="text-muted">(dejar vacío para no cambiar)</small></label>
                <input type="password" name="password" class="form-control"/>
            </div>
            <div class="row">
                <div class="col mb-3">
                    <label class="form-label">Nombre</label>
                    <input type="text" name="nombre" class="form-control"
                           value="<%= usuario.getNombre() %>" required/>
                </div>
                <div class="col mb-3">
                    <label class="form-label">Apellidos</label>
                    <input type="text" name="apellidos" class="form-control"
                           value="<%= usuario.getApellidos() %>" required/>
                </div>
            </div>

            <hr class="my-3"/>
            <p class="text-muted small mb-3">Datos de dirección</p>

            <div class="mb-3">
                <label class="form-label">Dirección</label>
                <input type="text" name="direccion" class="form-control"
                       value="<%= e.getDireccion() != null ? e.getDireccion() : "" %>"/>
            </div>
            <div class="row">
                <div class="col mb-3">
                    <label class="form-label">Población</label>
                    <input type="text" name="poblacion" class="form-control"
                           value="<%= e.getPoblacion() != null ? e.getPoblacion() : "" %>"/>
                </div>
                <div class="col mb-3">
                    <label class="form-label">Provincia</label>
                    <input type="text" name="provincia" class="form-control"
                           value="<%= e.getProvincia() != null ? e.getProvincia() : "" %>"/>
                </div>
            </div>
            <div class="mb-3">
                <label class="form-label">Código Postal</label>
                <input type="text" name="codigoPostal" class="form-control" maxlength="10"
                       value="<%= e.getCodigoPostal() != null ? e.getCodigoPostal() : "" %>"/>
            </div>

            <div class="mb-4">
                <label class="form-label">Áreas de interés</label>
                <div class="d-flex flex-wrap gap-2">
                    <% for (AreasInteres area : todasLasAreas) { %>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox"
                                   name="areasInteres"
                                   id="area-<%= area.getId() %>"
                                   value="<%= area.getId() %>"
                                   <%= areasSeleccionadas.contains(area.getId()) ? "checked" : "" %>>
                            <label class="form-check-label" for="area-<%= area.getId() %>">
                                <%= area.getDescripcion() %>
                            </label>
                        </div>
                    <% } %>
                </div>
            </div>

            <button type="submit" class="btn btn-primary w-100">Guardar cambios</button>
        </form>
    </div>
</main>

<jsp:include page="/WEB-INF/views/fragments/_footer.jsp"/>
