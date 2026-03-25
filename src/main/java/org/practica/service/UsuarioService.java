package org.practica.service;

import jakarta.servlet.http.HttpServletRequest;
import org.mindrot.jbcrypt.BCrypt;
import org.practica.dao.DAOFactory;
import org.practica.model.Admin;
import org.practica.model.AreasInteres;
import org.practica.model.Estudiante;
import org.practica.model.Profesor;
import org.practica.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioService {

    public static void crearDesdeRequest(HttpServletRequest request) {
        String rol = request.getParameter("rol");
        String passwordPlano = request.getParameter("password");
        String hashPassword = BCrypt.hashpw(passwordPlano, BCrypt.gensalt());

        Usuario usuario;
        if ("ESTUDIANTE".equals(rol)) {
            Estudiante e = new Estudiante(0,
                    request.getParameter("email"),
                    hashPassword,
                    request.getParameter("nombre"),
                    request.getParameter("apellidos"));
            e.setDireccion(request.getParameter("direccion"));
            e.setPoblacion(request.getParameter("poblacion"));
            e.setProvincia(request.getParameter("provincia"));
            e.setCodigoPostal(request.getParameter("codigoPostal"));
            String[] areasIds = request.getParameterValues("areasInteres");
            List<AreasInteres> areas = new ArrayList<>();
            if (areasIds != null) {
                for (String areaId : areasIds) {
                    AreasInteres a = new AreasInteres();
                    a.setId(Integer.parseInt(areaId));
                    areas.add(a);
                }
            }
            e.setAreasInteres(areas);
            usuario = e;
        } else if ("PROFESOR".equals(rol)) {
            usuario = new Profesor(0,
                    request.getParameter("email"),
                    hashPassword,
                    request.getParameter("nombre"),
                    request.getParameter("apellidos"));
        } else {
            usuario = new Admin(0,
                    request.getParameter("email"),
                    hashPassword,
                    request.getParameter("nombre"),
                    request.getParameter("apellidos"));
        }
        DAOFactory.getUsuarioDAO().insertar(usuario);
    }

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
            String[] areasIds = request.getParameterValues("areasInteres");
            List<AreasInteres> areas = new ArrayList<>();
            if (areasIds != null) {
                for (String areaId : areasIds) {
                    AreasInteres a = new AreasInteres();
                    a.setId(Integer.parseInt(areaId));
                    areas.add(a);
                }
            }
            e.setAreasInteres(areas);
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
