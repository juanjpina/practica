package org.practica.util;


import org.practica.conexion.Conexion;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SchemaInitializer {

public static void initialize(Connection con) throws SQLException {
    try(Statement stmt = con.createStatement()){

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
    codigo_postal VARCHAR(10),
    areas_interes TEXT
) """);

System.out.println("✔ Esquema creado correctamente");


    }catch (Exception e){
        System.err.println("🎈🎈Error al crear esquema " + e.getMessage());
    }
}

}
