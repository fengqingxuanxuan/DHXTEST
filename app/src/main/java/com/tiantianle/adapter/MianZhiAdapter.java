package com.tiantianle.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tiantianle.Bean.YidongBean;
import com.tiantianle.R;

import java.util.List;

/**
 * Created by PengBo  on 2017/2/18.
 */

public class MianZhiAdapter extends BaseAdapter {
    private List<YidongBean.BizContentBean> mBizContentBeen;
    private int selectorPosition = -1;


    public void setBizContentBeen(List<YidongBean.BizContentBean> bizContentBeen) {
        mBizContentBeen = bizContentBeen;
    }

    @Override
    public int getCount() {
        return mBizContentBeen == null ? 0 : mBizContentBeen.size();
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
    public View getView(int i, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder = null;
        if (view == null || !(view.getTag() instanceof ViewHolder)) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gridview_yidongkami, null, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        YidongBean.BizContentBean bizContentBean = mBizContentBeen.get(i);
        viewHolder.mTvMianzhiYidongkam.setText(bizContentBean.getSpecname());
        if(selectorPosition==i){
            viewHolder.mLlMianzhiYingdongkam.setBackgroundResource(R.drawable.radiobutton_back_shap_select);

        }else{
            viewHolder.mLlMianzhiYingdongkam.setBackgroundResource(R.drawable.radiobutton_back_shap_nomal);

        }

        return view;
    }


    public void changeState(int pos) {
            selectorPosition = pos;
            notifyDataSetChanged();

    }
    static class ViewHolder {
        protected TextView mTvMianzhiYidongkam;
        protected RelativeLayout mLlMianzhiYingdongkam;

        ViewHolder(View rootView) {
            initView(rootView);
        }

        private void initView(View rootView) {
            mTvMianzhiYidongkam = (TextView) rootView.findViewById(R.id.tv_mianzhi_yidongkam);
            mLlMianzhiYingdongkam = (RelativeLayout) rootView.findViewById(R.id.ll_mianzhi_yingdongkam);
        }
    }
}
