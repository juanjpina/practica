<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
     <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;600;700&display=swap" rel="stylesheet">
<style>
        body {
            background: linear-gradient(to bottom, rgba(235,192,40,0.8), rgba(255,234,195,0.8));
              font-family: 'Montserrat', sans-serif;
        }
        .card {
            border-radius: 15px;
        }
    </style>
</head>
<body>

<div class="container d-flex justify-content-center align-items-center" style="min-height: 100vh;">
    <div class="card shadow p-4" style="width: 400px;">
        <h4 class="card-title text-center mb-4">Iniciar Sesión</h4>

        <% if (request.getAttribute("error") != null) { %>
            <div class="alert alert-danger">${error}</div>
        <% } %>

        <form action="${pageContext.request.contextPath}/login" method="post">
            <div class="mb-3">
                <label class="form-label">Email</label>
                <input type="email" name="email" class="form-control" required/>
            </div>
            <div class="mb-3">
                <label class="form-label">Contraseña</label>
                <input type="password" name="password" class="form-control" required/>
            </div>
            <button type="submit" class="btn btn-primary w-100">Entrar</button>
        </form>

        <p class="text-center mt-3">
            ¿No tienes cuenta?
            <a href="${pageContext.request.contextPath}/registro">Regístrate</a>
        </p>
    </div>
</div>

</body>
</html>