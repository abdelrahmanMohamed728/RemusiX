package com.example.abdo.remusix;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SearchViewPagerAdapter extends FragmentPagerAdapter {
    public SearchViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        if (i==0)
        {
            return new ArtistSearchFragment();
        }
        else if (i==1)
            return new SongSearchFragment();
        else
            return new UserSearchFragment();

    }

    @Override
    public int getCount() {
        return 3;
    }
}
