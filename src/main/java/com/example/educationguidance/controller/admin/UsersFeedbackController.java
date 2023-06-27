package com.example.educationguidance.controller.admin;

import com.example.educationguidance.model.Model;
import com.example.educationguidance.model.UsersFeedback;
import com.example.educationguidance.views.FeedbackCellFactory;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class UsersFeedbackController implements Initializable {
    public ListView<UsersFeedback> feedback_listview;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initUsersFeedback();
       feedback_listview.setItems(Model.getInstance().getAllUsersFeedback());
       feedback_listview.setCellFactory(e-> new FeedbackCellFactory());
    }

    private void initUsersFeedback()
    {
        if (Model.getInstance().getAllUsersFeedback().isEmpty()) {
            Model.getInstance().setAllUsersFeedback();
        }
    }
}
