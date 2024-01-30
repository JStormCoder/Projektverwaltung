package de.andrej.projektverwaltung;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Projekte {
    private final StringProperty nummerProperty = new SimpleStringProperty();
    private final StringProperty nameProperty = new SimpleStringProperty();

    public Projekte(String nummer, String name) {
        this.nummerProperty.set(nummer);
        this.nameProperty.set(name);
    }

    public StringProperty nummerPropertyProperty() {
        return nummerProperty;
    }

    public StringProperty namePropertyProperty() {
        return nameProperty;
    }

    public String getCombinedValue() {
        return nummerProperty.get() + "\t" + nameProperty.get();
    }

}
