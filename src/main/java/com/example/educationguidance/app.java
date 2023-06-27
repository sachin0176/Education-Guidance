package com.example.educationguidance;

import com.example.educationguidance.model.Model;
import javafx.application.Application;
import javafx.stage.Stage;

public class app extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Model.getInstance().getViewFactory().showLoginWindow();
    }
}
