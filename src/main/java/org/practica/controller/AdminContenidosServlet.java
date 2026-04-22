package org.practica.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.practica.dao.DAOFactory;
import org.practica.model.Contenido;
import org.practica.service.ContenidoService;

import java.io.IOException;
import java.util.List;

/**
 * Sevlet admin contenidos
 * desde el rol admin gestiona los contenidos
 */
@MultipartConfig
@WebServlet(name = "adminContenidosServlet", urlPatterns = "/admin/contenidos")
public class AdminContenidosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if ("crear".equals(accion)) {
            request.setAttribute("cursos", DAOFactory.getCursoDAO().listarTodos());
            request.getRequestDispatcher("/WEB-INF/views/admin/crear-contenido.jsp").forward(request, response);
        } else if ("eliminar".equals(accion)) {
            int id = Integer.parseInt(request.getParameter("id"));
            DAOFactory.getContendioDAO().eliminar(id);
            response.sendRedirect(request.getContextPath() + "/admin/contenidos");
        } else if ("editar".equals(accion)) {
            int id = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("cursos", DAOFactory.getCursoDAO().listarTodos());
            request.setAttribute("contenidos", DAOFactory.getContendioDAO().buscarPorId(id));
            request.setAttribute("formAction", "/admin/contenidos");
            request.getRequestDispatcher("/WEB-INF/views/admin/editar-contenido.jsp").forward(request, response);
        } else {
            List<Contenido> contenido = DAOFactory.getContendioDAO().listarTodos();
            request.setAttribute("contenidos", contenido);
            request.setAttribute("formAction", "/admin/contenidos?accion=crear");
            request.setAttribute("formAction2", "/admin/contenidos?accion=editar&id=");
            request.setAttribute("formAction3", "/admin/contenidos");
            request.getRequestDispatcher("/WEB-INF/views/admin/contenido.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String accion = request.getParameter("accion");
        if ("guardar".equals(accion)) {
            ContenidoService.crearDesdeRequest(request);
            response.sendRedirect(request.getContextPath() + "/admin/contenidos");
        } else if ("actualizar".equals(accion)) {
            int id = Integer.parseInt(request.getParameter("id"));
            ContenidoService.editarContenidoRequest(request, id);
            response.sendRedirect(request.getContextPath() + "/admin/contenidos");
        }
    }

}
