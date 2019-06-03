package com.example.abdo.remusix;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;



public class TrendFragment extends Fragment {
      RecyclerView recyclerView;


    public TrendFragment() {

    }
   ArrayList<Song>arrayList;
    SongTrendAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_trend, container, false);
        arrayList = new ArrayList<>();
        LoadData("https://api.deezer.com/chart");
         adapter = new SongTrendAdapter(arrayList);
        recyclerView = v.findViewById(R.id.recycler1);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);

        return v;
    }
    public void LoadData(String url)
    {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,url, null, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                try {
                     response = response.getJSONObject("tracks");
                     JSONArray array = response.getJSONArray("data");
                     for (int i =0;i<10;i++) {
                         JSONObject track = array.getJSONObject(i);
                         String img = track.getJSONObject("artist").getString("picture_small");
                         String name = track.getString("title");
                         String link = track.getString("link");
                         String artistid = track.getJSONObject("artist").getString("id");
                         String id = track.getString("id");
                         arrayList.add(new Song(img,name,id,link,artistid));
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
                Toast.makeText(getContext(),"Invalid",Toast.LENGTH_LONG).show();

            }
        });
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(request);
    }

}
