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
 *
 * item_hong_bao_duihuan_adapter
 *
 * 兑换记录adapter
 */

public class RecordAdapter extends ListBaseAdapter<RedRECORDBean.BizContentBean> {

    private LayoutInflater inflater;

    public RecordAdapter(Context mContext, List<RedRECORDBean.BizContentBean> list) {
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
            view = inflater.inflate(R.layout.item_hong_bao_duihuan_adapter, null);
            holder = new ViewHolder();
            holder.tv = (SuperTextView) view.findViewById(R.id.suptertext_duihuan_hongbao);
            view.setTag(holder);
        }

        holder.tv.setLeftString(list.get(position).getPrice() + ""); //金额
        holder.tv.setRightString(list.get(position).getCtime());//时间


        return view;
    }
    class ViewHolder {
        private SuperTextView tv;//每一个Textview
    }
}
