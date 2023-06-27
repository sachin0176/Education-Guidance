package com.example.educationguidance.controller.admin;

import com.example.educationguidance.model.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UpdateController implements Initializable {
    public Label error_lbl;
    public Button edit_btn;
    public Button update_btn;
    public TextField search_grade_text_field;
    public TextArea edit_text_area;
    public Button search_btn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    search_btn.setOnAction(event -> {
        edit_text_area.setEditable(false);
        onSearchButton();});

    }

    private  void onSearchButton()
    {
        String grade_search=search_grade_text_field.getText();

        try {
            if(Model.getInstance().getDatabaseDriver().getDetails(grade_search).isBeforeFirst())
            {
               // Model.getInstance().setObservableListDetails();
                error_lbl.setText("");
                Model.getInstance().retrieveTextFromDatabase(grade_search);
                int size=Model.getInstance().getDetailsObservableList().size()-1;
                edit_text_area.textProperty().bindBidirectional(Model.getInstance().getDetailsObservableList().get(size).getContentProperty());
                edit_btn.setOnAction(event -> edit_text_area.setEditable(true));
                update_btn.setOnAction(event -> {
                    Model.getInstance().getDatabaseDriver().updateGradeDetails(grade_search,edit_text_area.getText());
                    error_lbl.setStyle("-fx-text-fill: #0000ff");
                    error_lbl.setText("Successfully Updated");
                    edit_text_area.setEditable(false);
                    search_grade_text_field.setText(null);
                    edit_text_area.setText(null);
                });
            }
            else {
                error_lbl.setText("No Such Grade");
                error_lbl.setStyle("-fx-text-fill: #EE0000");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
