module br.edu.ifpi {
    requires transitive javafx.controls;
    requires transitive java.sql;
    requires javafx.fxml;
    requires javafx.web;

    opens br.edu.ifpi to javafx.fxml;
    opens br.edu.ifpi.controllers to javafx.fxml;

    exports br.edu.ifpi;
    exports br.edu.ifpi.controllers;
}