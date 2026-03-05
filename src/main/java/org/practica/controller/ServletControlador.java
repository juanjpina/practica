package org.practica.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.tags.shaded.org.apache.bcel.generic.SWITCH;
import org.practica.dao.UsuarioDAOImplt;
import org.practica.model.Usuario;

import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(name="ServletControlador", urlPatterns = "/ServletControlador")
public class ServletControlador extends HttpServlet {

@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
String accion = Optional.ofNullable(request.getParameter("accion")).orElse("listar");
    switch (accion){
        case "listar"->this.listar(request,response);
        default -> this.listar(request,response);

    }


}

private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        List<Usuario> usuarios = new UsuarioDAOImplt().listarTodos();
        System.out.println("clientes" +usuarios);
        request.getRequestDispatcher("login.jsp").forward(request,response);
     }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
