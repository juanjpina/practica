package org.practica.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AdminServlet extends HttpServlet {

    protected void doGet (HttpServletResponse response, HttpServletRequest request) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        if(session==null||session.getAttribute("usuarioLogueado")==null){
            response.sendRedirect(request.getContextPath()+"/login");
            return;
        }

        request.getRequestDispatcher("/WEB-INF/views/admin/dashboard/jsp").forward(request,response);


    }


}
