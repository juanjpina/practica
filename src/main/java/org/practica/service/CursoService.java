package org.practica.service;

import jakarta.servlet.http.HttpServletRequest;
import org.practica.dao.DAOFactory;
import org.practica.model.AreasInteres;
import org.practica.model.Curso;
import org.practica.model.Profesor;
import org.practica.model.Usuario;

import java.util.ArrayList;
import java.util.List;


/**
 * Clase cursoService, clase genérica para acceder los diferentes roles a curso
 */
public class CursoService {
    /**
     * inserta en la BD un objeto curso
     *
     * @param request
     */
    public static void crearDesdeRequest(HttpServletRequest request) {
        Profesor profesor = (Profesor) request.getSession(false).getAttribute("usuarioLogueado");
        Curso c = new Curso(0,
                request.getParameter("titulo"),
                request.getParameter("descripcion"),
                Integer.parseInt(request.getParameter("duracion")),
                Integer.parseInt(request.getParameter("nivel")),
                profesor.getId()
        );
        String[] areasIds = request.getParameterValues("areasInteres");
        List<AreasInteres> areas = new ArrayList<>();
        if (areasIds != null) {
            for (String areaId : areasIds) {
                AreasInteres a = new AreasInteres();
                a.setId(Integer.parseInt(areaId.trim()));
                areas.add(a);
            }
        }
        c.setAreasInteres(areas);
        DAOFactory.getCursoDAO().insertar(c);
    }

    /**
     * Modifica en la BD el objeto curso
     *
     * @param request
     * @param id
     */
    public static void editarCursoRequest(HttpServletRequest request, int id) {
        Usuario usuario = (Usuario) request.getSession(false).getAttribute("usuarioLogueado");
        Curso c = new Curso(id,
                request.getParameter("titulo"),
                request.getParameter("descripcion"),
                Integer.parseInt(request.getParameter("duracion")),
                Integer.parseInt(request.getParameter("nivel")),
                usuario.getId()
        );
        String[] areasIds = request.getParameterValues("areasInteres");
        List<AreasInteres> areas = new ArrayList<>();
        if (areasIds != null) {
            for (String areaId : areasIds) {
                AreasInteres a = new AreasInteres();
                a.setId(Integer.parseInt(areaId.trim()));
                areas.add(a);
            }
        }
        c.setAreasInteres(areas);
        DAOFactory.getCursoDAO().actualizar(c);
    }

    public static void crearDesdeRequestAdmin(HttpServletRequest request) {

        Curso c = new Curso(0,
                request.getParameter("titulo"),
                request.getParameter("descripcion"),
                Integer.parseInt(request.getParameter("duracion")),
                Integer.parseInt(request.getParameter("nivel")),
                Integer.parseInt(request.getParameter("profesorId"))
        );
        String[] areasIds = request.getParameterValues("areasInteres");
        List<AreasInteres> areas = new ArrayList<>();
        if (areasIds != null) {
            for (String areaId : areasIds) {
                AreasInteres a = new AreasInteres();
                a.setId(Integer.parseInt(areaId.trim()));
                areas.add(a);
            }
        }
        c.setAreasInteres(areas);
        DAOFactory.getCursoDAO().insertar(c);
    }
}
