package com.tiantianle.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.tiantianle.view.MyListview;
import com.tiantianle.R;
import com.tiantianle.activity.HongBaoRember;
import com.tiantianle.adapter.HongBaoListviewAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.iwgang.countdownview.CountdownView;

/**
 * Created by wyj on 2017/1/22.
 * 整点红包
 */

public class HongBaoFragment extends Fragment implements View.OnClickListener {

    protected View rootView;
    protected ImageView mBighMenu;
    protected TextView mBighText;
    protected TextView mTextHongbaoRecord;
    protected CountdownView mTextCountdownvHongbaoFram;
    private SlidingMenu mMenu;
    private MyListview mMyListview;
    private HongBaoListviewAdapter mHongBaoListviewAdapter;
    protected List<String> mList;
    private String  ha[]={"移动联通","加固100元","是的30元卡尔","移动联通","加固100元","是的30元卡尔","移动联通","加固100元","是的30元卡尔"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.item_fram_hongbao, null, false);
        mMenu = (SlidingMenu) getActivity().findViewById(R.id.activity_main);
        initView(rootView);
        mTextCountdownvHongbaoFram.start(995550000);
        initdate();


        return rootView;
    }

    private void initdate(){
        mList=new ArrayList<>();
        mHongBaoListviewAdapter=new HongBaoListviewAdapter();

        for (int i=0;i<ha.length;i++){
            mList.add(ha[i]);
        }

        mHongBaoListviewAdapter.setList(mList);
        mMyListview.setAdapter(mHongBaoListviewAdapter);
        mHongBaoListviewAdapter.notifyDataSetChanged();

    }


    private void initView(View rootView) {
        ;
        mBighMenu = (ImageView) rootView.findViewById(R.id.bigh_menu);
        mBighMenu.setOnClickListener(HongBaoFragment.this);
        mBighText = (TextView) rootView.findViewById(R.id.bigh_text);
        mTextHongbaoRecord = (TextView) rootView.findViewById(R.id.text_hongbao_record);
        mTextHongbaoRecord.setOnClickListener(HongBaoFragment.this);
        mTextCountdownvHongbaoFram = (CountdownView) rootView.findViewById(R.id.text_countdownv_hongbao_fram);
        mMyListview= (MyListview) rootView.findViewById(R.id.mliseview_hongbao_fram);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.bigh_menu) {
            mMenu.showMenu();
        } else if (view.getId() == R.id.text_hongbao_record) {
            Intent intent = new Intent(getContext(), HongBaoRember.class);
            startActivity(intent);
        }
    }
}
