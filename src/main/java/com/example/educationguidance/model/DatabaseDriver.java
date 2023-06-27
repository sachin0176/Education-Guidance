package com.example.educationguidance.model;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.sql.*;

public class DatabaseDriver {

    private final Connection con;


    public DatabaseDriver() {

        try{

            con= DriverManager.getConnection("jdbc:sqlite:EducationGuidance.db");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    /*
    Client
     */

    public ResultSet getUserResult(String userName, String password)
    {
     ResultSet resultSet;
     Statement statement;
     try {
         {
             statement=this.con.createStatement();
             resultSet =statement.executeQuery("SELECT * FROM Users WHERE Username = '"+userName+"' AND Password ='"+password+"';");
         }
     } catch (SQLException e) {
         throw new RuntimeException(e);
     }
return resultSet;
    }
public ResultSet getDetails(String grade)
{
    Statement statement;
    ResultSet resultSet;
    try{

        statement=this.con.createStatement();

        resultSet=statement.executeQuery("SELECT * FROM EducationGrade WHERE Grades= '"+grade+"'  ;");
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    return resultSet;
}

public  void gradeUpdation(String userName , String grade_lvl)
{
    Statement statement;
    try {
        statement=con.createStatement();
        statement.executeUpdate("UPDATE Users SET GradeLevel ='"+grade_lvl+"' WHERE Username ='"+userName+"';");
        statement.close();
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}

public void UpdatePassword(String userName,String password)
{
    Statement statement;
    try {
        statement=this.con.createStatement();
        statement.executeUpdate("UPDATE Users SET Password ='"+password+"' WHERE Username ='"+userName+"';");
        statement.close();
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

}


public void createAccount(String firstName,String lastName,String userName,String grade_lvl,String  password,String dateCreated)
{
    Statement statement;
    Alert alert;
    Stage alertStage;
    try {
        statement=this.con.createStatement();

        String checkUsernameQuery = "SELECT COUNT(*) FROM Users WHERE Username = '"+userName+"'";
        ResultSet resultSet = statement.executeQuery(checkUsernameQuery);
        resultSet.next();
        int count = resultSet.getInt(1);
        if (count > 0) {
            // Username already exists, handle accordingly
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alertStage=(Stage) alert.getDialogPane().getScene().getWindow();
            alertStage.getIcons().add(new Image(String.valueOf(getClass().getResource("/Image/icon.png"))));
            alert.setContentText("choose a different username that Username already exists!");
            alert.showAndWait();
        } else {
            // Username is unique, proceed with the INSERT query
            statement.executeUpdate("INSERT INTO Users (FirstName, LastName, Username, Password, DateCreated, GradeLevel) VALUES ('"+firstName+"','"+lastName+"','"+userName+"','"+password+"','"+dateCreated+"','"+grade_lvl+"')");
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alertStage=(Stage) alert.getDialogPane().getScene().getWindow();
            alertStage.getIcons().add(new Image(String.valueOf(getClass().getResource("/Image/icon.png"))));
            alert.setContentText("User added successfully!");
            alert.showAndWait();

        }

        statement.close();
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

}

    public void reportFeedback(String firstName,String lastName,String userName,String grade_lvl,String  feedback,String dateCreated) {
        Statement statement;
        try {
            statement = this.con.createStatement();
            statement.executeUpdate("INSERT INTO "
                    + " UsersFeedback(FirstName,LastName,Username,GradeLevel,Feedback,Date) "
                    + " VALUES ('" + firstName + "','" + lastName + "','" + userName + "','"+grade_lvl+"','" + feedback + "','" + dateCreated + "');  ");
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
/*
Admin

 */

    public ResultSet getAdminResult(String userName, String password)
    {
        ResultSet resultSet;
        Statement statement;
        try {
            {
                statement=this.con.createStatement();
                resultSet =statement.executeQuery("SELECT * FROM Admin WHERE Username = '"+userName+"' AND Password ='"+password+"';");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }


    public ResultSet getUserDetails(int limit)
    {
        ResultSet resultSet;
        Statement statement;
        try {
            {
                statement=this.con.createStatement();
                resultSet =statement.executeQuery("SELECT * FROM Users ORDER BY Id LIMIT '"+limit+"' ;");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    public void updateGradeDetails(String grade_level, String gradeDetails)
    {

        Statement statement;
            try
            {
                statement=this.con.createStatement();
                statement.executeUpdate("UPDATE EducationGrade SET Details ='"+gradeDetails+"' WHERE Grades='"+grade_level+"'; ");
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

    }
    public ResultSet usersFeedbackDB(){

        Statement statement;
        ResultSet resultSet;
        try{
            statement= con.createStatement();
            resultSet=statement.executeQuery("SELECT * FROM UsersFeedback;");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }
}
