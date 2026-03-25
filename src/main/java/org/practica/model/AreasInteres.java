package org.practica.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AreasInteres {
    int id;
    String descripcion;

    public AreasInteres() {
    }

    public AreasInteres(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }


    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
