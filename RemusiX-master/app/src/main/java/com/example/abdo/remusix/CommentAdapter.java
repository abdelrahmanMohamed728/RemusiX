package com.example.abdo.remusix;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

public class CommentAdapter extends ArrayAdapter<Comment> {

        ImageView user_image;
        TextView user_name,num_like,comment_tv,comment_time;

  public CommentAdapter(@NonNull Context context, int resource, ArrayList<Comment> info) {
        super(context, resource,info);
        }


@NonNull
@Override
public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
        {

        if(convertView==null) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.comments_listitem, null, false);
        }
        user_image=convertView.findViewById(R.id.comment_user_img);
        user_name=convertView.findViewById(R.id.comment_username);
        num_like= convertView.findViewById(R.id.comment_likes_num);
        comment_tv=convertView.findViewById(R.id.tv_comment);
        comment_time=convertView.findViewById(R.id.comment_time);


        Comment comment= getItem(position);

        if(comment != null) {
        user_image.setImageResource(comment.getUser_img ());

        user_name.setText(comment.getUsrname ());
        num_like.setText(comment.getLikes ());
        comment_tv.setText(comment.getComment ());
        comment_time.setText(comment.getComment_time ());


        }


        return convertView ;
        }

        }
