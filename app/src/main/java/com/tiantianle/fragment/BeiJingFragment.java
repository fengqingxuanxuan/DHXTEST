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

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.tiantianle.R;
import com.tiantianle.activity.AutoBetting;
import com.tiantianle.activity.Betting;
import com.tiantianle.adapter.JiSuLiseFragmentAdapter;
import com.tiantianle.utils.IntentUtils;
import com.tiantianle.view.MyListview;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wyj on 2017/1/22.
 */

public class BeiJingFragment extends Fragment implements View.OnClickListener {
    protected View rootView;
    protected ImageView mBighMenu;
    protected TextView mBighText;
    protected SlidingMenu mMenu;
    protected TextView mTextBeijingDaotime1Fram;
    protected TextView mTextBeijingJianchiFram;
    protected TextView mTextBeijingJiangchishuliang1;
    protected TextView mTextBeijingQishuFram;
    protected TextView mTextBeijingTiemFram;
    protected TextView mTextBeijingLJisuList;
    protected Button mBtnBeijingTouzhuJiana1JisuList;
    protected TextView mTextBeijingDaotime2Fram;
    protected TextView mTextBeijingJianchi2Fram;
    protected TextView mTextBeijingJiangchishuliang2;
    protected TextView mTextBeijingQishu2Fram;
    protected TextView mTextBeijingTiem2Fram;
    protected TextView mTextBeijing2JisuList;
    protected Button mBtnBeijingTouzhu2JisuList;
    protected TextView mTextBeijingDaotime3Fram;
    protected TextView mTextBeijingJianchi3Fram;
    protected TextView mTextBeijingJiangchishuliang3;
    protected TextView mTextBeijingQishu3Fram;
    protected TextView mTextBeijingTiem3Fram;
    protected TextView mTextBeijing3JisuList;
    protected Button mBtnBeijingTouzhu3JisuList;
    protected TextView mTextBeijingDaotime4Fram;
    protected TextView mTextBeijingJianchi4Fram;
    protected TextView mTextBeijingJiangchishuliang4;
    protected TextView mTextBeijingQishu4Fram;
    protected TextView mTextBeijingTiem4Fram;
    protected TextView mTextBeijing4JisuList;
    protected Button mBtnBeijingTouzhu4JisuList;
    protected TextView mTextBeijingDaotime5Fram;
    protected TextView mTextBeijingJianchi5Fram;
    protected TextView mTextBeijingJiangchishuliang5;
    protected TextView mTextBeijingQishu5Fram;
    protected TextView mTextBeijingTiem5Fram;
    protected TextView mTextBeijing5JisuList;
    protected Button mBtnBeijingTouzhu5JisuList;
    protected MyListview mListviewFram;
    protected CheckBox mCbBeijingFragm;
    protected TextView mTextBejingFram;
    private PopupWindow mPopupWindow;
    private int is[] = {333, 444, 555, 622, 513};
    private List<Integer> mList;
    private JiSuLiseFragmentAdapter mJiSuLiseFragmentAdapter;
    private LinearLayout mNewModle;
    private LinearLayout mModelManagement;
    private LinearLayout mMyPaLoss;
    private LinearLayout aotu;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.item_fram_beijing28, null, false);
        initView(rootView);
        FragmentActivity activity = getActivity();
        mMenu = (SlidingMenu) activity.findViewById(R.id.activity_main);
        initData();
        mListviewFram.setDividerHeight(0);
        mCbBeijingFragm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    mCbBeijingFragm.setBackground(getResources().getDrawable(R.mipmap.up));
                    showpopuWindow();

                } else {
                    mCbBeijingFragm.setBackground(getResources().getDrawable(R.mipmap.down));
                    mPopupWindow.dismiss();
                }
            }
        });
        return rootView;
    }
    private void initData() {
        mList = new ArrayList<>();
        mJiSuLiseFragmentAdapter = new JiSuLiseFragmentAdapter();
        for (int i = 0; i < is.length; i++) {
            mList.add(is[i]);
        }
        mJiSuLiseFragmentAdapter.setList(mList);
        mListviewFram.setAdapter(mJiSuLiseFragmentAdapter);
        mJiSuLiseFragmentAdapter.notifyDataSetChanged();
    }

    private void initView(View rootView) {
        mBighMenu = (ImageView) rootView.findViewById(R.id.bigh_menu);
        mBighMenu.setOnClickListener(BeiJingFragment.this);
        mBighText = (TextView) rootView.findViewById(R.id.bigh_text);
        mTextBeijingDaotime1Fram = (TextView) rootView.findViewById(R.id.text_beijing_daotime_1_fram);
        mTextBeijingJianchiFram = (TextView) rootView.findViewById(R.id.text_beijing_jianchi_fram);
        mTextBeijingJiangchishuliang1 = (TextView) rootView.findViewById(R.id.text_beijing_jiangchishuliang_1);
        mTextBeijingQishuFram = (TextView) rootView.findViewById(R.id.text_beijing_qishu_fram);
        mTextBeijingTiemFram = (TextView) rootView.findViewById(R.id.text_beijing_tiem_fram);
        mTextBeijingLJisuList = (TextView) rootView.findViewById(R.id.text_beijing_l_jisu_list);
        mBtnBeijingTouzhuJiana1JisuList = (Button) rootView.findViewById(R.id.btn_beijing_touzhu_jiana_1_jisu_list);
        mBtnBeijingTouzhuJiana1JisuList.setOnClickListener(BeiJingFragment.this);
        mTextBeijingDaotime2Fram = (TextView) rootView.findViewById(R.id.text_beijing_daotime_2_fram);
        mTextBeijingJianchi2Fram = (TextView) rootView.findViewById(R.id.text_beijing_jianchi_2_fram);
        mTextBeijingJiangchishuliang2 = (TextView) rootView.findViewById(R.id.text_beijing_jiangchishuliang_2);
        mTextBeijingQishu2Fram = (TextView) rootView.findViewById(R.id.text_beijing_qishu_2_fram);
        mTextBeijingTiem2Fram = (TextView) rootView.findViewById(R.id.text_beijing_tiem_2_fram);
        mTextBeijing2JisuList = (TextView) rootView.findViewById(R.id.text_beijing_2_jisu_list);
        mBtnBeijingTouzhu2JisuList = (Button) rootView.findViewById(R.id.btn_beijing_touzhu_2_jisu_list);
        mBtnBeijingTouzhu2JisuList.setOnClickListener(BeiJingFragment.this);
        mTextBeijingDaotime3Fram = (TextView) rootView.findViewById(R.id.text_beijing_daotime_3_fram);
        mTextBeijingJianchi3Fram = (TextView) rootView.findViewById(R.id.text_beijing_jianchi_3_fram);
        mTextBeijingJiangchishuliang3 = (TextView) rootView.findViewById(R.id.text_beijing_jiangchishuliang_3);
        mTextBeijingQishu3Fram = (TextView) rootView.findViewById(R.id.text_beijing_qishu_3_fram);
        mTextBeijingTiem3Fram = (TextView) rootView.findViewById(R.id.text_beijing_tiem_3_fram);
        mTextBeijing3JisuList = (TextView) rootView.findViewById(R.id.text_beijing_3_jisu_list);
        mBtnBeijingTouzhu3JisuList = (Button) rootView.findViewById(R.id.btn_beijing_touzhu_3_jisu_list);
        mBtnBeijingTouzhu3JisuList.setOnClickListener(BeiJingFragment.this);
        mTextBeijingDaotime4Fram = (TextView) rootView.findViewById(R.id.text_beijing_daotime_4_fram);
        mTextBeijingJianchi4Fram = (TextView) rootView.findViewById(R.id.text_beijing_jianchi_4_fram);
        mTextBeijingJiangchishuliang4 = (TextView) rootView.findViewById(R.id.text_beijing_jiangchishuliang_4);
        mTextBeijingQishu4Fram = (TextView) rootView.findViewById(R.id.text_beijing_qishu_4_fram);
        mTextBeijingTiem4Fram = (TextView) rootView.findViewById(R.id.text_beijing_tiem_4_fram);
        mTextBeijing4JisuList = (TextView) rootView.findViewById(R.id.text_beijing_4_jisu_list);
        mBtnBeijingTouzhu4JisuList = (Button) rootView.findViewById(R.id.btn_beijing_touzhu_4_jisu_list);
        mBtnBeijingTouzhu4JisuList.setOnClickListener(BeiJingFragment.this);
        mTextBeijingDaotime5Fram = (TextView) rootView.findViewById(R.id.text_beijing_daotime_5_fram);
        mTextBeijingJianchi5Fram = (TextView) rootView.findViewById(R.id.text_beijing_jianchi_5_fram);
        mTextBeijingJiangchishuliang5 = (TextView) rootView.findViewById(R.id.text_beijing_jiangchishuliang_5);
        mTextBeijingQishu5Fram = (TextView) rootView.findViewById(R.id.text_beijing_qishu_5_fram);
        mTextBeijingTiem5Fram = (TextView) rootView.findViewById(R.id.text_beijing_tiem_5_fram);
        mTextBeijing5JisuList = (TextView) rootView.findViewById(R.id.text_beijing_5_jisu_list);
        mBtnBeijingTouzhu5JisuList = (Button) rootView.findViewById(R.id.btn_beijing_touzhu_5_jisu_list);
        mBtnBeijingTouzhu5JisuList.setOnClickListener(BeiJingFragment.this);
        mListviewFram = (MyListview) rootView.findViewById(R.id.beijng_listview_fram);
        mCbBeijingFragm = (CheckBox) rootView.findViewById(R.id.cb_beijing_fragm);
        mCbBeijingFragm.setOnClickListener(BeiJingFragment.this);
        mTextBejingFram = (TextView) rootView.findViewById(R.id.text_bejing_fram);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.bigh_menu) {
            mCbBeijingFragm.setBackground(getResources().getDrawable(R.mipmap.down));
            mMenu.showMenu();

        } else if (view.getId() == R.id.btn_beijing_touzhu_jiana_1_jisu_list) {
            IntentUtils.goTo(getContext(), Betting.class);
        } else if (view.getId() == R.id.btn_beijing_touzhu_2_jisu_list) {
            IntentUtils.goTo(getContext(), Betting.class);
        } else if (view.getId() == R.id.btn_beijing_touzhu_3_jisu_list) {
            IntentUtils.goTo(getContext(), Betting.class);
        } else if (view.getId() == R.id.btn_beijing_touzhu_4_jisu_list) {
            IntentUtils.goTo(getContext(), Betting.class);
        } else if (view.getId() == R.id.btn_beijing_touzhu_5_jisu_list) {
            IntentUtils.goTo(getContext(), Betting.class);
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
        mNewModle= (LinearLayout) inflate1.findViewById(R.id.ll_newPattern);
        mNewModle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "新建模式", Toast.LENGTH_SHORT).show();
            }
        });
        mModelManagement= (LinearLayout) inflate1.findViewById(R.id.ll_modelManagement);
        mModelManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "模式管理", Toast.LENGTH_SHORT).show();
            }
        });
        mMyPaLoss= (LinearLayout) inflate1.findViewById(R.id.ll_myPaLoss);
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
                mCbBeijingFragm.setBackground(getResources().getDrawable(R.mipmap.down));
            }
        });
        mPopupWindow.showAsDropDown(mCbBeijingFragm);
    }
}
