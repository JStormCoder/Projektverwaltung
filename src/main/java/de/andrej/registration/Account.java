package de.andrej.registration;


import javafx.beans.property.SimpleStringProperty;

public class Account {
    private SimpleStringProperty name;
    private SimpleStringProperty vorname;
    private SimpleStringProperty email;
    private SimpleStringProperty username;
    private SimpleStringProperty password;
    private SimpleStringProperty passwordRepeat;

    public Account(String name, String vorname, String email, String username, String password, String passwordRepeat) {
        this.name = new SimpleStringProperty(name);
        this.vorname = new SimpleStringProperty(vorname);
        this.email = new SimpleStringProperty(email);
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.passwordRepeat = new SimpleStringProperty(passwordRepeat);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getVorname() {
        return vorname.get();
    }

    public SimpleStringProperty vornameProperty() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname.set(vorname);
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getUsername() {
        return username.get();
    }

    public SimpleStringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getPassword() {
        return password.get();
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getPasswordRepeat() {
        return passwordRepeat.get();
    }

    public SimpleStringProperty passwordRepeatProperty() {
        return passwordRepeat;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat.set(passwordRepeat);
    }
}
