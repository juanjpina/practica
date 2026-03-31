<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/fragments/_header.jsp"/>
<main>
    <div class="container">
        <jsp:include page="/WEB-INF/views/fragments/_nav-admin.jsp"/>
    </div>
    <section>
        <div style="max-width: 520px; margin: 0 auto; padding: 30px;">
            <h4>Crear curso</h4>

            <% if (request.getAttribute("error") != null) { %>
                <div class="alert alert-danger">${error}</div>
            <% } %>

            <form action="${pageContext.request.contextPath}/profesor/cursos" method="post">
                <input type="hidden" name="accion" value="guardar"/>

                <%-- Selector de rol dentro del formulario --%>


                <%-- Campos comunes --%>
                <jsp:include page="/WEB-INF/views/fragments/_campos-base-curso.jsp"/>

                <%-- Campos exclusivos del estudiante --%>



</div>
                    </div>
                </div>

                <button type="submit" class="btn btn-primary w-100">Crear curso</button>
            </form>
        </div>
    </section>
</main>


<jsp:include page="/WEB-INF/views/fragments/_footer.jsp"/>
