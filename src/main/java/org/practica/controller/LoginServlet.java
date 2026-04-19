package org.practica.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;
import org.practica.dao.DAOFactory;
import org.practica.model.Usuario;

import java.io.IOException;

/**
 * Clase Servlet para login
 */
@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    /**
     * Metodo post para la recepción de la vista login. Según el rol del usuario sera enviado a su vista.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Usuario usuario = DAOFactory.getUsuarioDAO().buscarPorEmail(email);
        boolean passwordValida = false;
        if (usuario != null) {
            try {
                passwordValida = BCrypt.checkpw(password, usuario.getPassword());
            } catch (Exception e) {
                passwordValida = false;
            }
        }
        if (!passwordValida) {
            request.setAttribute("error", "email o password incorrectos");
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
            return;
        }

        HttpSession session = request.getSession();
        session.setAttribute("usuarioLogueado", usuario);
        session.setAttribute("rol", usuario.getRol());
        session.setAttribute("id", usuario.getId());

        switch (usuario.getRol()) {
            case "ADMIN" -> response.sendRedirect(request.getContextPath() + "/admin/dashboard");
            case "PROFESOR" -> response.sendRedirect(request.getContextPath() + "/profesor/dashboard");
            case "ESTUDIANTE" -> response.sendRedirect(request.getContextPath() + "/estudiante/dashboard");
                default -> response.sendRedirect(request.getContextPath() + "/login");
        }

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }

}
