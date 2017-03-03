package com.tiantianle.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.tiantianle.Bean.AllIndianaBean;
import com.tiantianle.R;
import com.tiantianle.adapter.AllAdapterIndinan;
import com.tiantianle.adapter.JieXiaoAdapterIndinan;
import com.tiantianle.intface.ShowTotalcount;
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
 * Created by PengBo  on 2017/2/5.
 */

public class JieXiaoFragmentIndinanremb extends Fragment {
    protected View rootView;
    protected PullToRefreshListView mPullRefreshListFramJiexiaoIndinan;
    private List<AllIndianaBean.BizContentBean> mAllIndianaBeen=new ArrayList<>();
    private List<AllIndianaBean.BizContentBean> res;
    private int page=1;
    private int yvshu;
    private int shang;
    private JieXiaoAdapterIndinan mAllAdapterIndinan;
    public static JieXiaoFragmentIndinanremb newInstance() {

        Bundle args = new Bundle();

        JieXiaoFragmentIndinanremb fragment = new JieXiaoFragmentIndinanremb();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.item_jiexiao_fram_indinanremb, null, false);
        initView(rootView);
        HttpDate(false);
        Pullto();
        return rootView;
    }

    private void Pullto(){
        mPullRefreshListFramJiexiaoIndinan.setMode(PullToRefreshBase.Mode.BOTH);
        mPullRefreshListFramJiexiaoIndinan.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                //开始下拉  我们做Http请求
                HttpDate(false);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                HttpDate(true);
                if(page<=shang){
                    page++;
                }


            }
        });
        mAllAdapterIndinan = new JieXiaoAdapterIndinan(new ShowTotalcount() {
            @Override
            public void TotalcountNum(int totalcount ) {
                yvshu=totalcount%15;
                if (yvshu!=0){
                    shang=totalcount/15+1;

                }
            }
        });
        mPullRefreshListFramJiexiaoIndinan.setAdapter(mAllAdapterIndinan);
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate    =   new Date(System.currentTimeMillis());
        String time=format.format(curDate);
        ILoadingLayout loadingLayoutProxy = mPullRefreshListFramJiexiaoIndinan.getLoadingLayoutProxy();
        loadingLayoutProxy.setRefreshingLabel("正在刷新..."); // 刷新时
        loadingLayoutProxy.setPullLabel("下拉刷新"); // 刚下拉时，显示的提示
        //loadingLayoutProxy.setLoadingDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
        loadingLayoutProxy.setLastUpdatedLabel(time); //一般是上次刷新的时间
        loadingLayoutProxy.setReleaseLabel("松手开始刷新");
    }


    private void HttpDate(final boolean isUP) {
        RequestParams entity = new RequestParams(HttpApi.REMB_ALL);
        entity.addParameter("account", Constant.Config.account);
        entity.addParameter("imei", Constant.Config.imei);
        entity.addParameter("type", Constant.Config.type1);
        entity.addParameter("status", -1);
        entity.addParameter("page", page);
        entity.addParameter("ismyself", 1);
        x.http().post(entity, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("这是全部", result);
                Gson gson = new Gson();
                AllIndianaBean allIndianaBean = gson.fromJson(result, AllIndianaBean.class);

                if (allIndianaBean.getState().equals("success")) {
                    //  mAllIndianaBeen = allIndianaBean.getBiz_content();
                    res=allIndianaBean.getBiz_content();
                    //  mAllAdapterIndinan.setList(mAllIndianaBeen);
                    // mListviewAllFramIndinanremb.setAdapter(mAllAdapterIndinan);
                    if(isUP){
                        mAllIndianaBeen.addAll(res);
                        mAllAdapterIndinan.setList(mAllIndianaBeen);
                        mAllAdapterIndinan.notifyDataSetChanged();
                    }else{
                        mAllAdapterIndinan.setList(res);
                        mAllAdapterIndinan.notifyDataSetChanged();
                    }
                    mPullRefreshListFramJiexiaoIndinan.onRefreshComplete();

                } else if (allIndianaBean.getState().equals("error")) {

                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

                Log.e("onError", ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });


    }
    private void initView(View rootView) {
        mPullRefreshListFramJiexiaoIndinan = (PullToRefreshListView) rootView.findViewById(R.id.pull_refresh_list_fram_jiexiaoIndinan);

    }
}
