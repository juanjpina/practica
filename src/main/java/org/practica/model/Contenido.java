package org.practica.model;

import java.time.LocalDate;

public class Contenido {
    private int id;
    private String titulo;
    private String tipo;
    private String url;
    private int orden;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public Contenido() {
    }

    public Contenido(int id, String titulo, String tipo, String url, int orden, LocalDate fechaInicio, LocalDate fechaFin) {
        this.id = id;
        this.titulo = titulo;
        this.tipo = tipo;
        this.url = url;
        this.orden = orden;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }
}
