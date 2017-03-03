package com.tiantianle.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.tiantianle.R;
import com.tiantianle.utils.ProgressWebView;

/**
 * Created by wyj on 2017/1/22.
 * <p>
 * 趋势fragment
 */

public class TrendFragment extends Fragment {
    protected View rootView;
    protected TextView mTextTrend;
    protected ImageView mImgSeting;
    protected ImageView mImgEnlarge;
    private SlidingMenu menu;

    private ProgressWebView progress_webview;
    private SwipeRefreshLayout swll;

    private AlertDialog dialog;
    private EditText et_trends_query;
    private TextView tv_cancel;     //取消
    private TextView tv_determine;  //确定


    private String url = "http://192.168.1.179/xs.html";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.item_fram_trend, null, false);
        FragmentActivity activity = getActivity();
        menu = (SlidingMenu) activity.findViewById(R.id.activity_main);
        menu.setSelected(false);
        initView(rootView);


        return rootView;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {

            getHttpWebView();

        } else {

        }
    }

    private void getHttpWebView() {

        WebSettings settings = progress_webview.getSettings();
        settings.setJavaScriptEnabled(true);
//        settings.setUseWideViewPort(true);
//        settings.setLoadWithOverviewMode(true);
        //支持屏幕缩放
//        settings.setSupportZoom(true);
//        settings.setBuiltInZoomControls(true);

        settings.setDisplayZoomControls(false); //隐藏webview缩放按钮
        progress_webview.getSettings().setJavaScriptEnabled(true);
        progress_webview.loadUrl(url);

    }


    private void initView(View rootView) {
        mTextTrend = (TextView) rootView.findViewById(R.id.text_trend);
        mImgSeting = (ImageView) rootView.findViewById(R.id.img_seting);
        mImgEnlarge = (ImageView) rootView.findViewById(R.id.img_enlarge);
        progress_webview = (ProgressWebView) rootView.findViewById(R.id.progress_webview);
        swll = (SwipeRefreshLayout) rootView.findViewById(R.id.swll);


        mImgSeting.setOnClickListener(new MyOnClickListener());
        mImgEnlarge.setOnClickListener(new MyOnClickListener());
        swll.setOnRefreshListener(new MyOnRefreshListener());
        swll.setColorSchemeResources(R.color.green, R.color.colorTitle, R.color.blue);//小圈圈的颜色。转一圈换一种颜色，每一圈耗时1s。
    }


    private class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.img_enlarge:  //缩小

                    break;

                case R.id.img_seting:   //设置

                    showDiaLog();
                    break;
                case R.id.tv_cancel:   //取消

                    dialog.dismiss();
                    dialog = null;
                    break;
                case R.id.tv_determine:   //确定

                    dialog.dismiss();
                    dialog = null;
                    break;
            }

        }
    }

    private void showDiaLog() {

        WindowManager wm = getActivity().getWindowManager();
        int width = wm.getDefaultDisplay().getWidth();
        if (dialog == null) {
            dialog = new AlertDialog.Builder(getActivity()).create();
        }
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        dialog.show();

        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = width;
        params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;// 就是这个属性导致不能获取焦点,默认的是FLAG_NOT_FOCUSABLE,故名思义不能获取输入
        dialog.getWindow().setAttributes(params);
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.trend_fragment_dialog, null);

        dialog.setContentView(contentView);

        et_trends_query = (EditText) contentView.findViewById(R.id.et_trends_query);
        tv_cancel = (TextView) contentView.findViewById(R.id.tv_cancel);
        tv_determine = (TextView) contentView.findViewById(R.id.tv_determine);

        tv_cancel.setOnClickListener(new MyOnClickListener());
        tv_determine.setOnClickListener(new MyOnClickListener());
    }

    private class MyOnRefreshListener implements SwipeRefreshLayout.OnRefreshListener {

        //下拉刷新
        @Override
        public void onRefresh() {

            //设置2秒的时间来执行以下事件
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    getHttpWebView();
                    swll.setRefreshing(false);
                }
            }, 3000);

        }
    }
}
