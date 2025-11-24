module com.example.cv {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;
    requires org.xerial.sqlitejdbc;

    opens com.example.cv to javafx.fxml;
    exports com.example.cv;
}