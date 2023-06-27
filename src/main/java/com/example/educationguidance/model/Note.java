package com.example.educationguidance.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableArray;

public class Note  {

    private StringProperty grade_level;
    private StringProperty details;


    public Note(String title, String content) {
        this.grade_level = new SimpleStringProperty(title);

        this.details = new SimpleStringProperty(content);
    }

    public StringProperty getTitleProperty() {
        return grade_level;
    }

    public StringProperty getContentProperty() {
        return details;
    }
}
