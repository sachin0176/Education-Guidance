package com.example.educationguidance.controller.admin;

import com.example.educationguidance.model.Model;
import com.example.educationguidance.model.User;
import com.example.educationguidance.views.UserCellFactory;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class latestUserController implements Initializable {
    public ListView<User> latest_user_list_view;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initLatestUsers();
        latest_user_list_view.setItems(Model.getInstance().getLatestUserDetails());
        latest_user_list_view.setCellFactory( e -> new UserCellFactory());
    }
    private void initLatestUsers()
    {
        if(Model.getInstance().getLatestUserDetails().isEmpty()){
            Model.getInstance().setLatestUserDetails();
        }
    }
}
