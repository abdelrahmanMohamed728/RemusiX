package com.example.abdo.remusix;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SongTrendAdapter extends RecyclerView.Adapter<SongTrendAdapter.MyViewHolder> {
    ImageView img;
    ArrayList<Song>arrayList;
    private Context context;
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.trendlayout, viewGroup, false);
        context = viewGroup.getContext();
        return new MyViewHolder(itemView);
    }

    public SongTrendAdapter(ArrayList<Song>a) {
        arrayList = a;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        Picasso.with(context).load(arrayList.get(i).getImg()).into(img);
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Song s = arrayList.get(i);
                Toast.makeText(context,s.getName(),Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.trend1);

        }
    }
}
