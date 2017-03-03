package com.tiantianle.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tiantianle.Bean.NewDongTaiBean;
import com.tiantianle.R;
import com.tiantianle.intface.DuiHuan;
import com.tiantianle.intface.UserDuiHuanRemb;

import java.util.List;

/**
 * Created by PengBo  on 2017/2/7.
 */

public class YiDongKaMiAdapter extends BaseAdapter {
    private List<NewDongTaiBean.BizContentBean> mList;
    private UserDuiHuanRemb mUserDuiHuanRemb;

    public YiDongKaMiAdapter(DuiHuan duiHuan) {
        mUserDuiHuanRemb = new UserDuiHuanRemb();
        mUserDuiHuanRemb.setDuiHuan(duiHuan);


    }

    public void setList(List<NewDongTaiBean.BizContentBean> list) {
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
        NewDongTaiBean.BizContentBean bizContentBean = mList.get(i);
        mUserDuiHuanRemb.UserDuiTotal(bizContentBean.getTotalcount());
        viewHolder.mTextTimeDontaiListJingdongeka.setText(bizContentBean.getCreatetime());
        viewHolder.mTextUserIdListadpterJingdongeka.setText(bizContentBean.getNickname());
        viewHolder.mTextDuihuanListadaterJingdongeka.setText("兑换"+bizContentBean.getSpecname()+bizContentBean.getWarename());
        return view;
    }

    static class ViewHolder {
        protected TextView mTextListJingdongeka;
        protected TextView mTextUserIdListadpterJingdongeka;
        protected TextView mTextDuihuanListadaterJingdongeka;
        protected TextView mTextTimeDontaiListJingdongeka;

        ViewHolder(View rootView) {
            initView(rootView);
        }

        private void initView(View rootView) {
            mTextListJingdongeka = (TextView) rootView.findViewById(R.id.text_list_jingdongeka);
            mTextUserIdListadpterJingdongeka = (TextView) rootView.findViewById(R.id.text_userId_listadpter_jingdongeka);
            mTextDuihuanListadaterJingdongeka = (TextView) rootView.findViewById(R.id.text_duihuan_listadater_jingdongeka);
            mTextTimeDontaiListJingdongeka = (TextView) rootView.findViewById(R.id.text_time_dontai_list_jingdongeka);
        }
    }
}
