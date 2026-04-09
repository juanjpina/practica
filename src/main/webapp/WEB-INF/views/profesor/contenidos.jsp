<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, org.practica.model.Contenido" %>

<jsp:include page="/WEB-INF/views/fragments/_header.jsp"/>
<jsp:include page="/WEB-INF/views/fragments/_nav-profesor.jsp"/>
<style>
table th, table td { padding: 12px 15px; }
.editar {
    text-decoration: none;
    color: inherit;
    border: 1px solid #333;
    border-radius: 5px;
    padding: 6px 10px;
    background: green;
}
.eliminar { background: red; }
.emoji { font-size: 1.1rem; }
</style>
<main style="padding: 30px;">
    <div style="max-width: 1100px; margin: 0 auto;">

        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2>Contenidos</h2>
            <a href="${pageContext.request.contextPath}/profesor/contenidos?accion=crear"
               class="btn btn-primary">+ Nuevo Contenido</a>
        </div>

        <table class="table table-lg table-striped table-hover table-bordered align-middle">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Título</th>
                    <th>Tipo</th>
                    <th>URL</th>
                    <th>Orden</th>
                    <th>Fecha Inicio</th>
                    <th>Fecha Fin</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Contenido> contenidos = (List<Contenido>) request.getAttribute("contenidos");
                    if (contenidos != null) {
                        for (Contenido c : contenidos) {
                %>
                <tr>
                    <td><%= c.getId() %></td>
                    <td><%= c.getTitulo() %></td>
                    <td><%= c.getTipo() %></td>
                    <td><%= c.getUrl() %></td>
                    <td><%= c.getOrden() %></td>
                    <td><%= c.getFechaInicio() %></td>
                    <td><%= c.getFechaFin() %></td>
                    <td>
                        <a href="${pageContext.request.contextPath}/profesor/contenidos?accion=editar&id=<%= c.getId() %>"
                           class="editar"><span class="emoji">🖌</span> Editar</a>

                        <form method="get" action="${pageContext.request.contextPath}/profesor/contenidos"
                              style="display:inline;"
                              onsubmit="return confirm('¿Eliminar este contenido?')">
                            <input type="hidden" name="accion" value="eliminar"/>
                            <input type="hidden" name="id" value="<%= c.getId() %>"/>
                            <button type="submit" class="editar eliminar"><span class="emoji">🗑</span> Eliminar</button>
                        </form>
                    </td>
                </tr>
                <%
                        }
                    }
                %>
            </tbody>
        </table>
    </div>
</main>

<jsp:include page="/WEB-INF/views/fragments/_footer.jsp"/>
