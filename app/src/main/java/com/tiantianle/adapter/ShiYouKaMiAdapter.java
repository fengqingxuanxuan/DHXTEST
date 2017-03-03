package com.tiantianle.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tiantianle.R;

import java.util.List;

/**
 * Created by PengBo  on 2017/2/7.
 */

public class ShiYouKaMiAdapter extends BaseAdapter {
    private List<String> mList;

    public void setList(List<String> list) {
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
    public View getView(int i, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder = null;
        if (view == null || !(view.getTag() instanceof ViewHolder)) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jingdongeka_list_dongtai_adapter, null, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        String s = mList.get(i);
        viewHolder.mTextUserIdListadpterJingdongeka.setText(s);
        return view;
    }

    static class ViewHolder {
        protected TextView mTextListJingdongeka;
        protected TextView mTextUserIdListadpterJingdongeka;
        protected TextView mTextTimeDontaiListJingdongeka;

        ViewHolder(View rootView) {
            initView(rootView);
        }

        private void initView(View rootView) {
            mTextListJingdongeka = (TextView) rootView.findViewById(R.id.text_list_jingdongeka);
            mTextUserIdListadpterJingdongeka = (TextView) rootView.findViewById(R.id.text_userId_listadpter_jingdongeka);
            mTextTimeDontaiListJingdongeka = (TextView) rootView.findViewById(R.id.text_time_dontai_list_jingdongeka);
        }
    }
}
