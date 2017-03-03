package com.tiantianle.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.tiantianle.R;
import com.tiantianle.fragment.MyBettingFragment;
import com.tiantianle.fragment.WinningName;

/*
*  开奖明细
* */
public class Detailed extends AppCompatActivity implements View.OnClickListener {


    protected TextView mTextDetailedQihao;
    protected TextView mTextDetailedQihaonum;
    protected TextView mTextDetailedTime;
    protected TextView mTextDetailedNum;
    protected TextView mTextDetailedNumjia;
    protected TextView mTextDetailedNunjieguo;
    protected LinearLayout mActivityDetailed;
    protected ImageView mBackMenuDeailed;
    protected TextView mBighTextDetailed;
    protected RadioButton mRadioZhongjianDetailed;
    protected RadioButton mRadioMyDetailed;
    protected FrameLayout mFramDetailed;
    private MyBettingFragment mMyBettingFragment;
    private WinningName mWinningName;
    private String expect;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_detailed);
        initView();
       Intent intent = getIntent();
        expect=intent.getStringExtra("expect");
        type =intent.getStringExtra("type");
        String frist=intent.getStringExtra("frist");
        String second=intent.getStringExtra("second");
        String three=intent.getStringExtra("three");
        String resultnum=intent.getStringExtra("resultnum");
        String time=intent.getStringExtra("time");
        mTextDetailedQihaonum.setText(expect);
        mTextDetailedTime.setText(time);
        mTextDetailedNumjia.setText(frist+"+"+second+"+"+three+"=");
        mTextDetailedNunjieguo.setText(resultnum);
        if(type.equals("11")){
            mBighTextDetailed.setText("加拿大28开奖明细");
        }else if(type.equals("10")){
            mBighTextDetailed.setText("北京28开奖明细");
        }else if(type.equals("9")){
            mBighTextDetailed.setText("极速28开奖明细");
        }
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        mMyBettingFragment =MyBettingFragment.newInstance(expect,type);
        fragmentTransaction.add(R.id.fram_detailed, mMyBettingFragment);
        mWinningName = WinningName.newInstance(expect,type);
        fragmentTransaction.add(R.id.fram_detailed, mWinningName);
        fragmentTransaction.hide(mMyBettingFragment);
        fragmentTransaction.show(mWinningName);
        fragmentTransaction.commit();

    }


    private void initView() {
        mTextDetailedQihao = (TextView) findViewById(R.id.text_detailed_qihao);
        mTextDetailedQihaonum = (TextView) findViewById(R.id.text_detailed_qihaonum);
        mTextDetailedTime = (TextView) findViewById(R.id.text_detailed_time);
        mTextDetailedNum = (TextView) findViewById(R.id.text_detailed_num);
        mTextDetailedNumjia = (TextView) findViewById(R.id.text_detailed_numjia);
        mTextDetailedNunjieguo = (TextView) findViewById(R.id.text_detailed_nunjieguo);
        mActivityDetailed = (LinearLayout) findViewById(R.id.activity_detailed);
        mBackMenuDeailed = (ImageView) findViewById(R.id.back_menu_deailed);
        mBackMenuDeailed.setOnClickListener(Detailed.this);
        mBighTextDetailed = (TextView) findViewById(R.id.bigh_text_detailed);
        mRadioZhongjianDetailed = (RadioButton) findViewById(R.id.radio_zhongjian_detailed);
        mRadioZhongjianDetailed.setOnClickListener(Detailed.this);
        mRadioMyDetailed = (RadioButton) findViewById(R.id.radio_my_detailed);
        mRadioMyDetailed.setOnClickListener(Detailed.this);
        mFramDetailed = (FrameLayout) findViewById(R.id.fram_detailed);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.radio_zhongjian_detailed) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.hide(mMyBettingFragment);
            fragmentTransaction.show(mWinningName);
            fragmentTransaction.commit();
        } else if (view.getId() == R.id.radio_my_detailed) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.show(mMyBettingFragment);
            fragmentTransaction.hide(mWinningName);
            fragmentTransaction.commit();
        } else if (view.getId() == R.id.back_menu_deailed) {
            finish();
        }
    }
}
