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
        // Required empty public constructor
    }
 ArtistSearchAdapter adapter;
    ArrayList<Artist>list;
    ListView listView;
    EditText search;
    Button btn;
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_search, container, false);
          list = new ArrayList<>();
          listView = v.findViewById(R.id.artistListSearch);
          search =v.findViewById(R.id.artistsearchedittext);
        btn = v.findViewById(R.id.artistsearchbtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadData("https://api.deezer.com/search/artist?q="+search.getText().toString());
                adapter = new ArtistSearchAdapter(getContext(),list);
                listView.setAdapter(adapter);
            }
        });
         listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 Artist artist = list.get(position);
                 Intent intent= new Intent(getActivity(),ArtistActivity.class);
                 intent.putExtra("artistid",artist.getId());
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
                        list.add(new Artist(object.getString("id"),object.getString("name"),object.getString("picture_small")));
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
