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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tiantianle.Bean.AllIndianaBean;
import com.tiantianle.R;
import com.tiantianle.intface.ShowTotalcount;
import com.tiantianle.intface.UserTotalcoutNum;

import java.util.List;

/**
 * Created by PengBo  on 2017/2/16.
 */

public class JieXiaoAdapterIndinan extends BaseAdapter {
    private List<AllIndianaBean.BizContentBean> mList;
    private UserTotalcoutNum mUserTotalcoutNum;
    private LocalBroadcastManager mLocalBroadcastManager;

    public JieXiaoAdapterIndinan(ShowTotalcount showTotalcount) {
        mUserTotalcoutNum = new UserTotalcoutNum();
        mUserTotalcoutNum.setShowTotalcout(showTotalcount);

    }

    public void setList(List<AllIndianaBean.BizContentBean> list) {
        mList = list;
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
    public View getView(final int i, View convertView, final ViewGroup viewGroup) {
        View view = convertView;
        ViewHolder viewHolder = null;
        if (view == null || !(view.getTag() instanceof ViewHolder)) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_jiexiao_adapter, null, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        AllIndianaBean.BizContentBean bizContentBean = mList.get(i);
        String specname = bizContentBean.getSpecname();
        specname = specname.substring(0, specname.length() - 2);
        mUserTotalcoutNum.UserTotal(bizContentBean.getTotalcount());
        viewHolder.mTextModouItmeJiexiaoAdapterIndinand.setText(specname);
        String issuenum = bizContentBean.getIssuenum();
        String replace = issuenum.replace(bizContentBean.getSpeccode(), "");
        viewHolder.mTextQishuJiexiaoFramIndinanremb.setText(replace);
        // viewHolder.mTextNameItmeAllAdapterIndinand.setText(bizContentBean.getWarename());
        viewHolder.mTextRenciJiexiaoFramIndinanremb.setText(bizContentBean.getPlaynum() + "");
        viewHolder.mTextRenci2JiexiaoFramIndinanremb.setText(bizContentBean.getWinnum() + "");
        viewHolder.mText3JiexiaoFramIndinanremb.setText(bizContentBean.getWinname() == null ? "" : bizContentBean.getWinname());
        int sta = bizContentBean.getStatus();
        if (sta == 2) {

        }

        viewHolder.mBtnAgainJiexiaoJiexiaoFramIndinanremb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mLocalBroadcastManager = LocalBroadcastManager.getInstance(viewGroup.getContext());
                Intent intent = new Intent("com.tiantianle.adapter");
                mLocalBroadcastManager.sendBroadcast(intent);
            }
        });
        return view;
    }


    static class ViewHolder {
        protected ImageView mImgAllFramIndinanremb;
        protected TextView mTextModouItmeJiexiaoAdapterIndinand;
        protected TextView mTextNameItmeJiexiaoAdapterIndinand;
        protected TextView mTextJiexiaoFramIndinanremb;
        protected TextView mTextQishuJiexiaoFramIndinanremb;
        protected TextView mText11jiexiaoFramIndinanrenb;
        protected TextView mTextRenciJiexiaoFramIndinanremb;
        protected LinearLayout mLlQuanbuIndinanremb;
        protected TextView mText2JiexiaoFramIndinanremb;
        protected TextView mText3JiexiaoFramIndinanremb;
        protected TextView mTextRenci2JiexiaoFramIndinanremb;
        protected Button mBtnAgainJiexiaoJiexiaoFramIndinanremb;
        protected RelativeLayout mLlJiexiaoJieixaoIndinanremb;

        ViewHolder(View rootView) {
            initView(rootView);
        }

        private void initView(View rootView) {
            mImgAllFramIndinanremb = (ImageView) rootView.findViewById(R.id.img_all_fram_indinanremb);
            mTextModouItmeJiexiaoAdapterIndinand = (TextView) rootView.findViewById(R.id.text_modou_itme_jiexiao_adapter_indinand);
            mTextNameItmeJiexiaoAdapterIndinand = (TextView) rootView.findViewById(R.id.text_name_itme_jiexiao_adapter_indinand);
            mTextJiexiaoFramIndinanremb = (TextView) rootView.findViewById(R.id.text_jiexiao_fram_indinanremb);
            mTextQishuJiexiaoFramIndinanremb = (TextView) rootView.findViewById(R.id.text_qishu_jiexiao_fram_indinanremb);
            mText11jiexiaoFramIndinanrenb = (TextView) rootView.findViewById(R.id.text1_1jiexiao_fram_indinanrenb);
            mTextRenciJiexiaoFramIndinanremb = (TextView) rootView.findViewById(R.id.text_renci_jiexiao_fram_indinanremb);
            mLlQuanbuIndinanremb = (LinearLayout) rootView.findViewById(R.id.ll_quanbu_indinanremb);
            mText2JiexiaoFramIndinanremb = (TextView) rootView.findViewById(R.id.text2_jiexiao_fram_indinanremb);
            mText3JiexiaoFramIndinanremb = (TextView) rootView.findViewById(R.id.text3_jiexiao_fram_indinanremb);
            mTextRenci2JiexiaoFramIndinanremb = (TextView) rootView.findViewById(R.id.text_renci2_jiexiao_fram_indinanremb);
            mBtnAgainJiexiaoJiexiaoFramIndinanremb = (Button) rootView.findViewById(R.id.btn_again_jiexiao_jiexiao_fram_indinanremb);
            mLlJiexiaoJieixaoIndinanremb = (RelativeLayout) rootView.findViewById(R.id.ll_jiexiao_jieixao_indinanremb);
        }
    }
}
