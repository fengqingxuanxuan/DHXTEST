package com.tiantianle.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.allen.library.SuperTextView;
import com.tiantianle.Bean.DuiHuanBean;
import com.tiantianle.R;
import com.tiantianle.intface.DuiHuan;
import com.tiantianle.intface.UserDuiHuanRemb;

import java.util.List;

/**
 * Created by PengBo  on 2017/2/6.
 */

public class ShaoppingDuiHuanAdapter extends BaseAdapter {
    private List<DuiHuanBean.BizContentBean> mList;
    private UserDuiHuanRemb mUserDuiHuanRemb;
    final int TYPE_1=0;
    final int TYPE_2=1;
    public ShaoppingDuiHuanAdapter(DuiHuan duiHuan){
        mUserDuiHuanRemb=new UserDuiHuanRemb();
        mUserDuiHuanRemb.setDuiHuan(duiHuan);

    }
    public void setList(List<DuiHuanBean.BizContentBean> list) {
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
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return TYPE_1;
        }else{

            return TYPE_2;
        }
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View view = convertView;
        int type=getItemViewType(i);
        ViewHolder1 viewHolder = null;
        ViewHolder2 viewHolder2=null;
        if (view == null ) {
            switch (type){
                case  TYPE_1 :
                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shaoppingduihuan2_list_adapter, null, false);
                    viewHolder2=new ViewHolder2();
                    viewHolder2.mSuptertextShaoppingduihuan2= (SuperTextView) view.findViewById(R.id.suptertext_shaopping_duihuan2);
                    view.setTag(viewHolder2);
                    break;
                case TYPE_2:
                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shaoppingduihuan_listview_adapter, null, false);
                    viewHolder=new ViewHolder1();
                    viewHolder.mSuptertextShaoppduihuaJilu= (SuperTextView) view.findViewById(R.id.suptertext_shaoppduihua_jilu);
                    view.setTag(viewHolder);
                    break;
            }
        } else {
            switch (type){
                case TYPE_1:
                    viewHolder2= (ViewHolder2) view.getTag();
                    break;
                case TYPE_2:
                    viewHolder= (ViewHolder1) view.getTag();
                    break;
            }
        }
        DuiHuanBean.BizContentBean bizContentBean = mList.get(i);
        switch (type){
            case TYPE_1:
                viewHolder2.mSuptertextShaoppingduihuan2.setLeftString("兑换商品");
                viewHolder2.mSuptertextShaoppingduihuan2.setCenterString("所需魔豆");
                viewHolder2.mSuptertextShaoppingduihuan2.setRightString("兑换时间");
                break;
            case TYPE_2:
                viewHolder.mSuptertextShaoppduihuaJilu.setLeftString(bizContentBean.getWarename());
                viewHolder.mSuptertextShaoppduihuaJilu.setCenterString(bizContentBean.getTotalfee()+"");
                viewHolder.mSuptertextShaoppduihuaJilu.setRightString(bizContentBean.getCreatetime());
                break;

        }
       mUserDuiHuanRemb.UserDuiTotal(bizContentBean.getTotalcount());
        return view;
    }

    static class ViewHolder1 {
        protected SuperTextView mSuptertextShaoppduihuaJilu;

    }
    static class ViewHolder2{
        private SuperTextView mSuptertextShaoppingduihuan2;

    }
}
