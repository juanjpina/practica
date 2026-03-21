<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, org.practica.model.Usuario" %>

<jsp:include page="/WEB-INF/views/fragments/_header.jsp"/>
<jsp:include page="/WEB-INF/views/fragments/_nav-admin.jsp"/>
<style>
table th, table td{
padding: 12px 15px;}
.editar{
    text-decoration:none;
    color:inherit;
    border: 1px solid #333;
    border-radius: 5px;
    padding: 6px 10px;
    background:green;
}
.eliminar{
background:red;
}
.emoji{
 font-size: 1.1rem;
}
</style>
<main style="padding: 30px;">
    <div style="max-width: 1100px; margin: 0 auto;">

        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2>Gestión de Usuarios</h2>
            <a href="${pageContext.request.contextPath}/admin/usuarios?accion=crear"
               class="btn btn-primary">+ Nuevo Usuario</a>
        </div>

        <table class="table table-lg table-striped table-hover table-bordered align-middle">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Apellidos</th>
                    <th>Email</th>
                    <th>Rol</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
                    for (Usuario u : usuarios) {
                %>
                <tr>
                    <td><%= u.getId() %></td>
                    <td><%= u.getNombre() %></td>
                    <td><%= u.getApellidos() %></td>
                    <td><%= u.getEmail() %></td>
                    <td>
                        <span class="badge <%=
                            "ADMIN".equals(u.getRol()) ? "bg-danger" :
                            "PROFESOR".equals(u.getRol()) ? "bg-warning text-dark" :
                            "bg-primary"
                        %>">
                            <%= u.getRol() %>
                        </span>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/admin/usuarios?accion=editar&id=<%= u.getId() %>"
                           class="editar"><span class="emoji">🖌</span> Editar</a>

                        <form method="post" action="${pageContext.request.contextPath}/admin/usuarios"
                              style="display:inline;"
                              onsubmit="return confirm('¿Eliminar este usuario?')">
                            <input type="hidden" name="accion" value="eliminar"/>
                            <input type="hidden" name="id" value="<%= u.getId() %>"/>
                            <button type="submit" class="editar eliminar"> <span class="emoji">🗑</span> Eliminar</button>
                        </form>
                    </td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </div>
</main>

<jsp:include page="/WEB-INF/views/fragments/_footer.jsp"/>