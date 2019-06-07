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

public class ArtistSearchAdapter extends ArrayAdapter<Artist> {
    public ArtistSearchAdapter(@NonNull Context context,  @NonNull List<Artist> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.artistsearchlayout,parent,false);
        Artist artist = getItem(position);
        ImageView img = convertView.findViewById(R.id.artistsearchimg);
        TextView txt = convertView.findViewById(R.id.artistsearchname);
        Picasso.with(getContext()).load(artist.getImg()).into(img);
        txt.setText(artist.getName());
        return convertView;
    }
}
