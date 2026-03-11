<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Registro</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;600;700&display=swap" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(to bottom, rgba(235,192,40,0.8), rgba(255,234,195,0.8));
            font-family: 'Montserrat', sans-serif;
        }
        .card { border-radius: 15px; }
    </style>
</head>
<body>
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
        </form>

        <p class="text-center mt-3">
            ¿Ya tienes cuenta?
            <a href="${pageContext.request.contextPath}/login">Iniciar sesión</a>
        </p>
    </div>
</div>
</body>
</html>
