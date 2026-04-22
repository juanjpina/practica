package org.practica.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Clase AuthFilter, comprueba si existe una sesion creada y acceso restringido a cada usuario.
 */
@WebFilter(urlPatterns = {"/admin/*", "/profesor/*", "/estudiante/*"})
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);
        String uri = request.getRequestURI();
        if (session == null || session.getAttribute("usuarioLogueado") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        String rol = (String) session.getAttribute("rol");
        boolean autorizado = false;
        if (uri.contains("/admin/") && "ADMIN".equals(rol)) {
            autorizado = true;
        } else if (uri.contains("/profesor/") && "PROFESOR".equals(rol)) {
            autorizado = true;

        } else if (uri.contains("/estudiante/") && "ESTUDIANTE".equals(rol)) {
            autorizado = true;

        }
        if (!autorizado) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        chain.doFilter(request, response);
    }


}
