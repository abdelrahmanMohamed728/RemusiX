package com.example.abdo.remusix;

public class User {
    private String UserName;
    private String Email;
    private String UserPassword;
    private String FirstName;
    private String LastName;
    private String Photo;
    private String Background;
    private Character Gender;
    private String FacebookUsername;
    private String GoogleUsername;
    private String SoundcloudUsername;
    private Object Birthdate;


    public User(String userName, String email, String userPassword, String firstName, String lastName, String photo, String background, Character gender, String facebookUsername, String googleUsername, String soundcloudUsername, Object birthdate) {
        UserName = userName;
        Email = email;
        UserPassword = userPassword;
        FirstName = firstName;
        LastName = lastName;
        Photo = photo;
        Background = background;
        Gender = gender;
        FacebookUsername = facebookUsername;
        GoogleUsername = googleUsername;
        SoundcloudUsername = soundcloudUsername;
        Birthdate = birthdate;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getUserPassword() {
        return UserPassword;
    }

    public void setUserPassword(String userPassword) {
        UserPassword = userPassword;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String photo) {
        Photo = photo;
    }

    public String getBackground() {
        return Background;
    }

    public void setBackground(String background) {
        Background = background;
    }

    public Character getGender() {
        return Gender;
    }

    public void setGender(Character gender) {
        Gender = gender;
    }

    public String getFacebookUsername() {
        return FacebookUsername;
    }

    public void setFacebookUsername(String facebookUsername) {
        FacebookUsername = facebookUsername;
    }

    public String getGoogleUsername() {
        return GoogleUsername;
    }

    public void setGoogleUsername(String googleUsername) {
        GoogleUsername = googleUsername;
    }

    public String getSoundcloudUsername() {
        return SoundcloudUsername;
    }

    public void setSoundcloudUsername(String soundcloudUsername) {
        SoundcloudUsername = soundcloudUsername;
    }

    public Object getBirthdate() {
        return Birthdate;
    }

    public void setBirthdate(Object birthdate) {
        Birthdate = birthdate;
    }
}
