package com.example.educationguidance.controller.admin;

import com.example.educationguidance.model.Model;
import com.example.educationguidance.model.User;
import com.example.educationguidance.views.UserCellFactory;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class AllUser implements Initializable {
    public Label allUser_lbl;
    public ListView<User> listView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initAllUser();
         listView.setItems(Model.getInstance().getAllUserDetails());
        listView.setCellFactory(e-> new UserCellFactory());
    }

    private void initAllUser()
    {
        if(Model.getInstance().getAllUserDetails().isEmpty()){
            Model.getInstance().setAllUserDetails();
    }
    }
}
