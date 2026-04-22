package org.practica.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase curso, constructor, get y set
 */
public class Curso {
    private int id;
    private String titulo;
    private String descripcion;
    private int duracion;
    private int nivel;
    private int profesorID;
    private List<Contenido> contenidos = new ArrayList<>();
    private List<AreasInteres> areasInteres;   // cargado de areas_interes_curso

    //    private List<Contenido> contenidos;        // cargado de la tabla contenidos
    public Curso() {
    }

    public Curso(int id, String titulo, String descripcion, int duracion, int nivel, int profesorID) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.nivel = nivel;
        this.profesorID = profesorID;
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

    public int getProfesorID() {
        return profesorID;
    }

    public void setProfesorID(int profesorID) {
        this.profesorID = profesorID;
    }

    public List<AreasInteres> getAreasInteres() {
        return areasInteres;
    }

    public void setAreasInteres(List<AreasInteres> areasInteres) {
        this.areasInteres = areasInteres;
    }

    public List<Contenido> getContenidos() {
        return contenidos;
    }

    public void setContenidos(List<Contenido> contendios) {
        this.contenidos = contenidos;
    }
}
