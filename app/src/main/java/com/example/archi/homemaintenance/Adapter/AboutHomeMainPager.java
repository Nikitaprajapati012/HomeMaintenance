package com.example.archi.homemaintenance.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.archi.homemaintenance.Fragment.AboutHomeQuetion.QueEightFragment;
import com.example.archi.homemaintenance.Fragment.AboutHomeQuetion.QueElevenFragment;
import com.example.archi.homemaintenance.Fragment.AboutHomeQuetion.QueFiveFragment;
import com.example.archi.homemaintenance.Fragment.AboutHomeQuetion.QueFourFragment;
import com.example.archi.homemaintenance.Fragment.AboutHomeQuetion.QueNineFragment;
import com.example.archi.homemaintenance.Fragment.AboutHomeQuetion.QueOneFragment;
import com.example.archi.homemaintenance.Fragment.AboutHomeQuetion.QueSevenFragment;
import com.example.archi.homemaintenance.Fragment.AboutHomeQuetion.QueSixFragment;
import com.example.archi.homemaintenance.Fragment.AboutHomeQuetion.QueTenFragment;
import com.example.archi.homemaintenance.Fragment.AboutHomeQuetion.QueThirteenFragment;
import com.example.archi.homemaintenance.Fragment.AboutHomeQuetion.QueThreeFragment;
import com.example.archi.homemaintenance.Fragment.AboutHomeQuetion.QueTwelveFragment;
import com.example.archi.homemaintenance.Fragment.AboutHomeQuetion.QueTwoFragment;


public class AboutHomeMainPager extends FragmentPagerAdapter {
    public Fragment fragment;

    public AboutHomeMainPager(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int position) {
        fragment = null;

        switch (position) {
            case 0:
                fragment = new QueOneFragment();
                break;
            case 1:
                fragment = new QueTwoFragment();
                break;
            case 2:
                fragment = new QueThreeFragment();
                break;
            case 3:
                fragment = new QueFourFragment();
                break;
            case 4:
                fragment = new QueFiveFragment();
                break;
            case 5:
                fragment = new QueSixFragment();
                break;
            case 6:
                fragment = new QueSevenFragment();
                break;
            case 7:
                fragment = new QueEightFragment();
                break;
            case 8:
                fragment = new QueNineFragment();
                break;
            case 9:
                fragment = new QueTenFragment();
                break;
            case 10:
                fragment = new QueElevenFragment();
                break;
            case 11:
                fragment = new QueTwelveFragment();
                break;
            case 12:
                fragment = new QueThirteenFragment();
                break;

        }
        return fragment;
    }


    @Override
    public int getCount() {
        return 13;
    }


}