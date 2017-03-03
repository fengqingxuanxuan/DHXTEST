package com.tiantianle.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tiantianle.Bean.ShaoppingBean;
import com.tiantianle.R;

import java.util.List;

/**
 * Created by PengBo  on 2017/2/16.
 */

public class ShaoppingAdapter extends BaseAdapter {
    private List<ShaoppingBean.BizContentBean> mList;

    public void setList(List<ShaoppingBean.BizContentBean> list) {
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
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shaopping_adapter, null, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        ShaoppingBean.BizContentBean bizContentBean = mList.get(i);
         viewHolder.mTextTatleShaopping.setText(bizContentBean.getWarename());

        return view;
    }

    static class ViewHolder {
        protected TextView mTextTatleShaopping;

        ViewHolder(View rootView) {
            initView(rootView);
        }

        private void initView(View rootView) {
            mTextTatleShaopping = (TextView) rootView.findViewById(R.id.text_tatle_shaopping);
        }
    }
}
