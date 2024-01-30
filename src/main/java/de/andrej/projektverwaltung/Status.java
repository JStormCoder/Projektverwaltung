package de.andrej.projektverwaltung;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Status {
    private final StringProperty status = new SimpleStringProperty();

    public Status(String leistungsstand) {
        this.status.set(leistungsstand);
    }

    public String getStatus() {
        return status.get();
    }

    public StringProperty statusProperty() {
        return status;
    }
}
