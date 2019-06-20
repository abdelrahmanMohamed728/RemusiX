package com.example.abdo.remusix;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.abdo.remusix.api.ApiService;
import com.example.abdo.remusix.api.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager1;
    TabLayout tab1;
    private LocationManager locationManager;
    String bestProvider;
    private ApiService apiService;
    Retrofit retrofit;
    public String getUsername()
    {
        return getIntent().getStringExtra("username");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setPowerRequirement(Criteria.POWER_LOW); // Chose your desired power consumption level.
        criteria.setAccuracy(Criteria.ACCURACY_FINE); // Choose your accuracy requirement.
        criteria.setSpeedRequired(true); // Chose if speed for first location fix is required.
        criteria.setAltitudeRequired(false); // Choose if you use altitude.
        criteria.setBearingRequired(false); // Choose if you use bearing.
        criteria.setCostAllowed(false); // Choose if this provider can waste money :-)
        bestProvider = locationManager.getBestProvider(criteria, true);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        Location lastKnownLocation = locationManager.getLastKnownLocation(bestProvider);
        if (lastKnownLocation!=null){
            retrofit = RetrofitClient.getInstance();
            apiService = retrofit.create(ApiService.class);
            Log.e("location ", lastKnownLocation.getLongitude() + " " + lastKnownLocation.getAltitude());
            Call<Boolean> call = apiService.updateUserLocation("ahmed_mohamed", lastKnownLocation.getLongitude(), lastKnownLocation.getLatitude());
            call.enqueue(new Callback<Boolean>() {
                @Override
                public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                    Log.e("eeeeeeeeeeeeeeeeeeee", response.body() + "");
                    if (response.isSuccessful() ) {
                        Toast.makeText(getApplicationContext(), "Location updated", Toast.LENGTH_SHORT << 3).show();

                    } else {
                        Toast.makeText(getApplicationContext(), "something is wrong", Toast.LENGTH_SHORT << 4).show();
                    }
                }

                @Override
                public void onFailure(Call<Boolean> call, Throwable t) {

                }
            });
        }


        viewPager1 = findViewById(R.id.viewpager1);
        tab1 = findViewById(R.id.tabLayout1);
        viewPagerAdapter adapter = new viewPagerAdapter(getSupportFragmentManager());
        viewPager1.setAdapter(adapter);
        tab1.setupWithViewPager(viewPager1);
        tab1.getTabAt(0).setIcon(R.drawable.home);
        tab1.getTabAt(1).setIcon(R.drawable.profile);
        tab1.getTabAt(2).setIcon(R.drawable.location);
        tab1.getTabAt(3).setIcon(R.drawable.notification);
        tab1.getTabAt(4).setIcon(R.drawable.trend);
        tab1.getTabAt(5).setIcon(R.drawable.search);


    }
}
