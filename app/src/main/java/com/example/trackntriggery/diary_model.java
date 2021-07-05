package com.example.trackntriggery;

public class diary_model {
    String title,date,text;

    public diary_model() {
    }

    public diary_model(String title, String date, String text) {
        this.title = title;
        this.date = date;
        this.text = text;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getText() {
        return text;
    }
}
