package com.example.abdo.remusix;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.abdo.remusix.api.NearbyUsersResponse;
import com.example.abdo.remusix.api.RegisterServiceResponse;

import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder> {

    private Context mycontext;
    private List<NearbyUsersResponse> myusers;

    public LocationAdapter(Context mycontext, List<NearbyUsersResponse> myusers) {
        this.mycontext = mycontext;
        this.myusers = myusers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mycontext).inflate(R.layout.location_user_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        NearbyUsersResponse user = myusers.get(i);
        viewHolder.location_username.setText(user.getUserName());
        viewHolder.location_user_email.setText(user.getEmail());

    }

    @Override
    public int getItemCount() {
        return myusers.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView location_username;
        public ImageView location_user_img;

        public TextView location_user_email;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

           location_user_img=itemView.findViewById(R.id.location_user_img);
            location_username=itemView.findViewById(R.id.location_username);
            location_user_email=itemView.findViewById(R.id.location_user_email);
        }

    }

}
