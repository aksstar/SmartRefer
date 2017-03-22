package com.example.aakash.smartrefer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by Home on 3/21/2017.
 */

public class wasteSwipe extends AppCompatActivity{
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_toolbar);
        toolbar = (Toolbar) findViewById(R.id.toolBAR);
        setSupportActionBar(toolbar);
        tabLayout = (TabLayout)findViewById(R.id.tab_LAYOUT);
        viewPager = (ViewPager)findViewById(R.id.view_PAGER);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragments(new HomeFragment(),"Home");
        viewPagerAdapter.addFragments(new FormFragment(),"Form");
        viewPagerAdapter.addFragments(new MapFragment(),"Maps");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
