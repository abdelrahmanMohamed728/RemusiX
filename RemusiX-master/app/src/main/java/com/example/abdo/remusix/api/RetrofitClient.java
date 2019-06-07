package com.example.abdo.remusix.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public  static Retrofit getInstance(){
        Retrofit.Builder builder=new Retrofit.Builder();
        builder.baseUrl("https://remusixapi.conveyor.cloud")
                .addConverterFactory(GsonConverterFactory.create());
        return builder.build();
    }
}
