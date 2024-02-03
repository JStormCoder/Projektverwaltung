package de.andrej.projektverwaltung;


import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;

import java.sql.ResultSet;
import java.sql.Connection;

import java.sql.Statement;

public class ProjektebankModel {
    private final String TABLE_NAME = "Projekte";

    private final String QUERY_DATA_FROM_PROJEKTE_TABLE = "SELECT * FROM " + TABLE_NAME;

    //Erstelle neues Projekt
    public void createProjekt(Statement statement, String nummer, String name) throws SQLException {
        statement.execute("INSERT INTO " + TABLE_NAME + " VALUES (" + nummer + ", " + name + "));");
        statement.close();
    }

    //Lade Projekt in die TreeTableView
//    private ObservableList<Projekte> loadProjekt() {
//
//    }




}
