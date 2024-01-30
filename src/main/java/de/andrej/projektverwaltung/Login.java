package de.andrej.projektverwaltung;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Login extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("projekterstellen.fxml"));
//        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("login.fxml"));
        Scene scene = new Scene(root);
//        scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());

//        primaryStage.initStyle(StageStyle.UNDECORATED);
//        primaryStage.setTitle("Projektverwaltung");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}