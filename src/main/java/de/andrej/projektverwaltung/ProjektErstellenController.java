package de.andrej.projektverwaltung;

import de.andrej.database.Database;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProjektErstellenController implements Initializable {


    private final ProjektebankModel projekteModel = new ProjektebankModel();
    private final Database database = new Database();

    @FXML
    private TextField projektNummer_textField;

    @FXML
    private TextField projektBezeichnung_Textfield;

    @FXML
    private CheckBox cB_LP1;

    @FXML
    private CheckBox cB_LP2;

    @FXML
    private CheckBox cB_LP3;

    @FXML
    private CheckBox cB_LP4;

    @FXML
    private CheckBox cB_LP5;

    @FXML
    private CheckBox cB_LP6;

    @FXML
    private TextField zusLeistung_Textfield_1;

    @FXML
    private TextField zusLeistung_Textfield_2;

    @FXML
    private TextField zusLeistung_Textfield_3;

    @FXML
    private TextField zusLeistung_Textfield_4;

    @FXML
    private TextField zusLeistung_Textfield_5;

    @FXML
    private TextField zusLeistung_Textfield_6;

    @FXML
    private Button button_Speichern;

    @FXML
    private Button button_Loeschen;

    @FXML
    private Button button_Projektuebersicht;

    @FXML
    private TreeTableView<Projekte> treeTableView;

    @FXML
    private TreeTableColumn<Projekte, String> projekt_column;

    @FXML
    private TreeTableColumn<Status, String> stand_column;
    @FXML
    private Circle connectionView;

    private TreeItem<Projekte> rootItem;
    private final String status = "0%";

    private ObservableList<Projekte> vorhandeneProjekte;


    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    public void createProject() {
        String nummer = projektNummer_textField.getText();
        String bezeichnung = projektBezeichnung_Textfield.getText();

        String lp1 = "";
        String lp2 = "";
        String lp3 = "";
        String lp4 = "";
        String lp5 = "";
        String lp6 = "";

        if (cB_LP1.isSelected()) {
            lp1 = "LP1";
        }
        if (cB_LP2.isSelected()) {
            lp2 = "LP2";
        }
        if (cB_LP3.isSelected()) {
            lp3 = "LP3";
        }
        if (cB_LP4.isSelected()) {
            lp4 = "LP4";
        }
        if (cB_LP5.isSelected()) {
            lp5 = "LP5";
        }
        if (cB_LP6.isSelected()) {
            lp6 = "LP6";
        }

        try {
            projekteModel.createProjekt(database.getStatement(), nummer, bezeichnung, lp1, lp2, lp3, lp4, lp5, lp6);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        boolean dbConnection = database.open();

        if (dbConnection) {
            System.out.println("Verbindung zur Datenbank vorhanden");
            connectionView.setFill(Color.GREEN);
        } else {
            System.out.println("Keine Verbindung");
            connectionView.setFill(Color.RED);
        }

        rootItem = new TreeItem<>(new Projekte("Daten", "", ""));
        treeTableView.setRoot(rootItem);

        treeTableView.setShowRoot(false);
        loadProjekt();

        projekt_column.setCellValueFactory(new TreeItemPropertyValueFactory<>("CombinedValue"));    //Der angegebene Name hier muss mit der Klasse "Projekte" ung get übereinstimmen
        stand_column.setCellValueFactory(new TreeItemPropertyValueFactory<>("Status"));


        speichern();


    }
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    @FXML
    private void speichern() {
        button_Speichern.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                String nummer = projektNummer_textField.getText();
                String name = projektBezeichnung_Textfield.getText();

                Projekte neuesProjekt = new Projekte(nummer, name, "");
                TreeItem<Projekte> newProjektItem = new TreeItem<>(neuesProjekt);
                newProjektItem.setExpanded(true);

                // Füge das neue Projekt-Item zur Wurzel der TreeTableView hinzu
                rootItem.getChildren().add(newProjektItem);

                // Leistungen als TreeItem dem neuen TreeItem hinzufügen
                // ausgewählte Checkboxen in die TreeTableView einfügen
                if (cB_LP1.isSelected()) {
                    String leistung1 = cB_LP1.getText();
                    Projekte neueLeistung1 = new Projekte(leistung1, "", status);
                    TreeItem<Projekte> leistungLP1 = new TreeItem<>(neueLeistung1);
                    newProjektItem.getChildren().add(leistungLP1);
                }
                if (cB_LP2.isSelected()) {
                    String leistung2 = cB_LP2.getText();
                    Projekte neueLeistung2 = new Projekte(leistung2, "", status);
                    TreeItem<Projekte> leistungLP2 = new TreeItem<>(neueLeistung2);
                    newProjektItem.getChildren().add(leistungLP2);
                }
                if (cB_LP3.isSelected()) {
                    String leistung3 = cB_LP3.getText();
                    Projekte neueLeistung3 = new Projekte(leistung3, "", status);
                    TreeItem<Projekte> leistungLP3 = new TreeItem<>(neueLeistung3);
                    newProjektItem.getChildren().add(leistungLP3);
                }
                if (cB_LP4.isSelected()) {
                    String leistung4 = cB_LP4.getText();
                    Projekte neueLeistung4 = new Projekte(leistung4, "", status);
                    TreeItem<Projekte> leistungLP4 = new TreeItem<>(neueLeistung4);
                    newProjektItem.getChildren().add(leistungLP4);
                }
                if (cB_LP5.isSelected()) {
                    String leistung5 = cB_LP5.getText();
                    Projekte neueLeistung5 = new Projekte(leistung5, "", status);
                    TreeItem<Projekte> leistungLP5 = new TreeItem<>(neueLeistung5);
                    newProjektItem.getChildren().add(leistungLP5);
                }
                if (cB_LP6.isSelected()) {
                    String leistung6 = cB_LP6.getText();
                    Projekte neueLeistung6 = new Projekte(leistung6, "", status);
                    TreeItem<Projekte> leistungLP6 = new TreeItem<>(neueLeistung6);
                    newProjektItem.getChildren().add(leistungLP6);
                }

                // zusätzliche Leistungen in die TreeTableView einfügen
                if (!zusLeistung_Textfield_1.getText().isEmpty()) {
                    String zusLeistung1 = zusLeistung_Textfield_1.getText();
                    Projekte neueZusLeistung1 = new Projekte(zusLeistung1, "", status);
                    TreeItem<Projekte> zL1 = new TreeItem<>(neueZusLeistung1);
                    newProjektItem.getChildren().add(zL1);
                }
                if (!zusLeistung_Textfield_2.getText().isEmpty()) {
                    String zusLeistung2 = zusLeistung_Textfield_2.getText();
                    Projekte neueZusLeistung2 = new Projekte(zusLeistung2, "", status);
                    TreeItem<Projekte> zL2 = new TreeItem<>(neueZusLeistung2);
                    newProjektItem.getChildren().add(zL2);
                }
                if (!zusLeistung_Textfield_3.getText().isEmpty()) {
                    String zusLeistung3 = zusLeistung_Textfield_3.getText();
                    Projekte neueZusLeistung3 = new Projekte(zusLeistung3, "", status);
                    TreeItem<Projekte> zL3 = new TreeItem<>(neueZusLeistung3);
                    newProjektItem.getChildren().add(zL3);
                }
                if (!zusLeistung_Textfield_4.getText().isEmpty()) {
                    String zusLeistung4 = zusLeistung_Textfield_4.getText();
                    Projekte neueZusLeistung4 = new Projekte(zusLeistung4, "", status);
                    TreeItem<Projekte> zL4 = new TreeItem<>(neueZusLeistung4);
                    newProjektItem.getChildren().add(zL4);
                }
                if (!zusLeistung_Textfield_5.getText().isEmpty()) {
                    String zusLeistung5 = zusLeistung_Textfield_5.getText();
                    Projekte neueZusLeistung5 = new Projekte(zusLeistung5, "", status);
                    TreeItem<Projekte> zL5 = new TreeItem<>(neueZusLeistung5);
                    newProjektItem.getChildren().add(zL5);
                }
                if (!zusLeistung_Textfield_6.getText().isEmpty()) {
                    String zusLeistung6 = zusLeistung_Textfield_6.getText();
                    Projekte neueZusLeistung6 = new Projekte(zusLeistung6, "", status);
                    TreeItem<Projekte> zL6 = new TreeItem<>(neueZusLeistung6);
                    newProjektItem.getChildren().add(zL6);
                }

                createProject();


                // Leere die Eingabefelder um zu signalisieren, dass das Projekt gespeichert worden ist
                projektNummer_textField.clear();
                projektBezeichnung_Textfield.clear();
                cB_LP1.setSelected(false);
                cB_LP2.setSelected(false);
                cB_LP3.setSelected(false);
                cB_LP4.setSelected(false);
                cB_LP5.setSelected(false);
                cB_LP6.setSelected(false);
                zusLeistung_Textfield_1.clear();
                zusLeistung_Textfield_2.clear();
                zusLeistung_Textfield_3.clear();
                zusLeistung_Textfield_4.clear();
                zusLeistung_Textfield_5.clear();
                zusLeistung_Textfield_6.clear();

            }
        });
    }
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

    public void loadProjekt() {
        try {
            vorhandeneProjekte = projekteModel.loadProject(database.getStatement(), vorhandeneProjekte);


        } catch (SQLException e) {
            e.printStackTrace();
        }

        projekt_column.setCellValueFactory(new TreeItemPropertyValueFactory<>("CombinedValue"));
        stand_column.setCellValueFactory(new TreeItemPropertyValueFactory<>("Status"));

        TreeItem<Projekte> mainProject = new TreeItem<>();

        for (Projekte data : vorhandeneProjekte) {

            String kombiValue = data.getCombinedValue().trim();
            if (kombiValue.isEmpty() || kombiValue.equals("null")) {
                continue;
            }

            if (!data.getCombinedValue().trim().equals("LP1") &&
                    !data.getCombinedValue().trim().equals("LP2") &&
                    !data.getCombinedValue().trim().equals("LP3") &&
                    !data.getCombinedValue().trim().equals("LP4") &&
                    !data.getCombinedValue().trim().equals("LP5") &&
                    !data.getCombinedValue().trim().equals("LP6")) {
                mainProject = new TreeItem<>(data);
                rootItem.getChildren().add(mainProject);
                continue;
            } else if (data.getCombinedValue().trim().equals("LP1") ||
                    data.getCombinedValue().trim().equals("LP2") ||
                    data.getCombinedValue().trim().equals("LP3") ||
                    data.getCombinedValue().trim().equals("LP4") ||
                    data.getCombinedValue().trim().equals("LP5") ||
                    data.getCombinedValue().trim().equals("LP6")) {
                TreeItem<Projekte> subProject = new TreeItem<>(data);
                mainProject.getChildren().add(subProject);
            }
            mainProject.setExpanded(true);


        }
    }
}




