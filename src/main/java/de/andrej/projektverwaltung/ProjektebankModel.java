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


//            String lp1 = resultSet.getString(3);
//            String lp2 = resultSet.getString(4);
//            String lp3 = resultSet.getString(5);
//            String lp4 = resultSet.getString(6);
//            String lp5 = resultSet.getString(7);
//            String lp6 = resultSet.getString(8);

            list.add(new Projekte(projekt, "", ""));


//            TreeTableView tableView = new TreeTableView();
//            TreeItem<Projekte> root = new TreeItem<>(new Projekte("Daten", "", ""));
//
//            tableView.setRoot(root);
//            tableView.setShowRoot(false);
//
//            Projekte neuesProjekt = new Projekte(nummer, name, "");
//            TreeItem<Projekte> neuesProjektItem = new TreeItem<>(neuesProjekt);
//
//            root.getChildren().add(neuesProjektItem);
//
//            Projekte neueLP1 = new Projekte("", lp1, status);
//            TreeItem<Projekte> neueLp1 = new TreeItem<Projekte>(neueLP1);
//            neuesProjektItem.getChildren().add(neueLp1);

        }
        statement.close();


        return list;
    }


}
