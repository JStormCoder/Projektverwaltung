package de.andrej.projektverwaltung;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Projekte {
    private final StringProperty nummerProperty = new SimpleStringProperty();
    private final StringProperty nameProperty = new SimpleStringProperty();
    private final StringProperty statusProperty = new SimpleStringProperty();

    public Projekte(String nummer, String name, String status) {
        this.nummerProperty.set(nummer);
        this.nameProperty.set(name);
        this.statusProperty.set(status);
    }

    public String getName() {
        return nameProperty.get();
    }

    public StringProperty namePropertyProperty() {
        return nameProperty;
    }

    public String getNummer() {
        return nummerProperty.get();
    }

    public StringProperty nummerPropertyProperty() {
        return nummerProperty;
    }

    public String getStatus() {
        return statusProperty.get();
    }

    public StringProperty statusPropertyProperty() {
        return statusProperty;
    }

    public String getCombinedValue() {
        return nummerProperty.get() + "\t" + nameProperty.get();
    }

}
