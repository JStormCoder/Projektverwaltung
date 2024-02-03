package de.andrej.database;

import java.sql.*;


public class Database {

    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    //Datenbank "Projektverwaltung". Verbindungsaufbau
    public static final String DB_NAME = "projektverwaltung.sqlite";
    public static final String PATH = "D:\\Andrej\\Projekte\\JavaProjekte\\Projektverwaltung\\";
    public static final String CONNECTION_STRING = "jdbc:sqlite:" + PATH + DB_NAME;

    private Connection connection;
    private Connection connection2;

    private Statement statement;
    private Statement statement2;

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
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@


    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    //Datenbank "Projekte". Verbindungsaufbau
    private static final String DB_NAME2 = "Projektebank.sqlite";
    private static final String PATH2 = "D:\\Andrej\\Projekte\\JavaProjekte\\Projektverwaltung\\";
    private static final String CONNECTION_STRING2 = "jdbc:sqlite:" + PATH2 + DB_NAME2;

    public boolean openProjektebank() {
        try {
            connection2 = DriverManager.getConnection(CONNECTION_STRING2);
            statement = connection2.createStatement();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public Connection getConnection2() {
        return connection2;
    }

    public Statement getStatement2() {
        return statement2;
    }
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
}
