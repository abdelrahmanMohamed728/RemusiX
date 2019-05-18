package com.example.abdo.remusix;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;


import com.example.abdo.remusix.R;

import java.util.ArrayList;

public class CommentsActivity extends AppCompatActivity {


    ListView list;
    CommentAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_comments);
//    public Comment(String usrname, String comment_time, String likes, String comment, int user_img) {
        ArrayList<Comment> comment= new ArrayList<>();
        comment.add(new Comment ("aya","2 min ago","+2","hello here",R.drawable.test1));
        comment.add(new Comment ("Ahmed","6 min ago","+5","hello Comment here",R.drawable.test1));
        comment.add(new Comment ("Mohmaed","12 min ago","+2","hello here",R.drawable.test1));
        list= findViewById(R.id.comment_listview);
        list.setAdapter(adapter);
    }
}
