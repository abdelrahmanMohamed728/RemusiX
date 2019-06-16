package com.example.abdo.remusix;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
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
import java.util.List;


public class SearchFragment extends Fragment {


    public SearchFragment() {
    }
    SongSearchAdapter adapter1;
    ArrayList<Song> list1;
    EditText search;
    Button btn1;
     RadioGroup radioGroup;
     ListView listView;
    ArtistSearchAdapter adapter;
    ArrayList<Artist>list;
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_search, container, false);
        radioGroup= v.findViewById(R.id.radioGroup1);

        btn1 = v.findViewById(R.id.artistsearchbtn);
        search = v.findViewById(R.id.artistsearchedittext);
        listView = v.findViewById(R.id.searchListView);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = radioGroup.getCheckedRadioButtonId();
                if (id==R.id.radioArtist)
                {

                    list = new ArrayList<>();
                    Log.e("tag","clicked1");
                    LoadData("https://api.deezer.com/search/artist?q="+search.getText().toString());
                    adapter = new ArtistSearchAdapter(getContext(),list);
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Artist artist = list.get(position);
                            Intent intent= new Intent(getActivity(),ArtistActivity.class);
                            intent.putExtra("artistid",artist.getId());
                            startActivity(intent);
                        }
                    });
                }
                else if (id==R.id.radioSong)
                {
                    list1 = new ArrayList<>();
                    Log.e("tag","clicked2");
                    LoadData1("https://api.deezer.com/search/track?q="+search.getText().toString());
                    adapter1 = new SongSearchAdapter(getContext(),list1);
                    listView.setAdapter(adapter1);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Song  song = list1.get(position);
                            Intent intent= new Intent(getActivity(),SongActivity.class);
                            intent.putExtra("songid",song.getId());
                            startActivity(intent);
                        }
                    });
                }
                else
                {
                    listView.setAdapter(null);
                }
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        if (checkedId==R.id.radioArtist)
                        {

                            list = new ArrayList<>();
                            Log.e("tag","clicked1");
                            LoadData("https://api.deezer.com/search/artist?q="+search.getText().toString());
                            adapter = new ArtistSearchAdapter(getContext(),list);
                            listView.setAdapter(adapter);
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Artist artist = list.get(position);
                                    Intent intent= new Intent(getActivity(),ArtistActivity.class);
                                    intent.putExtra("artistid",artist.getId());
                                    startActivity(intent);
                                }
                            });
                        }
                        else if (checkedId==R.id.radioSong)
                        {
                            list1 = new ArrayList<>();
                            Log.e("tag","clicked2");
                            LoadData1("https://api.deezer.com/search/track?q="+search.getText().toString());
                            adapter1 = new SongSearchAdapter(getContext(),list1);
                            listView.setAdapter(adapter1);
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Song  song = list1.get(position);
                                    Intent intent= new Intent(getActivity(),SongActivity.class);
                                    intent.putExtra("songid",song.getId());
                                    startActivity(intent);
                                }
                            });
                        }
                        else
                        {
                            listView.setAdapter(null);
                        }
                    }
                });
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
                        list.add(new Artist(object.getString("id"),object.getString("name"),object.getString("picture_small")));
                        adapter.notifyDataSetChanged();
                    }

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    Log.e("tag1",e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("tag",error.getMessage().toString());

            }
        });
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(request);
    }
    public void LoadData1(String url)
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
                        list1.add(s);
                        adapter1.notifyDataSetChanged();
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
