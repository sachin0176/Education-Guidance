package com.example.educationguidance.controller.user;

import com.example.educationguidance.model.Model;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;


import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class GradeController implements Initializable {

    public Button grade1_btn;
    public Button grade2_btn;
    public Button grade3_btn;
    public Button grade4_btn;
    public Button grade5_btn;
    public Button grade6_btn;
    public Button grade7_btn;
    public Button grade8_btn;
    public Button grade9_btn;
    public Button grade10_btn;
    public Button military_school_btn;
    public Button law_btn;
    public Button scientist_btn;
    public Button defence_btn;
    public Button bachelors_degree_btn;
    public Button doctor_btn;
    public Button master_degree_btn;
    public Button gate_btn;
    public Button civil_service_btn;

    public Button latest_tech_btn;
    public Button profile_btn;
    public Button logout_btn;
    public Button report_btn;
    public Button ncc_btn;
    public Button engr_btn;
    public Button neet_btn;
    public Button pro_lan_btn;
    public Button inter_btn;
    public Button iit_btn;
    private String dateString;
    private Stage menuStage;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LocalDate localDate=LocalDate.now();
        String [] parts=localDate.toString().split("-");
        dateString=parts[0]+"-"+parts[1]+"-"+parts[2];

        onClick();


    }

private void onClick()
{
    report_btn.setOnAction(event -> Model.getInstance().getViewFactory().showUserFeedbackWindow());
    logout_btn.setOnAction(event -> onLogout());

    profile_btn.setOnAction(event -> {
        Model.getInstance().getViewFactory().showUserProfileWindow();
        menuStage=(Stage) logout_btn.getScene().getWindow();
        // menu stage closed
        Model.getInstance().getViewFactory().closeStage(menuStage);
    });
    grade1_btn.setOnAction(event -> showDetails("Grade1"));
    grade2_btn.setOnAction(event -> showDetails("Grade2"));
    grade3_btn.setOnAction(event -> showDetails("Grade3"));
    grade4_btn.setOnAction(event -> showDetails("Grade4"));
    grade5_btn.setOnAction(event -> showDetails("Grade5"));
    grade6_btn.setOnAction(event -> showDetails("Grade6"));
    grade7_btn.setOnAction(event -> showDetails("Grade7"));
    grade8_btn.setOnAction(event -> showDetails("Grade8"));
    grade9_btn.setOnAction(event -> showDetails("Grade9"));
    grade10_btn.setOnAction(event -> showDetails("Grade10"));
    military_school_btn.setOnAction(event -> showDetails("MilitarySchool"));
    law_btn.setOnAction(event -> showDetails("Law"));
    scientist_btn.setOnAction(event -> showDetails("Scientist"));
    bachelors_degree_btn.setOnAction(event -> showDetails("BachelorsDegree"));
    master_degree_btn.setOnAction(event -> showDetails("MastersDegree"));
    gate_btn.setOnAction(event -> showDetails("Gate"));
    defence_btn.setOnAction(event ->  showDetails("Defence"));
    civil_service_btn.setOnAction(event -> showDetails("CivilService"));
    neet_btn.setOnAction(event -> showDetails("NEET"));
    doctor_btn.setOnAction(event -> showDetails("Doctor"));
    engr_btn.setOnAction(event -> showDetails("Engineering"));
    latest_tech_btn.setOnAction(event -> showDetails("LatestTechnology"));
    pro_lan_btn.setOnAction(event -> showDetails("ProgrammingLanguage"));
    ncc_btn.setOnAction(event -> showDetails("NCC"));
    inter_btn.setOnAction(event -> showDetails("12th"));
    iit_btn.setOnAction(event -> showDetails("IIT"));




}
    private void showDetails(String grade_lvl)
    {
        menuStage=(Stage) logout_btn.getScene().getWindow();
        // menu stage closed
        Model.getInstance().getViewFactory().closeStage(menuStage);

        FXMLLoader loader=new FXMLLoader(getClass().getResource("/FXML/User/details.fxml"));
        DetailContainer detailContainer=new DetailContainer();
        loader.setController(detailContainer);
        Model.getInstance().getViewFactory().createStage(loader);
       // Model.getInstance().setObservableListDetails();
        Model.getInstance().retrieveTextFromDatabase(grade_lvl);
        int size =Model.getInstance().getDetailsObservableList().size()-1;

        detailContainer.grade_lbl.textProperty().bind(Model.getInstance().getDetailsObservableList().get(size).getTitleProperty());
        detailContainer.text_area.textProperty().bind(Model.getInstance().getDetailsObservableList().get(size).getContentProperty());
        detailContainer.date_lbl.textProperty().set(dateString);
        Stage stage=(Stage) detailContainer.grade_lbl.getScene().getWindow();
        detailContainer.back_btn.setOnAction(event1 -> onClose(stage));
    }
    private void onClose(Stage stage) {
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showGradeWindow();
    }

    private void onLogout()
    {
        Stage menuStage =(Stage) logout_btn.getScene().getWindow();
        // menu stage closed
        Model.getInstance().getViewFactory().closeStage(menuStage);
        // show login window
        Model.getInstance().getViewFactory().showLoginWindow();
        // user login flag false

        Model.getInstance().setUserLoginSuccessFlag(false);
    }

}
