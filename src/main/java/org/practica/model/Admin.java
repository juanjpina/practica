package org.practica.model;

/**
 * clase administrador
 */
public class Admin extends Usuario {
    public Admin() {
    }

    public Admin(int id, String email, String password, String nombre, String apellidos) {
        super(id, email, password, nombre, apellidos,"ADMIN");

    }

}
