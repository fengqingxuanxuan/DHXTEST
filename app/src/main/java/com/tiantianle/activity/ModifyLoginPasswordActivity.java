package com.tiantianle.activity;

import android.os.Bundle;
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

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2017/2/13.
 * <p>
 * 修改(登录/交易)密码
 */
public class ModifyLoginPasswordActivity extends BaseActivity {

    //标题
    private ImageView img_back_title;
    private TextView tv_title_title;
    private TextView tv_deletes_title;

    private TextView tv_OiginalPassword;
    private EditText et_OiginalPassword;//原密码

    private TextView tv_New_Password;
    private EditText et_New_Password;//新密码

    private TextView tv_Confirm_Password;
    private EditText et_Confirm_Password;//确认新密码

    private Button bt_confirm_change;//确认修改
    private TextView tv_Message_Authentication;//短信验证

    private Boolean ifbool = true;//true为修改登录密码  false为修改交易密码

    private String New_Password;//新登录密码  新交易密码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifyloginpassword);
        ifbool = getIntent().getBooleanExtra("bool", true);

        LogUtil.e("ifbool = " + ifbool);
        InitView();
        setData();
    }

    private void setData() {
        if (ifbool) {
            //修改登录密码
            tv_title_title.setText(getString(R.string.Modify_login_password));
            tv_OiginalPassword.setText(getString(R.string.OiginalPassword));
            tv_New_Password.setText(getString(R.string.New_Password));
            tv_Confirm_Password.setText(getString(R.string.Confirm_Password));
        } else {
            //修改交易密码
            tv_title_title.setText(getString(R.string.Modify_trading_password));
            tv_OiginalPassword.setText(getString(R.string.Oiginal_Transaction_Password));
            tv_New_Password.setText(getString(R.string.New_Transaction_Password));
            tv_Confirm_Password.setText(getString(R.string.Confirm_Transaction_Password));
        }

        tv_deletes_title.setVisibility(View.GONE);
    }

    private void InitView() {

        img_back_title = (ImageView) findViewById(R.id.img_back_title);
        tv_title_title = (TextView) findViewById(R.id.tv_title_title);
        tv_deletes_title = (TextView) findViewById(R.id.tv_deletes_title);

        tv_OiginalPassword = (TextView) findViewById(R.id.tv_OiginalPassword);
        et_OiginalPassword = (EditText) findViewById(R.id.et_OiginalPassword);

        tv_New_Password = (TextView) findViewById(R.id.tv_New_Password);
        et_New_Password = (EditText) findViewById(R.id.et_New_Password);

        tv_Confirm_Password = (TextView) findViewById(R.id.tv_Confirm_Password);
        et_Confirm_Password = (EditText) findViewById(R.id.et_Confirm_Password);

        bt_confirm_change = (Button) findViewById(R.id.bt_confirm_change);
        tv_Message_Authentication = (TextView) findViewById(R.id.tv_Message_Authentication);


        img_back_title.setOnClickListener(new ModifyLoginPasswordActivity.MyOnClickListener());
        bt_confirm_change.setOnClickListener(new MyOnClickListener());
        tv_Message_Authentication.setOnClickListener(new MyOnClickListener());
    }

    private class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.img_back_title:// 返回

                    finish();
                    break;
                case R.id.bt_confirm_change:// 确认修改

                    getHttpData();
                    break;
                case R.id.tv_Message_Authentication:// 短信验证

                    IntentUtils.goTo(ModifyLoginPasswordActivity.this, MessageAuthenticationActivity.class, ifbool);
                    finish();
                    break;
            }

        }
    }


    private void getHttpData() {

        String OiginalPassword = et_OiginalPassword.getText().toString();
        String Confirm_Password = et_Confirm_Password.getText().toString();
        New_Password = et_New_Password.getText().toString();

        if (TextUtils.isEmpty(OiginalPassword)) {
            ToastUtils.show(ModifyLoginPasswordActivity.this, "原密码不能为空!", 2000);
            return;
        } else if (TextUtils.isEmpty(New_Password)) {
            ToastUtils.show(ModifyLoginPasswordActivity.this, "新密码不能为空!", 2000);
            return;
        } else if (TextUtils.isEmpty(Confirm_Password)) {
            ToastUtils.show(ModifyLoginPasswordActivity.this, "确认密码不能为空!", 2000);
            return;
        } else if (!Confirm_Password.equals(New_Password)) {
            LogUtil.e("Confirm_Password = " + Confirm_Password + "New_Password = " + New_Password);
            ToastUtils.show(ModifyLoginPasswordActivity.this, "密码输入不一致!", 2000);
            return;
        }

        showDialog(ModifyLoginPasswordActivity.this, "努力加载中...",false);

        RequestParams params = null;
        if(ifbool){
            params = new RequestParams(HttpApi.CHANGE_PASSWORD);
            params.addParameter("account", Constant.Config.account);
            params.addParameter("imei", Constant.Config.imei);
            params.addParameter("code", "");
            params.addParameter("oldpassword", OiginalPassword);
            params.addParameter("password", New_Password);
            params.addParameter("type", "0");
        }else{
            params = new RequestParams(HttpApi.MODIFY_trading_PASSWORD);
            params.addParameter("account", Constant.Config.account);
            params.addParameter("imei", Constant.Config.imei);
            params.addParameter("oldpwd", OiginalPassword);
            params.addParameter("password", New_Password);
        }

        x.http().post(params, new Callback.CommonCallback<String>() {

            //请求成功
            @Override
            public void onSuccess(String result) {

                LogUtil.e("联网成功 == " + result.toString());

                try {
                    JSONObject jsonObject = new JSONObject(result);
                    if (jsonObject.get("state").toString().equals("success")) {

                        if (ifbool) {
                            Constant.Config.password = New_Password;
                        }

                        ToastUtils.showShort(ModifyLoginPasswordActivity.this, "修改成功!");
                        finish();
                    } else {
                        ToastUtils.showShort(ModifyLoginPasswordActivity.this, jsonObject.get("biz_content").toString());
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
                ToastUtils.showShort(ModifyLoginPasswordActivity.this, "网络不给力！");
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
