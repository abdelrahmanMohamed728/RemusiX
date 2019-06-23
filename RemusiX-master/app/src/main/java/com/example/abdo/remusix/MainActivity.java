package com.example.abdo.remusix;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.media.AudioManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.abdo.remusix.api.ApiService;
import com.example.abdo.remusix.api.RetrofitClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

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
    BroadcastReceiver mReceiver;
    Retrofit retrofit;
    Artist Artist;
    String SongId;
    Song Song;
    String ArtistId;
    AudioManager audioManager;

    public String getUsername()
    {
        return getIntent().getStringExtra("username");
    }

    public String getUserId()
    {
        return getIntent().getStringExtra("userid");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        criteria.setAccuracy(Criteria.ACCURACY_FINE); // Choose your accuracy requirement.
        criteria.setSpeedRequired(true);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(false);
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
       mReceiver  = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent)
            {

                String action = intent.getAction();
                String cmd = intent.getStringExtra("command");
                String artist = intent.getStringExtra("artist");
                String album = intent.getStringExtra("album");
                String track = intent.getStringExtra("track");
                try {
                    Toast.makeText(MainActivity.this,track,Toast.LENGTH_LONG).show();
                    SearchSong("https://api.deezer.com/search?q=" + track);

                }
                catch (Exception ex)
                {
                  Log.e("tag",ex.toString());
                }

            }
        };
    }

    @Override
    protected void onStart() {
super.onStart();


        audioManager= (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        if (audioManager.isMusicActive()) {

            IntentFilter iF = new IntentFilter();
            iF.addAction("com.android.music.metachanged");
            iF.addAction("com.android.music.playbackcomplete");

            registerReceiver(mReceiver, iF);
        }
    }
    public void AddPost()
    {
        String url="https://remusixapi.conveyor.cloud/api/Posts/CreatePost";
        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);

        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new com.android.volley.Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("tag","error");

            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap();
                MyData.put("SongID",SongId);
                MyData.put("ArtistID",ArtistId);
                MyData.put("PostTime","2019-06-17 18:09:10.460");
                MyData.put("UserID","1");

                return MyData;
            }
        };
        MyRequestQueue.add(MyStringRequest);
    }
    public void AddSong()
    {
        String url="https://remusixapi.conveyor.cloud/api/Songs/AddSong";
        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);

        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
              SongId = response;
              AddPost();
            }
        }, new com.android.volley.Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("tag","error");

            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap();
                MyData.put("SongName",Song.getName());
                MyData.put("SongLink", Song.getLink());
                MyData.put("ArtistID",ArtistId);
                return MyData;
            }
        };
        MyRequestQueue.add(MyStringRequest);
    }
    public void AddArtist()
    {
        String url="https://remusixapi.conveyor.cloud/api/Artists/AddArtist";
        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);

        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ArtistId = response;
                AddSong();
            }
        }, new com.android.volley.Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("tag","error");

            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap();
                MyData.put("ArtistName",Artist.getName());
                MyData.put("ArtistPhoto", Artist.getImg());
                return MyData;
            }
        };
        MyRequestQueue.add(MyStringRequest);

    }
    public void SearchSong(String url)
    {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,url, null, new com.android.volley.Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray array = response.getJSONArray("data");
                        JSONObject object = array.getJSONObject(0);
                        String name = object.getString("title");
                        String id = object.getString("id");
                        String link = object.getString("link");
                        JSONObject artist = object.getJSONObject("artist");
                        String img = artist.getString("picture_big");
                        String artistname = artist.getString("name");
                        String artistid = artist.getString("id");
                        Song = new Song(img,name,id,link,artistid);
                        Song.setArtistName(artistname);
                        Artist = new Artist(Song.getArtistId(),Song.getArtistName(),Song.getImg());
                        AddArtist();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    Log.e("tag1",e.toString());
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(request);
    }
}
