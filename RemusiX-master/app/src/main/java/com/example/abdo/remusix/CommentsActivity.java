package com.example.abdo.remusix;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.abdo.remusix.R;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CommentsActivity extends AppCompatActivity {


    ListView listView;
    CommentAdapter adapter;
    ImageView img1;
    ImageView img2;
    TextView name;
    TextView time;
    TextView likes;
    ArrayList<Comment>list;
    TextView comments;
    EditText EditTextComment;
    Button AddCommentButton;
    PostData post;
    String date;
    ImageView likesImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_comments);
        likesImage = findViewById(R.id.liks_img);
        img1 = findViewById(R.id.commentPostimg1);
        img2 = findViewById(R.id.commentpostimg2);
        AddCommentButton = findViewById(R.id.addCommentButton);
        EditTextComment = findViewById(R.id.addCommentEditText);
        name = findViewById(R.id.commentpostname);
        time = findViewById(R.id.commentposttime);
        likes = findViewById(R.id.commentpostliks_num);
        comments = findViewById(R.id.commentpostcomment_num);
        list =  new ArrayList<>();
         post =(PostData) getIntent().getSerializableExtra("post");
        name.setText(post.getName());
        likes.setText(String.valueOf(post.getLike()));
        comments.setText(String.valueOf(post.getComment()));
        time.setText(post.getTime());
        listView= findViewById(R.id.comment_listview);
        LoadData("https://remusixapi.conveyor.cloud/api/Posts/GetComments?s_postid="+post.getId());
        adapter = new CommentAdapter(CommentsActivity.this,list);
        listView.setAdapter(adapter);
        Toast.makeText(CommentsActivity.this,post.getId(),Toast.LENGTH_LONG).show();
        AddCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
                date = sdf.format(new Date());
                addComment("https://remusixapi.conveyor.cloud/api/Posts/AddComment");

            }
        });
        likesImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addLike("https://remusixapi.conveyor.cloud/api/Posts/LikeDislike?s_postid="+post.getId()+"&s_userid="+getIntent().getStringExtra("userid"));

            }
        });
    }
    public void addLike(String url)
    {

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,url, null,new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                try
                {

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    Log.e("tag",e.toString());
                }
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {

            }
        });
        RequestQueue queue = Volley.newRequestQueue(CommentsActivity.this);
        queue.add(request);
    }
    public void addComment(String url)
    {
        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);

        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("tag","check");
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("tag","error");

            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap();
                MyData.put("PostID",post.getId());
                MyData.put("UserID", getIntent().getStringExtra("userid"));
                MyData.put("Comment", EditTextComment.getText().toString());
                MyData.put("CommentTime",date);
                return MyData;
            }
        };
        MyRequestQueue.add(MyStringRequest);
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
                        JSONObject comment = response.getJSONObject(i);
                        String username = comment.getString("UserName");
                        String UsernamePhoto = comment.getString("Photo");
                        String content = comment.getString("Comment");
                        String CommentTime = comment.getString("CommentTime");
                        list.add(new Comment(username,CommentTime,UsernamePhoto,content));
                        adapter.notifyDataSetChanged();
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    Log.e("tag",e.toString());
                }
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {

            }
        });
        RequestQueue queue = Volley.newRequestQueue(CommentsActivity.this);
        queue.add(request);

    }
}
