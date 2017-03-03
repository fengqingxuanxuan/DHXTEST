package com.tiantianle.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.allen.library.SuperTextView;
import com.tiantianle.Bean.RedRECORDBean;
import com.tiantianle.R;

import java.util.List;

/**
 * Created by PengBo  on 2017/2/6.
 * 中奖红包列表
 */


public class HongBaoListviewAdapter extends ListBaseAdapter<RedRECORDBean.BizContentBean> {

    private LayoutInflater inflater;

    public HongBaoListviewAdapter(Context mContext, List list) {
        super(mContext, list);
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public View initView(int position, View convertView) {
        View view;
        ViewHolder holder;
        if (convertView != null) {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        } else {
            view = inflater.inflate(R.layout.item_hongbao_mylist_adapter, null);
            holder = new ViewHolder();
            holder.tv = (SuperTextView) view.findViewById(R.id.suptext_hongbao_fram_mlist);
            view.setTag(holder);
        }

        holder.tv.setLeftString(list.get(position).getName()); //用户名
        holder.tv.setCenterString(list.get(position).getPrice() + ""); //金额
        holder.tv.setRightString(list.get(position).getCtime());//时间


        return view;
    }

    class ViewHolder {
        private SuperTextView tv;//每一个Textview
    }
}
