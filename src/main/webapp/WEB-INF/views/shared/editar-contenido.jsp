<%@ page import="org.practica.model.Contenido" %>
<
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/WEB-INF/views/fragments/_header.jsp"/>
<jsp:include page="/WEB-INF/views/fragments/_nav-profesor.jsp"/>
<%
    Contenido contendio = (Contenido) request.getAttribute("contenidos");

%>
main>
<section>
    <div style="max-width: 520px; margin: 0 auto; padding: 30px;">
        <h4>Modificar contenido</h4>

        <form id="formContenido"
              action="${pageContext.request.contextPath}/profesor/contenidos"
              method="post"
              enctype="multipart/form-data">
            <input type="hidden" name="accion" value="actualizar"/>

            <%-- Título --%>
            <div class="mb-3">
                <label class="form-label">Título *</label>
                <input type="text" name="titulo" class="form-control" required/>
            </div>

            <%-- Curso --%>
            <div class="mb-3">
                <label class="form-label">Curso *</label>
                <select name="cursoId" class="form-select" required>
                    <option value="">-- Selecciona un curso --</option>
                    <%
                        List<Curso> cursos = (List<Curso>) request.getAttribute("cursos");
                        if (cursos != null) {
                            for (Curso c : cursos) {
                    %>
                    <option value="<%= c.getId() %>"><%= c.getTitulo() %></option>
                    <%
                            }
                        }
                    %>
                </select>
            </div>

            <%-- Tipo --%>
            <div class="mb-3">
                <label class="form-label">Tipo *</label>
                <select name="tipo" id="tipo" class="form-select" required onchange="toggleTipo()">
                    <option value="">-- Selecciona tipo --</option>
                    <option value="URL">URL</option>
                    <option value="PDF">PDF</option>
                    <option value="VIDEO">Video</option>
                </select>
            </div>

            <%-- URL (visible si tipo = URL o VIDEO) --%>
            <div class="mb-3" id="campoUrl" style="display:none;">
                <label class="form-label">URL *</label>
                <input type="url" name="url" class="form-control" placeholder="https://..."/>
            </div>

            <%-- Archivo PDF (visible si tipo = PDF) --%>
            <div class="mb-3" id="campoPdf" style="display:none;">
                <label class="form-label">Archivo PDF *</label>
                <input type="file" name="archivo" class="form-control" accept=".pdf"/>
            </div>

            <%-- Orden y fechas --%>
            <div class="row">
                <div class="col mb-3">
                    <label class="form-label">Orden *</label>
                    <input type="number" name="orden" class="form-control" min="1" required/>
                </div>
            </div>

            <div class="row">
                <div class="col mb-3">
                    <label class="form-label">Fecha inicio *</label>
                    <input type="date" name="fechaInicio" class="form-control" required/>
                </div>
                <div class="col mb-3">
                    <label class="form-label">Fecha fin *</label>
                    <input type="date" name="fechaFin" class="form-control" required/>
                </div>
            </div>

            <button type="submit" class="btn btn-primary w-100">Crear contenido</button>
        </form>
    </div>
</section>
</main>

<script>
    function toggleTipo() {
        const tipo = document.getElementById("tipo").value;
        const campoUrl = document.getElementById("campoUrl");
        const campoPdf = document.getElementById("campoPdf");

        campoUrl.style.display = "none";
        campoPdf.style.display = "none";

        if (tipo === "PDF") {
            campoPdf.style.display = "block";
        } else if (tipo === "URL" || tipo === "VIDEO") {
            campoUrl.style.display = "block";
        }
    }
</script>










<jsp:include page="/WEB-INF/views/fragments/_footer.jsp"/>