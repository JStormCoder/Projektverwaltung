module de.andrej.projektverwaltung {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;

    opens de.andrej.projektverwaltung to javafx.fxml;
    exports de.andrej.projektverwaltung;
    opens de.andrej.registration to javafx.fxml;
    exports de.andrej.registration;

}