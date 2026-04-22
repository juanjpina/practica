package org.practica.dto;

/**
 * Clase cursoAnalisisDTO, constructor, get y set.
 */
public class CursoAnalisisDTO {
    private int cursoId;
    private String titulo;
    private int numInscritos;
    private double mediaValoracion;

    public CursoAnalisisDTO(int cursoId, String titulo, int numInscritos, double mediaValoracion) {
        this.cursoId = cursoId;
        this.titulo = titulo;
        this.numInscritos = numInscritos;
        this.mediaValoracion = mediaValoracion;
    }

    public int getCursoId() {
        return cursoId;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getNumInscritos() {
        return numInscritos;
    }

    public double getMediaValoracion() {
        return mediaValoracion;
    }
}
