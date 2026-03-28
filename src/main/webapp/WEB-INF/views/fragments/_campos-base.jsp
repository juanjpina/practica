<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="mb-3">
    <label class="form-label">Email *</label>
    <input type="email" name="email" class="form-control"
           value="${param.email}" required/>
</div>
<div class="mb-3">
    <label class="form-label">Contraseña *</label>
    <input type="password" name="password" class="form-control" required/>
</div>
<div class="row">
    <div class="col mb-3">
        <label class="form-label">Nombre *</label>
        <input type="text" name="nombre" class="form-control"
               value="${param.nombre}" required/>
    </div>
    <div class="col mb-3">
        <label class="form-label">Apellidos *</label>
        <input type="text" name="apellidos" class="form-control"
               value="${param.apellidos}" required/>
    </div>
</div>
