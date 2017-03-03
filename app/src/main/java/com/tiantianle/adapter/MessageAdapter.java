package com.tiantianle.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.tiantianle.Bean.MessageBean;
import com.tiantianle.R;

import java.util.List;

/**
 * Created by Administrator on 2017/2/8.
 *
 * 我的消息adapter
 */

public class MessageAdapter extends ListBaseAdapter<MessageBean.message>{

    private LayoutInflater inflater;

    public MessageAdapter(Context mContext, List list) {
        super(mContext, list);
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public View initView(int position, View convertView) {
        View view;
        ViewHolder holder;
        if(convertView != null){
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }else{
            view = inflater.inflate(R.layout.item_message,null);
            holder = new ViewHolder();
            holder.tv_title = (TextView) view.findViewById(R.id.tv_title);
            holder.tv_time = (TextView) view.findViewById(R.id.tv_time);
            view.setTag(holder);
        }

        holder.tv_title.setText(list.get(position).getTitle());

        holder.tv_time.setText(list.get(position).getTime());

        return view;
    }

    class ViewHolder{
        private TextView tv_time;//时间
        private TextView tv_title;//标题
    }
}
