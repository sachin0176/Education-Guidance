package com.example.educationguidance.controller.user;

import com.example.educationguidance.model.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ChangePasswordController implements Initializable {
    public PasswordField old_fld;
    public PasswordField new_fld;
    public PasswordField conf_fld;
    public Button reset_btn;
    public Label err_lbl;
    public Button back_btn;
    private  Stage stage;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        reset_btn.setOnAction(event -> updatePassword());
        back_btn.setOnAction(event ->goBackWindow());
    }

    private void updatePassword(){
        String userName= Model.getInstance().getUser().userNameProperty().getValue();
        String password=old_fld.getText();
        ResultSet resultSet=Model.getInstance().getDatabaseDriver().getUserResult(userName,password);
        stage=(Stage)err_lbl.getScene().getWindow();

        try{
            if(resultSet.isBeforeFirst())
            {
                if(new_fld.getText().matches( "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*]).{8,}$")) {
                    if (new_fld.getText().equals(conf_fld.getText())) {
                        Model.getInstance().getDatabaseDriver().UpdatePassword(userName,new_fld.getText());
                        err_lbl.setStyle("-fx-text-fill: #0000FF;");
                        err_lbl.setText("Successful");
                        Model.getInstance().getViewFactory().showLoginWindow();
                        Model.getInstance().setUserLoginSuccessFlag(false);
                        Model.getInstance().getViewFactory().closeStage(stage);


                    } else {
                        err_lbl.setText("Incorrect Password");
                    }
                }
                else {
                    err_lbl.setText("At least one all [A-Z],[a-z],[0-9],[!@#$%^&*]\nA minimum length of 8 characters");
                }


            }
            else
                err_lbl.setText("Wrong Old Password");


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void goBackWindow()
    {
        stage=(Stage)err_lbl.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showUserProfileWindow();

    }



}
