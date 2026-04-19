<%@ page import="org.practica.model.Curso" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/WEB-INF/views/fragments/_header.jsp"/>

<section>
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





</section>

<jsp:include page="/WEB-INF/views/fragments/_footer.jsp" />