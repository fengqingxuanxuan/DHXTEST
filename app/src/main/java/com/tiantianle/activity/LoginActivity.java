package com.tiantianle.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.tiantianle.BaseActivity;
import com.tiantianle.MainActivity;
import com.tiantianle.R;
import com.tiantianle.utils.Constant;
import com.tiantianle.utils.HttpApi;
import com.tiantianle.utils.IntentUtils;
import com.tiantianle.utils.ToastUtils;
import com.tiantianle.utils.VerificationUtil;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2017/2/14.
 * 登录界面
 */
public class LoginActivity extends BaseActivity {

    private ImageView img_back_title;
    private TextView tv_title_title;

    private EditText et_number; //手机号
    private EditText et_Password; //密码
    private Button bt_login_immediately;//立即登录
    private TextView tv_forgot_password;//忘记密码
    private TextView tv_register_now;//立即注册

    private String number;//手机号
    private String password;//密码


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        InitView();

    }

    private void InitView() {
        img_back_title = (ImageView) findViewById(R.id.img_back_title);
        tv_title_title = (TextView) findViewById(R.id.tv_title_title);

        et_number = (EditText) findViewById(R.id.et_number);
        et_Password = (EditText) findViewById(R.id.et_Password);
        bt_login_immediately = (Button) findViewById(R.id.bt_login_immediately);
        tv_forgot_password = (TextView) findViewById(R.id.tv_forgot_password);
        tv_register_now = (TextView) findViewById(R.id.tv_register_now);

        tv_title_title.setText("登录");

        img_back_title.setOnClickListener(new MyOnClickListener());
        bt_login_immediately.setOnClickListener(new MyOnClickListener());
        tv_forgot_password.setOnClickListener(new MyOnClickListener());
        tv_register_now.setOnClickListener(new MyOnClickListener());
    }

    private class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.img_back_title:// 返回

                    finish();
                    break;
                case R.id.bt_login_immediately:// 立即登录

                    getHttpData();
                    break;
                case R.id.tv_forgot_password:// 忘记密码

                    IntentUtils.goTo(LoginActivity.this, ForgotPasswordActivity.class, false);
                    break;
                case R.id.tv_register_now:// 立即注册

                    IntentUtils.goTo(LoginActivity.this, ForgotPasswordActivity.class, true);
                    break;
            }

        }
    }

    private void getHttpData() {
         number = et_number.getText().toString();
         password = et_Password.getText().toString();

        if (TextUtils.isEmpty(number) || !VerificationUtil.isValidTelNumber(number)) {
            ToastUtils.show(LoginActivity.this, "电话号码不符合规则!", 2000);
            return;
        } else if (TextUtils.isEmpty(password)) {
            ToastUtils.show(LoginActivity.this, "密码不能为空!", 2000);
            return;
        } else if (TextUtils.isEmpty(Constant.Config.imei)) {
            ToastUtils.show(LoginActivity.this, "设备号为空，请重新启动!", 2000);
            return;
        }

        showDialog(LoginActivity.this, "努力加载中...",true);


        final RequestParams params = new RequestParams(HttpApi.LONGIN);
        params.addParameter("account", number);
        params.addParameter("imei", Constant.Config.imei);
        params.addParameter("password", password);


        x.http().post(params, new Callback.CommonCallback<String>() {

            //请求成功
            @Override
            public void onSuccess(String result) {

                LogUtil.e("联网成功 == " + result.toString());

                try {
                    JSONObject jsonObject = new JSONObject(result);
                    if (jsonObject.get("state").toString().equals("success")) {

                        ToastUtils.showShort(LoginActivity.this, jsonObject.get("biz_content").toString());

                        Constant.Config.account = number;
                        Constant.Config.password = password;

//                        SharedPreferences.Editor editor = MainActivity.sp.edit();
//                        editor.putString("account", number);
//                        editor.putString("password", password);
//                        editor.commit();

                        finish();
                    } else {
                        ToastUtils.showShort(LoginActivity.this, jsonObject.get("biz_content").toString());
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    LogUtil.e("解析异常！");
                }

            }

            //请求失败
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e("联网失败 == " + ex.getMessage());
                ToastUtils.showShort(LoginActivity.this, "网络不给力！");
            }

            //主动调用取消请求的回调方法
            @Override
            public void onCancelled(CancelledException cex) {
                LogUtil.e("onCancelled == " + cex.getMessage());
            }

            // 不管成功或者失败最后都会回调该接口
            @Override
            public void onFinished() {
                LogUtil.e("onFinished == ");
                closeDialog();
            }
        });
    }
}
