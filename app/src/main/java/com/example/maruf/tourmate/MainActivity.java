package com.example.maruf.tourmate;

import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabItem tabNearby;
    private TabItem tabWeather;
    private TabItem tabEvent;
    private  PageAdapter pageAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.app_name));
        setSupportActionBar(toolbar);

        tabLayout = findViewById(R.id.tablayout);
        tabEvent = findViewById(R.id.tabEvent);
        tabNearby = findViewById(R.id.tabNearby);
        viewPager = findViewById(R.id.viewPager);

        pageAdapter = new PageAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition() == 1){
                    toolbar.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.colorNearbyPrimary));
                    tabLayout.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.colorNearbyPrimary));
                    getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this,R.color.colorNearByPrimaryDark));

                }else if(tab.getPosition() == 2 ){
                    toolbar.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.colorWeatherPrimary));
                    tabLayout.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.colorWeatherPrimary));
                    getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this,R.color.colorWeatherPrimaryDark));
                }else{
                    toolbar.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.colorPrimary));
                    tabLayout.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.colorPrimary));
                    getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this,R.color.colorPrimaryDark));
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));





    }
}