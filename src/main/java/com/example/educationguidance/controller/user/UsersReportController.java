package com.example.educationguidance.controller.user;

import com.example.educationguidance.model.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;

public class UsersReportController implements Initializable {
    public TextArea fd_fld;
    public Button send_btn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        send_btn.setOnAction(event -> {
            if(!fd_fld.getText().isEmpty()){
                Stage stage=(Stage) fd_fld.getScene().getWindow();
                Model.getInstance().getViewFactory().closeStage(stage);
                String firstName=Model.getInstance().getUser().firstNameProperty().getValue();
                String lastName= Model.getInstance().getUser().lastNameProperty().getValue();
                String userName=Model.getInstance().getUser().userNameProperty().getValue();
                String grade_lvl= Model.getInstance().getUser().grade_levelProperty().getValue();

               Model.getInstance().getDatabaseDriver().reportFeedback(firstName,lastName,userName,grade_lvl
                       ,fd_fld.getText(),LocalDate.now().toString());
                       //  Show a success message to the user
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Feedback submitted");
               Stage alertStage=(Stage) alert.getDialogPane().getScene().getWindow();
               alertStage.getIcons().add(new Image(String.valueOf(getClass().getResource("/Image/icon.png"))));
                alert.setContentText("Thank you for your feedback!");
                alert.showAndWait();

            }
        });
    }
}
