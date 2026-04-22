package org.practica.model;

import java.time.LocalDate;

/**
 * Clase progreso constructor, get y set
 */
public class Progreso {
    private int id;
    private int idEstudiante;
    private int idContenido;
    private boolean completado;
    private LocalDate fechaAcceso;

    public Progreso() {
    }

    public Progreso(int id, int idEstudiante, int idContenido, boolean completado, LocalDate fechaAcceso) {
        this.id = id;
        this.idEstudiante = idEstudiante;
        this.idContenido = idContenido;
        this.completado = completado;
        this.fechaAcceso = fechaAcceso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public int getIdContenido() {
        return idContenido;
    }

    public void setIdContenido(int idContenido) {
        this.idContenido = idContenido;
    }

    public boolean isCompletado() {
        return completado;
    }

    public void setCompletado(boolean completado) {
        this.completado = completado;
    }

    public LocalDate getFechaAcceso() {
        return fechaAcceso;
    }

    public void setFechaAcceso(LocalDate fechaAcceso) {
        this.fechaAcceso = fechaAcceso;
    }
}
