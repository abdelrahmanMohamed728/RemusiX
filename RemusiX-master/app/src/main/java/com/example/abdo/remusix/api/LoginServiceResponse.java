
package com.example.abdo.remusix.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginServiceResponse {

    @SerializedName("UserID")
    @Expose
    private Integer userID;
    @SerializedName("UserName")
    @Expose
    private String userName;
    @SerializedName("FirstName")
    @Expose
    private String firstName;
    @SerializedName("LastName")
    @Expose
    private String lastName;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("UserPassword")
    @Expose
    private String userPassword;
    @SerializedName("Photo")
    @Expose
    private Object photo;
    @SerializedName("Background")
    @Expose
    private Object background;
    @SerializedName("Gender")
    @Expose
    private String gender;
    @SerializedName("FacebookUsername")
    @Expose
    private Object facebookUsername;
    @SerializedName("GoogleUsername")
    @Expose
    private Object googleUsername;
    @SerializedName("SoundcloudUsername")
    @Expose
    private Object soundcloudUsername;
    @SerializedName("Birthdate")
    @Expose
    private Object birthdate;

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Object getPhoto() {
        return photo;
    }

    public void setPhoto(Object photo) {
        this.photo = photo;
    }

    public Object getBackground() {
        return background;
    }

    public void setBackground(Object background) {
        this.background = background;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Object getFacebookUsername() {
        return facebookUsername;
    }

    public void setFacebookUsername(Object facebookUsername) {
        this.facebookUsername = facebookUsername;
    }

    public Object getGoogleUsername() {
        return googleUsername;
    }

    public void setGoogleUsername(Object googleUsername) {
        this.googleUsername = googleUsername;
    }

    public Object getSoundcloudUsername() {
        return soundcloudUsername;
    }

    public void setSoundcloudUsername(Object soundcloudUsername) {
        this.soundcloudUsername = soundcloudUsername;
    }

    public Object getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Object birthdate) {
        this.birthdate = birthdate;
    }

}
