package com.example.educationguidance.controller.admin;

import com.example.educationguidance.model.Model;
import com.example.educationguidance.views.AdminMenuOption;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminMenuController implements Initializable {
    public Button latest_user_btn;
    public Button all_user_btn;
    public Button logout_btn;
    public Button update_btn;

    public Button feedback_btn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        all_user_btn.setOnAction(event -> onAllUser());
        latest_user_btn.setOnAction(event -> onLatestUser());
        update_btn.setOnAction(event -> onUpdate());
        feedback_btn.setOnAction(event -> onUsersFeedback());
        logout_btn.setOnAction(event -> onLogout());
    }

    private void onAllUser() {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOption.ALL_USER);


    }

    private void onLatestUser()
    {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOption.LATEST_USER);

    }

    private void onUpdate()
    {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOption.UPDATE);

    }


    private  void onUsersFeedback()
    {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOption.FEEDBACK);
    }

    private void onLogout()
    {
        // stage
        Stage stage=(Stage) update_btn.getScene().getWindow();
        // close stage
        Model.getInstance().getViewFactory().closeStage(stage);

        // show login window
        Model.getInstance().getViewFactory().showLoginWindow();
        // admin flag false
        Model.getInstance().setAdminLoginSuccessFlag(false);
    }
}

