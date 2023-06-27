package com.example.educationguidance.controller.user;

import com.example.educationguidance.model.Model;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CreateAccountController implements Initializable {
    public TextField firstName_fld;
    public TextField lastName_fld;
    public TextField userName_fld;
    public TextField grade_level_fld;
    public TextField password_fld;
    public TextField conf_password_fld;
    public Button create_bt;
    public Label error_lbl;
    public Button go_back_btn;

    private Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        create_bt.setOnAction(event -> userAccountCreate());
        go_back_btn.setOnAction(event -> showLoginW());
    }


    private void userAccountCreate() {

        stage = (Stage) error_lbl.getScene().getWindow();

        if (!firstName_fld.getText().isEmpty()
                && !lastName_fld.getText().isEmpty()
                && !userName_fld.getText().isEmpty()
                && !grade_level_fld.getText().isEmpty()) {
            if (password_fld.getText().matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*]).{8,}$")) {
                if (password_fld.getText().equals(conf_password_fld.getText())) {
                    Model.getInstance().getDatabaseDriver().createAccount(firstName_fld.getText(),
                            lastName_fld.getText(),userName_fld.getText(),grade_level_fld.getText()
                            ,password_fld.getText(), LocalDate.now().toString());
                    showLoginW();
                    isEmpty();

                } else {
                    error_lbl.setText("Mismatch Password");
                }
            } else {
                error_lbl.setText("At least one all [A-Z],[a-z],[0-9],[!@#$%^&*]\nA minimum length of 8 characters");
            }


        } else
           error_lbl.setText("Fill up all things");

    }

private void isEmpty()
{
    firstName_fld.setText("");
    lastName_fld.setText("");
    userName_fld.setText("");
    password_fld.setText("");
    conf_password_fld.setText("");
    grade_level_fld.setText("");
}

private void showLoginW()
{
    stage = (Stage) error_lbl.getScene().getWindow();
    Model.getInstance().getViewFactory().closeStage(stage);
    Model.getInstance().getViewFactory().showLoginWindow();


}


}
