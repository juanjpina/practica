package org.practica.dao;

public class DAOFactory {
    public static UsuarioDAO getUsuarioDAO(){
        return new UsuarioDAOImplt();
    }
}
