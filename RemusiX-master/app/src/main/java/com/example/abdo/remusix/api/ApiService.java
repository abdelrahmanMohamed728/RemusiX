package com.example.abdo.remusix.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {


    @POST("/api/Users/Register")
        Call<Boolean> regsiterForUser(@Body RegisterServiceResponse userinformation);


    @GET("/api/Users/Login")
    Call<LoginServiceResponse> loginForUser(@Query("s_email") String email, @Query("s_password") String password);


    @GET("/api/Users/IsUser")
    Call<Boolean> userExistance(@Query("s_email") String email);




}
