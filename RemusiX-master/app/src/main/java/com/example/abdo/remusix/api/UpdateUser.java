package com.example.abdo.remusix.api;

public class UpdateUser {

    String username;
    double longitude;
    double latitude;

    public UpdateUser(String username, double longitude, double latitude) {
        this.username = username;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
