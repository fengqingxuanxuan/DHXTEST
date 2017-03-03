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
import com.tiantianle.activity.ModelManagementActivity;
import com.tiantianle.adapter.JiSuLiseFragmentAdapter;
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
    protected TextView mTextJianchiFram;
    protected TextView mTextJiangchishuliang1;
    protected TextView mTextQishuFram;
    protected TextView mTextTiemFram;
    protected TextView mTextLJisuList;
    protected Button mBtnTouzhu1JisuList;
    protected TextView mTextDaotime2Fram;
    protected TextView mTextJianchi2Fram;
    protected TextView mTextJiangchishuliang2;
    protected TextView mTextQishu2Fram;
    protected TextView mTextTiem2Fram;
    protected TextView mText2JisuList;
    protected Button mBtnTouzhu2JisuList;
    protected TextView mTextDaotime3Fram;
    protected TextView mTextJianchi3Fram;
    protected TextView mTextJiangchishuliang3;
    protected TextView mTextQishu3Fram;
    protected TextView mTextTiem3Fram;
    protected TextView mText3JisuList;
    protected Button mBtnTouzhu3JisuList;

    protected TextView mTextJianchi4Fram;
    protected TextView mTextJiangchishuliang4;
    protected TextView mTextQishu4Fram;
    protected TextView mTextTiem4Fram;
    protected TextView mText4JisuList;
    protected Button mBtnTouzhu4JisuList;
    protected TextView mTextDaotime4Fram;
    protected TextView mTextJianchi5Fram;
    protected TextView mTextJiangchishuliang5;
    protected TextView mTextQishu5Fram;
    protected TextView mTextTiem5Fram;
    protected TextView mText5JisuList;
    protected Button mBtnTouzhu5JisuList;
    protected TextView mTextDaotime5Fram;
    protected MyListview mListview;
    protected CheckBox mCbJisuFragm;
    private SlidingMenu menu;
    private LinearLayout aotu;
    private PopupWindow mPopupWindow;
    private int is[] = {333, 444, 555, 622, 513};
    private List<Integer> mList;
    private JiSuLiseFragmentAdapter mJiSuLiseFragmentAdapter;
    private  LinearLayout mNewModle;
    private LinearLayout mModelManagement;
    private LinearLayout mMyPaLoss;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.item_fram_jisu, null, false);
        initView(rootView);
        initData();
        FragmentActivity activity = getActivity();
        menu = (SlidingMenu) activity.findViewById(R.id.activity_main);
        mListview.setDividerHeight(0);
        mCbJisuFragm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    mCbJisuFragm.setBackground(getResources().getDrawable(R.mipmap.up));
                    showpopuWindow();
                }else{
                    mCbJisuFragm.setBackground(getResources().getDrawable(R.mipmap.down));
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
        mListview.setAdapter(mJiSuLiseFragmentAdapter);
        mJiSuLiseFragmentAdapter.notifyDataSetChanged();
    }

    private void initView(View rootView) {

        mBighMenu = (ImageView) rootView.findViewById(R.id.bigh_menu);
        mBighMenu.setOnClickListener(JiSuFragment.this);
        mBighText = (TextView) rootView.findViewById(R.id.bigh_text);
        mTextJisu28Fram = (TextView) rootView.findViewById(R.id.text_jisu28_fram);
        mTextDaotime1Fram = (TextView) rootView.findViewById(R.id.text_daotime_1_fram);
        mTextJianchiFram = (TextView) rootView.findViewById(R.id.text_jianchi_fram);
        mTextJiangchishuliang1 = (TextView) rootView.findViewById(R.id.text_jiangchishuliang_1);
        mTextQishuFram = (TextView) rootView.findViewById(R.id.text_qishu_fram);
        mTextTiemFram = (TextView) rootView.findViewById(R.id.text_tiem_fram);
        mTextLJisuList = (TextView) rootView.findViewById(R.id.text_l_jisu_list);
        mBtnTouzhu1JisuList = (Button) rootView.findViewById(R.id.btn_touzhu_1_jisu_list);
        mBtnTouzhu1JisuList.setOnClickListener(JiSuFragment.this);
        mTextDaotime2Fram = (TextView) rootView.findViewById(R.id.text_daotime_2_fram);
        mTextJianchi2Fram = (TextView) rootView.findViewById(R.id.text_jianchi_2_fram);
        mTextJiangchishuliang2 = (TextView) rootView.findViewById(R.id.text_jiangchishuliang_2);
        mTextQishu2Fram = (TextView) rootView.findViewById(R.id.text_qishu_2_fram);
        mTextTiem2Fram = (TextView) rootView.findViewById(R.id.text_tiem_2_fram);
        mText2JisuList = (TextView) rootView.findViewById(R.id.text_2_jisu_list);
        mBtnTouzhu2JisuList = (Button) rootView.findViewById(R.id.btn_touzhu_2_jisu_list);
        mBtnTouzhu2JisuList.setOnClickListener(JiSuFragment.this);
        mTextDaotime3Fram = (TextView) rootView.findViewById(R.id.text_daotime_3_fram);
        mTextJianchi3Fram = (TextView) rootView.findViewById(R.id.text_jianchi_3_fram);
        mTextJiangchishuliang3 = (TextView) rootView.findViewById(R.id.text_jiangchishuliang_3);
        mTextQishu3Fram = (TextView) rootView.findViewById(R.id.text_qishu_3_fram);
        mTextTiem3Fram = (TextView) rootView.findViewById(R.id.text_tiem_3_fram);
        mText3JisuList = (TextView) rootView.findViewById(R.id.text_3_jisu_list);
        mBtnTouzhu3JisuList = (Button) rootView.findViewById(R.id.btn_touzhu_3_jisu_list);
        mBtnTouzhu3JisuList.setOnClickListener(JiSuFragment.this);
        mTextDaotime4Fram = (TextView) rootView.findViewById(R.id.text_daotime_4_fram);
        mTextJianchi4Fram = (TextView) rootView.findViewById(R.id.text_jianchi_4_fram);
        mTextJiangchishuliang4 = (TextView) rootView.findViewById(R.id.text_jiangchishuliang_4);
        mTextQishu4Fram = (TextView) rootView.findViewById(R.id.text_qishu_4_fram);
        mTextTiem4Fram = (TextView) rootView.findViewById(R.id.text_tiem_4_fram);
        mText4JisuList = (TextView) rootView.findViewById(R.id.text_4_jisu_list);
        mBtnTouzhu4JisuList = (Button) rootView.findViewById(R.id.btn_touzhu_4_jisu_list);
        mBtnTouzhu4JisuList.setOnClickListener(JiSuFragment.this);
        mTextDaotime4Fram = (TextView) rootView.findViewById(R.id.text_daotime_4_fram);
        mTextJianchi5Fram = (TextView) rootView.findViewById(R.id.text_jianchi_5_fram);
        mTextJiangchishuliang5 = (TextView) rootView.findViewById(R.id.text_jiangchishuliang_5);
        mTextQishu5Fram = (TextView) rootView.findViewById(R.id.text_qishu_5_fram);
        mTextTiem5Fram = (TextView) rootView.findViewById(R.id.text_tiem_5_fram);
        mText5JisuList = (TextView) rootView.findViewById(R.id.text_5_jisu_list);
        mBtnTouzhu5JisuList = (Button) rootView.findViewById(R.id.btn_touzhu_5_jisu_list);
        mBtnTouzhu5JisuList.setOnClickListener(JiSuFragment.this);
        mTextDaotime5Fram = (TextView) rootView.findViewById(R.id.text_daotime_5_fram);
        mListview = (MyListview) rootView.findViewById(R.id.jisu_listview_fram);
        mCbJisuFragm = (CheckBox) rootView.findViewById(R.id.cb_jisu_fragm);
        mCbJisuFragm.setOnClickListener(JiSuFragment.this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.bigh_menu) {
            mCbJisuFragm.setBackground(getResources().getDrawable(R.mipmap.down));
            menu.showMenu();
        } else if (view.getId() == R.id.btn_touzhu_1_jisu_list) {
            IntentUtils.goTo(getContext(), Betting.class);
        } else if (view.getId() == R.id.btn_touzhu_2_jisu_list) {
            IntentUtils.goTo(getContext(), Betting.class);
        } else if (view.getId() == R.id.btn_touzhu_3_jisu_list) {
            IntentUtils.goTo(getContext(), Betting.class);
        } else if (view.getId() == R.id.btn_touzhu_4_jisu_list) {
            IntentUtils.goTo(getContext(), Betting.class);
        } else if (view.getId() == R.id.btn_touzhu_5_jisu_list) {
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
                IntentUtils.goTo(getActivity(),ModelManagementActivity.class);
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
                mCbJisuFragm.setBackground(getResources().getDrawable(R.mipmap.down));
            }
        });
        mPopupWindow.showAsDropDown(mCbJisuFragm);
        // mPopupWindow.showAtLocation(getActivity().findViewById(R.id.radiob_trend_main), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0);
    }
}
