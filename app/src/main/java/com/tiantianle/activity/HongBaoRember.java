package com.tiantianle.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.tiantianle.BaseActivity;
import com.tiantianle.Bean.RedRECORDBean;
import com.tiantianle.R;
import com.tiantianle.adapter.RecordAdapter;
import com.tiantianle.utils.Constant;
import com.tiantianle.utils.HttpApi;
import com.tiantianle.utils.ToastUtils;
import com.tiantianle.view.PullToRefreshView;

import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
* 兑换记录界面
* */
public class HongBaoRember extends BaseActivity {

    private final int YES = 1;

    protected ImageView mImgHongbaoRemberBack;  //返回按钮
    protected ListView mListvView;
    private PullToRefreshView ll_record;

    private RedRECORDBean redbean; // 中奖红包列表bean
    private List<RedRECORDBean.BizContentBean> redlist = new ArrayList<RedRECORDBean.BizContentBean>(); // 所有中奖信息

    private RecordAdapter adapter;

    private int page = 1;

    private Handler mHnaHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case YES:

                    if (adapter != null) {
                        adapter.notifyDataSetChanged();
                    } else {
                        adapter = new RecordAdapter(HongBaoRember.this, redlist);
                        mListvView.setAdapter(adapter);
                    }
                break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_hong_bao_rember);
        initView();
        
        getHttpData();
    }

    private void getHttpData() {

        RequestParams params = new RequestParams(HttpApi.RED_ENVELOPE_RECORD);
        params.addParameter("account", Constant.Config.account);
        params.addParameter("imei", Constant.Config.imei);

        params.addParameter("page", page);
        params.addParameter("ismyself", "1");

        x.http().post(params, new Callback.CommonCallback<String>() {

            //请求成功
            @Override
            public void onSuccess(String result) {

                LogUtil.e("联网成功 == " + result.toString());

                redbean = gs.fromJson(result, RedRECORDBean.class);

                if (redbean.getState().equals("success")) {

                    if (redlist == null) {
                        redlist = new ArrayList<RedRECORDBean.BizContentBean>();
                    }


                    if (redbean.getBiz_content() != null && redbean.getBiz_content().size() > 0) {

                        redlist.addAll(redbean.getBiz_content());
                        Message mg = Message.obtain(mHnaHandler, YES);
                        mg.sendToTarget();
                    }else{
                        ToastUtils.showShort(HongBaoRember.this,"暂无记录!");
                    }
                }

            }

            //请求失败
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e("联网失败 == " + ex.getMessage());
                ToastUtils.showShort(HongBaoRember.this, "网络不给力！");
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
                onLoad();
            }

        });

    }

    // 获得数据后一定要调用onLoad()方法，否则刷新会一直进行，根本停不下来
    private void onLoad() {
        ll_record.onHeaderRefreshComplete();
        ll_record.onFooterRefreshComplete();
    }


    private void initView() {

        ll_record = (PullToRefreshView) findViewById(R.id.ll_record);
        ll_record.setLastUpdated(getTimes());                                 //更新刷新时间
        ll_record.setOnFooterRefreshListener(new MyOnFooterRefreshListener());//设置上拉加载监听
        ll_record.setOnHeaderRefreshListener(new MyOnFooterRefreshListener());//设置下拉刷新监听

        mImgHongbaoRemberBack = (ImageView) findViewById(R.id.img_hongbaoRember_back);
        mListvView = (ListView) findViewById(R.id.listview_hongbao_DuiHuan);

        mImgHongbaoRemberBack.setOnClickListener(new MyOnClickListener());
    }

    private class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.img_hongbaoRember_back:   //返回
                    finish();
                    break;
            }
        }


    }

    //获取当前系统时间
    private CharSequence getTimes() {

        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd HH:mm:ss");//设置日期显示格式
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String time = formatter.format(curDate);// 将时间装换为设置好的格式
        return time;
    }

    private class MyOnFooterRefreshListener implements PullToRefreshView.OnFooterRefreshListener,PullToRefreshView.OnHeaderRefreshListener {

        //上拉加载
        @Override
        public void onFooterRefresh(PullToRefreshView view) {

            page++;
            getHttpData();

        }
        //下拉刷新
        @Override
        public void onHeaderRefresh(PullToRefreshView view) {

            ll_record.setLastUpdated(getTimes());//更新刷新时间
            page = 1;
            redlist.clear();
            getHttpData();
        }
    }
}
