<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/WEB-INF/views/fragments/_header.jsp"/>
<main>
    <div class="container">
        <jsp:include page="/WEB-INF/views/fragments/_nav-admin.jsp"/>
    </div>
    <section>
        <div style="max-width: 520px; margin: 0 auto; padding: 30px;">
            <h4>Crear usuario</h4>

            <% if (request.getAttribute("error") != null) { %>
                <div class="alert alert-danger">${error}</div>
            <% } %>

            <form action="${pageContext.request.contextPath}/admin/usuarios" method="post">
                <input type="hidden" name="accion" value="crear"/>

                <%-- Selector de rol dentro del formulario --%>
                <div class="mb-3">
                    <label class="form-label">Rol</label>
                    <select id="rol" name="rol" class="form-select">
                        <option value="ESTUDIANTE">Estudiante</option>
                        <option value="PROFESOR">Profesor</option>
                        <option value="ADMIN">Administrador</option>
                    </select>
                </div>

                <%-- Campos comunes --%>
                <jsp:include page="/WEB-INF/views/fragments/_campos-base.jsp"/>

                <%-- Campos exclusivos del estudiante --%>
                <div id="campos-estudiante">
                    <hr class="my-3"/>
                    <p class="text-muted small mb-3">Datos de dirección</p>
                    <div class="mb-3">
                        <label class="form-label">Dirección</label>
                        <input type="text" name="direccion" class="form-control"/>
                    </div>
                    <div class="row">
                        <div class="col mb-3">
                            <label class="form-label">Población</label>
                            <input type="text" name="poblacion" class="form-control"/>
                        </div>
                        <div class="col mb-3">
                            <label class="form-label">Provincia</label>
                            <input type="text" name="provincia" class="form-control"/>
                        </div>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Código Postal</label>
                        <input type="text" name="codigoPostal" class="form-control" maxlength="10"/>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Áreas de interés</label>
                        <input type="text" name="areasInteres" class="form-control"
                               placeholder="Ej: Java, Web, Bases de datos"/>
                    </div>
                </div>

                <button type="submit" class="btn btn-primary w-100">Crear usuario</button>
            </form>
        </div>
    </section>
</main>

<script>
    document.getElementById("rol").addEventListener("change", function () {
        document.getElementById("campos-estudiante").style.display =
            this.value === 'ESTUDIANTE' ? 'block' : 'none';
    });
</script>

<jsp:include page="/WEB-INF/views/fragments/_footer.jsp"/>
