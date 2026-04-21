package org.practica.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.practica.dao.DAOFactory;
import org.practica.model.Contenido;
import org.practica.model.Curso;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "estudianteContenidos", urlPatterns = "/estudiante/contenidos")
public class EstudianteContenidos extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int estudianteId = (int) request.getSession().getAttribute("id");
        String contenidoIdParam = request.getParameter("contenidoId");

        if (contenidoIdParam != null) {
            int contenidoId = Integer.parseInt(contenidoIdParam);

            if (!DAOFactory.getProgresoDAO().existeProgreso(estudianteId, contenidoId)) {
                DAOFactory.getProgresoDAO().registrarAcceso(estudianteId, contenidoId);
            }

            Contenido contenido = DAOFactory.getContendioDAO().buscarPorId(contenidoId);
            response.sendRedirect(contenido.getUrl());
        } else {
            List<Curso> cursos = DAOFactory.getCursoDAO().listarInscritosConContenidos(estudianteId);

            Map<Integer, Integer> progresoPorCurso = new HashMap<>();
            for (Curso curso : cursos) {
                int completados = DAOFactory.getProgresoDAO().listarProgresoPorCurso(estudianteId, curso.getId()).size();
                progresoPorCurso.put(curso.getId(), completados);
            }

            request.setAttribute("cursos", cursos);
            request.setAttribute("progresoPorCurso", progresoPorCurso);
            request.getRequestDispatcher("/WEB-INF/views/estudiante/contenidos.jsp").forward(request, response);
        }
    }
}
