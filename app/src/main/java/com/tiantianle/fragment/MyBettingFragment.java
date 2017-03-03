package com.tiantianle.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.tiantianle.Bean.MyBettingBean;
import com.tiantianle.R;
import com.tiantianle.activity.MyBettingAdapter;
import com.tiantianle.utils.Constant;
import com.tiantianle.utils.HttpApi;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * Created by PengBo  on 2017/2/23.
 * 我的投注
 */

public class MyBettingFragment extends Fragment {
    protected View rootView;
    protected PullToRefreshListView mPullRefreshListFramMebetting;
    private  String   expect;
    private  String  type;
    private MyBettingAdapter mMyBettingAdapter;
    private List<MyBettingBean.BizContentBean> mList;

    public static MyBettingFragment newInstance(String expect, String type) {
        Bundle args = new Bundle();
        args.putString("expect",expect);
        args.putString("type",type);
        MyBettingFragment fragment = new MyBettingFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.item_fram_mebetting, null, false);
        initView(rootView);

        Bundle arguments = getArguments();
        expect=arguments.getString("expect");
        type =arguments.getString("type");
        HttpData();
        return rootView;
    }
    private void HttpData(){

        RequestParams entity = new RequestParams(HttpApi.MYBETTING);
        entity.addParameter("account", Constant.Config.account);
        entity.addParameter("type",type);
        entity.addParameter("expect",expect);
        entity.addParameter("imei", Constant.Config.imei);
        x.http().post(entity, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson=new Gson();
                MyBettingBean myBettingBean = gson.fromJson(result, MyBettingBean.class);
                if(myBettingBean.getState().equals("success")){
                    mMyBettingAdapter=new MyBettingAdapter();
                    mList=myBettingBean.getBiz_content();
                    mList.add(0,null);
                    mMyBettingAdapter.setList(mList);
                    mPullRefreshListFramMebetting.setAdapter(mMyBettingAdapter);
                    mMyBettingAdapter.notifyDataSetChanged();
                }else if(myBettingBean.getState().equals("error")){

                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("onError我的投注",ex.getMessage());
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
        mPullRefreshListFramMebetting = (PullToRefreshListView) rootView.findViewById(R.id.pull_refresh_list_fram_mebetting);
    }
}
