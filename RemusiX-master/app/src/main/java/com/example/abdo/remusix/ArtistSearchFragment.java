package com.example.abdo.remusix;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ArtistSearchFragment extends Fragment {


    public ArtistSearchFragment() {
        // Required empty public constructor
    }
    ArtistSearchAdapter adapter;
    ArrayList<Artist>list;
    ListView listView;
    EditText search;
    Button btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_artist_search, container, false);
        Log.e("tag","opened");
        listView = v.findViewById(R.id.artistListSearch);
        btn = getActivity().findViewById(R.id.artistsearchbtn);
        search = getActivity().findViewById(R.id.artistsearchedittext);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list = new ArrayList<>();
                Log.e("tag","clicked");
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

    @Override
    public void onStart() {

        super.onStart();
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
}
