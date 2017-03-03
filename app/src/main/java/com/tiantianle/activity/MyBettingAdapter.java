package com.tiantianle.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tiantianle.Bean.MyBettingBean;
import com.tiantianle.R;

import java.util.List;

/**
 * Created by PengBo  on 2017/2/24.
 */

public class MyBettingAdapter extends BaseAdapter {
    private List<MyBettingBean.BizContentBean> mList;

    public void setList(List<MyBettingBean.BizContentBean> list) {
        mList = list;
    }

    final int TYPE_1=0;
    final int TYPE_2=1;
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
        }
        return TYPE_2;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View view = convertView;
        int type=getItemViewType(i);
        ViewHolder viewHolder = null;
        ViewHolder1 viewHolder1=null;
        if (view == null ) {
            switch (type){
                case  TYPE_1:
                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mybetting_detailed_type1, null, false);
                    viewHolder = new ViewHolder();
                    viewHolder.mTextMybettingDetailedHaomaType1 = (TextView) view.findViewById(R.id.text_mybetting_detailed_haoma_type1);
                    viewHolder.mTextMybettingDetailedPeilvType1 = (TextView) view.findViewById(R.id.text_mybetting_detailed_peilv_type1);
                    viewHolder.mTextMybettingDetailedTouzhuType1 = (TextView) view.findViewById(R.id.text_mybetting_detailed_touzhu_type1);
                    viewHolder.mTextMybettingDetailedWinmodouType1 = (TextView) view.findViewById(R.id.text_mybetting_detailed_winmodou_type1);
                    view.setTag(viewHolder);
                    break;
                case TYPE_2:
                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mybetting_detailed_type2, null, false);
                    viewHolder1=new ViewHolder1();
                    viewHolder1.mTextMybettingDetailedHaomaType2 = (TextView) view.findViewById(R.id.text_mybetting_detailed_haoma_type2);
                    viewHolder1.mTextMybettingDetailedPeilvType2 = (TextView) view.findViewById(R.id.text_mybetting_detailed_peilv_type2);
                    viewHolder1.mTextMybettingDetailedTouzhuType2 = (TextView) view.findViewById(R.id.text_mybetting_detailed_touzhue_type2);
                    viewHolder1.mTextMybettingDetailedWinmodouType2 = (TextView) view.findViewById(R.id.text_mybetting_detailed_winmodou_type2);
                    view.setTag(viewHolder1);
                    break;
            }
        } else {
            switch (type){
                case  TYPE_1:
                    viewHolder = (ViewHolder) view.getTag();
                    break;
                case  TYPE_2:
                    viewHolder1= (ViewHolder1) view.getTag();
                    break;
            }
        }

        MyBettingBean.BizContentBean bizContentBean = mList.get(i);
        switch (type){
            case  TYPE_1:
                viewHolder.mTextMybettingDetailedHaomaType1.setText("号码");
                viewHolder.mTextMybettingDetailedPeilvType1.setText("赔率");
                viewHolder.mTextMybettingDetailedTouzhuType1.setText("投注额");
                viewHolder.mTextMybettingDetailedWinmodouType1.setText("获得魔豆");
                break;
            case  TYPE_2:
                viewHolder1.mTextMybettingDetailedPeilvType2.setText(bizContentBean.getOdds());
                viewHolder1.mTextMybettingDetailedTouzhuType2.setText(bizContentBean.getMoney()+"");
                viewHolder1.mTextMybettingDetailedWinmodouType2.setText(bizContentBean.getLuckresult()+"");
                break;
        }

        return view;
    }

    static class ViewHolder {
        protected TextView mTextMybettingDetailedHaomaType1;
        protected TextView mTextMybettingDetailedPeilvType1;
        protected TextView mTextMybettingDetailedTouzhuType1;
        protected TextView mTextMybettingDetailedWinmodouType1;
    }
    static class ViewHolder1{
        protected TextView mTextMybettingDetailedHaomaType2;
        protected TextView mTextMybettingDetailedPeilvType2;
        protected TextView mTextMybettingDetailedTouzhuType2;
        protected TextView mTextMybettingDetailedWinmodouType2;

    }
}

