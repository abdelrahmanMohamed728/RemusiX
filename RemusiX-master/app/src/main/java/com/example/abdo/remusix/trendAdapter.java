package com.example.abdo.remusix;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class trendAdapter extends RecyclerView.Adapter<trendAdapter.MyViewHolder> {
    ImageView img;
    ArrayList<Integer>arrayList;
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.trendlayout, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    public trendAdapter(ArrayList<Integer>a) {
        arrayList = a;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        img.setImageResource(arrayList.get(i));
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
