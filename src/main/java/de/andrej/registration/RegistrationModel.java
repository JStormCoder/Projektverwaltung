package de.andrej.registration;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class RegistrationModel {

    private final String TABLE_NAME = "accounts";

    private final String QUERY_DATA_FROM_ACCOUNTS_TABLE = "SELECT * FROM " + TABLE_NAME;

    //Erstelle Account
    public void createAccount(Statement statement, String nString, String vString, String eString, String uString, String pString, String prString) throws SQLException {

        statement.execute("INSERT INTO " + TABLE_NAME + " VALUES " + "('" + nString + "', '" + vString + "', '" + eString + "', '" + uString + "', '" + pString + "', '" + prString + "')");
        statement.close();
    }

    //Lade Account
    public ObservableList<Account> loadAccount(Statement statement, ObservableList<Account> list) throws SQLException {
        list = FXCollections.observableArrayList();

        ResultSet resultSet = statement.executeQuery(QUERY_DATA_FROM_ACCOUNTS_TABLE);

        while (resultSet.next()) {
            String name = resultSet.getString(1);
            String vorname = resultSet.getString(2);
            String email = resultSet.getString(3);
            String username = resultSet.getString(4);
            String password = resultSet.getString(5);
            String passwordRepeat = resultSet.getString(6);

            list.add(new Account(name, vorname, email, username, password, passwordRepeat));
        }

        statement.close();

        return list;
    }


}
