package com.tiantianle.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.tiantianle.R;

/**
 * Created by wyj on 2017/1/22.
 */

public class HallFragment extends Fragment  {
    protected View rootView;
    protected ImageView mBighMenu;
    protected TextView mBighText;
    protected ImageView mBighDown;
    protected FrameLayout mFramHall;
    private  SlidingMenu menu;
    private JiSuFragment mJiSuFragment;
    private JiaNaDaFragment mJiaNaDaFragment;
    private BeiJingFragment mBeiJingFragment;
    private IndianaFragment mIndianaFragment;
    private HongBaoFragment mHongBaoFragment;
    private LocalBroadcastManager mLocalBroadcastManager;
    private MyLocalBroadcast mReceiver;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.item_fram_hall, null, false);
        initView(rootView);
        aDdFragment();

        mLocalBroadcastManager = LocalBroadcastManager.getInstance(getContext());
        mReceiver = new MyLocalBroadcast();
        IntentFilter  mFilter = new IntentFilter();
        mFilter.addAction("com.tiantianle.jisu");
        mFilter.addAction("com.tiantianle.jianada");
        mFilter.addAction("com.tiantianle.beijing");
        mFilter.addAction("com.tiantianle.indiana");
        mFilter.addAction("com.tiantianle.hongbao");
        mLocalBroadcastManager.registerReceiver(mReceiver, mFilter);
        return rootView;
    }
    private void aDdFragment(){
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        mJiSuFragment = new JiSuFragment();
        fragmentTransaction.add(R.id.fram_hall, mJiSuFragment);
        mJiaNaDaFragment = new JiaNaDaFragment();
        fragmentTransaction.add(R.id.fram_hall, mJiaNaDaFragment);
        mBeiJingFragment = new BeiJingFragment();
        fragmentTransaction.add(R.id.fram_hall, mBeiJingFragment);
        mIndianaFragment = new IndianaFragment();
        fragmentTransaction.add(R.id.fram_hall, mIndianaFragment);
        mHongBaoFragment = new HongBaoFragment();
        fragmentTransaction.add(R.id.fram_hall, mHongBaoFragment);
        fragmentTransaction.show(mJiSuFragment);
        fragmentTransaction.hide(mJiaNaDaFragment);
        fragmentTransaction.hide(mBeiJingFragment);
        fragmentTransaction.hide(mIndianaFragment);
        fragmentTransaction.hide(mHongBaoFragment);
        fragmentTransaction.commit();

    }
    private void initView(View rootView) {
        mFramHall = (FrameLayout) rootView.findViewById(R.id.fram_hall);
    }
    class MyLocalBroadcast extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(action.equals("com.tiantianle.jisu")){
                FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                fragmentTransaction.show(mJiSuFragment);
                fragmentTransaction.hide(mJiaNaDaFragment);
                fragmentTransaction.hide(mBeiJingFragment);
                fragmentTransaction.hide(mIndianaFragment);
                fragmentTransaction.hide(mHongBaoFragment);
                fragmentTransaction.commit();
            }else if(action.equals("com.tiantianle.jianada")){
                FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                fragmentTransaction.hide(mJiSuFragment);
                fragmentTransaction.show(mJiaNaDaFragment);
                fragmentTransaction.hide(mBeiJingFragment);
                fragmentTransaction.hide(mIndianaFragment);
                fragmentTransaction.hide(mHongBaoFragment);
                fragmentTransaction.commit();

            } else if(action.equals("com.tiantianle.beijing")){
                FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                fragmentTransaction.hide(mJiSuFragment);
                fragmentTransaction.hide(mJiaNaDaFragment);
                fragmentTransaction.show(mBeiJingFragment);
                fragmentTransaction.hide(mIndianaFragment);
                fragmentTransaction.hide(mHongBaoFragment);
                fragmentTransaction.commit();
            }else if(action.equals("com.tiantianle.indiana")){
                FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                fragmentTransaction.hide(mJiSuFragment);
                fragmentTransaction.hide(mJiaNaDaFragment);
                fragmentTransaction.hide(mBeiJingFragment);
                fragmentTransaction.show(mIndianaFragment);
                fragmentTransaction.hide(mHongBaoFragment);
                fragmentTransaction.commit();
            }else if(action.equals("com.tiantianle.hongbao")){
                FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                fragmentTransaction.hide(mJiSuFragment);
                fragmentTransaction.hide(mJiaNaDaFragment);
                fragmentTransaction.hide(mBeiJingFragment);
                fragmentTransaction.hide(mIndianaFragment);
                fragmentTransaction.show(mHongBaoFragment);
                fragmentTransaction.commit();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLocalBroadcastManager.unregisterReceiver(mReceiver);
    }
}
