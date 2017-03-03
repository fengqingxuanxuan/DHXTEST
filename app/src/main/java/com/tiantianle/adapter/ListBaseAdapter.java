package com.tiantianle.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * @类名：ListBaseAdapter.java
 * @功能描述:列表控件绑定数据基类
 * @作者：Candle
 * @创建时间:2014-12-26.下午1:42:20
 */
public abstract class ListBaseAdapter<T> extends BaseAdapter {
    protected Context mContext;
    protected LayoutInflater inflate;
    protected List<T> list;

    public ListBaseAdapter(Context mContext, List<T> list) {
        this.list = list;
        this.mContext = mContext;
        this.inflate = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 重写view方法
        return initView(position, convertView);

    }

    /**
     * getView方法需重写
     *
     * @param position
     * @param convertView
     * @return
     */
    public abstract View initView(int position, View convertView);

    public void Update(List<T> list) {
        this.list = list;
        this.notifyDataSetChanged();
    }

}
