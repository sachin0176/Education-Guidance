package com.example.educationguidance.views;

import com.example.educationguidance.controller.admin.AdminController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;

public class ViewFactory {


    //admin
private  AccountType loginAccountType;

private AnchorPane allUser;
private  AnchorPane latestUsers;
private  AnchorPane updateDetails;
private AnchorPane usersFeedbackWindow;
private final ObjectProperty<AdminMenuOption> adminSelectedMenuItem;
    public ViewFactory() {
        this.loginAccountType=AccountType.USER;
        this.adminSelectedMenuItem=new SimpleObjectProperty<>();
    }

    public void setLoginAccountType(AccountType loginAccountType) {
        this.loginAccountType = loginAccountType;
    }

    public AccountType getLoginAccountType() {
        return loginAccountType;
    }


// user view

    public void showCreateAccountWindow()
    {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/FXML/User/createAccount.fxml"));
        createStage(loader);
    }
    public void showGradeWindow() {

        FXMLLoader loader=new FXMLLoader(getClass().getResource("/FXML/User/grade.fxml"));
        createStage(loader);
    }

    public void showUserFeedbackWindow(){
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/FXML/User/reportFeedback.fxml"));
        createStage(loader);
    }

    public void showUserProfileWindow()
    {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/FXML/User/userProfile.fxml"));
        createStage(loader);
    }

    public void showPasswordChange()
    {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/FXML/User/changePassword.fxml"));
        createStage(loader);
    }
    // admin


    public AnchorPane showAllUsers() {

        if(allUser==null){
            try{
                allUser =new FXMLLoader(getClass().getResource("/FXML/Admin/allUser.fxml")).load();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return allUser;
    }

    public AnchorPane showUpdateDetails() {

        if(updateDetails==null){
            try{
                updateDetails =new FXMLLoader(getClass().getResource("/FXML/Admin/update.fxml")).load();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return updateDetails;
    }
    public AnchorPane showLatestUsers() {

        if(latestUsers==null){
            try{
                latestUsers =new FXMLLoader(getClass().getResource("/FXML/Admin/latestUser.fxml")).load();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return latestUsers;
    }

    public AnchorPane showUsersFeedbackWindow()
    {
        if(usersFeedbackWindow==null)
        {
            try{
                usersFeedbackWindow=new FXMLLoader(getClass().getResource("/FXML/Admin/userFeedback.fxml")).load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return  usersFeedbackWindow;
    }

    public ObjectProperty<AdminMenuOption> getAdminSelectedMenuItem() {
        return adminSelectedMenuItem;
    }

    public void showAdminWindow()
    {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/FXML/Admin/admin.fxml"));
        AdminController adminController=new AdminController();
        loader.setController(adminController);
        createStage(loader);
    }



    public void showLoginWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/login.fxml"));
        createStage(loader);
    }



    public  void createStage(FXMLLoader loader){
        Scene scene=null;
        try{
            scene=new Scene(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Stage stage=new Stage();
        stage.setScene(scene);
        stage.setTitle("Education Guidance");
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/Image/icon.png"))));
        stage.setResizable(false);
        stage.show();
    }

    public void closeStage(Stage stage)
    {
        stage.close();
    }

}
