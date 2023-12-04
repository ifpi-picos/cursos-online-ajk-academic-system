module br.edu.ifpi {
    requires transitive javafx.controls;
    requires transitive java.sql;
    requires javafx.fxml;
    requires javafx.web;

    opens br.edu.ifpi.controllers to javafx.fxml;
    opens br.edu.ifpi.controllers.admin to javafx.fxml;
    opens br.edu.ifpi.controllers.student to javafx.fxml;
    opens br.edu.ifpi.controllers.teacher to javafx.fxml;

    exports br.edu.ifpi;
    exports br.edu.ifpi.controllers;
    exports br.edu.ifpi.entities;
    exports br.edu.ifpi.entities.enums;
    exports br.edu.ifpi.util;
    exports br.edu.ifpi.data.dao;
}