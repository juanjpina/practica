<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, org.practica.model.AreasInteres" %>

<jsp:include page="/WEB-INF/views/fragments/_header.jsp"/>
<jsp:include page="/WEB-INF/views/fragments/_nav-admin.jsp"/>

<style>
    .editar { text-decoration: none; color: inherit; border: 1px solid #333; border-radius: 5px; padding: 6px 10px; background: green; }
    .eliminar { background: red; }
    .emoji { font-size: 1.1rem; }
</style>

<main style="padding: 30px;">
    <div style="max-width: 800px; margin: 0 auto;">

        <h2>Áreas de Interés</h2>

        <%
            AreasInteres areaEditar = (AreasInteres) request.getAttribute("area");
            boolean editando = areaEditar != null;
        %>

        <div style="background:#f8f9fa; border:1px solid #ddd; border-radius:8px; padding:20px; margin-bottom:30px;">
            <h5><%= editando ? "Editar área" : "Nueva área" %></h5>
            <form method="post" action="${pageContext.request.contextPath}/admin/areas">
                <input type="hidden" name="accion" value="<%= editando ? "actualizar" : "guardar" %>"/>
                <% if (editando) { %>
                    <input type="hidden" name="id" value="<%= areaEditar.getId() %>"/>
                <% } %>
                <div style="display:flex; gap:10px; align-items:center;">
                    <input type="text" name="descripcion" class="form-control"
                           placeholder="Descripción del área"
                           value="<%= editando ? areaEditar.getDescripcion() : "" %>"
                           required style="max-width:400px;"/>
                    <button type="submit" class="btn btn-primary"><%= editando ? "Actualizar" : "Añadir" %></button>
                    <% if (editando) { %>
                        <a href="${pageContext.request.contextPath}/admin/areas" class="btn btn-secondary">Cancelar</a>
                    <% } %>
                </div>
            </form>
        </div>

        <table class="table table-striped table-hover table-bordered align-middle">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Descripción</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<AreasInteres> areas = (List<AreasInteres>) request.getAttribute("areas");
                    for (AreasInteres a : areas) {
                %>
                <tr>
                    <td><%= a.getId() %></td>
                    <td><%= a.getDescripcion() %></td>
                    <td>
                        <a href="${pageContext.request.contextPath}/admin/areas?accion=editar&id=<%= a.getId() %>"
                           class="editar"><span class="emoji">🖌</span> Editar</a>

                        <form method="get" action="${pageContext.request.contextPath}/admin/areas"
                              style="display:inline;"
                              onsubmit="return confirm('¿Eliminar esta área de interés?')">
                            <input type="hidden" name="accion" value="eliminar"/>
                            <input type="hidden" name="id" value="<%= a.getId() %>"/>
                            <button type="submit" class="editar eliminar"><span class="emoji">🗑</span> Eliminar</button>
                        </form>
                    </td>
                </tr>
                <% } %>
            </tbody>
        </table>

    </div>
</main>

<jsp:include page="/WEB-INF/views/fragments/_footer.jsp"/>