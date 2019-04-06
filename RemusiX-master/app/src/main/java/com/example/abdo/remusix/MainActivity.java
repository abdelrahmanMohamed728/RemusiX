package com.example.abdo.remusix;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
  ViewPager viewPager1;
  TabLayout tab1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager1 = findViewById(R.id.viewpager1);
        tab1 = findViewById(R.id.tabLayout1);
        viewPagerAdapter adapter = new viewPagerAdapter(getSupportFragmentManager());
        viewPager1.setAdapter(adapter);
        tab1.setupWithViewPager(viewPager1);
        tab1.getTabAt(0).setIcon(R.drawable.home);
        tab1.getTabAt(1).setIcon(R.drawable.profile);
        tab1.getTabAt(2).setIcon(R.drawable.location);
        tab1.getTabAt(3).setIcon(R.drawable.notification);
        tab1.getTabAt(4).setIcon(R.drawable.trend);
    }
}
