package com.tiantianle.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.tiantianle.R;

import java.util.List;

import cn.iwgang.countdownview.CountdownView;

/**
 * Created by PengBo  on 2017/2/9.
 */

public class JiSuLiseFragmentAdapter extends BaseAdapter {
    private List<Integer> mList;

    public void setList(List<Integer> list) {
        mList = list;
    }

    @Override
    public int getCount() {
        return mList==null?0:mList.size();
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
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listview_adapter_jisu, null, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();

        }
        Integer integer = mList.get(i);

        return view;
    }

    static class ViewHolder {

        protected TextView mTextJiangchi;
        protected TextView mTextJiangchishuliang;
        protected TextView mTextDiJisuList;
        protected TextView mTextQishuJisuList;
        protected TextView mTextShuJisuList;
        protected TextView mTextTimeJisuList;
        protected TextView mTextLJisuList;


        ViewHolder(View rootView) {
            initView(rootView);
        }

        private void initView(View rootView) {

            mTextJiangchi = (TextView) rootView.findViewById(R.id.text_jiangchi);
            mTextJiangchishuliang = (TextView) rootView.findViewById(R.id.text_jiangchishuliang);
            mTextDiJisuList = (TextView) rootView.findViewById(R.id.text_di_jisu_list);
            mTextQishuJisuList = (TextView) rootView.findViewById(R.id.text_qishu_jisu_list);
            mTextShuJisuList = (TextView) rootView.findViewById(R.id.text_shu_jisu_list);
            mTextTimeJisuList = (TextView) rootView.findViewById(R.id.text_time_jisu_list);
            mTextLJisuList = (TextView) rootView.findViewById(R.id.text_l_jisu_list);

        }
    }
}
