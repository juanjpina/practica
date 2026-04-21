package org.practica.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.practica.dao.DAOFactory;
import org.practica.dto.CursoAnalisisDTO;
import org.practica.dto.CursoParticipacionDTO;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminEstadisticasServlet", urlPatterns = "/admin/estadisticas")
public class AdminEstadisticasServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String seccion = request.getParameter("seccion");
        if (seccion == null) seccion = "usuarios";

        if ("usuarios".equals(seccion)) {
            int totalEstudiantes = DAOFactory.getUsuarioDAO().listarPorRol("ESTUDIANTE").size();
            int totalProfesores  = DAOFactory.getUsuarioDAO().listarPorRol("PROFESOR").size();
            int totalAdmins      = DAOFactory.getUsuarioDAO().listarPorRol("ADMIN").size();

            request.setAttribute("totalEstudiantes", totalEstudiantes);
            request.setAttribute("totalProfesores", totalProfesores);
            request.setAttribute("totalAdmins", totalAdmins);
            request.setAttribute("seccion", "usuarios");
        } else if ("cursos".equals(seccion)) {
            int totalCursos = DAOFactory.getCursoDAO().listarTodos().size();
            request.setAttribute("totalCursos", totalCursos);
            request.setAttribute("seccion", "cursos");
        } else if ("participacion".equals(seccion)) {
            List<CursoParticipacionDTO> participacion = DAOFactory.getCursoDAO().listarParticipacionPorCurso();
            request.setAttribute("participacion", participacion);
            request.setAttribute("seccion", "participacion");
        } else if ("inscripciones".equals(seccion)) {
            List<CursoAnalisisDTO> inscripciones = DAOFactory.getCursoDAO().listarInscripcionesPorCurso();
            int totalInscripciones = DAOFactory.getCursoDAO().contarInscripciones();
            request.setAttribute("inscripciones", inscripciones);
            request.setAttribute("totalInscripciones", totalInscripciones);
            request.setAttribute("seccion", "inscripciones");
        }

        request.getRequestDispatcher("/WEB-INF/views/admin/estadisticas.jsp").forward(request, response);
    }
}
