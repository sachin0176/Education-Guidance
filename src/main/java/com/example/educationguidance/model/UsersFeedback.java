package com.example.educationguidance.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class UsersFeedback {

    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty userName;
    private final  StringProperty grade_lvl;
    private final ObjectProperty<LocalDate> date;
    private  final  StringProperty message;

    public UsersFeedback(String firstName,String lastName, String userName,String grade_lvl,LocalDate date, String message)
    {
        this.firstName=new SimpleStringProperty(this,"First Name",firstName);
        this.lastName=new SimpleStringProperty(this,"Last Name", lastName);
        this.date=new SimpleObjectProperty<>(this,"Date",date);
        this.userName=new SimpleStringProperty(this,"Username",userName);
        this.message=new SimpleStringProperty(this,"Message", message);
        this.grade_lvl=new SimpleStringProperty(this,"Grade Level", grade_lvl);
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

    public StringProperty grade_lvlProperty() {
        return grade_lvl;
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    public StringProperty messageProperty() {
        return message;
    }
}
