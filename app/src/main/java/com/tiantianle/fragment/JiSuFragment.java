package com.tiantianle.fragment;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.tiantianle.Bean.TheLotteryBean;
import com.tiantianle.R;
import com.tiantianle.activity.AutoBetting;
import com.tiantianle.activity.Betting;
import com.tiantianle.adapter.TheLotteryAdapter;
import com.tiantianle.utils.IntentUtils;
import com.tiantianle.view.MyListview;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wyj on 2017/1/22.
 * 极速28Fragment
 */

public class JiSuFragment extends Fragment implements View.OnClickListener {
    protected View rootView;

    protected ImageView mBighMenu;
    protected TextView mBighText;
    protected TextView mTextJisu28Fram;

    protected TextView mTextDaotime1Fram;

    protected MyListview mListview;
    protected CheckBox mCbJisuFragm;
    protected PullToRefreshScrollView mPullJisuRefreshScrollview;
    private SlidingMenu menu;
    private LinearLayout aotu;
    private PopupWindow mPopupWindow;
    private int is[] = {333, 444, 555, 622, 513};
    private List<TheLotteryBean.BizContentBean> mList;
    private TheLotteryAdapter mJiSuLiseFragmentAdapter;
    private LinearLayout mNewModle;
    private LinearLayout mModelManagement;
    private LinearLayout mMyPaLoss;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.item_fram_jisu, null, false);
        initView(rootView);

        FragmentActivity activity = getActivity();
        menu = (SlidingMenu) activity.findViewById(R.id.activity_main);
        mListview.setDividerHeight(0);
        mCbJisuFragm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    mCbJisuFragm.setBackground(getResources().getDrawable(R.mipmap.up));
                    showpopuWindow();
                } else {
                    mCbJisuFragm.setBackground(getResources().getDrawable(R.mipmap.down));
                    mPopupWindow.dismiss();
                }
            }
        });
        return rootView;
    }


    private void initView(View rootView) {

        mBighMenu = (ImageView) rootView.findViewById(R.id.bigh_menu);
        mBighMenu.setOnClickListener(JiSuFragment.this);
        mBighText = (TextView) rootView.findViewById(R.id.bigh_text);
        mTextJisu28Fram = (TextView) rootView.findViewById(R.id.text_jisu28_fram);

        mListview = (MyListview) rootView.findViewById(R.id.jisu_listview_fram);
        mCbJisuFragm = (CheckBox) rootView.findViewById(R.id.cb_jisu_fragm);
        mCbJisuFragm.setOnClickListener(JiSuFragment.this);
        mPullJisuRefreshScrollview = (PullToRefreshScrollView) rootView.findViewById(R.id.pull_jisu_refresh_scrollview);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.bigh_menu) {
            mCbJisuFragm.setBackground(getResources().getDrawable(R.mipmap.down));
            menu.showMenu();
//        } else if (view.getId() == R.id.btn_touzhu_1_jisu_list) {
//            IntentUtils.goTo(getContext(), Betting.class);
//        } else if (view.getId() == R.id.btn_touzhu_2_jisu_list) {
//            IntentUtils.goTo(getContext(), Betting.class);
//        } else if (view.getId() == R.id.btn_touzhu_3_jisu_list) {
//            IntentUtils.goTo(getContext(), Betting.class);
//        } else if (view.getId() == R.id.btn_touzhu_4_jisu_list) {
//            IntentUtils.goTo(getContext(), Betting.class);
//        } else if (view.getId() == R.id.btn_touzhu_5_jisu_list) {
//            IntentUtils.goTo(getContext(), Betting.class);
        }
    }

    private void showpopuWindow() {
        mPopupWindow = new PopupWindow();
        View inflate1 = View.inflate(getContext(), R.layout.item_popuwindow, null);
        mPopupWindow.setContentView(inflate1);
        aotu = (LinearLayout) inflate1.findViewById(R.id.ll_auto);
        aotu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUtils.goTo(getContext(), AutoBetting.class);
            }
        });
        mNewModle = (LinearLayout) inflate1.findViewById(R.id.ll_newPattern);
        mNewModle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "新建模式", Toast.LENGTH_SHORT).show();
            }
        });
        mModelManagement = (LinearLayout) inflate1.findViewById(R.id.ll_modelManagement);
        mModelManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "模式管理", Toast.LENGTH_SHORT).show();
            }
        });
        mMyPaLoss = (LinearLayout) inflate1.findViewById(R.id.ll_myPaLoss);
        mMyPaLoss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "我的盈亏", Toast.LENGTH_SHORT).show();
            }
        });
        mPopupWindow.setHeight(WindowManager.LayoutParams.MATCH_PARENT);
        mPopupWindow.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        LinearLayout viewById = (LinearLayout) inflate1.findViewById(R.id.ll_dimiss);
        viewById.setBackgroundColor(Color.BLACK);
        viewById.setAlpha(0.6F);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
                mCbJisuFragm.setBackground(getResources().getDrawable(R.mipmap.down));
            }
        });
        mPopupWindow.showAsDropDown(mCbJisuFragm);
        // mPopupWindow.showAtLocation(getActivity().findViewById(R.id.radiob_trend_main), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0);
    }
}
