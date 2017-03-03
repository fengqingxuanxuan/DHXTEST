package com.tiantianle.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.tiantianle.Bean.ModelManagementBean;
import com.tiantianle.R;

import java.util.List;

/**
 * Created by Administrator on 2017/2/23.
 *
 * 模式管理adapter
 */
public class ModeiManagementAdapter extends ListBaseAdapter<ModelManagementBean.BizContentBean> {

    private LayoutInflater inflater;
    private Callback callback;

    public ModeiManagementAdapter(Context mContext, List<ModelManagementBean.BizContentBean> list) {
        super(mContext, list);
        inflater = LayoutInflater.from(mContext);
        callback = (Callback) mContext;
    }

    @Override
    public View initView(int position, View convertView) {
        View view;
        ViewHolder holder;
        if (convertView != null) {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        } else {
            view = inflater.inflate(R.layout.item_listview_model, null);
            holder = new ViewHolder();
            holder.Mode_Name = (TextView) view.findViewById(R.id.Mode_Name);
            holder.money_sum = (TextView) view.findViewById(R.id.money_sum);
            holder.Modify_Name = (TextView) view.findViewById(R.id.Modify_Name);
            holder.Delete_Name = (TextView) view.findViewById(R.id.Delete_Name);

            holder.Modify_Name.setTag(position);
            holder.Delete_Name.setTag(position);
            view.setTag(holder);
        }
        holder.Mode_Name.setText(list.get(position).getName());
        holder.money_sum.setText(list.get(position).getMoney() + "");

        holder.Modify_Name.setOnClickListener(new MyOnClickListener());
        holder.Delete_Name.setOnClickListener(new MyOnClickListener());
        return view;
    }

    class ViewHolder {
        private TextView Mode_Name;//模式名称
        private TextView money_sum;//投注金额
        private TextView Modify_Name;//改名
        private TextView Delete_Name;//删除
    }

    private class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.Modify_Name:  //改名
                    callback.ModifyClick(v);
                    break;

                case R.id.Delete_Name:  //删除
                    callback.DeleteClick(v);
                    break;
            }
        }
    }

    public interface Callback {
        public void ModifyClick(View v);
        public void DeleteClick(View v);
    }
}
