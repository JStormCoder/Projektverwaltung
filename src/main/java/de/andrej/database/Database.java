package de.andrej.database;

import java.sql.*;


public class Database {

    public static final String DB_NAME = "projektverwaltung.sqlite";
    public static final String PATH = "D:\\Andrej\\Projekte\\JavaProjekte\\Projektverwaltung\\";
    public static final String CONNECTION_STRING = "jdbc:sqlite:" + PATH + DB_NAME;

    private Connection connection;

    private Statement statement;

    public boolean open(){
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
