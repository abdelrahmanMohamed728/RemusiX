package com.example.abdo.remusix;

public class notification {
    private int Image;
    private String username;
    private String action;
    private String time;

    public notification(int image, String username, String action, String time) {
        Image = image;
        this.username = username;
        this.action = action;
        this.time = time;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
