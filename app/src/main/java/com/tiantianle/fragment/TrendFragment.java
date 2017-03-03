package com.tiantianle.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.tiantianle.R;

/**
 * Created by wyj on 2017/1/22.
 */

public class TrendFragment extends Fragment implements View.OnClickListener {
    protected View rootView;
    protected TextView mTextTrend;
    protected ImageView mImgSeting;
    protected ImageView mImgEnlarge;
    private SlidingMenu menu;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.item_fram_trend, null, false);
        FragmentActivity activity = getActivity();
        menu = (SlidingMenu) activity.findViewById(R.id.activity_main);
        menu.setSelected(false);
        initView(rootView);
        return rootView;
    }

    private void initView(View rootView) {
        mTextTrend = (TextView) rootView.findViewById(R.id.text_trend);
        mImgSeting = (ImageView) rootView.findViewById(R.id.img_seting);
        mImgSeting.setOnClickListener(TrendFragment.this);
        mImgEnlarge = (ImageView) rootView.findViewById(R.id.img_enlarge);
        mImgEnlarge.setOnClickListener(TrendFragment.this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.img_seting) {

        } else if (view.getId() == R.id.img_enlarge) {

        }
    }
}
