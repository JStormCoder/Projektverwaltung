package de.andrej.registration;

import de.andrej.database.Database;
import de.andrej.projektverwaltung.Login;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RegistrationController implements Initializable {

    //Variablen
    private RegistrationModel registrationModel = new RegistrationModel();
    private Database database = new Database();

    //Tabelle
    private ObservableList<Account> accounts;

    @FXML
    private TableView<Account> accountTableView;

    @FXML
    private TableColumn<Account, String> nameColumn;
    @FXML
    private TableColumn<Account, String> vornameColumn;
    @FXML
    private TableColumn<Account, String> emailColumn;
    @FXML
    private TableColumn<Account, String> userNameColumn;
    @FXML
    private TableColumn<Account, String> passwordColumn;
    @FXML
    private TableColumn<Account, String> passwordRepeatColumn;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField vornameTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private PasswordField pwWiederholenTextField;

    @FXML
    private Circle registrationStatus;

    @FXML
    private Button erstelleAccountButton;

    @FXML
    private Button cancelButton;

    public RegistrationController() {
    }

    @FXML
    public void createAccount(ActionEvent event) {
        createAccount();
        loadAccount();

    }

    // Erstellen von Accounts
    public void createAccount() {
        String name = nameTextField.getText();
        String vorname = vornameTextField.getText();
        String email = emailTextField.getText();
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        String passwordRepeat = pwWiederholenTextField.getText();

        try {
            registrationModel.createAccount(database.getStatement(), name, vorname, email, username, password, passwordRepeat);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        clearAll();

    }

    // Accounts laden
    public void loadAccount() {
        try {
            accounts = registrationModel.loadAccount(database.getStatement(), accounts);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        nameColumn.setCellValueFactory(new PropertyValueFactory<Account, String>("name"));
        vornameColumn.setCellValueFactory(new PropertyValueFactory<Account, String>("vorname"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<Account, String>("email"));
        userNameColumn.setCellValueFactory(new PropertyValueFactory<Account, String>("username"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<Account, String>("password"));
        passwordRepeatColumn.setCellValueFactory(new PropertyValueFactory<Account, String>("passwordRepeat"));

        accountTableView.setItems(accounts);
    }

    @FXML
    void closeRegistration(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(Login.class.getResource("login.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();


    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        boolean dbConnection = database.open();

        if (dbConnection) {
            registrationStatus.setFill(Color.GREEN);
            loadAccount();
        } else {
            registrationStatus.setFill(Color.RED);
        }

    }

    public void clearAll() {
        nameTextField.clear();
        vornameTextField.clear();
        emailTextField.clear();
        usernameTextField.clear();
        passwordTextField.clear();
        pwWiederholenTextField.clear();

    }

}
