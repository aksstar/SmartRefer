package com.example.aakash.smartrefer;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Home on 3/21/2017.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
ArrayList<Fragment> fragments=new ArrayList<>();
    ArrayList<String> tabTitles=new ArrayList<>();
    public void addFragments(Fragment fragments, String titles)
    {
        this.fragments.add(fragments);
        this.tabTitles.add(titles);
    }
    public ViewPagerAdapter(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        return fragments.get(position);
    }


    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles.get(position);
    }
}
