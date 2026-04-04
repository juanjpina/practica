<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, org.practica.model.Usuario" %>

<jsp:include page="/WEB-INF/views/fragments/_header.jsp"/>
<jsp:include page="/WEB-INF/views/fragments/_nav-admin.jsp"/>
<main>
    <section>
        <div style="max-width: 520px; margin: 0 auto; padding: 30px;">
            <h4>Crear curso</h4>

            <form action="${pageContext.request.contextPath}/admin/cursos" method="post">
                <input type="hidden" name="accion" value="guardar"/>

                <div class="mb-3">
                    <label class="form-label">Profesor *</label>
                    <select name="profesorId" class="form-select" required>
                        <option value="">-- Selecciona un profesor --</option>
                        <%
                            List<Usuario> profesores = (List<Usuario>) request.getAttribute("profesores");
                            if (profesores != null) {
                                for (Usuario p : profesores) {
                        %>
                        <option value="<%= p.getId() %>"><%= p.getNombre() %> <%= p.getApellidos() %></option>
                        <%
                                }
                            }
                        %>
                    </select>
                </div>

                <jsp:include page="/WEB-INF/views/fragments/_campos-base-curso.jsp"/>

                <button type="submit" class="btn btn-primary w-100">Crear curso</button>
            </form>
        </div>
    </section>
</main>

<jsp:include page="/WEB-INF/views/fragments/_footer.jsp"/>
