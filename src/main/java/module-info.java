module br.edu.ifpi {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;

    opens br.edu.ifpi to javafx.fxml;
    exports br.edu.ifpi;
}