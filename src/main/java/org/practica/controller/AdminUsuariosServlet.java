package org.practica.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * clase para la gestión de los usuarios CRUD
 */
@WebServlet(name = "AdminUsuariosServlet", urlPatterns = "/admin/usuarios")
public class AdminUsuariosServlet extends HttpServlet {

    /**
     * Listar los usuarios
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {




    }

}

