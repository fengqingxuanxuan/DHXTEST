package com.tiantianle.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by PengBo  on 2017/2/5.
 */

public class IndinanFragmentAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Fragment> mFragments;
    private String str[]={"全部","进行中","已揭晓"};
    public IndinanFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setFragments(ArrayList<Fragment> fragments) {
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return str[position];
    }
}
