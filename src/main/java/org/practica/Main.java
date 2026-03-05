package org.practica;


import org.practica.conexion.Conexion;

import java.sql.*;


public class Main {

    public static void main(String[] args) {

        try {
            Connection conexion = Conexion.getConnection();

            if (conexion != null) {
                System.out.println("✅ Conexión exitosa!");
                conexion.close();
            } else {
                System.out.println("❌ Conexión nula");
            }

        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
    }
}