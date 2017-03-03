package com.tiantianle.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tiantianle.BaseActivity;
import com.tiantianle.Bean.MessageBean;
import com.tiantianle.R;
import com.tiantianle.utils.Constant;
import com.tiantianle.utils.HttpApi;
import com.tiantianle.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2017/2/8.
 *
 * 具体消息信息界面
 */
public class SpecificMessageActivity extends BaseActivity{

    private ImageView img_back_title;

    private TextView tv_deletes_title;

    private TextView tv_title;

    private TextView tv_time;

    private TextView tv_body;//内容

    private int position;

    private MessageBean.message message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specificmessage);

        Intent intent = this.getIntent();
        message = (MessageBean.message) intent.getSerializableExtra("class");

        InitView();
        SetData();
    }

    private void SetData() {

        tv_deletes_title.setVisibility(View.VISIBLE);

        tv_deletes_title.setText("删除");

        tv_title.setText(message.getTitle());

        tv_time.setText(message.getTime());

        tv_body.setText(message.getBody());

    }

    private void InitView() {

        img_back_title = (ImageView) findViewById(R.id.img_back_title);
        tv_deletes_title = (TextView) findViewById(R.id.tv_deletes_title);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_time = (TextView) findViewById(R.id.tv_time);
        tv_body = (TextView) findViewById(R.id.tv_body);

        img_back_title.setOnClickListener(new MyOnClickListener());
        tv_deletes_title.setOnClickListener(new MyOnClickListener());

    }


    private class MyOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()){

                case R.id.img_back_title: //返回
                    finish();
                    break;

                case R.id.tv_deletes_title: //删除

                    getHttpDelete();
                    break;

            }
        }
    }

    //删除
    private void getHttpDelete() {

        showDialog(SpecificMessageActivity.this,"正在加载...",false);

        RequestParams params = new RequestParams(HttpApi.MY_MESSAGE_DELETE);
        params.addParameter("account", Constant.Config.account);
        params.addParameter("imei",Constant.Config.imei);
        params.addParameter("msgid", message.getMsgid());

        x.http().post(params, new Callback.CommonCallback<String>() {

            //请求成功
            @Override
            public void onSuccess(String result) {
                LogUtil.e("联网成功 == " + result.toString());

                try {
                    JSONObject jsonObject = new JSONObject(result);


                    if(jsonObject.get("state").equals("success")){

                        YesDelete();
                        ToastUtils.showShort(SpecificMessageActivity.this,jsonObject.get("biz_content").toString());

                    }else{
                        ToastUtils.showShort(SpecificMessageActivity.this,jsonObject.get("biz_content").toString());
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
                ToastUtils.showShort(SpecificMessageActivity.this, "网络不给力！");
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

    //删除成功后调用
    private void YesDelete() {

        Intent mIntent = new Intent();
        mIntent.putExtra("isbool",true);
        setResult(RESULT_OK, mIntent);
        finish();
    }
}
