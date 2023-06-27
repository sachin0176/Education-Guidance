package com.example.educationguidance.controller.admin;

import com.example.educationguidance.model.Model;
import com.example.educationguidance.model.UsersFeedback;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class UsersFeedbackCellController  implements Initializable {


    public Label firsName_lbl;
    public Label lastName_lbl;
    public Label userName_lbl;
    public Label grade_lvl;
    public Label dt_lbl;
    public TextArea feedback_fld;
    private final UsersFeedback usersFeedback;


    public UsersFeedbackCellController(UsersFeedback usersFeedback){
        this.usersFeedback=usersFeedback;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        firsName_lbl.textProperty().bind(usersFeedback.firstNameProperty());
        lastName_lbl.textProperty().bind(usersFeedback.lastNameProperty());
        userName_lbl.textProperty().bind(usersFeedback.userNameProperty());
        grade_lvl.textProperty().bind(usersFeedback.grade_lvlProperty());
        dt_lbl.textProperty().bind(usersFeedback.dateProperty().asString());
        feedback_fld.textProperty().bind(usersFeedback.messageProperty());
    }
}
