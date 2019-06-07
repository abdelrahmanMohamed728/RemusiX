
package com.example.abdo.remusix.api;

public class RegisterServiceResponse {

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


    public RegisterServiceResponse(String userName, String email, String userPassword, String firstName, String lastName, String photo, String background, Character gender, String facebookUsername, String googleUsername, String soundcloudUsername, Object birthdate) {
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


}
