package de.andrej.projektverwaltung;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProjektebankModel {
    private final String TABLE_NAME = "Projekte";

    private final String QUERY_DATA_FROM_PROJEKTE_TABLE = "SELECT * FROM " + TABLE_NAME;

    public PreparedStatement preparedStatement;

    public ProjektebankModel() {
    }

    //Erstelle neues Projekt
    public void createProjekt(Statement statement, String nummer, String name, String lp1, String lp2, String lp3, String lp4, String lp5, String lp6) throws SQLException {
        statement.execute("INSERT INTO " + TABLE_NAME + " VALUES ('" + nummer + "', '" + name + "', '" + lp1 + "', '" + lp2 + "', '" + lp3 + "', '" + lp4 + "', '" + lp5 + "', '" + lp6 + "')");
        statement.close();
    }

    //Lade Projekt in die TreeTableView
    public ObservableList<Projekte> loadProject(Statement statement, ObservableList<Projekte> list) throws SQLException {
        list = FXCollections.observableArrayList();
        ResultSet resultSet = statement.executeQuery(QUERY_DATA_FROM_PROJEKTE_TABLE);


        while (resultSet.next()) {

            String status = "0%";

            String nummer = resultSet.getString(1);
            String name = resultSet.getString(2);
            String projekt = nummer + "\t" + name;
            Projekte mainProjekt = new Projekte(projekt, "", "");

            String lp1 = resultSet.getString(3);
            Projekte leistung1 = new Projekte(lp1, "", status);

            String lp2 = resultSet.getString(4);
            Projekte leistung2 = new Projekte(lp2, "", status);

            String lp3 = resultSet.getString(5);
            Projekte leistung3 = new Projekte(lp3, "", status);

            String lp4 = resultSet.getString(6);
            Projekte leistung4 = new Projekte(lp4, "", status);

            String lp5 = resultSet.getString(7);
            Projekte leistung5 = new Projekte(lp5, "", status);

            String lp6 = resultSet.getString(8);
            Projekte leistung6 = new Projekte(lp6, "", status);

            list.addAll(mainProjekt, leistung1, leistung2, leistung3, leistung4, leistung5, leistung6);
        }
        statement.close();

        return list;
    }
}
