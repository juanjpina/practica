package org.practica.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.practica.dao.DAOFactory;

import java.io.IOException;

@WebServlet(name = "estudianteContenidos", urlPatterns = "/estudiante/contenidos")
public class EstudianteContenidos extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = (int) request.getSession().getAttribute("id");
        request.setAttribute("cursos", DAOFactory.getCursoDAO().listarInscritosConContenidos(id));
        request.getRequestDispatcher("/WEB-INF/views/estudiante/contenidos.jsp").forward(request,response);
    }
}
