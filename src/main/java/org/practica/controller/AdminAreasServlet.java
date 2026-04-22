package org.practica.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.practica.dao.DAOFactory;
import org.practica.model.AreasInteres;

import java.io.IOException;

/**
 * Servler admin areas de interes
 * desde rol admin gestiona las areas de interes
 */
@WebServlet(name = "adminAreasServlet", urlPatterns = "/admin/areas")
public class AdminAreasServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if ("eliminar".equals(accion)) {
            int id = Integer.parseInt(request.getParameter("id"));
            DAOFactory.getAreasDeInteresDAO().eliminar(id);
            response.sendRedirect(request.getContextPath() + "/admin/areas");

        } else if ("editar".equals(accion)) {
            int id = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("area", DAOFactory.getAreasDeInteresDAO().buscarPorId(id));
            request.setAttribute("areas", DAOFactory.getAreasDeInteresDAO().listarTodos());
            request.getRequestDispatcher("/WEB-INF/views/admin/areas.jsp").forward(request, response);

        } else {
            request.setAttribute("areas", DAOFactory.getAreasDeInteresDAO().listarTodos());
            request.getRequestDispatcher("/WEB-INF/views/admin/areas.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String accion = request.getParameter("accion");

        if ("guardar".equals(accion)) {
            String descripcion = request.getParameter("descripcion");
            DAOFactory.getAreasDeInteresDAO().insertar(new AreasInteres(0, descripcion));

        } else if ("actualizar".equals(accion)) {
            int id = Integer.parseInt(request.getParameter("id"));
            String descripcion = request.getParameter("descripcion");
            DAOFactory.getAreasDeInteresDAO().actualizar(new AreasInteres(id, descripcion));
        }

        response.sendRedirect(request.getContextPath() + "/admin/areas");
    }
}