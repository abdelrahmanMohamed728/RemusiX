package com.example.abdo.remusix;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TrendFragment extends Fragment {
      RecyclerView recyclerView;
      RecyclerView recyclerView2;

    public TrendFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_trend, container, false);
        ArrayList<Integer>arrayList = new ArrayList<>();
        ArrayList<Integer>arrayList2 = new ArrayList<>();
        arrayList.add(R.drawable.test1);
        arrayList.add(R.drawable.test2);
        arrayList.add(R.drawable.test1);
        arrayList.add(R.drawable.test2);
        arrayList.add(R.drawable.test1);
        arrayList.add(R.drawable.test2);
        arrayList.add(R.drawable.test1);
        arrayList.add(R.drawable.test2);
        trendAdapter adapter = new trendAdapter(arrayList);
        arrayList2.add(R.drawable.test1);
        arrayList2.add(R.drawable.test2);
        arrayList2.add(R.drawable.test1);
        arrayList2.add(R.drawable.test2);
        arrayList2.add(R.drawable.test1);
        arrayList2.add(R.drawable.test2);
        arrayList2.add(R.drawable.test1);
        arrayList2.add(R.drawable.test2);
        arrayList2.add(R.drawable.test1);
        arrayList2.add(R.drawable.test2);
        arrayList2.add(R.drawable.test1);
        arrayList2.add(R.drawable.test2);
        trendAdapter adapter2 = new trendAdapter(arrayList2);
        recyclerView = v.findViewById(R.id.recycler1);
        recyclerView2 = v.findViewById(R.id.recycler2);
        GridLayoutManager manager = new GridLayoutManager(getContext(), 12, GridLayoutManager.VERTICAL, false);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
               return 4;
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView2.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        recyclerView2.setAdapter(adapter2);
        return v;
    }

}
