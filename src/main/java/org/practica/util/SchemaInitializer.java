package org.practica.util;


import org.practica.conexion.Conexion;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SchemaInitializer {

    public static void initialize(Connection con) throws SQLException {
        try (Statement stmt = con.createStatement()) {

            stmt.executeUpdate("""
                    CREATE TABLE IF NOT EXISTS usuarios (
                        id SERIAL PRIMARY KEY,
                        email VARCHAR(256) UNIQUE NOT NULL,
                        password VARCHAR(256) NOT NULL,
                        nombre VARCHAR(256) NOT NULL,
                        apellidos VARCHAR(256) NOT NULL,
                        rol VARCHAR(20) NOT NULL,
                        direccion     VARCHAR(255),
                        poblacion     VARCHAR(100),
                        provincia     VARCHAR(100),
                        codigo_postal VARCHAR(10)
                    ) """);


            stmt.executeUpdate("""
                    CREATE TABLE IF NOT EXISTS areas_interes(
                    id SERIAL PRIMARY KEY,
                    descripcion VARCHAR(100))
                    """);


            stmt.executeUpdate("""
                    CREATE TABLE IF NOT EXISTS cursos(
                    id SERIAL PRIMARY KEY,
                    titulo VARCHAR(100) NOT NULL,
                    descripcion VARCHAR(256) NOT NULL,
                    duracion INT,
                    nivel INT,
                    profesor_id INT REFERENCES usuarios(id) ON DELETE SET NULL)
                    """);

            stmt.executeUpdate("""
                    CREATE TABLE IF NOT EXISTS areas_interes_usuarios(
                    id SERIAL PRIMARY KEY,
                    usuario_id INT REFERENCES usuarios(id) ON DELETE SET NULL,
                    areas_id INT REFERENCES areas_interes(id) ON DELETE SET NULL)                                                 
                    """);

            stmt.executeUpdate("""
                    CREATE TABLE IF NOT EXISTS areas_interes_curso(
                    id SERIAL PRIMARY KEY,
                    curso_id INT REFERENCES cursos(id) ON DELETE SET NULL,
                    areas_id INT REFERENCES areas_interes(id) ON DELETE SET NULL)                                             
                    """);

            stmt.executeUpdate("""
                    CREATE TABLE IF NOT EXISTS contenidos(
                    id SERIAL PRIMARY KEY,
                    titulo varchar(100),
                    tipo varchar(100),
                    url varchar(256),
                    orden int,
                    fecha_inicio date,
                    fecha_fin date,
                    curso_id INT REFERENCES cursos(id) ON DELETE SET NULL)
                    
                    """);
            stmt.executeUpdate("""
                                        CREATE TABLE IF NOT EXISTS progreso_estudiante(
                                            id SERIAL PRIMARY KEY,
                                            id_estudiante INT REFERENCES usuarios(id) ON DELETE SET NULL,
                                            id_contenido INT REFERENCES contenidos(id) ON DELETE SET NULL,
                                            completado BOOL,
                                            fecha_acceso date)
                    """);

            stmt.executeUpdate("""
                    CREATE TABLE IF NOT EXISTS cursos_estudiante(
                        id SERIAL PRIMARY KEY,
                        fecha_inscripcion DATE,
                        valoracion INT,
                        favorito BOOL,
                        curso_id INT REFERENCES cursos(id) ON DELETE SET NULL,
                        estudiante_id INT REFERENCES usuarios(id) ON DELETE SET NULL 
                    )
                    """);

            System.out.println("✔ Esquema creado correctamente");


        } catch (Exception e) {
            System.err.println("🎈🎈Error al crear esquema " + e.getMessage());
        }
    }

}
