package org.practica.dao;

import org.practica.model.AreasInteres;

import java.util.List;

/**
 * Clase que devuelve el objeto de usuario para DAO
 */
public class DAOFactory {
    public static UsuarioDAO getUsuarioDAO() {
        return new UsuarioDAOImplt();
    }
    public static AreasDeInteresDAO getAreasDeInteresDAO(){ return new AreasDeInteresImplt() ;}
    public static  CursoDAOImplt getCursoDAO(){return  new CursoDAOImplt();}

}
