package de.andrej.projektverwaltung;

import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ProjektebankModel {
    private final String TABLE_NAME = "Projekte";

    private final String QUERY_DATA_FROM_PROJEKTE_TABLE = "SELECT * FROM " + TABLE_NAME;

    //Erstelle neues Projekt
    public void createProjekt(Statement statement, String nummer, String name, String lp1, String lp2, String lp3, String lp4, String lp5, String lp6) throws SQLException {
        statement.execute("INSERT INTO " + TABLE_NAME + " VALUES (" + nummer + ", " + name + ", " + lp1 + ", " + lp2 + ", " + lp3 + ", " + lp4 + ", " + lp5 + ", " + lp6 + "));");
        statement.close();
    }

    //Lade Projekt in die TreeTableView
//    private ObservableList<Projekte> loadProjekt() {
//
//    }



}
