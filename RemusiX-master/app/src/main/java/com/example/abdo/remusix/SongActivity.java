package com.example.abdo.remusix;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.deezer.sdk.network.connect.DeezerConnect;
import com.deezer.sdk.network.request.event.DeezerError;
import com.deezer.sdk.player.ArtistRadioPlayer;
import com.deezer.sdk.player.TrackPlayer;
import com.deezer.sdk.player.exception.TooManyPlayersExceptions;
import com.deezer.sdk.player.networkcheck.WifiAndMobileNetworkStateChecker;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

public class SongActivity extends AppCompatActivity {
    ImageView songImg;
    TextView songName;
    Button btn;
    String songLink;
    TextView artistName;
    Boolean check;
  String id;
    @Override
    protected void onStart() {
        super.onStart();
        check=false;
    }
TrackPlayer trackPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);
        songImg = findViewById(R.id.songImg);
        songName = findViewById(R.id.songName);
        artistName = findViewById(R.id.artistName);
        check = false;
        btn = findViewById(R.id.songButton);
         id = getIntent().getStringExtra("songid");
        LoadData("https://api.deezer.com/track/"+id);
      final   String applicationID = "351984";
      final   DeezerConnect deezerConnect = new DeezerConnect(SongActivity.this, applicationID);
        try {
            trackPlayer = new TrackPlayer(getApplication(), deezerConnect, new WifiAndMobileNetworkStateChecker());
        } catch (TooManyPlayersExceptions tooManyPlayersExceptions) {
            tooManyPlayersExceptions.printStackTrace();
        } catch (DeezerError deezerError) {
            deezerError.printStackTrace();
        }
        btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                        if (!check)
                        {
                            trackPlayer.playTrack(Long.parseLong(id));
                            check = true;
                            btn.setText("Stop");
                        } else {
                            trackPlayer.stop();
                            check = false;
                            btn.setText("Listen Now");

                        }
                }
            });

    }
    public void LoadData(String url)
    {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,url, null, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                try {

                        JSONObject track = response;
                        String img = track.getJSONObject("artist").getString("picture_medium");
                        String name = track.getString("title");
                        String link = track.getString("link");
                        String artistid = track.getJSONObject("artist").getString("id");
                        String id = track.getString("id");
                        Picasso.with(SongActivity.this).load(img).into(songImg);
                        songName.setText(name);
                        Log.e("tag",name);
                        artistName.setText("by: "+track.getJSONObject("artist").getString("name"));
                        songLink=link;
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    Log.e("tag",e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue queue = Volley.newRequestQueue(SongActivity.this);
        queue.add(request);
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            trackPlayer.stop();
            check = false;
        }
        catch (Exception ex)
        {

        }
    }
}
