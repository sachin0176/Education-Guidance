package com.example.educationguidance.views;

import com.example.educationguidance.controller.admin.UsersFeedbackCellController;
import com.example.educationguidance.model.UsersFeedback;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class FeedbackCellFactory extends ListCell<UsersFeedback> {

    @Override
    protected void updateItem(UsersFeedback usersFeedback, boolean empty) {
        super.updateItem(usersFeedback, empty);
        if(empty)
        {
            setText(null);
            setGraphic(null);

        }
        else
        {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/FXML/Admin/usersFeedbackCell.fxml"));
            UsersFeedbackCellController usersFeedbackCellController=new UsersFeedbackCellController(usersFeedback);
            loader.setController(usersFeedbackCellController);
            setText(null);
            try{
                setGraphic(loader.load());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
