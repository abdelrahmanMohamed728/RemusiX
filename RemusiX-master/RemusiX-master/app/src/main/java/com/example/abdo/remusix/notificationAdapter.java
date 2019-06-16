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
import java.util.List;

public class notificationAdapter extends ArrayAdapter<notification> {
    public notificationAdapter(@NonNull Context context, @NonNull ArrayList<notification> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @Nullable ViewGroup parent) {
        convertView= LayoutInflater.from(getContext()).inflate(R.layout.notificationslayout,parent,false);
        notification notification1 = getItem(position);
        ImageView img1 = convertView.findViewById(R.id.notiProfile);
        TextView name = convertView.findViewById(R.id.notiName);
        TextView post = convertView.findViewById(R.id.notiPost);
        TextView time = convertView.findViewById(R.id.notiTime);
        img1.setImageResource(notification1.getImage());
        name.setText(notification1.getUsername());
        post.setText(notification1.getAction());
        time.setText(notification1.getTime());
        return convertView;
    }
}
