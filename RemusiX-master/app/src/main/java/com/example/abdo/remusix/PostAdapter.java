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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class PostAdapter extends ArrayAdapter<PostData> {

    ImageView image,Singer_image;
    TextView name,activiation,num_like,num_comment;

    public PostAdapter(@NonNull Context context,  ArrayList<PostData> info) {
        super(context, 0,info);
    }
PostData dataClass;

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.home_listitem, null, false);
        image=convertView.findViewById(R.id.img);
        Singer_image=convertView.findViewById(R.id.singer_img);
        name= convertView.findViewById(R.id.name);
        activiation=convertView.findViewById(R.id.time);
        num_comment=convertView.findViewById(R.id.comment_num);
        num_like=convertView.findViewById(R.id.liks_num);
         dataClass= getItem(position);
        if(dataClass != null) {
            name.setText(dataClass.getName());
            activiation.setText(dataClass.getTime());
            num_comment.setText(String.valueOf(dataClass.getComment()));
            num_like.setText(String.valueOf(dataClass.getLike()));
            Picasso.with(getContext()).load(dataClass.getSinger_img()).into(Singer_image);
        }

        return convertView ;
    }

   private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}


