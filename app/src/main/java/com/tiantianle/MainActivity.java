package com.tiantianle;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.tiantianle.fragment.HallFragment;
import com.tiantianle.fragment.MyFragment;
import com.tiantianle.fragment.ShaoppingFragment;
import com.tiantianle.fragment.TrendFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    protected SlidingMenu mMenu;
    protected ImageView mPepoHead;
    protected TextView mVip;
    protected TextView mTextVip;
    protected TextView mMagicbean;
    protected TextView mTextMagicbean;
    protected RadioButton mJisu28;
    protected RadioButton mJianada28;
    protected RadioButton mBeijing28;
    protected RadioButton mIndiana;
    protected RadioButton mHongbao;
    protected RadioButton mBigHome;
    protected RadioButton mTrend;
    protected RadioButton mShaopping;
    protected RadioButton mMY;
    private long firstTime=0;
    private HallFragment mHallFragment;
    private TrendFragment mTrendFragment;
    private ShaoppingFragment mShaoppingFragment;
    private MyFragment mMyFragment;
    private LocalBroadcastManager mLocalBroadcastManager;
    private String  imei;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
        initView();
        initGigHome();
        initShaopp();
        initTrend();
        initMe();
        addFragment();
       /* TelephonyManager telephonyManager=(TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        imei=telephonyManager.getDeviceId();*/
    }

    private void addFragment(){
        FragmentTransaction mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        mHallFragment = new HallFragment();
        mFragmentTransaction.add(R.id.fram_big_hall, mHallFragment);
        mTrendFragment = new TrendFragment();
       /* Bundle bundle=new Bundle();
         bundle.putString("imei",imei);
        mTrendFragment.setArguments(bundle);*/
        mFragmentTransaction.add(R.id.fram_big_hall, mTrendFragment);
        mShaoppingFragment = new ShaoppingFragment();
        mFragmentTransaction.add(R.id.fram_big_hall, mShaoppingFragment);
        mMyFragment = new MyFragment();
        mFragmentTransaction.add(R.id.fram_big_hall, mMyFragment);
        mFragmentTransaction.show(mHallFragment);
        mFragmentTransaction.hide(mTrendFragment);
        mFragmentTransaction.hide(mShaoppingFragment);
        mFragmentTransaction.hide(mMyFragment);
        mFragmentTransaction.commit();


    }

    private void initView() {
        mMenu = (SlidingMenu) findViewById(R.id.activity_main);
        mMenu.setMode(SlidingMenu.LEFT);
        mMenu.setFadeDegree(0.35f);
        mMenu.setMenu(R.layout.behind_menu);
        mMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);


        mPepoHead = (ImageView) findViewById(R.id.pepoHead);
        mPepoHead.setOnClickListener(MainActivity.this);
        mVip = (TextView) findViewById(R.id.vip);
        mTextVip = (TextView) findViewById(R.id.text_vip);
        mMagicbean = (TextView) findViewById(R.id.magicbean);
        mTextMagicbean = (TextView) findViewById(R.id.text_magicbean);
        mJisu28 = (RadioButton) findViewById(R.id.jisu28);
        mJisu28.setChecked(true);
        mJisu28.setOnClickListener(MainActivity.this);
        mJianada28 = (RadioButton) findViewById(R.id.jianada28);
        mJianada28.setOnClickListener(MainActivity.this);
        mBeijing28 = (RadioButton) findViewById(R.id.beijing28);
        mBeijing28.setOnClickListener(MainActivity.this);
        mIndiana = (RadioButton) findViewById(R.id.indiana);
        mIndiana.setOnClickListener(MainActivity.this);
        mHongbao = (RadioButton) findViewById(R.id.hongbao);
        mHongbao.setOnClickListener(MainActivity.this);

        mBigHome = (RadioButton) findViewById(R.id.radiob_bighome_main);
        mBigHome.setChecked(true);
        mBigHome.setOnClickListener(MainActivity.this);
        mTrend = (RadioButton) findViewById(R.id.radiob_trend_main);
        mTrend.setOnClickListener(MainActivity.this);
        mShaopping = (RadioButton) findViewById(R.id.radiob_shaopp_main);
        mShaopping.setOnClickListener(MainActivity.this);
        mMY = (RadioButton) findViewById(R.id.radiob_me_main);
        mMY.setOnClickListener(MainActivity.this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.pepoHead) {

        } else if (view.getId() == R.id.jisu28) {

            Intent intent=new Intent("com.tiantianle.jisu");
            mLocalBroadcastManager.sendBroadcast(intent);
            mMenu.showContent();
        } else if (view.getId() == R.id.jianada28) {
            Intent intent=new Intent("com.tiantianle.jianada");
            mLocalBroadcastManager.sendBroadcast(intent);
            mMenu.showContent();
        } else if (view.getId() == R.id.beijing28) {
            Intent intent=new Intent("com.tiantianle.beijing");
            mLocalBroadcastManager.sendBroadcast(intent);
            mMenu.showContent();
        } else if (view.getId() == R.id.indiana) {
            Intent intent=new Intent("com.tiantianle.indiana");
            mLocalBroadcastManager.sendBroadcast(intent);
            mMenu.showContent();
        } else if (view.getId() == R.id.hongbao) {
            Intent intent=new Intent("com.tiantianle.hongbao");
            mLocalBroadcastManager.sendBroadcast(intent);
            mMenu.showContent();
        }else if(view.getId()==R.id.radiob_bighome_main){
            mMenu.setSlidingEnabled(true);
          ;   FragmentTransaction mFragmentTransaction = getSupportFragmentManager().beginTransaction();
            mFragmentTransaction.show(mHallFragment);
            mFragmentTransaction.hide(mTrendFragment);
            mFragmentTransaction.hide(mShaoppingFragment);
            mFragmentTransaction.hide(mMyFragment);
            mFragmentTransaction.commit();

        }else if(view.getId()==R.id.radiob_trend_main){
            mMenu.setSlidingEnabled(false);
            FragmentTransaction mFragmentTransaction = getSupportFragmentManager().beginTransaction();
            mFragmentTransaction.hide(mHallFragment);
            mFragmentTransaction.show(mTrendFragment);
            mFragmentTransaction.hide(mShaoppingFragment);
            mFragmentTransaction.hide(mMyFragment);
            mFragmentTransaction.commit();
        }else if(view.getId()==R.id.radiob_shaopp_main){
            mMenu.setSlidingEnabled(false);
            FragmentTransaction mFragmentTransaction = getSupportFragmentManager().beginTransaction();
            mFragmentTransaction.hide(mHallFragment);
            mFragmentTransaction.hide(mTrendFragment);
            mFragmentTransaction.show(mShaoppingFragment);
            mFragmentTransaction.hide(mMyFragment);
            mFragmentTransaction.commit();
        }else if(view.getId()==R.id.radiob_me_main){
            mMenu.setSlidingEnabled(false);
            FragmentTransaction mFragmentTransaction = getSupportFragmentManager().beginTransaction();
            mFragmentTransaction.hide(mHallFragment);
            mFragmentTransaction.hide(mTrendFragment);
            mFragmentTransaction.hide(mShaoppingFragment);
            mFragmentTransaction.show(mMyFragment);
            mFragmentTransaction.commit();
        }
    }

    private void initGigHome() {
        Drawable drawableShout = getResources().getDrawable(R.drawable.hall);
        drawableShout.setBounds(0, 0, 60, 60);
        mBigHome.setCompoundDrawables(null, drawableShout, null, null);
    }

    private void initShaopp() {
        Drawable drawable = getResources().getDrawable(R.drawable.shaopping);
        drawable.setBounds(0, 0, 60, 60);
        mShaopping.setCompoundDrawables(null, drawable, null, null);
    }

    private void initTrend() {
        Drawable drawable = getResources().getDrawable(R.drawable.trend);
        drawable.setBounds(0, 0, 60, 60);
        mTrend.setCompoundDrawables(null, drawable, null, null);

    }

    private void initMe() {
        Drawable drawable = getResources().getDrawable(R.drawable.my);
        drawable.setBounds(0, 0, 60, 60);
        mMY.setCompoundDrawables(null, drawable, null, null);
    }
    @Override
/*    public boolean onKeyUp(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (event.getAction() == KeyEvent.ACTION_UP && event.getRepeatCount() == 0) {
            mMenu.showContent();
            }
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }*/
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            mMenu.showContent();
            if(System.currentTimeMillis()-firstTime>2000){
                Toast.makeText(this,"再点击一次退出天天乐",Toast.LENGTH_SHORT).show();
                firstTime=System.currentTimeMillis();
            }else{
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //super.onSaveInstanceState(outState);
    }
}