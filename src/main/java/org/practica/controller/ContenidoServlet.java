package org.practica.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.practica.dao.DAOFactory;
import org.practica.model.Contenido;
import org.practica.model.Curso;
import org.practica.service.ContenidoService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@MultipartConfig
@WebServlet(name = "contenidoServlet", urlPatterns = "/profesor/contenidos")
public class ContenidoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if ("crear".equals(accion)) {



           request.setAttribute("cursos", DAOFactory.getCursoDAO().listarTodos());
            request.getRequestDispatcher("/WEB-INF/views/profesor/crear-contenido.jsp").forward(request, response);
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

         if("guardar".equals(accion)) {
             ContenidoService.crearDesdeRequest(request);
             response.sendRedirect(request.getContextPath()+"/profesor/contenidos");



        } else if ("actualizar".equals(accion)) {

        }

    }
}
