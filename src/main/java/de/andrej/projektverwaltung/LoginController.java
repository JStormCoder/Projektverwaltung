package de.andrej.projektverwaltung;

import de.andrej.database.Database;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private TextField userNameTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Button okButton;

    @FXML
    private Button registrierenButton;

    @FXML
    private Circle dbStatusAnzeige;


    @FXML
    private Button beendenButton;

    //Datenbankobjekte
    Database database = new Database();
    LoginModel loginModel = new LoginModel();

    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    @FXML
    void beendeAnwendung(ActionEvent event) throws SQLException {
        Platform.exit();
        database.getConnection().close();
    }
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@


    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    @FXML
    void buttonProperty(KeyEvent event) {
        String uString = userNameTextField.getText();
        String pwString = passwordTextField.getText();

        boolean isDisabled = (uString.isEmpty() || uString.trim().isEmpty() || uString.length() < 4) ||
                (pwString.isEmpty() || pwString.trim().isEmpty() || pwString.length() < 4);

        okButton.setDisable(isDisabled);
    }
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@


    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        okButton.setDisable(true);

        boolean isConnected = database.open();
        if (isConnected) {
            dbStatusAnzeige.setFill(Color.GREEN);
//            System.out.println("Verbindung ist aufgebaut.");

        } else {
            dbStatusAnzeige.setFill(Color.RED);
//            System.out.println("Keine Verbindung aufgebaut.");
        }
    }
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@


    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    //Öffnet die Registration, wenn noch keine Anmeldedaten vorhanden sind. Bei der ersten Verwendung
    @FXML
    void registrationOpen(ActionEvent event) {
        try {
            Stage registrationStage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader();

            Pane root = FXMLLoader.load(getClass().getResource("/registration/registration.fxml"));

            Scene scene = new Scene(root);

            registrationStage.setScene(scene);
            registrationStage.setTitle("Registration");
            registrationStage.setResizable(false);
            registrationStage.show();

            loginStageClose();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@


    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    //Nach erfolgreichem Login, soll die Maske mit den Projekten geöffnet werden
    @FXML
    void oeffneProjekte(ActionEvent event) {
        String username = userNameTextField.getText();
        String password = passwordTextField.getText();

        Connection con = database.getConnection();
        try {
            if (loginModel.isLogin(username, password, con)) {
                Stage stage = (Stage) okButton.getScene().getWindow();
                stage.close();

                successLogin();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@


    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    public void loginStageClose() {
        Stage loginStage = (Stage) registrierenButton.getScene().getWindow();
        loginStage.close();
    }
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@


    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    public void successLogin() throws IOException {

        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();

            Parent root = FXMLLoader.load(getClass().getResource("projekterstellen.fxml"));

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.setTitle("Projekte");
            stage.show();

            database.getConnection().close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@


}
