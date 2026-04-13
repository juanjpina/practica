package org.practica.service;

import jakarta.servlet.http.HttpServletRequest;
import org.practica.dao.DAOFactory;
import org.practica.model.Contenido;

import java.time.LocalDate;

public class ContenidoService {
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
}
