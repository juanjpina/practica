package org.practica.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "EstudianteServlet", urlPatterns = "/estudiante/dashboard")
public class EstudianteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession(false);
        if(session==null || session.getAttribute("usuarioLogueado")==null){
        response.sendRedirect(request.getContextPath()+"/login");
        return;
        }
        // Comprobar que el rol es correcto
        String rol = (String) session.getAttribute("rol");
        if (!"ESTUDIANTE".equals(rol)) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        request.getRequestDispatcher("/WEB-INF/views/estudiante/dashboard.jsp").forward(request,response);
    }




}
