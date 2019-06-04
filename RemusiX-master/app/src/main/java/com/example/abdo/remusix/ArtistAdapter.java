package com.example.abdo.remusix;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ArtistAdapter extends ArrayAdapter<Song> {
    public ArtistAdapter(@NonNull Context context,  @NonNull List<Song> objects) {
        super(context,0,  objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView =   LayoutInflater.from(getContext()).inflate(R.layout.artisttopsonglayout, null, false);
        String name = getItem(position).getName();
        TextView textView = convertView.findViewById(R.id.topSong);
        textView.setText(name);
        return convertView;
    }
}
