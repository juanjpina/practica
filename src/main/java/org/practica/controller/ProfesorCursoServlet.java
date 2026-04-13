package org.practica.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.practica.dao.DAOFactory;
import org.practica.model.Curso;
import org.practica.service.CursoService;

import java.io.IOException;

/**
 * Clase para los cursos de profesor
 */
@WebServlet(name = "profesorCursoServlet", urlPatterns = "/profesor/cursos")
public class ProfesorCursoServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if ("crear".equals(accion)) {

            request.setAttribute("areasInteres", DAOFactory.getAreasDeInteresDAO().listarTodos());
            request.getRequestDispatcher("/WEB-INF/views/profesor/crear-curso.jsp").forward(request, response);


        } else if ("guardar".equals(accion)) {
            CursoService.crearDesdeRequest(request);
            response.sendRedirect(request.getContextPath() + "/profesor/cursos");

        } else if ("actualizar".equals(accion)) {
            int id = Integer.parseInt(request.getParameter("id"));
            CursoService.editarCursoRequest(request, id);
            response.sendRedirect(request.getContextPath() + "/profesor/cursos");
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");


        if ("crear".equals(accion)) {
            request.setAttribute("areasInteres", DAOFactory.getAreasDeInteresDAO().listarTodos());
            request.getRequestDispatcher("/WEB-INF/views/profesor/crear-curso.jsp").forward(request, response);
        } else if ("eliminar".equals(accion)) {
            int id = Integer.parseInt(request.getParameter("id"));
            DAOFactory.getCursoDAO().eliminar(id);
            response.sendRedirect(request.getContextPath() + "/profesor/cursos");
        } else if ("editar".equals(accion)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Curso curso = DAOFactory.getCursoDAO().obtenerCurso(id);
            request.setAttribute("curso", curso);
            request.setAttribute("areasInteres", DAOFactory.getAreasDeInteresDAO().listarTodos());
            request.setAttribute("formAction", "/profesor/cursos");
            request.getRequestDispatcher("/WEB-INF/views/shared/editar-curso.jsp").forward(request, response);


        } else {
            request.setAttribute("cursos", DAOFactory.getCursoDAO().listarTodos());
            request.getRequestDispatcher("/WEB-INF/views/profesor/cursos.jsp").forward(request, response);
        }
    }


}
