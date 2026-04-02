package org.practica.dto;

import org.practica.model.Curso;

public class CursoDTO {
    private Curso curso;
    private String nombreProfesor;
    private int id;
    private String titulo;
    private String descripcion;
    private int duracion;
    private int nivel;

    public CursoDTO(Curso curso, String nombreProfesor) {
        this.id = curso.getId();
        this.titulo = curso.getTitulo();
        this.descripcion = curso.getDescripcion();
        this.duracion = curso.getDuracion();
        this.nivel = curso.getNivel();
        this.nombreProfesor = nombreProfesor;
    }



    public String getNombreProfesor() {
        return nombreProfesor;
    }

    public void setNombreProfesor(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
}
