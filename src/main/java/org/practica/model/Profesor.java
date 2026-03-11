package org.practica.model;

/**
 * clase Profesor
 */
public class Profesor extends Usuario {
    public Profesor() {
    }

    public Profesor(int id, String email, String password, String nombre, String apellidos) {
        super(id, email, password, nombre, apellidos, "PROFESOR");
    }

}
