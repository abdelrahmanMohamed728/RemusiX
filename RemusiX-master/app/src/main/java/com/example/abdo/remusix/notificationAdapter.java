package com.example.abdo.remusix;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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
import java.util.List;

public class notificationAdapter extends ArrayAdapter<notification> {
    public notificationAdapter(@NonNull Context context, @NonNull ArrayList<notification> objects) {
        super(context, 0, objects);
    }
   String username;
    TextView name;
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @Nullable ViewGroup parent) {
        convertView= LayoutInflater.from(getContext()).inflate(R.layout.notificationslayout,parent,false);
        notification notification1 = getItem(position);
        ImageView img1 = convertView.findViewById(R.id.notiProfile);
         name = convertView.findViewById(R.id.notiName);
        TextView post = convertView.findViewById(R.id.notiPost);
        TextView time = convertView.findViewById(R.id.notiTime);
        GetUser("https://remusixapi.conveyor.cloud/api/Users/GetUser?s_userid="+notification1.getUsername());

        if (notification1.getAction().equals("like"))
        {
          post.setText("Liked your post");
        }
        else if (notification1.getAction().equals("comment"))
        {
            post.setText("Commented your post");
        }
        else
            post.setText("Followed You");

        time.setText(notification1.getTime());
        return convertView;
    }
    public void GetUser(String url)
    {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,url, null, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                try
                {
                    name.setText(response.getString("UserName"));
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
