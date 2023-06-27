package com.example.educationguidance.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class User {

    private  final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty userName;
    private final ObjectProperty<LocalDate> dateCreated;
    private final StringProperty grade_level;

    public User(String firstName,String lastName, String userName,LocalDate localDate ,String grade){
    this.firstName=new SimpleStringProperty(this,"First Name", firstName);
    this.lastName=new SimpleStringProperty(this,"Last Name",lastName);
    this.userName=new SimpleStringProperty(this,"Username",userName);
    this.dateCreated=new SimpleObjectProperty<>(this,"Date Created", localDate);
    this.grade_level=new SimpleStringProperty(this,"Grade Level",grade);


    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public StringProperty userNameProperty() {
        return userName;
    }

    public ObjectProperty<LocalDate> dateCreatedProperty() {
        return dateCreated;
    }

    public StringProperty grade_levelProperty() {
        return grade_level;
    }
}
