package org.practica.model;

public class Admin extends Profesor{
    public Admin() {
    }

    public Admin(int id, String email, String password, String nombre, String apellidos) {
        super(id, email, password, nombre, apellidos);
    setRol("ADMIN");
    }


}
