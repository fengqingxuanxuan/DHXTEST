package com.tiantianle.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tiantianle.BaseActivity;
import com.tiantianle.MainActivity;
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
 * Created by Administrator on 2017/2/21.
 * 设置交易密码
 */
public class SetTradingPassword extends BaseActivity{

    private ImageView img_back_title;   //返回
    private TextView tv_title_title;    //title
    private TextView tv_deletes_title;

    private LinearLayout ll_Confirm_Password;//确认密码布局

    private TextView tv_OiginalPassword;
    private EditText et_OiginalPassword;//交易密码

    private TextView tv_New_Password;
    private EditText et_New_Password;//确认密码


    private Button bt_confirm_change;//确认修改
    private TextView tv_Message_Authentication;//短信验证

    private String password;    //交易密码
    private String confirmPassword; //确认密码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifyloginpassword);
        IntentView();

    }

    private void IntentView() {

        img_back_title = (ImageView) findViewById(R.id.img_back_title);
        tv_title_title = (TextView) findViewById(R.id.tv_title_title);
        tv_deletes_title = (TextView) findViewById(R.id.tv_deletes_title);

        ll_Confirm_Password = (LinearLayout) findViewById(R.id.ll_Confirm_Password);

        tv_OiginalPassword = (TextView) findViewById(R.id.tv_OiginalPassword);
        et_OiginalPassword = (EditText) findViewById(R.id.et_OiginalPassword);

        tv_New_Password = (TextView) findViewById(R.id.tv_New_Password);
        et_New_Password = (EditText) findViewById(R.id.et_New_Password);

        bt_confirm_change = (Button) findViewById(R.id.bt_confirm_change);
        tv_Message_Authentication = (TextView) findViewById(R.id.tv_Message_Authentication);

        tv_deletes_title.setVisibility(View.GONE);
        ll_Confirm_Password.setVisibility(View.GONE);
        tv_Message_Authentication.setVisibility(View.GONE);

        tv_title_title.setText("设置交易密码");
        tv_OiginalPassword.setText("交易密码");
        tv_New_Password.setText("确认密码");

        img_back_title.setOnClickListener(new MyOnClickListener());
        bt_confirm_change.setOnClickListener(new MyOnClickListener());
    }

    private class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.img_back_title:   //返回

                    finish();
                    break;

                case R.id.bt_confirm_change:   //确认修改

                    setTradingPassword();
                    break;
            }
        }
    }

    //设置交易密码
    private void setTradingPassword() {
        password = et_OiginalPassword.getText().toString();
        confirmPassword = et_New_Password.getText().toString();

        if(!password.equals(confirmPassword)){
            ToastUtils.show(SetTradingPassword.this,"密码前后不一致!",3000);
            return;
        }else if(TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)){
            ToastUtils.show(SetTradingPassword.this,"交易密码不能为空!",3000);
            return;
       }

        showDialog(SetTradingPassword.this, "努力加载中...",false);


        RequestParams params = new RequestParams(HttpApi.MODIFY_TRADING_PASSWORD);
        params.addParameter("account", Constant.Config.account);
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

                        ToastUtils.showShort(SetTradingPassword.this, jsonObject.get("biz_content").toString());
                        IntentUtils.goTo(SetTradingPassword.this, MainActivity.class);
                    } else {
                        ToastUtils.showShort(SetTradingPassword.this, jsonObject.get("biz_content").toString());
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
                ToastUtils.showShort(SetTradingPassword.this, "网络不给力！");
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
