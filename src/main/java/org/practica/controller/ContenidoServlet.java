package org.practica.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.practica.dao.DAOFactory;
import org.practica.model.Contenido;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "contenidoServlet", urlPatterns = "/profesor/contenidos")
public class ContenidoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if ("crear".equals(accion)) {

        } else if ("eliminar".equals(accion)) {

        } else if ("editar".equals(accion)) {

        } else {
            List<Contenido> contenido = DAOFactory.getContendioDAO().listarTodos();
            request.setAttribute("contenidos", contenido);
            request.getRequestDispatcher("/WEB-INF/views/profesor/contenidos.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String accion = request.getParameter("accion");


    }
}
