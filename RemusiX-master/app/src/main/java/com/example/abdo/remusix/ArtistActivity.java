package com.example.abdo.remusix;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ArtistActivity extends AppCompatActivity {
   ImageView img;
   TextView txt;
   ArtistAdapter adapter;
   ListView listView;
   ArrayList<Song>arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist);
        img = findViewById(R.id.artistImg);
        txt = findViewById(R.id.artistName);
        listView = findViewById(R.id.artistList);
        String id = getIntent().getStringExtra("artistid");
        LoadData("https://api.deezer.com/artist/"+id);
       arrayList = new ArrayList<>();
        LoadData1("https://api.deezer.com/artist/"+id+"/top");
        adapter = new ArtistAdapter(ArtistActivity.this,arrayList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song s = arrayList.get(position);
                Intent intent = new Intent(ArtistActivity.this,SongActivity.class);
                intent.putExtra("songid",s.getId());
                startActivity(intent);
            }
        });

    }
    public void LoadData(String url)
    {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,url, null, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                try {
                     txt.setText(response.getString("name"));
                     Picasso.with(ArtistActivity.this).load(response.getString("picture_medium")).into(img);

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
                Toast.makeText(ArtistActivity.this,"Invalid",Toast.LENGTH_LONG).show();

            }
        });
        RequestQueue queue = Volley.newRequestQueue(ArtistActivity.this);
        queue.add(request);
    }
    public void LoadData1(String url)
    {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,url, null, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray array = response.getJSONArray("data");
                    for (int i =0;i<5;i++)
                    {
                        JSONObject object = array.getJSONObject(i);
                        arrayList.add(new Song("",object.getString("title"),object.getString("id"),null,null));
                        adapter.notifyDataSetChanged();
                    }


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
                Toast.makeText(ArtistActivity.this,"Invalid",Toast.LENGTH_LONG).show();

            }
        });
        RequestQueue queue = Volley.newRequestQueue(ArtistActivity.this);
        queue.add(request);
    }
}
