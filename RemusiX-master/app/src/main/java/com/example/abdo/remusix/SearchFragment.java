package com.example.abdo.remusix;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class SearchFragment extends Fragment {


    public SearchFragment() {
    }
    ViewPager viewPager1;
    EditText search;
    Button btn1;
    TabLayout tab1;
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_search, container, false);
        viewPager1 = v.findViewById(R.id.viewpager2);

        tab1 = v.findViewById(R.id.tabLayout2);
        SearchViewPagerAdapter adapter = new SearchViewPagerAdapter(getActivity().getSupportFragmentManager());
        viewPager1.setAdapter(adapter);

        tab1.setupWithViewPager(viewPager1);
        tab1.getTabAt(0).setText("Artists");
        tab1.getTabAt(1).setText("Songs");
        tab1.getTabAt(2).setText("Users");
        return v;
    }

}
