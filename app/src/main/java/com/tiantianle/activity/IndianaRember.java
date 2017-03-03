package com.tiantianle.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.tiantianle.Bean.NewDongTaiBean;
import com.tiantianle.R;
import com.tiantianle.adapter.IndinanFragmentAdapter;
import com.tiantianle.fragment.AllFragmentIndinanrember;
import com.tiantianle.fragment.JieXiaoFragmentIndinanremb;
import com.tiantianle.fragment.JingXingFragmentIndinanremb;

import java.util.ArrayList;
import java.util.List;

public class IndianaRember extends AppCompatActivity implements View.OnClickListener {


    protected ImageView mImgIndianaRemberBack;
    protected TabLayout mTabIndianaremb;
    protected ViewPager mVpIndianaramb;
    protected LinearLayout mActivityIndianaRember;
    private IndinanFragmentAdapter mFragmentAdapter;
    private List<Fragment> mList;
    private LocalBroadcastManager mLocalBroadcastManager;
    private AdapterBroadcast mReceiver;
    private List<NewDongTaiBean.BizContentBean> mBizContentBeen=new ArrayList<>();
    private List<NewDongTaiBean.BizContentBean>mRes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_indiana_rember);
        initView();
        initTaV();
        mLocalBroadcastManager=LocalBroadcastManager.getInstance(this);
        mReceiver = new AdapterBroadcast();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.tiantianle.adapter");
        mLocalBroadcastManager.registerReceiver(mReceiver, filter);

    }

    private void initView() {
        mImgIndianaRemberBack = (ImageView) findViewById(R.id.img_indianaRember_back);
        mImgIndianaRemberBack.setOnClickListener(IndianaRember.this);
        mTabIndianaremb = (TabLayout) findViewById(R.id.tab_indianaremb);
        mVpIndianaramb = (ViewPager) findViewById(R.id.vp_indianaramb);
        mActivityIndianaRember = (LinearLayout) findViewById(R.id.activity_indiana_rember);
    }

    private void initTaV() {
        mFragmentAdapter = new IndinanFragmentAdapter(getSupportFragmentManager());
        mList = new ArrayList<>();
        mList.add(AllFragmentIndinanrember.newInstance());
        mList.add(JingXingFragmentIndinanremb.newInstance());
        mList.add(JieXiaoFragmentIndinanremb.newInstance());
        mFragmentAdapter.setFragments((ArrayList<Fragment>) mList);
        mVpIndianaramb.setAdapter(mFragmentAdapter);
        mTabIndianaremb.setupWithViewPager(mVpIndianaramb);
        mTabIndianaremb.setTabMode(TabLayout.MODE_FIXED);


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.img_indianaRember_back) {
            finish();
        }
    }

    class AdapterBroadcast extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocalBroadcastManager.unregisterReceiver(mReceiver);
    }
}
