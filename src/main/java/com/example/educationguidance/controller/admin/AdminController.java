package com.example.educationguidance.controller.admin;

import com.example.educationguidance.model.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

public BorderPane admin_parent;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().addListener((observable, oldValue, newValue) -> {
            switch(newValue) {
                case ALL_USER -> admin_parent.setCenter(Model.getInstance().getViewFactory().showAllUsers());
                case UPDATE -> admin_parent.setCenter(Model.getInstance().getViewFactory().showUpdateDetails());
                case FEEDBACK -> admin_parent.setCenter(Model.getInstance().getViewFactory().showUsersFeedbackWindow());
                default -> admin_parent.setCenter(Model.getInstance().getViewFactory().showLatestUsers());

            }
        });
    }
}
