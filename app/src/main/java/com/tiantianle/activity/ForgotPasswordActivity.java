package com.tiantianle.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
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
 * Created by Administrator on 2017/2/15.
 * <p>
 * 忘记密码 / 立即注册
 */
public class ForgotPasswordActivity extends BaseActivity {

    private ImageView img_back_title;
    private TextView tv_title_title;

    private EditText et_number; //手机号
    private EditText et_code;//验证码
    private EditText et_Password; //密码

    private TextView tv_get_code;//获取验证码
    private Button bt_reset_password;//重置密码

    private TimeCount time;

    private int code;//生成随机的验证码

    private Boolean isbool = false;  //true 为注册界面  false 为登录界面

    private String number;
    private String codes;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        isbool = getIntent().getBooleanExtra("bool", false);
        LogUtil.e("isbool = " + isbool);
        InitView();
        time = new TimeCount(60000, 1000);

    }

    private void InitView() {

        img_back_title = (ImageView) findViewById(R.id.img_back_title);
        tv_title_title = (TextView) findViewById(R.id.tv_title_title);

        et_number = (EditText) findViewById(R.id.et_number);
        et_code = (EditText) findViewById(R.id.et_code);
        et_Password = (EditText) findViewById(R.id.et_Password);

        tv_get_code = (TextView) findViewById(R.id.tv_get_code);
        bt_reset_password = (Button) findViewById(R.id.bt_reset_password);

        if (isbool) {
            tv_title_title.setText(getString(R.string.Registered));
            et_Password.setHint(getString(R.string.Please_Passwords));
            bt_reset_password.setText(getString(R.string.Register_Now));
        } else {
            tv_title_title.setText(getString(R.string.Forgot_Passwords));
            et_Password.setHint(getString(R.string.Please_New_Password));
            bt_reset_password.setText(getString(R.string.Reset_Password));
        }


        img_back_title.setOnClickListener(new MyOnClickListener());
        tv_get_code.setOnClickListener(new MyOnClickListener());
        bt_reset_password.setOnClickListener(new MyOnClickListener());

    }

    private class MyOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.img_back_title: //返回

                    finish();
                    break;
                case R.id.tv_get_code: //获取验证码

                    getHttpData();

                    break;
                case R.id.bt_reset_password: //重置密码  立即注册

                    setHttpData();

                    break;
            }
        }
    }


    private void setHttpData() {

         number = et_number.getText().toString();
         codes = et_code.getText().toString();
         password = et_Password.getText().toString();

        if (!VerificationUtil.isValidTelNumber(number)) {
            ToastUtils.show(ForgotPasswordActivity.this, "手机号不正确!", 2000);
            return;
        }else if(!codes.equals(code+"")){
            ToastUtils.show(ForgotPasswordActivity.this, "验证码错误!", 2000);
            return;
        }else if(TextUtils.isEmpty(password)){
            ToastUtils.show(ForgotPasswordActivity.this, "密码不能为空!", 2000);
            return;
        }else if(TextUtils.isEmpty(Constant.Config.imei)){
            ToastUtils.show(ForgotPasswordActivity.this, "设备号为空，请重新启动!", 2000);
            return;
        }
        RequestParams params = null;

        if (isbool) {

            //注册请求
            params = new RequestParams(HttpApi.REGISTERED);
            params.addParameter("account", number);
            params.addParameter("imei", Constant.Config.imei);
            params.addParameter("code", code);
            params.addParameter("password", password);
        }else{

            //找回密码请求
            params = new RequestParams(HttpApi.RESET_PASSWPRD);
            params.addParameter("account", number);
            params.addParameter("imei", Constant.Config.imei);
            params.addParameter("code", code);
            params.addParameter("password", password);
        }

        x.http().post(params, new Callback.CommonCallback<String>() {

            //请求成功
            @Override
            public void onSuccess(String result) {

                LogUtil.e("联网成功 == " + result.toString());

                try {
                    JSONObject jsonObject = new JSONObject(result);
                    if (jsonObject.get("state").toString().equals("success")) {

                        ToastUtils.showShort(ForgotPasswordActivity.this, jsonObject.get("biz_content").toString());

                        Constant.Config.account = number;
                        Constant.Config.account = password;

//                        SharedPreferences.Editor editor = MainActivity.sp.edit();
//                        editor.putString("account", number);
//                        editor.putString("password", password);
//                        editor.commit();
                        IntentUtils.goTo(ForgotPasswordActivity.this, MainActivity.class);
                    } else {
                        ToastUtils.showShort(ForgotPasswordActivity.this, jsonObject.get("biz_content").toString());
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
                ToastUtils.showShort(ForgotPasswordActivity.this, "网络不给力！");
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

    //获取验证码
    private void getHttpData() {

        code = (int) ((Math.random() * 10000) + 1000);
        LogUtil.e("code = " + code);

        number = et_number.getText().toString();

        if (!VerificationUtil.isValidTelNumber(number)) {
            ToastUtils.show(ForgotPasswordActivity.this, "手机号不正确!", 2000);
            return;
        }else if(TextUtils.isEmpty(Constant.Config.imei)){
            ToastUtils.show(ForgotPasswordActivity.this, "设备号为空，请重新启动!", 2000);
            return;
        }
        LogUtil.e("imei = " + Constant.Config.imei);

        time.start();

        RequestParams params = null;

        if (isbool) {
            params = new RequestParams(HttpApi.GET_CODE);
            params.addParameter("account", number);
            params.addParameter("imei", Constant.Config.imei);
            params.addParameter("code", code);
            params.addParameter("type", "注册应用");
        }else{
            params = new RequestParams(HttpApi.GET_CODE);
            params.addParameter("account", number);
            params.addParameter("imei", Constant.Config.imei);
            params.addParameter("code", code);
            params.addParameter("type", "找回密码");
        }


        x.http().post(params, new Callback.CommonCallback<String>() {

            //请求成功
            @Override
            public void onSuccess(String result) {

                LogUtil.e("联网成功 == " + result.toString());

                try {
                    JSONObject jsonObject = new JSONObject(result);
                    if (jsonObject.get("state").toString().equals("success")) {

                        ToastUtils.showShort(ForgotPasswordActivity.this, jsonObject.get("biz_content").toString());
                        time.start();
                    } else {
                        ToastUtils.showShort(ForgotPasswordActivity.this, jsonObject.get("biz_content").toString());
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
                ToastUtils.showShort(ForgotPasswordActivity.this, "验证码发送失败！");
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
            tv_get_code.setText("重新验证");
            tv_get_code.setClickable(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程显示
            tv_get_code.setClickable(false);
            tv_get_code.setText(millisUntilFinished / 1000 + "秒");

        }
    }

    // 如果出现意外中断计时，重置状态
    private void stopTimer() {
        if (null != time) {
            time.cancel();
            tv_get_code.setText("重新验证");
            tv_get_code.setClickable(true);
        }
    }
}
