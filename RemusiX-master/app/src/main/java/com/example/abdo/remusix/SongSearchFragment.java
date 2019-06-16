package com.example.abdo.remusix;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SongSearchFragment extends Fragment {
    SongSearchAdapter adapter;
    ArrayList<Song> list;
    ListView listView;
    EditText search;
    Button btn;


    public SongSearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_song_search, container, false);
        listView = v.findViewById(R.id.songListSearch);
        btn = getActivity().findViewById(R.id.artistsearchbtn);
        search = getActivity().findViewById(R.id.artistsearchedittext);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list = new ArrayList<>();
                Log.e("tag","clicked2");
                LoadData("https://api.deezer.com/search/track?q="+search.getText().toString());
                adapter = new SongSearchAdapter(getContext(),list);
                listView.setAdapter(adapter);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song  song = list.get(position);
                Intent intent= new Intent(getActivity(),SongActivity.class);
                intent.putExtra("songid",song.getId());
                startActivity(intent);
            }
        });
        return v;
    }
    public void LoadData(String url)
    {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,url, null, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray array = response.getJSONArray("data");
                    for (int i =0;i<array.length();i++)
                    {
                        JSONObject object = array.getJSONObject(i);
                         String name = object.getString("title");
                         String id = object.getString("id");
                         String link = object.getString("link");
                         JSONObject artist = object.getJSONObject("artist");
                         String img = artist.getString("picture_small");
                         String artistname = artist.getString("name");
                         String artistid = artist.getString("id");
                         Song s = new Song(img,name,id,link,artistid);
                         s.setArtistName(artistname);
                         list.add(s);
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


            }
        });
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(request);
    }
}
