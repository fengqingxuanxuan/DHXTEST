package com.tiantianle.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.tiantianle.Bean.DuiHuanBean;
import com.tiantianle.R;
import com.tiantianle.adapter.ShaoppingDuiHuanAdapter;
import com.tiantianle.intface.DuiHuan;
import com.tiantianle.utils.Constant;
import com.tiantianle.utils.HttpApi;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 商城兑换记录
 */

public class ShaoppingDuiHuanJiLu extends AppCompatActivity implements View.OnClickListener {

    protected ImageView mImgShangchengduihuanRemberBack;
    protected LinearLayout mActivityShaoppingDuiHuanJiLu;
    protected PullToRefreshListView mPullRefreshListDuihuanremb;
    private List<DuiHuanBean.BizContentBean> mList=new ArrayList<>();
    private List<DuiHuanBean.BizContentBean>res;
    private int page = 1;
    private ShaoppingDuiHuanAdapter mShaoppingDuiHuanAdapter;
    private int yvshu;
    private int shang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_shaopping_dui_huan_ji_lu);
        initView();
        HttpDate(false);
        Pullto();

    }

    private void HttpDate(final boolean isUP) {
        RequestParams entity = new RequestParams(HttpApi.REMB_ALL);
        entity.addParameter("account", Constant.Config.account);
        entity.addParameter("imei", Constant.Config.imei);
        entity.addParameter("page", page);
        entity.addParameter("type", Constant.Config.tyoe0);
        entity.addParameter("status", -1);
        entity.addParameter("ismyself", 1);

        x.http().post(entity, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                DuiHuanBean duiHuanBean = gson.fromJson(result, DuiHuanBean.class);
                if (duiHuanBean.getState().equals("success")) {
                    res = duiHuanBean.getBiz_content();
                    if(isUP){
                        mList.addAll(res);
                        mShaoppingDuiHuanAdapter.setList(mList);
                        mShaoppingDuiHuanAdapter.notifyDataSetChanged();
                    }else{
                        mShaoppingDuiHuanAdapter.setList(res);
                        mShaoppingDuiHuanAdapter.notifyDataSetChanged();
                    }
                    mPullRefreshListDuihuanremb.onRefreshComplete();
                } else if (duiHuanBean.getState().equals("error")) {
                }
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
            }
            @Override
            public void onCancelled(CancelledException cex) {
            }
            @Override
            public void onFinished() {

            }
        });


    }

    private void Pullto() {
        mPullRefreshListDuihuanremb.setMode(PullToRefreshBase.Mode.BOTH);
        mPullRefreshListDuihuanremb.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                //开始下拉  我们做Http请求
                HttpDate(false);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                HttpDate(true);
                if (page <= shang) {
                    page++;
                }
            }
        });
        mShaoppingDuiHuanAdapter=new ShaoppingDuiHuanAdapter(new DuiHuan() {
            @Override
            public void DuiHuanRemb(int totalcount) {
                yvshu = totalcount % 15;
                if (yvshu != 0) {
                    shang = totalcount / 15 + 1;
                }
            }
        });
        mPullRefreshListDuihuanremb.setAdapter(mShaoppingDuiHuanAdapter);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());
        String time = format.format(curDate);
        ILoadingLayout loadingLayoutProxy = mPullRefreshListDuihuanremb.getLoadingLayoutProxy();
        loadingLayoutProxy.setRefreshingLabel("正在刷新..."); // 刷新时
        loadingLayoutProxy.setPullLabel("下拉刷新"); // 刚下拉时，显示的提示
        //loadingLayoutProxy.setLoadingDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
        loadingLayoutProxy.setLastUpdatedLabel(time); //一般是上次刷新的时间
        loadingLayoutProxy.setReleaseLabel("松手开始刷新");
    }

    private void initView() {
        mImgShangchengduihuanRemberBack = (ImageView) findViewById(R.id.img_shangchengduihuan_Rember_back);
        mImgShangchengduihuanRemberBack.setOnClickListener(ShaoppingDuiHuanJiLu.this);
        mActivityShaoppingDuiHuanJiLu = (LinearLayout) findViewById(R.id.activity_shaopping_dui_huan_ji_lu);
        mPullRefreshListDuihuanremb = (PullToRefreshListView) findViewById(R.id.pull_refresh_list_Duihuanremb);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.img_shangchengduihuan_Rember_back) {
            finish();
        }
    }
}
