package org.practica.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.practica.dao.DAOFactory;
import org.practica.model.AreasInteres;
import org.practica.model.Usuario;
import org.practica.service.UsuarioService;

import java.io.IOException;
import java.util.List;

/**
 * clase para la gestión de los usuarios CRUD
 */
@WebServlet(name = "AdminUsuariosServlet", urlPatterns = "/admin/usuarios")
public class AdminUsuariosServlet extends HttpServlet {

    /**
     * Listar los usuarios, editar usuarios
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String accion = request.getParameter("accion");

// AdminServlet.java
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

        if ("editar".equals(accion)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Usuario usuario = DAOFactory.getUsuarioDAO().buscarPorID(id);
            List<AreasInteres> areasInteres = DAOFactory.getAreasDeInteresDAO().listarTodos();
            request.setAttribute("areasInteres", areasInteres);
            request.setAttribute("usuario", usuario);
            request.getRequestDispatcher("/WEB-INF/views/admin/editar-usuario.jsp").forward(request, response);
        }else if("crear".equals(accion)){
            request.setAttribute("areasInteres", DAOFactory.getAreasDeInteresDAO().listarTodos());
            request.getRequestDispatcher("/WEB-INF/views/admin/crear-usuario.jsp").forward(request, response);
        }else {
            List<Usuario> usuarios = DAOFactory.getUsuarioDAO().listarTodos();
            request.setAttribute("usuarios", usuarios);
            request.getRequestDispatcher("/WEB-INF/views/admin/usuarios.jsp").forward(request, response);
        }


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String accion = request.getParameter("accion");
        if("crear".equals(accion)){
            UsuarioService.crearDesdeRequest(request);
            response.sendRedirect(request.getContextPath()+"/admin/usuarios");
        }else if("eliminar".equals(accion)){
            int id= Integer.parseInt(request.getParameter("id"));
            DAOFactory.getUsuarioDAO().eliminar(id);
            response.sendRedirect(request.getContextPath()+"/admin/usuarios");
        }else if("actualizar".equals(accion)){
            int id= Integer.parseInt(request.getParameter("id"));
            String rol = request.getParameter("rol");
            UsuarioService.actualizarDesdeRequest(request,id,rol);
            response.sendRedirect(request.getContextPath()+"/admin/usuarios");
        }



    }


}

