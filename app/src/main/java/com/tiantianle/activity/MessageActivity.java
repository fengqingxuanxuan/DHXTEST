package com.tiantianle.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.tiantianle.BaseActivity;
import com.tiantianle.Bean.MessageBean;
import com.tiantianle.R;
import com.tiantianle.adapter.MessageAdapter;
import com.tiantianle.utils.Constant;
import com.tiantianle.utils.HttpApi;
import com.tiantianle.utils.ToastUtils;
import com.tiantianle.view.PullToRefreshView;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/2/7.
 * 我的消息
 */

public class MessageActivity extends BaseActivity {

    protected static final int YES = 1; //请求成功

    private ImageView img_back_title;//返回

    private TextView tv_deletes_title;//清空

    private int page = 1;

    private PullToRefreshView ll_message;

    private ListView lv_message;

    private MessageBean MeBean = new MessageBean();

    private List<MessageBean.message> bean = new ArrayList<MessageBean.message>();

    private MessageAdapter adapter;


    private Handler mHnaHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case YES: //请求成功

                    bean.addAll(MeBean.getBiz_content());
                    if (adapter != null) {
                        adapter.notifyDataSetChanged();
                    } else {
                        adapter = new MessageAdapter(MessageActivity.this, bean);
                        lv_message.setAdapter(adapter);
                    }

                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        InitView();
        HttpData();
    }

    /* 重写 onActivityResult()*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Boolean bool = data.getBooleanExtra("isbool", false);
        switch (requestCode) {

            case DEFAULT_KEYS_DIALER:
                if (bool) {
                    page = 1;
                    bean.clear();
                    HttpData();
                }

                break;
            default:
                break;
        }
    }

    private void HttpData() {

        showDialog(MessageActivity.this,"正在加载...",false);

        RequestParams params = new RequestParams(HttpApi.MY_MESSAGE);
        LogUtil.e("account = " + Constant.Config.account);
        params.addParameter("account", Constant.Config.account);
        params.addParameter("page", page + "");
        params.addParameter("imei", Constant.Config.imei);
        x.http().post(params, new Callback.CommonCallback<String>() {

            //请求成功
            @Override
            public void onSuccess(String result) {
                LogUtil.e("联网成功 == " + result.toString());

                try {
                    JSONObject jsonObject = new JSONObject(result);
                    if (jsonObject.get("state").equals("success") && !jsonObject.get("biz_content").equals("暂无信息")) {
                        MeBean.getBiz_content().clear();
                        MeBean = gs.fromJson(result, MessageBean.class);

                        if (MeBean.getBiz_content() != null && MeBean.getBiz_content().size() > 0) {
                            Message mg = Message.obtain(mHnaHandler, YES);
                            mg.sendToTarget();
                        } else {
                            ToastUtils.showShort(MessageActivity.this, "暂未发现更多！");
                        }
                    } else {

                        ToastUtils.showShort(MessageActivity.this, jsonObject.get("biz_content").toString());
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
                ToastUtils.showShort(MessageActivity.this, "网络不给力！");
            }

            //主动调用取消请求的回调方法
            @Override
            public void onCancelled(CancelledException cex) {
                LogUtil.e("onCancelled == " + cex.getMessage());
                onLoad();
            }

            // 不管成功或者失败最后都会回调该接口
            @Override
            public void onFinished() {
                LogUtil.e("onFinished == ");
                onLoad();
                closeDialog();

            }
        });

    }

    private void InitView() {
        img_back_title = (ImageView) findViewById(R.id.img_back_title);
        tv_deletes_title = (TextView) findViewById(R.id.tv_deletes_title);
        lv_message = (ListView) findViewById(R.id.lv_message);
        ll_message = (PullToRefreshView) findViewById(R.id.ll_message);

        tv_deletes_title.setVisibility(View.VISIBLE);

        img_back_title.setOnClickListener(new MyOnClickListener());
        tv_deletes_title.setOnClickListener(new MyOnClickListener());
        lv_message.setOnItemClickListener(new MyOnItemClickListener());

        ll_message.setLastUpdated(getTime());//更新刷新时间
        ll_message.setOnFooterRefreshListener(new MyOnFooterRefreshListener());//设置上拉加载监听
        ll_message.setOnHeaderRefreshListener(new MyOnFooterRefreshListener());//设置下拉刷新监听
    }

    class MyOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {

                case R.id.img_back_title:  //返回
                    finish();
                    break;

                case R.id.tv_deletes_title:  //清空

                    getHttpDelete();

                    break;

            }
        }
    }

    private void getHttpDelete() {

        //清空个人消息

        showDialog(MessageActivity.this,"正在加载...",false);
        RequestParams params = new RequestParams(HttpApi.MY_MESSAGE_DELETE_ALL);
        params.addParameter("account", Constant.Config.account);
        params.addParameter("imei", Constant.Config.imei);
        x.http().post(params, new Callback.CommonCallback<String>() {

            //请求成功
            @Override
            public void onSuccess(String result) {
                LogUtil.e("联网成功 == " + result.toString());

                try {
                    JSONObject jsonObject = new JSONObject(result);


                    if (jsonObject.get("state").equals("success")) {


                        if (bean != null && bean.size() > 0) {
                            bean.clear();
                            adapter.notifyDataSetChanged();
                        }

                        ToastUtils.showShort(MessageActivity.this, jsonObject.get("biz_content").toString());

                    } else {
                        ToastUtils.showShort(MessageActivity.this, jsonObject.get("biz_content").toString());
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
                ToastUtils.showShort(MessageActivity.this, "网络不给力！");
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

    class MyOnItemClickListener implements android.widget.AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

//            IntentUtils.goTo(MessageActivity.this,SpecificMessageActivity.class,bean.get(position));

            Intent intent = new Intent();
            intent.setClass(MessageActivity.this, SpecificMessageActivity.class);
            intent.putExtra("class", (Serializable) bean.get(position));
            startActivityForResult(intent, DEFAULT_KEYS_DIALER);
        }
    }


    private class MyOnFooterRefreshListener implements PullToRefreshView.OnFooterRefreshListener, PullToRefreshView.OnHeaderRefreshListener {


        //上拉加载
        @Override
        public void onFooterRefresh(PullToRefreshView view) {
            page++;
            HttpData();
        }

        //下拉刷新
        @Override
        public void onHeaderRefresh(PullToRefreshView view) {

            ll_message.setLastUpdated(getTime());//更新刷新时间
            page = 1;
            bean.clear();
            HttpData();

        }
    }

    // 获得数据后一定要调用onLoad()方法，否则刷新会一直进行，根本停不下来
    private void onLoad() {
        ll_message.onHeaderRefreshComplete();
        ll_message.onFooterRefreshComplete();

    }

    //获取当前系统时间
    private String getTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd HH:mm:ss");//设置日期显示格式
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String time = formatter.format(curDate);// 将时间装换为设置好的格式
        return time;
    }
}
