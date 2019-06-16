package com.example.abdo.remusix.api;

import android.util.JsonToken;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ApiService {


    @POST("/api/Users/Register")
        Call<Boolean> regsiterForUser(@Body RegisterServiceResponse userinformation);


    @GET("/api/Users/Login")
    Call<LoginServiceResponse> loginForUser(@Query("s_email")String email,@Query("s_password")String password);


    @GET("/api/Users/IsUser")
    Call<Boolean> userExistence(@Query("s_email")String email);



    @GET("/api/Users/UpdateLocation")
    Call<Boolean>updateUserLocation(@Query("s_username") String username,@Query("s_longitude") double longitude,@Query("s_latitude") double latitude);


    @GET("/api/Users/UserLocation")
   Call<double[]> getUserLocation(@Query("s_username") String Username);



    @GET("/api/Users/NearbyUsers")
    Call<NearbyUsersResponse[]> getNearbyUsers(@Query("s_username") String Username,@Query("s_longitude") double longitude,@Query("s_latitude") double latitude);



}
