package com.example.educationguidance.controller.user;

import com.example.educationguidance.model.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class UserProfileController implements Initializable {
    public Label firstName;
    public Label lastName;
    public TextField grade_fld;
    public Button grade_edit_btn;
    public Button grade_update_btn;
    public Button chP_btn;
    public Button back_btn;
    public Label userName_lbl;
    public Label date_lbl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initBind();
        gradeLavelEdit();
        goBackWindow();
        chP_btn.setOnAction(event -> changePassword());
    }

    private  void initBind()
    {

        firstName.textProperty().bind(Model.getInstance().getUser().firstNameProperty());
        lastName.textProperty().bind(Model.getInstance().getUser().lastNameProperty());
        userName_lbl.textProperty().bind(Model.getInstance().getUser().userNameProperty());
        grade_fld.textProperty().bindBidirectional(Model.getInstance().getUser().grade_levelProperty());
        date_lbl.textProperty().bind(Model.getInstance().getUser().dateCreatedProperty().asString());
        grade_fld.textProperty().bindBidirectional(Model.getInstance().getUser().grade_levelProperty());
    }



    private  void gradeLavelEdit()
    {
        grade_edit_btn.setOnAction(event -> {
            grade_fld.setEditable(true);
            grade_update_btn.setOnAction(event1 -> {
                grade_fld.setEditable(false);
                String userName=Model.getInstance().getUser().userNameProperty().getValue();
                Model.getInstance().getDatabaseDriver().gradeUpdation(userName,grade_fld.getText());
            });

        });
    }
    private void goBackWindow()
    {
        back_btn.setOnAction(event -> {
            Stage stage=(Stage) back_btn.getScene().getWindow();

            Model.getInstance().getViewFactory().closeStage(stage);
            Model.getInstance().getViewFactory().showGradeWindow();
        });
    }

    private void changePassword()
    {
        Stage stage=(Stage)date_lbl.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showPasswordChange();
    }
}
