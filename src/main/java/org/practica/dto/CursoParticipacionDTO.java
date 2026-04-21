package org.practica.dto;

public class CursoParticipacionDTO {
    private String titulo;
    private double mediaValoracion;
    private double progresoMedio;

    public CursoParticipacionDTO(String titulo, double mediaValoracion, double progresoMedio) {
        this.titulo = titulo;
        this.mediaValoracion = mediaValoracion;
        this.progresoMedio = progresoMedio;
    }

    public String getTitulo() { return titulo; }
    public double getMediaValoracion() { return mediaValoracion; }
    public double getProgresoMedio() { return progresoMedio; }
}
