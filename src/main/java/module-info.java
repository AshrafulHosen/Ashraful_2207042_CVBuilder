module com.example.cv {
    // JavaFX modules needed for your UI
    requires javafx.controls;
    requires javafx.fxml;

    // Database modules needed for SQLite
    requires java.sql;
    requires org.xerial.sqlitejdbc;

    // Permissions to open your code to JavaFX
    opens com.example.cv to javafx.fxml;
    exports com.example.cv;
}