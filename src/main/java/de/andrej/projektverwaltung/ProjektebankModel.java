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


            String nummer = resultSet.getString(1);
            String name = resultSet.getString(2);
            String projekt = nummer + "\t" + name;

            Projekte mainProjekt = new Projekte(projekt, "", "");


            String lp1 = resultSet.getString(3);
            Projekte leistung1 = new Projekte(lp1, "", "");


//            String lp2 = resultSet.getNString(4);
//            String lp3 = resultSet.getNString(5);
//            String lp4 = resultSet.getNString(6);
//            String lp5 = resultSet.getNString(7);
//            String lp6 = resultSet.getNString(8);


            list.addAll(mainProjekt, leistung1);
        }
        statement.close();

        return list;
    }
}
