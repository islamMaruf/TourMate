package com.example.maruf.tourmate;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PageAdapter extends FragmentPagerAdapter {
    private int numberOfTabs;

    public PageAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.numberOfTabs = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case  0:
                return new EventFragment();
            case 1:
                return new NearbyFragment();
            case 2:
                return new WeatherFragment();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
