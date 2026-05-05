package org.practica.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.practica.dao.CursoDAO;
import org.practica.dao.DAOFactory;
import org.practica.model.Curso;
import org.practica.service.CursoService;

import java.io.IOException;

/**
 * Servlet admin cursos
 * desde rol admin gestiona los cursos
 */
@WebServlet(name = "adminCursoServlet", urlPatterns = "/admin/cursos")
public class AdminCursoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String accion = request.getParameter("accion");

        if ("crear".equals(accion)) {
            request.setAttribute("areasInteres", DAOFactory.getAreasDeInteresDAO().listarTodos());
            request.setAttribute("profesores", DAOFactory.getUsuarioDAO().listarPorRol("PROFESOR"));
            request.getRequestDispatcher("/WEB-INF/views/admin/crear-curso.jsp").forward(request, response);
        } else if ("eliminar".equals(accion)) {
            int id = Integer.parseInt(request.getParameter("id"));
            DAOFactory.getCursoDAO().eliminar(id);
            response.sendRedirect(request.getContextPath() + "/admin/cursos");
        } else if ("editar".equals(accion)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Curso curso = DAOFactory.getCursoDAO().obtenerCurso(id);
            request.setAttribute("curso", curso);
            request.setAttribute("areasInteres", DAOFactory.getAreasDeInteresDAO().listarTodos());
            request.setAttribute("formAction", "/admin/cursos");
            request.setAttribute("navFragment", "/WEB-INF/views/fragments/_nav-admin.jsp");
            request.getRequestDispatcher("/WEB-INF/views/shared/editar-curso.jsp").forward(request, response);
        } else {
            request.setAttribute("cursos", DAOFactory.getCursoDAO().listarTodosConProfesor());
            request.getRequestDispatcher("/WEB-INF/views/admin/cursos.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");


        if ("guardar".equals(accion)) {
            CursoService.crearDesdeRequestAdmin(request);
            response.sendRedirect(request.getContextPath() + "/admin/cursos");

        } else if ("actualizar".equals(accion)) {
            int id = Integer.parseInt(request.getParameter("id"));
            CursoService.editarCursoRequest(request, id);
            response.sendRedirect(request.getContextPath() + "/admin/cursos");
        }
    }
}
