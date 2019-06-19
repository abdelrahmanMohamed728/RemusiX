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

import com.example.abdo.remusix.api.RegisterServiceResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UserSearchByEmailAdapter extends ArrayAdapter<RegisterServiceResponse> {
    public UserSearchByEmailAdapter(@NonNull Context context, @NonNull List<RegisterServiceResponse> objects) {
        super(context, 0, objects);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.location_user_item,parent,false);
        RegisterServiceResponse s = getItem(position);
        ImageView img = convertView.findViewById(R.id.location_user_img);
        TextView txt = convertView.findViewById(R.id.location_username);
        TextView txt2 = convertView.findViewById(R.id.location_user_email);
       if (s.getPhoto()!=null)Picasso.with(getContext()).load(s.getPhoto()).into(img);
       else Picasso.with(getContext()).load(R.drawable.amrdiab).into(img);
        txt.setText(s.getFirstName());
        txt2.setText(s.getUserName());
        return convertView;
    }
}
