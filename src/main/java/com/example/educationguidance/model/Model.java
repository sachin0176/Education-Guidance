package com.example.educationguidance.model;

import com.example.educationguidance.views.ViewFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Model {

    private  static  Model model;
    private  final ViewFactory viewFactory;
    private  final DatabaseDriver databaseDriver;

    /*
    user
     */
    private  final User user;
    private boolean userLoginSuccessFlag;
    private final ObservableList<Note> detailsObservableList;

// admin

    private boolean adminLoginSuccessFlag;
    private final ObservableList<User> latestUser;
    private  final ObservableList<User> allUser;
    private final UsersFeedback usersFeedback;
    private final  ObservableList<UsersFeedback> allUsersFeedback;
    private Model()
    {

        this.viewFactory=new ViewFactory();
        this.databaseDriver=new DatabaseDriver();

        // user data section
        user= new User("","","",null,"");
        userLoginSuccessFlag=false;
        this.detailsObservableList = FXCollections.observableArrayList();


        // Admin data section
        usersFeedback=new UsersFeedback("","","","",null,"");
        adminLoginSuccessFlag=false;
        this.latestUser=FXCollections.observableArrayList();
        this.allUser=FXCollections.observableArrayList();
        this.allUsersFeedback=FXCollections.observableArrayList();


    }

    public static  synchronized Model getInstance()
    {
        if(model==null)
            model=new Model();
        return  model;
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }

    public DatabaseDriver getDatabaseDriver() {
        return databaseDriver;
    }

    /*

    Client
     */
    public boolean isUserLoginSuccessFlag() {
        return userLoginSuccessFlag;
    }

    public void setUserLoginSuccessFlag(boolean userLoginSuccessFlag) {
        this.userLoginSuccessFlag = userLoginSuccessFlag;
    }

    public  User getUser()
    {
        return user;
    }
    public void evaluateUserCred(String username,String password)
    {
        ResultSet resultSet= getDatabaseDriver().getUserResult(username,password);

        try{
            if(resultSet.isBeforeFirst()){
                String firstName=resultSet.getString("FirstName");
                String lastName=resultSet.getString("LastName");
                String userName = resultSet.getString("Username");
                String grade_lvl=resultSet.getString("GradeLevel");
                String parts[]=resultSet.getString("DateCreated").split("-");
                LocalDate datecreated =LocalDate.of(Integer.parseInt(parts[0]),Integer.parseInt(parts[1]),Integer.parseInt(parts[2]));
               user.firstNameProperty().set(firstName);
               user.lastNameProperty().set(lastName);
               user.userNameProperty().set(userName);
               user.dateCreatedProperty().set(datecreated);
               user.grade_levelProperty().set(grade_lvl);
                this.userLoginSuccessFlag=true;
            }
            resultSet.close();
        }catch ( Exception e)
        {
            e.printStackTrace();
        }
    }


    public void retrieveTextFromDatabase(String grade_level) {


        ResultSet resultSet = this.databaseDriver.getDetails(grade_level);
        try {

            // Retrieve the text from the ResultSet
            if (resultSet.next()) {
              Note note=new Note(grade_level,resultSet.getString("Details"));

              detailsObservableList.add(note);

            }

            // Close the ResultSet, statement, and connection
            resultSet.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public ObservableList<Note> getDetailsObservableList() {
        return detailsObservableList;
    }

    public void setObservableListDetails() {
        this.detailsObservableList.clear();
    }


    /*

    admin method
     */



    public boolean isAdminLoginSuccessFlag() {
        return adminLoginSuccessFlag;
    }

    public void setAdminLoginSuccessFlag(boolean adminLoginSuccessFlag) {
        this.adminLoginSuccessFlag = adminLoginSuccessFlag;
    }

    public void evaluateAdminCred(String username, String password)
    {
        ResultSet resultSet= getDatabaseDriver().getAdminResult(username,password);

        try{
            if(resultSet.isBeforeFirst()){
                this.adminLoginSuccessFlag=true;
            }
            resultSet.close();
        }catch ( Exception e)
        {
            e.printStackTrace();
        }
    }


    private void preparedUser(ObservableList<User> users , int limit)
    {
        ResultSet resultSet=this.databaseDriver.getUserDetails(limit);
        try{

            while (resultSet.next()){
            String firstName=resultSet.getString("FirstName");
            String lastName=resultSet.getString("LastName");
            String userName = resultSet.getString("Username");
            String grade_lvl=resultSet.getString("GradeLevel");
            String parts[]=resultSet.getString("DateCreated").split("-");
            LocalDate datecreated =LocalDate.of(Integer.parseInt(parts[0]),Integer.parseInt(parts[1]),Integer.parseInt(parts[2]));
            users.add( new User(firstName,lastName,userName,datecreated,grade_lvl));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public ObservableList<User> getLatestUserDetails() {
        return latestUser;
    }

    public void setLatestUserDetails(){preparedUser(this.latestUser,5);}

    public ObservableList<User> getAllUserDetails() {
        return allUser;
    }
    public void setAllUserDetails(){
        preparedUser(allUser,-1);
    }


    private void prepareUsersFeeback(ObservableList<UsersFeedback> users_feedback )
    {
        ResultSet resultSet=this.databaseDriver.usersFeedbackDB();
        try{

            while (resultSet.next()){
                String firstName=resultSet.getString("FirstName");
                String lastName=resultSet.getString("LastName");
                String userName = resultSet.getString("Username");
                String grade_lvl=resultSet.getString("GradeLevel");
                String parts[]=resultSet.getString("Date").split("-");
                LocalDate datecreated =LocalDate.of(Integer.parseInt(parts[0]),Integer.parseInt(parts[1]),Integer.parseInt(parts[2]));
                String message=resultSet.getString("Feedback");
                users_feedback.add(new UsersFeedback(firstName,lastName,userName,grade_lvl,datecreated,message));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public UsersFeedback getUsersFeedback() {
        return usersFeedback;
    }

    public ObservableList<UsersFeedback> getAllUsersFeedback() {
        return allUsersFeedback;
    }
    public void setAllUsersFeedback(){ prepareUsersFeeback(allUsersFeedback);}
}
