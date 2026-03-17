package org.practica.dao;

import org.practica.conexion.Conexion;
import org.practica.model.Admin;
import org.practica.model.Estudiante;
import org.practica.model.Profesor;
import org.practica.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que implementa al usuario para DAO
 */
public class UsuarioDAOImplt implements UsuarioDAO {
    /**
     *
     * @param rs
     * @return estudiante o admin o profesor
     * @throws SQLException
     */
    private Usuario construirUsuario(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String email = rs.getString("email");
        String password = rs.getString("password");
        String nombre = rs.getString("nombre");
        String apellidos = rs.getString("apellidos");
        String rol = rs.getString("rol");

        switch (rol) {
            case "ESTUDIANTE":
                Estudiante e = new Estudiante(id, email, password, nombre, apellidos);
                e.setDireccion(rs.getString("direccion"));
                e.setPoblacion(rs.getString("poblacion"));
                e.setProvincia(rs.getString("provincia"));
                e.setCodigoPostal(rs.getString("codigo_postal"));
                e.setAreasInteres(rs.getString("areas_interes"));
                return e;
            case "PROFESOR":
                return new Profesor(id, email, password, nombre, apellidos);
            case "ADMIN":
                return new Admin(id, email, password, nombre, apellidos);
            default:
                throw new SQLException("Rol desconocido" + rol);
        }

    }

    /**
     * Insertar en tabla datos usuario
     *
     * @param usuario
     */
    @Override
    public void insertar(Usuario usuario) {
        String sql = """
                INSERT INTO usuarios 
                (email, password, nombre,apellidos, rol, direccion, poblacion, provincia, codigo_postal, areas_interes)
                VALUES(?,?,?,?,?,?,?,?,?,?)
                """;
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, usuario.getEmail());
            ps.setString(2, usuario.getPassword());
            ps.setString(3, usuario.getNombre());
            ps.setString(4, usuario.getApellidos());
            ps.setString(5, usuario.getRol());

            // Campos específicos de Estudiante, null para el resto
            if (usuario instanceof Estudiante) {
                Estudiante e = (Estudiante) usuario;
                ps.setString(6, e.getDireccion());
                ps.setString(7, e.getPoblacion());
                ps.setString(8, e.getProvincia());
                ps.setString(9, e.getCodigoPostal());
                ps.setString(10, e.getAreasInteres());
            } else {
                ps.setNull(6, Types.VARCHAR);
                ps.setNull(7, Types.VARCHAR);
                ps.setNull(8, Types.VARCHAR);
                ps.setNull(9, Types.VARCHAR);
                ps.setNull(10, Types.VARCHAR);
            }

            ps.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    /**
     * Buscar usuario por ID
     *
     * @param id
     * @return usuario
     */
    @Override
    public Usuario buscarPorID(int id) {
        String sql = "SELECT * FROM usuarios WHERE id=?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return construirUsuario(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar usuario ", e);
        }
        return null;
    }

    /**
     * busca usuario por email
     *
     * @param email
     * @return usuario
     */
    @Override
    public Usuario buscarPorEmail(String email) {
        String sql = "SELECT * FROM usuarios WHERE email=?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return construirUsuario(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar usuario", e);
        }
        return null;
    }

    /**
     * devuelve todos los estudiantes, proferores  o admin.
     *
     * @return usuario
     */
    @Override
    public List<Usuario> listarTodos() {
        String sql = "SELECT * FROM usuarios";
        List<Usuario> usuarios = new ArrayList<>();
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("Ejecutando SELECT..."); // debug

            while (rs.next()) {
                System.out.println("Fila encontrada, rol: " + rs.getString("rol")); // debug
                usuarios.add(construirUsuario(rs));
            }

            System.out.println("Total leídos: " + usuarios.size()); // debug

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return usuarios;
    }

    @Override
    public void actualizar(Usuario usuario) {
        String sql="UPDATE usuarios SET email=?, password=?, nombre=?,apellidos=?, rol=?, direccion=?, poblacion=?, provincia=?, codigo_postal=?, areas_interes=? WHERE id=? ";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, usuario.getEmail());
            ps.setString(2, usuario.getPassword());
            ps.setString(3, usuario.getNombre());
            ps.setString(4, usuario.getApellidos());
            ps.setString(5, usuario.getRol());


            // Campos específicos de Estudiante, null para el resto
            if (usuario instanceof Estudiante) {
                Estudiante e = (Estudiante) usuario;
                ps.setString(6, e.getDireccion());
                ps.setString(7, e.getPoblacion());
                ps.setString(8, e.getProvincia());
                ps.setString(9, e.getCodigoPostal());
                ps.setString(10, e.getAreasInteres());
            } else {
                ps.setNull(6, Types.VARCHAR);
                ps.setNull(7, Types.VARCHAR);
                ps.setNull(8, Types.VARCHAR);
                ps.setNull(9, Types.VARCHAR);
                ps.setNull(10, Types.VARCHAR);
            }
            ps.setInt(11, usuario.getId());
            ps.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }


    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM usuarios WHERE id=?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, id);
            ps.executeUpdate();


        }catch (SQLException e) {
            e.printStackTrace(System.out);


    }
}
}