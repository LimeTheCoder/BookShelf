package com.limethecoder.data.domain;


import java.util.Date;

public class Review {
    private User user;
    private String text;
    private String type;
    private Date date;

    public Review() {}

    public Review(User user, String text, String type, Date date) {
        this.user = user;
        this.text = text;
        this.type = type;
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
