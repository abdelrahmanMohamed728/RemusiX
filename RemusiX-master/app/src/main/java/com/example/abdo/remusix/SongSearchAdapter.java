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

import com.squareup.picasso.Picasso;

import java.util.List;

public class SongSearchAdapter extends ArrayAdapter<Song> {
    public SongSearchAdapter(@NonNull Context context, @NonNull List<Song> objects) {
        super(context, 0, objects);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.songsearchlayout,parent,false);
        Song s = getItem(position);
        ImageView img = convertView.findViewById(R.id.songsearchimg);
        TextView txt = convertView.findViewById(R.id.songsearchname);
        TextView txt2 = convertView.findViewById(R.id.artistsongsearchname);
        Picasso.with(getContext()).load(s.getImg()).into(img);
        txt.setText(s.getName());
        txt2.setText(s.getArtistName());
        return convertView;
    }
}
