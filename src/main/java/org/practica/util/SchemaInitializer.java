package org.practica.util;


import org.practica.conexion.Conexion;

import java.sql.Connection;
import java.sql.Statement;

public class SchemaInitializer {

public static void initialize(){
    try(Connection connection = Conexion.getConnection();
        Statement stmt = connection.createStatement()){

stmt.executeUpdate("""
CREATE TABLE IF NOT EXISTS admin (
    id SERIAL PRIMARY KEY,
    email VARCHAR(256) UNIQUE NOT NULL,
    password VARCHAR(256) NOT NULL,
    nombre VARCHAR(256) NOT NULL,
    apellidos VARCHAR(256) NOT NULL,
    rol VARCHAR(20) NOT NULL DEFAULT 'ADMIN'
) """);

System.out.println("✔ Esquema creado correctamente");


    }catch (Exception e){
        System.err.println("🎈🎈Error al crear esquema " + e.getMessage());
    }
}

}
