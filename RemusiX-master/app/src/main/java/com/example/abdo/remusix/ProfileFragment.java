package com.example.abdo.remusix;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;



public class ProfileFragment extends Fragment {
      ListView listView;
      ArrayList<PostData>list;
      PostAdapter adapter;
      TextView usernameTextView;
      String username;
      Button btn;
      int LikesCount;
    public ProfileFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_profile, container, false);
        listView=v.findViewById(R.id.ProfileListView);
        usernameTextView = v.findViewById(R.id.profileUserText);
        btn = v.findViewById(R.id.profileFollow);
        MainActivity activity = (MainActivity)getActivity();
        username =  activity.getUsername();
        usernameTextView.setText(username);
        list=new ArrayList<>();
        LoadData("https://remusixapi.conveyor.cloud/api/Posts/UserPosts?s_username="+username);
        adapter = new PostAdapter(getContext(),list);
        listView.setAdapter(adapter);
        return v;
    }

    public void LoadLikessCount(String url)
    {
        LikesCount=0;
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,url, null, new Response.Listener<JSONArray>(){
            @Override
            public void onResponse(JSONArray response) {
                try
                {
                  LikesCount = response.length();

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    Log.e("tag",e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {

            }
        });
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(request);

    }
    public void LoadData(String url)
    {
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,url, null, new Response.Listener<JSONArray>(){
            @Override
            public void onResponse(JSONArray response) {
                try
                {
                     for (int i =0;i<response.length();i++)
                     {
                         JSONObject object = response.getJSONObject(i);
                         String user = object.getString("UserName");
                          if (user.equals(username)) {
                              String id = object.getString("PostID");
                              String Artist = object.getString("ArtistName");
                              String Song = object.getString("SongName");
                              String Userimg = object.getString("Photo");
                              String ArtistImg = object.getString("ArtistPhoto");
                              String time = object.getString("PostTime");
                              LoadLikessCount("https://remusixapi.conveyor.cloud/api/Posts/UsersLikePost?s_postid="+id);
                              PostData data = new PostData(id, user, time, LikesCount, 1, Userimg, ArtistImg, Artist, Song);
                              list.add(data);
                              adapter.notifyDataSetChanged();
                          }
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
            public void onErrorResponse(VolleyError error)
            {

            }
        });
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(request);
    }
}
