package de.andrej.database;

import java.sql.*;

public class DB_Projektbank {

    public static final String DB_NAME = "Projektebank.sqlite";
    public static final String DB_PATH = "D:\\Andrej\\Projekte\\JavaProjekte\\Projektverwaltung\\";
    public static final String CONNECTION_STRING = "jdbc:sqlite:" + DB_PATH + DB_NAME;

    private Connection connection;
    private Statement statement;

    public boolean open() {
        try {
            connection = DriverManager.getConnection(CONNECTION_STRING);
            statement = connection.createStatement();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        return statement;
    }
}
