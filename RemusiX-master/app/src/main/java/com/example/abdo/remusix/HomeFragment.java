package com.example.abdo.remusix;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    ListView list;
    HomeAdapter adapter;
    public HomeFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("tag","home");
         View view= inflater.inflate(R.layout.fragment_home, container, false);
        ArrayList<HomeData> data= new ArrayList<>();
        data.add(new HomeData("Amna","Active 2 hours ago",R.drawable.pic,R.drawable.amrd,"40k","60k"));
        data.add(new HomeData("Manar","Active 3 hours ago",R.drawable.pic,R.drawable.singer,"120k","50k"));
        data.add(new HomeData("Essraa","Active 5 hours ago",R.drawable.pic,R.drawable.amrd,"300","20"));
         data.add(new HomeData("Rana","Active 12 hours ago",R.drawable.pic,R.drawable.singer,"20","500"));
        list= view.findViewById(R.id.listview);
        adapter = new HomeAdapter(getContext (),R.layout.fragment_home,data);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getActivity(),CommentsActivity.class));
            }
        });
        return view;
    }

}
