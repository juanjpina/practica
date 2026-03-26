<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.practica.model.Usuario, org.practica.model.Estudiante" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.practica.model.AreasInteres" %>
<%@ page import="java.util.List" %>

<jsp:include page="/WEB-INF/views/fragments/_header.jsp"/>
<jsp:include page="/WEB-INF/views/fragments/_nav-admin.jsp"/>

<%
    Usuario usuario = (Usuario) request.getAttribute("usuario");
    List<AreasInteres> todasLasAreas = (List<AreasInteres>) request.getAttribute("areasInteres");
%>

<main style="padding: 30px;">
    <div style="max-width: 600px; margin: 0 auto;">
        <h2>Editar Usuario</h2>

        <form action="${pageContext.request.contextPath}/admin/usuarios" method="post">
            <input type="hidden" name="accion" value="actualizar"/>
            <input type="hidden" name="id" value="<%= usuario.getId() %>"/>

            <%-- Campos comunes --%>
            <div class="mb-3">
                <label class="form-label">Email</label>
                <input type="email" name="email" class="form-control"
                       value="<%= usuario.getEmail() %>" required/>
            </div>
            <div class="mb-3">
                <label class="form-label">Contraseña</label>
                <input type="password" name="password" class="form-control" required/>
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
            <div class="mb-3">
                <label class="form-label">Rol</label>
                <select name="rol" class="form-select">
                    <option value="ESTUDIANTE" <%= "ESTUDIANTE".equals(usuario.getRol()) ? "selected" : "" %>>Estudiante</option>
                    <option value="PROFESOR"   <%= "PROFESOR".equals(usuario.getRol())   ? "selected" : "" %>>Profesor</option>
                    <option value="ADMIN"      <%= "ADMIN".equals(usuario.getRol())      ? "selected" : "" %>>Admin</option>
                </select>
            </div>

            <%-- Campos exclusivos del estudiante --%>
            <% if (usuario instanceof Estudiante) {
                Estudiante e = (Estudiante) usuario;
                List<Integer> ids= new ArrayList<>();
                for(AreasInteres area : e.getAreasInteres()){
                    ids.add(area.getId());
                }
            %>
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
            <% } %>

            <button type="submit" class="btn btn-primary w-100">Guardar cambios</button>
        </form>
    </div>
</main>

<jsp:include page="/WEB-INF/views/fragments/_footer.jsp"/>