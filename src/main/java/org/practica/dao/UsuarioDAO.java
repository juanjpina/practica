package org.practica.dao;

import org.practica.model.Usuario;

import java.util.List;

/**
 * interface de usuario con los metodos para implementar DAO
 */
public interface UsuarioDAO {
    void insertar(Usuario usuario);

    Usuario buscarPorID(int id);

    Usuario buscarPorEmail(String email);

    List<Usuario> listarTodos();

    void actualizar(Usuario usuario);

    void eliminar(int id);

    List<Usuario> listarPorRol(String usuario);
}
