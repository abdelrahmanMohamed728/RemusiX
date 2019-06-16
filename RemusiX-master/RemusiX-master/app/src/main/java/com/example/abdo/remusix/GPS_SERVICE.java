package com.example.abdo.remusix;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.abdo.remusix.api.ApiService;
import com.example.abdo.remusix.api.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GPS_SERVICE extends Service {
    private Retrofit retrofit;

    private ApiService apiService;


    private LocationManager locationManager;
    private LocationListener locationListener;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (locationManager!=null){
            locationManager.removeUpdates(locationListener);

        }
    }
}
