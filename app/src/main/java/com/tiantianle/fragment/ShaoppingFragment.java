package com.tiantianle.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.tiantianle.Bean.ShaoppingBean;
import com.tiantianle.R;
import com.tiantianle.activity.ShaoppingDuiHuanJiLu;
import com.tiantianle.activity.YiDongKaMi;
import com.tiantianle.adapter.ShaoppingAdapter;
import com.tiantianle.utils.Constant;
import com.tiantianle.utils.HttpApi;
import com.tiantianle.utils.ToastUtils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * Created by wyj on 2017/1/22.
 * 兑换商城
 */

public class ShaoppingFragment extends Fragment implements View.OnClickListener {
    private View rootView;
    private TextView mTextShaopping;
    private TextView mTextExchangeRecord;
    private ListView mListviewShaoppingAdapter;
    private SlidingMenu menu;
    private int page=1;
    private ShaoppingAdapter mShaoppingAdapter;
    private List<ShaoppingBean.BizContentBean> mBizContentBeen;
    private String warename;
    private String warecode;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.item_fram_shaoppinng, null, false);
        initView(rootView);
        FragmentActivity activity = getActivity();
        menu = (SlidingMenu) activity.findViewById(R.id.activity_main);
        HttpData();
        mListviewShaoppingAdapter .setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                warename=mBizContentBeen.get(i).getWarename();
                warecode=mBizContentBeen.get(i).getWarecode();
                Intent intent=new Intent(getActivity(), YiDongKaMi.class);
                intent.putExtra("warename",warename);
                intent.putExtra("warecode",warecode);
                intent.putExtra("","");
                startActivity(intent);

            }
        });
        return rootView;
    }


    private void initView(View rootView) {
        mTextShaopping = (TextView) rootView.findViewById(R.id.text_shaopping);
        mTextExchangeRecord = (TextView) rootView.findViewById(R.id.text_exchange_record);
        mTextExchangeRecord.setOnClickListener(ShaoppingFragment.this);
        mListviewShaoppingAdapter = (ListView) rootView.findViewById(R.id.listview_shaopping_adapter);
    }
    private void HttpData(){
        RequestParams entity = new RequestParams(HttpApi.SHAOPPING);
        entity.addParameter("account", Constant.Config.account);
        entity.addParameter("imei", Constant.Config.imei);
        entity.addParameter("page",page);
        entity.addParameter("type", Constant.Config.tyoe0);
        x.http().post(entity, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson=new Gson();
                ShaoppingBean shaoppingBean = gson.fromJson(result, ShaoppingBean.class);
                if(shaoppingBean.getState().equals("success")){
                    mShaoppingAdapter=new ShaoppingAdapter();
                    mBizContentBeen=shaoppingBean.getBiz_content();
                    mShaoppingAdapter.setList(mBizContentBeen);
                    mListviewShaoppingAdapter.setAdapter(mShaoppingAdapter);
                    mShaoppingAdapter.notifyDataSetChanged();
                }else if(shaoppingBean.getState().equals("error")){
                    ToastUtils.showShort(getContext(),"缺少请求参数");
                }
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("onError兑换商城",ex.getMessage());
            }
            @Override
            public void onCancelled(CancelledException cex) {
            }
            @Override
            public void onFinished() {
            }
        });


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.text_exchange_record) {
            Intent intent = new Intent(getContext(), ShaoppingDuiHuanJiLu.class);
            startActivity(intent);
        }
    }
}
