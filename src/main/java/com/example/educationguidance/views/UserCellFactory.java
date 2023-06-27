package com.example.educationguidance.views;

import com.example.educationguidance.controller.admin.UserCellController;
import com.example.educationguidance.model.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class UserCellFactory extends ListCell<User> {

    @Override
    protected void updateItem(User user, boolean empty) {
        super.updateItem(user, empty);

        if(empty){
            setText(null);
            setGraphic(null);
        }
        else{

            FXMLLoader loader=new FXMLLoader(getClass().getResource("/FXML/Admin/UserCell.fxml"));
            UserCellController userCellController=new UserCellController(user);
            loader.setController(userCellController);
            setText(null);
            try{

                setGraphic(loader.load());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
