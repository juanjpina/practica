package org.practica.service;

import jakarta.servlet.http.HttpServletRequest;
import org.mindrot.jbcrypt.BCrypt;
import org.practica.dao.DAOFactory;
import org.practica.model.Admin;
import org.practica.model.Estudiante;
import org.practica.model.Profesor;
import org.practica.model.Usuario;

public class UsuarioService {

    public static void actualizarDesdeRequest(HttpServletRequest request, int id, String rol){
        String hashPassword = null;
        String passwordPlano = request.getParameter("password");
        if (passwordPlano != null && !passwordPlano.isEmpty()) {
            hashPassword = BCrypt.hashpw(passwordPlano, BCrypt.gensalt());
        }
        Usuario usuario;
        if("ESTUDIANTE".equals(rol)){
            Estudiante e = new Estudiante(id,
                    request.getParameter("email"),
                    hashPassword,
                    request.getParameter("nombre"),
                    request.getParameter("apellidos"));
            e.setDireccion(request.getParameter("direccion"));
            e.setPoblacion(request.getParameter("poblacion"));
            e.setProvincia(request.getParameter("provincia"));
            e.setCodigoPostal(request.getParameter("codigoPostal"));
            e.setAreasInteres(request.getParameter("areasInteres"));
            usuario=e;
        }else if("PROFESOR".equals(rol)){
            usuario = new Profesor(id,
                    request.getParameter("email"),
                    hashPassword,
                    request.getParameter("nombre"),
                    request.getParameter("apellidos"));
        }else{
            usuario = new Admin(id,
                    request.getParameter("email"),
                    hashPassword,
                    request.getParameter("nombre"),
                    request.getParameter("apellidos"));
        }
        DAOFactory.getUsuarioDAO().actualizar(usuario);

    }
}
