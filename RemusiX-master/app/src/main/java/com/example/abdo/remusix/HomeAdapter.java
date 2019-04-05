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


public class HomeAdapter extends ArrayAdapter<HomeData> {

    ImageView image,Singer_image;
    TextView name,activiation,num_like,num_comment;

    public HomeAdapter(@NonNull Context context, int resource, ArrayList<HomeData> info) {
        super(context, resource,info);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {

        if(convertView==null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.home_listitem, null, false);
        }
        image=convertView.findViewById(R.id.img);
        Singer_image=convertView.findViewById(R.id.singer_img);
        name= convertView.findViewById(R.id.name);
        activiation=convertView.findViewById(R.id.time);
        num_comment=convertView.findViewById(R.id.comment_num);
        num_like=convertView.findViewById(R.id.liks_num);


        HomeData dataClass= getItem(position);

        if(dataClass != null) {
            image.setImageResource(dataClass.getImg());
            Singer_image.setImageResource(dataClass.getSinger_img());

            name.setText(dataClass.getName());
            activiation.setText(dataClass.getTime());
            num_comment.setText(dataClass.getComment());
            num_like.setText(dataClass.getLikes());


        }


        return convertView ;
    }

}


