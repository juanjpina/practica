<%--
  Created by IntelliJ IDEA.
  User: juan
  Date: 22/3/26
  Time: 3:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/WEB-INF/views/fragments/_header.jsp"/>
<main>
      <!-- contenido de la página -->
    <div class="container">
    <jsp:include page="/WEB-INF/views/fragments/_nav-admin.jsp"/>
    </div>
        <section class="container">
    <label>Elija una opción</label>
    <select id="rol" name="rol">
    <option value="estudiante">ESTUDIANTE</option>
    <option value="profesor">PROFESOR</option>
    <option value="admin">ADMINISTRADOR</option>
    </select>
            <div class="container d-flex justify-content-center align-items-center py-5">
                <div class="card shadow p-4" style="width: 520px;">
                    <h4 class="card-title text-center mb-4">Crear cuenta</h4>

                        <% if (request.getAttribute("error") != null) { %>
                    <div class="alert alert-danger">${error}</div>
                        <% } %>

                    <form action="${pageContext.request.contextPath}/registro" method="post">

                        <%-- Fragmento campos comunes --%>
                        <jsp:include page="/WEB-INF/views/fragments/_campos-base.jsp"/>

                        <%-- Campos exclusivos del estudiante --%>
                            <div id="campos-estudiante" style="display: block">
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

                        <button type="submit" class="btn btn-primary w-100">Registrarse</button>
                            </div>
                    </form>
                </div>

    </section>
</main>

<script>

    (document.getElementById("rol")).addEventListener("change", function(){
        document.getElementById("campos-estudiante").style.display=
            this.value==='estudiante'?'block':'none';
    });
</script>
<jsp:include page="/WEB-INF/views/fragments/_footer.jsp"/>