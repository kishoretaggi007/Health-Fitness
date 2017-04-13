package com.example.personal.myapplication2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class Pager extends FragmentPagerAdapter {

    public Pager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position ==0) {
            return new Tab1();
        } else if (position == 1) {
            return new Tab2();
        } else if(position==2)
        {return  new Tab3();}
        else {
        return new Others();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}