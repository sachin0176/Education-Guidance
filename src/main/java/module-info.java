module com.example.educationguidance {
    requires javafx.controls;
    requires javafx.fxml;
    requires de.jensd.fx.glyphs.fontawesome;
    requires java.sql;
    requires org.xerial.sqlitejdbc;


    opens com.example.educationguidance to javafx.fxml;
    exports com.example.educationguidance;
    exports com.example.educationguidance.controller;
    exports com.example.educationguidance.model;
    exports com.example.educationguidance.views;
    exports com.example.educationguidance.controller.user;
    exports com.example.educationguidance.controller.admin;

}