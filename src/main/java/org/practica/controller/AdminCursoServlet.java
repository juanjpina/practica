package org.practica.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.practica.dao.DAOFactory;
import org.practica.model.Curso;
import org.practica.service.CursoService;

import java.io.IOException;

@WebServlet(name = "adminCursoServlet", urlPatterns = "admin/cursos")
public class AdminCursoServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String accion = request.getParameter("accion");
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuarioLogueado") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

// Comprobar que el rol es correcto
        String rol = (String) session.getAttribute("rol");
        if (!"ADMIN".equals(rol)) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        if ("crear".equals(accion)) {
            request.setAttribute("areasInteres", DAOFactory.getAreasDeInteresDAO().listarTodos());
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
            request.getRequestDispatcher("/WEB-INF/views/admin/editar-curso.jsp").forward(request, response);
        } else {
            request.setAttribute("cursos", DAOFactory.getCursoDAO().obtenerTodos());
            request.getRequestDispatcher("/WEB-INF/views/admin/cursos.jsp").forward(request, response);
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuarioLogueado") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        String rol = (String) session.getAttribute("rol");
        if (!"ADMIN".equals(rol)) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        if ("crear".equals(accion)) {

            request.setAttribute("areasInteres", DAOFactory.getAreasDeInteresDAO().listarTodos());
            request.getRequestDispatcher("/WEB-INF/views/admin/crear-curso.jsp").forward(request, response);

        } else if ("guardar".equals(accion)) {
            CursoService.crearDesdeRequest(request);
            response.sendRedirect(request.getContextPath() + "/admin/cursos");

        } else if ("actualizar".equals(accion)) {
            int id = Integer.parseInt(request.getParameter("id"));
            CursoService.editarCursoRequest(request, id);
            response.sendRedirect(request.getContextPath() + "/admin/cursos");
        }
    }
}
