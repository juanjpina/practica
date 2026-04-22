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

/**
 * Servlet estudiante contenidos
 * desde rol estudiante gestiona los contenidos
 */
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
            Map<Integer, Integer> valoracionPorCurso = new HashMap<>();
            Map<Integer, Boolean> favoritosPorCurso = new HashMap<>();
            for (Curso curso : cursos) {
                int completados = DAOFactory.getProgresoDAO().listarProgresoPorCurso(estudianteId, curso.getId()).size();
                progresoPorCurso.put(curso.getId(), completados);
                valoracionPorCurso.put(curso.getId(), DAOFactory.getCursoDAO().obtenerValoracion(estudianteId, curso.getId()));
                favoritosPorCurso.put(curso.getId(), DAOFactory.getCursoDAO().obtenerFavorito(estudianteId, curso.getId()));
            }

            request.setAttribute("cursos", cursos);
            request.setAttribute("progresoPorCurso", progresoPorCurso);
            request.setAttribute("valoracionPorCurso", valoracionPorCurso);
            request.setAttribute("favoritosPorCurso", favoritosPorCurso);
            request.getRequestDispatcher("/WEB-INF/views/estudiante/contenidos.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int estudianteId = (int) request.getSession().getAttribute("id");
        int cursoId = Integer.parseInt(request.getParameter("cursoId"));
        String valoracion = request.getParameter("valoracion");

        String accion = request.getParameter("accion");

        if ("favorito".equals(accion)) {
            boolean actual = DAOFactory.getCursoDAO().obtenerFavorito(estudianteId, cursoId);
            DAOFactory.getCursoDAO().marcarFavorito(estudianteId, cursoId, !actual);
        } else {
            if (valoracion != null && Integer.parseInt(valoracion) >= 1 && Integer.parseInt(valoracion) <= 5) {
                DAOFactory.getCursoDAO().valorarCurso(estudianteId, cursoId, Integer.parseInt(valoracion));
            }
        }

        response.sendRedirect(request.getContextPath() + "/estudiante/contenidos");
    }
}
