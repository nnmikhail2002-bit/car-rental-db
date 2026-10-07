package ru.misha.config;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final Dotenv dotenv = Dotenv.load();

    private static final String DB_NAME =
            dotenv.get("POSTGRES_DB");

    private static final String USER =
            dotenv.get("POSTGRES_USER");

    private static final String PASSWORD =
            dotenv.get("POSTGRES_PASSWORD");

    private static final String PORT =
            dotenv.get("POSTGRES_PORT");

    private static final String URL =
            "jdbc:postgresql://localhost:" + PORT + "/" + DB_NAME;

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                URL,
                USER,
                PASSWORD
        );
    }
}