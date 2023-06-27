package com.example.educationguidance.controller.admin;

import com.example.educationguidance.model.User;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class UserCellController implements Initializable {
    public Label first_name_lbl;
    public Label last_name_lbl;
    public Label user_name_lbl;
    public Label date_lbl;
    public Label grade_lbl;

    private final User user;


    public UserCellController(User user ){
        this.user=user;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        first_name_lbl.textProperty().bind(user.firstNameProperty());
        last_name_lbl.textProperty().bind(user.lastNameProperty());
        user_name_lbl.textProperty().bind(user.userNameProperty());
        date_lbl.textProperty().bind(user.dateCreatedProperty().asString());
        grade_lbl.textProperty().bind(user.grade_levelProperty());
    }
}
