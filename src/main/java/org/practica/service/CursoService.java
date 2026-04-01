package org.practica.service;

import jakarta.servlet.http.HttpServletRequest;
import org.practica.dao.DAOFactory;
import org.practica.model.AreasInteres;
import org.practica.model.Curso;
import org.practica.model.Profesor;
import java.util.ArrayList;
import java.util.List;

public class CursoService {
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
    public static void editarCursoRequest(HttpServletRequest request, int id) {
        Profesor profesor = (Profesor) request.getSession(false).getAttribute("usuarioLogueado");
        Curso c = new Curso(id,
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
        DAOFactory.getCursoDAO().actualizar(c);
    }
}
