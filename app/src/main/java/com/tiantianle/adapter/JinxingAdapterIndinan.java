package com.tiantianle.adapter;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tiantianle.Bean.AllIndianaBean;
import com.tiantianle.R;
import com.tiantianle.intface.MyInterface;
import com.tiantianle.intface.ShowTotalcount;
import com.tiantianle.intface.UserPopuwindow;
import com.tiantianle.intface.UserTotalcoutNum;
import com.tiantianle.utils.ToastUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by PengBo  on 2017/2/16.
 */

public class JinxingAdapterIndinan extends BaseAdapter {
    private List<AllIndianaBean.BizContentBean> mList;
    private UserTotalcoutNum mUserTotalcoutNum;
    private LocalBroadcastManager mLocalBroadcastManager;
    private UserPopuwindow mUserPopuwindow;
    private String nain;



    public void setList(List<AllIndianaBean.BizContentBean> list) {
        mList = list;
    }

    public JinxingAdapterIndinan(ShowTotalcount showTotalcount,MyInterface myInterface) {
        mUserTotalcoutNum = new UserTotalcoutNum();
        mUserTotalcoutNum.setShowTotalcout(showTotalcount);
        mUserPopuwindow = new UserPopuwindow();
        mUserPopuwindow.setMyInterface(myInterface);
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, final ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder = null;
        if (view == null || !(view.getTag() instanceof ViewHolder)) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_all_adapter_indinanremb, null, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        final AllIndianaBean.BizContentBean bizContentBean = mList.get(i);
        String specname = bizContentBean.getSpecname();
        mUserTotalcoutNum.UserTotal(bizContentBean.getTotalcount());
        //mUserPopuwindow.userPopu(bizContentBean.getChangeid(),bizContentBean.getIssuenum(),bizContentBean.getWarecode(),bizContentBean.getWarename(),bizContentBean.getSpeccode(),bizContentBean.getSpecname(),bizContentBean.getPrice(),bizContentBean.getType(),nain,bizContentBean.getUsernum(),bizContentBean.getPlaynum());
        specname = specname.substring(0, specname.length() - 2);
        viewHolder.mTextModouItmeAllAdapterIndinand.setText(specname);
        String issuenum = bizContentBean.getIssuenum();
        String replace = issuenum.replace(bizContentBean.getSpeccode(), "");
        viewHolder.mTextQishuAllFramIndinanremb.setText(replace);
        viewHolder.mTextNameItmeAllAdapterIndinand.setText(bizContentBean.getWarename());
        viewHolder.mTextRenciAllFramIndinanremb.setText(bizContentBean.getPlaynum() + "");
        viewHolder.mProgressBarAllAdapterIndianaremb.setMax(bizContentBean.getUsernum());
        viewHolder.mProgressBarAllAdapterIndianaremb.setProgress(bizContentBean.getPlaynum());
        int sta = bizContentBean.getStatus();
        if (sta == 0) {
            viewHolder.mLlAllAdapterIndinanremb.setVisibility(View.VISIBLE);
            viewHolder.mLlAllJieixaoIndinanremb.setVisibility(View.GONE);
            viewHolder.mRlAllAdapterIndinanremb.setVisibility(View.GONE);
        } else if (sta == 1) {
            viewHolder.mLlAllJieixaoIndinanremb.setVisibility(View.GONE);
            viewHolder.mLlAllAdapterIndinanremb.setVisibility(View.GONE);
            viewHolder.mRlAllAdapterIndinanremb.setVisibility(View.VISIBLE);
        } else if (sta == 2) {
            viewHolder.mLlQuanbuIndinanremb.setVisibility(View.GONE);
            viewHolder.mLlAllJieixaoIndinanremb.setVisibility(View.GONE);
            viewHolder.mRlAllAdapterIndinanremb.setVisibility(View.GONE);
            viewHolder.mLlAllAdapterIndinanremb.setVisibility(View.GONE);
        }
        viewHolder.mTextUsernumAllIndinaremb.setText("总需" + bizContentBean.getUsernum() + "人次");
        viewHolder.mTextShentyvrenshuAllIndinanremb.setText("剩余" + (bizContentBean.getUsernum() - bizContentBean.getPlaynum()));
        viewHolder.mBtnZhuijiaAllAdapterIndinanremb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Getime();
                mUserPopuwindow.userPopu(bizContentBean.getChangeid(),bizContentBean.getIssuenum(),bizContentBean.getWarecode(),bizContentBean.getWarename(),bizContentBean.getSpeccode(),bizContentBean.getSpecname(),bizContentBean.getPrice(),bizContentBean.getType(),nain,bizContentBean.getUsernum(),bizContentBean.getPlaynum());
            }
        });
        viewHolder.mBtnAgainAllFramIndinanremb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mLocalBroadcastManager= LocalBroadcastManager.getInstance(parent.getContext());
                Intent intent=new Intent("com.tiantianle.adapter");
                mLocalBroadcastManager.sendBroadcast(intent);

            }
        });
        viewHolder.mBtnAgainAllJiexiaoFramIndinanremb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return view;
    }
    private void Getime(){
        SimpleDateFormat format=new SimpleDateFormat("yyyyMMddHHmmss");
        Date curDate    =   new Date(System.currentTimeMillis());
        nain=format.format(curDate);
    }
    static class ViewHolder {
        protected ImageView mImgAllFramIndinanremb;
        protected TextView mTextModouItmeAllAdapterIndinand;
        protected TextView mTextNameItmeAllAdapterIndinand;
        protected TextView mTextAllFramIndinanremb;
        protected TextView mTextQishuAllFramIndinanremb;
        protected TextView mText11allFramIndinanrenb;
        protected TextView mTextRenciAllFramIndinanremb;
        protected LinearLayout mLlQuanbuIndinanremb;
        protected ProgressBar mProgressBarAllAdapterIndianaremb;
        protected TextView mTextUsernumAllIndinaremb;
        protected TextView mTextShentyvrenshuAllIndinanremb;
        protected Button mBtnZhuijiaAllAdapterIndinanremb;
        protected LinearLayout mLlAllAdapterIndinanremb;
        protected Button mBtnAgainAllFramIndinanremb;
        protected RelativeLayout mRlAllAdapterIndinanremb;
        protected TextView mText2AllFramIndinanremb;
        protected TextView mText3AllFramIndinanremb;
        protected TextView mTextRenci2AllFramIndinanremb;
        protected Button mBtnAgainAllJiexiaoFramIndinanremb;
        protected RelativeLayout mLlAllJieixaoIndinanremb;

        ViewHolder(View rootView) {
            initView(rootView);
        }

        private void initView(View rootView) {
            mImgAllFramIndinanremb = (ImageView) rootView.findViewById(R.id.img_all_fram_indinanremb);
            mTextModouItmeAllAdapterIndinand = (TextView) rootView.findViewById(R.id.text_modou_itme_all_adapter_indinand);
            mTextNameItmeAllAdapterIndinand = (TextView) rootView.findViewById(R.id.text_name_itme_all_adapter_indinand);
            mTextAllFramIndinanremb = (TextView) rootView.findViewById(R.id.text_all_fram_indinanremb);
            mTextQishuAllFramIndinanremb = (TextView) rootView.findViewById(R.id.text_qishu_all_fram_indinanremb);
            mText11allFramIndinanrenb = (TextView) rootView.findViewById(R.id.text1_1all_fram_indinanrenb);
            mTextRenciAllFramIndinanremb = (TextView) rootView.findViewById(R.id.text_renci_all_fram_indinanremb);
            mLlQuanbuIndinanremb = (LinearLayout) rootView.findViewById(R.id.ll_quanbu_indinanremb);
            mProgressBarAllAdapterIndianaremb = (ProgressBar) rootView.findViewById(R.id.progressBar_all_adapter_indianaremb);
            mTextUsernumAllIndinaremb = (TextView) rootView.findViewById(R.id.text_usernum_all_indinaremb);
            mTextShentyvrenshuAllIndinanremb = (TextView) rootView.findViewById(R.id.text_shentyvrenshu_all_indinanremb);
            mBtnZhuijiaAllAdapterIndinanremb = (Button) rootView.findViewById(R.id.btn_zhuijia_all_adapter_indinanremb);
            mLlAllAdapterIndinanremb = (LinearLayout) rootView.findViewById(R.id.ll_all_adapter_indinanremb);
            mBtnAgainAllFramIndinanremb = (Button) rootView.findViewById(R.id.btn_again_all_fram_indinanremb);
            mRlAllAdapterIndinanremb = (RelativeLayout) rootView.findViewById(R.id.rl_all_adapter_indinanremb);
            mText2AllFramIndinanremb = (TextView) rootView.findViewById(R.id.text2_all_fram_indinanremb);
            mText3AllFramIndinanremb = (TextView) rootView.findViewById(R.id.text3_all_fram_indinanremb);
            mTextRenci2AllFramIndinanremb = (TextView) rootView.findViewById(R.id.text_renci2_all_fram_indinanremb);
            mBtnAgainAllJiexiaoFramIndinanremb = (Button) rootView.findViewById(R.id.btn_again_all_jiexiao_fram_indinanremb);
            mLlAllJieixaoIndinanremb = (RelativeLayout) rootView.findViewById(R.id.ll_all_jieixao_indinanremb);
        }
    }
}
