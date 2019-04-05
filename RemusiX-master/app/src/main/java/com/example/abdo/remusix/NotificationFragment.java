package com.example.abdo.remusix;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends Fragment {
   ListView listView;

    public NotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_notification, container, false);
        listView = v.findViewById(R.id.notiList);
        ArrayList<notification>arrayList = new ArrayList<>();
        arrayList.add(new notification(R.drawable.lovly,"Ahmed","shared a post","2m ago"));
        notificationAdapter adapter = new notificationAdapter(getContext(),arrayList);
        listView.setAdapter(adapter);
        return v;
    }

}
