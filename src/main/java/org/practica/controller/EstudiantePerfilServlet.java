package org.practica.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.practica.dao.DAOFactory;
import org.practica.model.AreasInteres;
import org.practica.model.Usuario;
import org.practica.service.UsuarioService;

import java.io.IOException;
import java.util.List;

/**
 * Servlet estudiante
 * desde rol estudiante gestiona su perfil
 */
@WebServlet(name = "EstudiantePerfilServlet", urlPatterns = "/estudiante/perfil")
public class EstudiantePerfilServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        int id = (int) session.getAttribute("id");

        Usuario usuario = DAOFactory.getUsuarioDAO().buscarPorID(id);
        List<AreasInteres> todasLasAreas = DAOFactory.getAreasDeInteresDAO().listarTodos();

        request.setAttribute("usuario", usuario);
        request.setAttribute("areasInteres", todasLasAreas);
        request.getRequestDispatcher("/WEB-INF/views/estudiante/mi-cuenta.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        int id = (int) session.getAttribute("id");

        UsuarioService.actualizarDesdeRequest(request, id, "ESTUDIANTE");

        // Refrescar el objeto Usuario en sesión con los datos actualizados
        session.setAttribute("usuarioLogueado", DAOFactory.getUsuarioDAO().buscarPorID(id));

        response.sendRedirect(request.getContextPath() + "/estudiante/perfil?guardado=ok");
    }
}
