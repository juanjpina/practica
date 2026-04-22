package org.practica.service;

import jakarta.servlet.http.HttpServletRequest;
import org.practica.dao.DAOFactory;
import org.practica.model.Contenido;

import java.time.LocalDate;

/**
 * Clase contenidoSerice, clase genérica para acceder los diferentes roles a contenido
 */
public class ContenidoService {
    /**
     * inserta en la BD un objeto contenido
     * @param request
     */
    public static void crearDesdeRequest(HttpServletRequest request) {
        Contenido contenido = new Contenido(0,
                request.getParameter("titulo"),
                request.getParameter("tipo"),
                request.getParameter("url"),
                Integer.parseInt(request.getParameter("orden")),
                LocalDate.parse(request.getParameter("fechaInicio")),
                LocalDate.parse(request.getParameter("fechaFin")),
                Integer.parseInt(request.getParameter("cursoId"))
        );
        DAOFactory.getContendioDAO().insertar(contenido);
    }

    /**
     * Modifica en la BD el objeto contenido
     * @param request
     * @param id
     */
    public static void editarContenidoRequest(HttpServletRequest request, int id) {
        Contenido contenido = new Contenido(id,
                request.getParameter("titulo"),
                request.getParameter("tipo"),
                request.getParameter("url"),
                Integer.parseInt(request.getParameter("orden")),
                LocalDate.parse(request.getParameter("fechaInicio")),
                LocalDate.parse(request.getParameter("fechaFin")),
                Integer.parseInt(request.getParameter("cursoId"))
        );
        DAOFactory.getContendioDAO().actualizar(contenido);
    }


}

