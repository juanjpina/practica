package org.practica.dao;

/**
 * Clase que devuelve el objeto de usuario para DAO
 */
public class DAOFactory {
    public static UsuarioDAO getUsuarioDAO() {
        return new UsuarioDAOImplt();
    }
}
