package org.practica.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.practica.dao.DAOFactory;
import org.practica.model.Usuario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet estudiante cursos
 * Desde rol estudiante gestiona los cursos
 */
@WebServlet(name = "estudianteCursoServlet", urlPatterns = "/estudiante/curso")
public class EstudianteCursoServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        int estudianteId = usuario.getId();

        String[] ids = request.getParameterValues("cursoId");
        if (ids != null && ids.length > 0) {
            List<Integer> cursosIds = new ArrayList<>();
            for (String id : ids) {
                try {
                    cursosIds.add(Integer.parseInt(id));
                } catch (NumberFormatException ignored) {
                }
            }
            DAOFactory.getCursoDAO().inscribirEstudiante(estudianteId, cursosIds);
            response.sendRedirect(request.getContextPath() + "/estudiante/curso?inscripcion=ok");
        } else {
            response.sendRedirect(request.getContextPath() + "/estudiante/curso?inscripcion=vacio");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        HttpSession session = request.getSession(false);
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        int estudianteId = usuario.getId();

        request.setAttribute("cursos", DAOFactory.getCursoDAO().listarTodosConProfesor());
        request.setAttribute("inscritos", DAOFactory.getCursoDAO().listarCursosInscritos(estudianteId));
        request.getRequestDispatcher("/WEB-INF/views/estudiante/cursos.jsp").forward(request, response);
    }
}
