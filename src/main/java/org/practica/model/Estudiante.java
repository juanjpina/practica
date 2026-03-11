package org.practica.model;

/**
 * Clase Estudiante
 */
public class Estudiante extends Usuario {
    private String direccion;
    private String poblacion;
    private String provincia;
    private String codigoPostal;
    private String areasInteres;

    public Estudiante() {
    }

    ;

    public Estudiante(int id, String email, String password, String nombre, String apellidos) {
        super(id, email, password, nombre, apellidos, "ESTUDIANTE");
    }

    ;


    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getProvincia() {
        return this.provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCodigoPostal() {
        return this.codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getAreasInteres() {
        return this.areasInteres;
    }

    public void setAreasInteres(String areasInteres) {
        this.areasInteres = areasInteres;
    }
}
