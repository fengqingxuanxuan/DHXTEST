package com.tiantianle.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.tiantianle.BaseActivity;
import com.tiantianle.MainActivity;
import com.tiantianle.R;
import com.tiantianle.utils.Constant;
import com.tiantianle.utils.IntentUtils;

/**
 * Created by Administrator on 2017/2/22.
 * 首页activity
 */

public class HomePageActivity extends BaseActivity {

    private final int YES = 1;
    private final int NO = 2;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case YES:

                    IntentUtils.goTo(HomePageActivity.this, LoginActivity.class);
                    finish();
                    break;

                case NO:

                    IntentUtils.goTo(HomePageActivity.this, MainActivity.class);
                    finish();
                    break;

            }
        }
    };

    private SharedPreferences sp;
    private TelephonyManager telephonyManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        sp = getSharedPreferences("ttl", MODE_PRIVATE);
        Constant.Config.sp = sp;
        Constant.Config.account = sp.getString("account", null);
        Constant.Config.password = sp.getString("password", null);

        telephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        Constant.Config.imei = telephonyManager.getDeviceId();

        if (TextUtils.isEmpty(Constant.Config.account)) {
            mHandler.sendEmptyMessageDelayed(YES, 3000);
        } else {
            mHandler.sendEmptyMessageDelayed(NO, 3000);
        }
    }

    @Override
    protected void onDestroy() {
        mHandler.removeCallbacksAndMessages(null);
        mHandler = null;
        super.onDestroy();
    }
}
