package com.example.educationguidance.controller;

import com.example.educationguidance.model.Model;
import com.example.educationguidance.views.AccountType;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class loginController implements Initializable {
    public TextField username_fld;
    public PasswordField password_fld;
    public Button login_btn;
    public Label err_lbl;
    public Button create_act_btn;
    public ChoiceBox<AccountType> choice_box_act;

   private Stage stage;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choice_box_act.setItems(FXCollections.observableArrayList(AccountType.ADMIN,AccountType.USER));
        choice_box_act.setValue(Model.getInstance().getViewFactory().getLoginAccountType());
        choice_box_act.valueProperty().addListener(observable -> setAcc_selector());
        login_btn.setOnAction(event -> onLogin());
        create_act_btn.setOnAction(event -> createAccount());
    }

    private  void onLogin()
    {

        stage=(Stage) err_lbl.getScene().getWindow();
       if(Model.getInstance().getViewFactory().getLoginAccountType()==AccountType.USER){
           Model.getInstance().evaluateUserCred(username_fld.getText(), password_fld.getText());
            if(Model.getInstance().isUserLoginSuccessFlag()){
                Model.getInstance().getViewFactory().showGradeWindow();
                Model.getInstance().getViewFactory().closeStage(stage);
                err_lbl.setText("");
            }
            else {
                username_fld.setText("");
                password_fld.setText("");
                err_lbl.setText("No Such Credential");
            }
       }
       else {

               Model.getInstance().evaluateAdminCred(username_fld.getText(), password_fld.getText());
               if(Model.getInstance().isAdminLoginSuccessFlag()){
                   Model.getInstance().getViewFactory().showAdminWindow();
                   Model.getInstance().getViewFactory().closeStage(stage);
                   err_lbl.setText("");
               }
               else {
                   username_fld.setText("");
                   password_fld.setText("");
                   err_lbl.setText("No Such Credential");
               }
       }
    }


    private void createAccount()
    {
        stage=(Stage) err_lbl.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showCreateAccountWindow();
    }

    private void setAcc_selector()
    {
        Model.getInstance().getViewFactory().setLoginAccountType(choice_box_act.getValue());
        if(choice_box_act.getValue()==AccountType.ADMIN){
            create_act_btn.setVisible(false);
        }
        else {
            create_act_btn.setText("Create  Account");
            create_act_btn.setVisible(true);
        }
    }
}
