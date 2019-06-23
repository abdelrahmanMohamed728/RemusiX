package com.example.abdo.remusix;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class NotificationFragment extends Fragment {
   ListView listView;

    public NotificationFragment() {

    }
    ArrayList<notification>list;
notificationAdapter adapter;
PostData post;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("tag","notification");
        View v= inflater.inflate(R.layout.fragment_notification, container, false);
        listView = v.findViewById(R.id.notiList);
        list = new ArrayList<>();
  LoadData("https://remusixapi.conveyor.cloud/api/Posts/GetNotifications");
         adapter = new notificationAdapter(getContext(),list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String action = list.get(position).getAction();
                if (action.equals("follow"))
                {

                }
                else if (action.equals("like")||action.equals("comment"))
                {
                  Intent intent = new Intent(getActivity(),CommentsActivity.class);
                    intent.putExtra("post",post);
                    intent.putExtra("userid",((MainActivity) getActivity()).getUserId());
                    startActivity(intent);
                }
            }
        });
        return v;
    }
    public void GetPost(String url)
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

                                String id = object.getString("PostID");
                                String Artist = object.getString("ArtistName");
                                String Song = object.getString("SongName");
                                String Userimg = object.getString("Photo");
                                String ArtistImg = object.getString("ArtistPhoto");
                                String time = object.getString("PostTime");
                                 post = new PostData(id, user, time, 1, 1, Userimg, ArtistImg, Artist, Song);


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
   public void LoadData(String url)
   {
       JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,url, null, new Response.Listener<JSONArray>(){
           @Override
           public void onResponse(JSONArray response) {
               try {
                  JSONArray array  = response;
                   for (int i =0;i<array.length();i++)
                   {
                       JSONObject object = array.getJSONObject(i);
                       notification notification = new notification("1",object.getString("nUserID"),object.getString("Action"),object.getString("ActionTime"));
                       notification.setPostid(object.getString("PostID"));
                       list.add(notification);
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


           }
       });
       RequestQueue queue = Volley.newRequestQueue(getActivity());
       queue.add(request);
   }
}
