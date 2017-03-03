package com.tiantianle.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.tiantianle.BaseActivity;
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
 * <p>
 * 短信验证
 */
public class MessageAuthenticationActivity extends BaseActivity {

    private ImageView img_back_title;
    private TextView tv_title_title;
    private TextView tv_deletes_title;

    private TextView tv_Transaction_prompt;//修改提示
    private TextView tv_number;//电话号码
    private EditText et_code;//验证码
    private Button bt_get_code;//获取验证码
    private EditText et_password;//输入密码
    private Button bt_confirm_change;//确认修改
    private TextView tv_Password_Authentication;//密码验证

    private TimeCount time;

    private int code;//验证码

    private Boolean ifbool = true;  //true为修改登录密码  false 为修改交易密码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messageauthentication);
        time = new TimeCount(60000, 1000);
        ifbool = getIntent().getBooleanExtra("bool",true);
        System.out.println("ifbool = " + ifbool);

        InitView();

    }

    private void InitView() {

        img_back_title = (ImageView) findViewById(R.id.img_back_title);
        tv_title_title = (TextView) findViewById(R.id.tv_title_title);
        tv_deletes_title = (TextView) findViewById(R.id.tv_deletes_title);


        tv_Transaction_prompt = (TextView) findViewById(R.id.tv_Transaction_prompt);
        tv_number = (TextView) findViewById(R.id.tv_number);
        et_code = (EditText) findViewById(R.id.et_code);
        bt_get_code = (Button) findViewById(R.id.bt_get_code);
        et_password = (EditText) findViewById(R.id.et_password);
        bt_confirm_change = (Button) findViewById(R.id.bt_confirm_change);
        tv_Password_Authentication = (TextView) findViewById(R.id.tv_Password_Authentication);

        tv_number.setText(Constant.Config.account);
        tv_title_title.setText("短信验证");

        if(ifbool){
            tv_Transaction_prompt.setText(getString(R.string.Transaction_prompt));
        }else{
            tv_Transaction_prompt.setText(getString(R.string.Trading_prompt));
        }


        img_back_title.setOnClickListener(new MyOnClickListener());
        bt_get_code.setOnClickListener(new MyOnClickListener());
        bt_confirm_change.setOnClickListener(new MyOnClickListener());
        tv_Password_Authentication.setOnClickListener(new MyOnClickListener());

    }


    private class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.img_back_title://返回

                    finish();
                    break;
                case R.id.bt_get_code://获取验证码
                    code = (int) ((Math.random() * 10000) + 1000);
                    System.out.println("code = " + code);
                    String number = tv_number.getText().toString();

                    if (!VerificationUtil.isValidTelNumber(number)) {
                        ToastUtils.show(MessageAuthenticationActivity.this, "手机号不正确!", 2000);
                    } else {
                        time.start();
                        getHttpData();
                    }

                    break;
                case R.id.bt_confirm_change://确认修改

                    String codes = et_code.getText().toString();

                    String password = et_password.getText().toString();

                    if (TextUtils.isEmpty(codes) || TextUtils.isEmpty(password)) {
                        ToastUtils.show(MessageAuthenticationActivity.this, "验证码或密码不能为空!", 3000);
                    } else if (!codes.equals(code + "")) {
                        ToastUtils.show(MessageAuthenticationActivity.this, "验证码错误!", 3000);
                    } else {
                        setHttpPassword();
                    }
                    break;
                case R.id.tv_Password_Authentication://密码验证

                    IntentUtils.goTo(MessageAuthenticationActivity.this, ModifyLoginPasswordActivity.class,ifbool);
                    finish();
                    break;
            }

        }
    }

    //确认修改请求
    private void setHttpPassword() {
        RequestParams params = new RequestParams(HttpApi.CHANGE_PASSWORD);
        params.addParameter("account", Constant.Config.account);
        params.addParameter("imei", Constant.Config.imei);
        params.addParameter("code", et_code.getText().toString());
        params.addParameter("password", et_password.getText().toString());
        params.addParameter("type", "1");
        showDialog(MessageAuthenticationActivity.this,"努力加载中...",false);

        x.http().post(params, new Callback.CommonCallback<String>() {

            //请求成功
            @Override
            public void onSuccess(String result) {

                LogUtil.e("联网成功 == " + result.toString());

                try {
                    JSONObject jsonObject = new JSONObject(result);
                    if (jsonObject.get("state").toString().equals("success")) {

                        ToastUtils.showShort(MessageAuthenticationActivity.this, "修改成功!");
                        finish();
                    } else {
                        ToastUtils.showShort(MessageAuthenticationActivity.this, jsonObject.get("biz_content").toString());
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
                ToastUtils.showShort(MessageAuthenticationActivity.this, "网络不给力！");
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

    //发送验证码请求
    private void getHttpData() {

        RequestParams params = new RequestParams(HttpApi.GET_CODE);
        params.addParameter("account", Constant.Config.account);
        params.addParameter("imei", Constant.Config.imei);
        params.addParameter("code", code);
        params.addParameter("type", "找回密码");

        x.http().post(params, new Callback.CommonCallback<String>() {

            //请求成功
            @Override
            public void onSuccess(String result) {

                LogUtil.e("联网成功 == " + result.toString());

                try {
                    JSONObject jsonObject = new JSONObject(result);
                    if (jsonObject.get("state").toString().equals("success")) {

                        ToastUtils.showShort(MessageAuthenticationActivity.this, jsonObject.get("biz_content").toString());
                        time.start();
                    } else {
                        ToastUtils.showShort(MessageAuthenticationActivity.this, jsonObject.get("biz_content").toString());
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
                ToastUtils.showShort(MessageAuthenticationActivity.this, "网络不给力！");
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

    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {// 计时完毕时触发
            bt_get_code.setText("重新验证");
            bt_get_code.setClickable(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程显示
            bt_get_code.setClickable(false);
            bt_get_code.setText(millisUntilFinished / 1000 + "秒");

        }
    }

    // 如果出现意外中断计时，重置状态
    private void stopTimer() {
        if (null != time) {
            time.cancel();
            bt_get_code.setText("重新验证");
            bt_get_code.setClickable(true);
        }
    }
}
