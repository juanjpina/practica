package org.practica.conexion;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class Conexion {
    private static final String HOST     = System.getenv().getOrDefault("DB_HOST", "localhost");
    private static final String PORT     = System.getenv().getOrDefault("DB_PORT", "5432");
    private static final String DATABASE = System.getenv().getOrDefault("DB_NAME", "practica");
    private static final String USER     = System.getenv().getOrDefault("DB_USER", "practica");
    private static final String PASSWORD = System.getenv().getOrDefault("DB_PASS", "practica");

    private static final String URL = String.format(
            "jdbc:postgresql://%s:%s/%s", HOST, PORT, DATABASE
    );

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver PostgreSQL no encontrado", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}