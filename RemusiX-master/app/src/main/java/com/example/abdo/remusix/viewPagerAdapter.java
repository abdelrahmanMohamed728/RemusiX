package com.example.abdo.remusix;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class viewPagerAdapter extends FragmentPagerAdapter {
    public viewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        if (i==0)
        {
            return new HomeFragment();
        }
        else if (i==1)
            return new ProfileFragment();
        else if (i==2)
            return new LocationFragment();
        else if (i==3)
            return new NotificationFragment();
        else if (i==4)
            return new TrendFragment();
        else if (i==5)
            return new SearchFragment();
        else
            return null;
    }

    @Override
    public int getCount() {
        return 6;
    }
}
