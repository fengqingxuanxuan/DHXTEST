package com.tiantianle.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.allen.library.SuperTextView;
import com.tiantianle.BaseActivity;
import com.tiantianle.R;
import com.tiantianle.utils.IntentUtils;

/**
 * Created by Administrator on 2017/2/10.
 * 密码设置
 */
public class PasswordActivity extends BaseActivity {

    private SuperTextView tv_login_password;// 修改登录密码
    private SuperTextView tv_trading_password;// 修改交易密码

    private ImageView img_back_title;//返回

    private TextView tv_title_title;//标题

    private TextView tv_deletes_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        InitView();
        HttpData();
    }

    private void HttpData() {
        tv_login_password = (SuperTextView) findViewById(R.id.tv_login_password);

        tv_trading_password = (SuperTextView) findViewById(R.id.tv_trading_password);

        img_back_title = (ImageView) findViewById(R.id.img_back_title);

        tv_title_title = (TextView) findViewById(R.id.tv_title_title);

        tv_deletes_title = (TextView) findViewById(R.id.tv_deletes_title);

        tv_title_title.setText("密码设置");

        tv_deletes_title.setVisibility(View.GONE);

        tv_login_password.setOnClickListener(new MyOnClickListener());

        tv_trading_password.setOnClickListener(new MyOnClickListener());

        img_back_title.setOnClickListener(new MyOnClickListener());

    }

    private void InitView() {

    }

    private class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.tv_login_password: //修改登录密码

                    IntentUtils.goTo(PasswordActivity.this, ModifyLoginPasswordActivity.class,true);
                    break;

                case R.id.tv_trading_password://修改交易密码

                    IntentUtils.goTo(PasswordActivity.this, ModifyLoginPasswordActivity.class,false);
                    break;

                case R.id.img_back_title: //返回

                    finish();

                    break;
            }

        }
    }
}
