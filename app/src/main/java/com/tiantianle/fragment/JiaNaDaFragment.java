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

public class JiaNaDaFragment extends Fragment implements View.OnClickListener {
    protected View rootView;
    protected ImageView mBighMenu;
    protected TextView mBighText;
    protected TextView mTextJianaDaotime1Fram;
    protected TextView mTextJianaJianchiFram;
    protected TextView mTextJianaJiangchishuliang1;
    protected TextView mTextJianaQishuFram;
    protected TextView mTextJianaTiemFram;
    protected TextView mTextJianaLJisuList;
    protected Button mBtnJianaTouzhuJiana1JisuList;
    protected TextView mTextJianaDaotime2Fram;
    protected TextView mTextJianaJianchi2Fram;
    protected TextView mTextJianaJiangchishuliang2;
    protected TextView mTextJianaQishu2Fram;
    protected TextView mTextJianaTiem2Fram;
    protected TextView mTextJiana2JisuList;
    protected Button mBtnJianaTouzhu2JisuList;
    protected TextView mTextJianaDaotime3Fram;
    protected TextView mTextJianaJianchi3Fram;
    protected TextView mTextJianaJiangchishuliang3;
    protected TextView mTextJianaQishu3Fram;
    protected TextView mTextJianaTiem3Fram;
    protected TextView mTextJiana3JisuList;
    protected Button mBtnJianaTouzhu3JisuList;
    protected TextView mTextJianaDaotime4Fram;
    protected TextView mTextJianaJianchi4Fram;
    protected TextView mTextJianaJiangchishuliang4;
    protected TextView mTextJianaQishu4Fram;
    protected TextView mTextJianaTiem4Fram;
    protected TextView mTextJiana4JisuList;
    protected Button mBtnJianaTouzhu4JisuList;
    protected TextView mTextJianaDaotime5Fram;
    protected TextView mTextJianaJianchi5Fram;
    protected TextView mTextJianaJiangchishuliang5;
    protected TextView mTextJianaQishu5Fram;
    protected TextView mTextJianaTiem5Fram;
    protected TextView mTextJiana5JisuList;
    protected Button mBtnJianaTouzhu5JisuList;
    protected MyListview mListview;
    protected CheckBox mCbJianaFragm;
    protected TextView mTextJianaFram;

    private SlidingMenu mMenu;
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
        rootView = inflater.inflate(R.layout.item_fram_jianada, null, false);
        initView(rootView);
        initData();
        FragmentActivity activity = getActivity();
        mMenu = (SlidingMenu) activity.findViewById(R.id.activity_main);
        mListview.setDividerHeight(0);
        mCbJianaFragm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    mCbJianaFragm.setBackground(getResources().getDrawable(R.mipmap.up));
                    showpopuWindow();
                }else{
                    mCbJianaFragm.setBackground(getResources().getDrawable(R.mipmap.down));
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
        mBighMenu.setOnClickListener(JiaNaDaFragment.this);
        mBighText = (TextView) rootView.findViewById(R.id.bigh_text);

        mTextJianaDaotime1Fram = (TextView) rootView.findViewById(R.id.text_jiana_daotime_1_fram);
        mTextJianaJianchiFram = (TextView) rootView.findViewById(R.id.text_jiana_jianchi_fram);
        mTextJianaJiangchishuliang1 = (TextView) rootView.findViewById(R.id.text_jiana_jiangchishuliang_1);
        mTextJianaQishuFram = (TextView) rootView.findViewById(R.id.text_jiana_qishu_fram);
        mTextJianaTiemFram = (TextView) rootView.findViewById(R.id.text_jiana_tiem_fram);
        mTextJianaLJisuList = (TextView) rootView.findViewById(R.id.text_jiana_l_jisu_list);
        mBtnJianaTouzhuJiana1JisuList = (Button) rootView.findViewById(R.id.btn_jiana_touzhu_jiana_1_jisu_list);
        mBtnJianaTouzhuJiana1JisuList.setOnClickListener(JiaNaDaFragment.this);
        mTextJianaDaotime2Fram = (TextView) rootView.findViewById(R.id.text_jiana_daotime_2_fram);
        mTextJianaJianchi2Fram = (TextView) rootView.findViewById(R.id.text_jiana_jianchi_2_fram);
        mTextJianaJiangchishuliang2 = (TextView) rootView.findViewById(R.id.text_jiana_jiangchishuliang_2);
        mTextJianaQishu2Fram = (TextView) rootView.findViewById(R.id.text_jiana_qishu_2_fram);
        mTextJianaTiem2Fram = (TextView) rootView.findViewById(R.id.text_jiana_tiem_2_fram);
        mTextJiana2JisuList = (TextView) rootView.findViewById(R.id.text_jiana_2_jisu_list);
        mBtnJianaTouzhu2JisuList = (Button) rootView.findViewById(R.id.btn_jiana_touzhu_2_jisu_list);
        mBtnJianaTouzhu2JisuList.setOnClickListener(JiaNaDaFragment.this);
        mTextJianaDaotime3Fram = (TextView) rootView.findViewById(R.id.text_jiana_daotime_3_fram);
        mTextJianaJianchi3Fram = (TextView) rootView.findViewById(R.id.text_jiana_jianchi_3_fram);
        mTextJianaJiangchishuliang3 = (TextView) rootView.findViewById(R.id.text_jiana_jiangchishuliang_3);
        mTextJianaQishu3Fram = (TextView) rootView.findViewById(R.id.text_jiana_qishu_3_fram);
        mTextJianaTiem3Fram = (TextView) rootView.findViewById(R.id.text_jiana_tiem_3_fram);
        mTextJiana3JisuList = (TextView) rootView.findViewById(R.id.text_jiana_3_jisu_list);
        mBtnJianaTouzhu3JisuList = (Button) rootView.findViewById(R.id.btn_jiana_touzhu_3_jisu_list);
        mBtnJianaTouzhu3JisuList.setOnClickListener(JiaNaDaFragment.this);
        mTextJianaDaotime4Fram = (TextView) rootView.findViewById(R.id.text_jiana_daotime_4_fram);
        mTextJianaJianchi4Fram = (TextView) rootView.findViewById(R.id.text_jiana_jianchi_4_fram);
        mTextJianaJiangchishuliang4 = (TextView) rootView.findViewById(R.id.text_jiana_jiangchishuliang_4);
        mTextJianaQishu4Fram = (TextView) rootView.findViewById(R.id.text_jiana_qishu_4_fram);
        mTextJianaTiem4Fram = (TextView) rootView.findViewById(R.id.text_jiana_tiem_4_fram);
        mTextJiana4JisuList = (TextView) rootView.findViewById(R.id.text_jiana_4_jisu_list);
        mBtnJianaTouzhu4JisuList = (Button) rootView.findViewById(R.id.btn_jiana_touzhu_4_jisu_list);
        mBtnJianaTouzhu4JisuList.setOnClickListener(JiaNaDaFragment.this);
        mTextJianaDaotime5Fram = (TextView) rootView.findViewById(R.id.text_jiana_daotime_5_fram);
        mTextJianaJianchi5Fram = (TextView) rootView.findViewById(R.id.text_jiana_jianchi_5_fram);
        mTextJianaJiangchishuliang5 = (TextView) rootView.findViewById(R.id.text_jiana_jiangchishuliang_5);
        mTextJianaQishu5Fram = (TextView) rootView.findViewById(R.id.text_jiana_qishu_5_fram);
        mTextJianaTiem5Fram = (TextView) rootView.findViewById(R.id.text_jiana_tiem_5_fram);
        mTextJiana5JisuList = (TextView) rootView.findViewById(R.id.text_jiana_5_jisu_list);
        mBtnJianaTouzhu5JisuList = (Button) rootView.findViewById(R.id.btn_jiana_touzhu_5_jisu_list);
        mBtnJianaTouzhu5JisuList.setOnClickListener(JiaNaDaFragment.this);
        mListview = (MyListview) rootView.findViewById(R.id.jiana_listview_fram);
        mCbJianaFragm = (CheckBox) rootView.findViewById(R.id.cb_jiana_fragm);
        mCbJianaFragm.setOnClickListener(JiaNaDaFragment.this);
        mTextJianaFram = (TextView) rootView.findViewById(R.id.text_jiana_fram);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.bigh_menu) {

            mCbJianaFragm.setBackground(getResources().getDrawable(R.mipmap.down));
            mMenu.showMenu();
        }  else if (view.getId() == R.id.btn_jiana_touzhu_jiana_1_jisu_list) {
            IntentUtils.goTo(getContext(), Betting.class);
        } else if (view.getId() == R.id.btn_jiana_touzhu_2_jisu_list) {
            IntentUtils.goTo(getContext(), Betting.class);
        } else if (view.getId() == R.id.btn_jiana_touzhu_3_jisu_list) {
            IntentUtils.goTo(getContext(), Betting.class);
        } else if (view.getId() == R.id.btn_jiana_touzhu_4_jisu_list) {
            IntentUtils.goTo(getContext(), Betting.class);
        } else if (view.getId() == R.id.btn_jiana_touzhu_5_jisu_list) {
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
                mCbJianaFragm.setBackground(getResources().getDrawable(R.mipmap.down));

            }
        });
        mPopupWindow.showAsDropDown(mCbJianaFragm);
    }
}
