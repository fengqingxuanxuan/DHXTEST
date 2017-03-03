package com.tiantianle.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.allen.library.SuperTextView;
import com.tiantianle.Bean.WinBean;
import com.tiantianle.R;

import java.util.List;

/**
 * Created by PengBo  on 2017/2/23.
 */

public class WinningNameAdapter extends BaseAdapter {
    private List<WinBean.BizContentBean> mList;
    final int TYPE_1=0;
    final int TYPE_2=1;

    public void setList(List<WinBean.BizContentBean> list) {
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
        ViewHolder viewHolder = null;
        ViewHoler1 viewHoler1=null;
        if (view == null ) {
            switch (type){
                case TYPE_1:
                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_winname_detailed_type1, null, false);
                    viewHolder = new ViewHolder();
                    viewHolder.mSuptertextMybettingDedailedType1 = (SuperTextView) view.findViewById(R.id.suptertext_mybetting_dedailed_type1);
                    view.setTag(viewHolder);
                    break;
                case TYPE_2:
                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_winname_detailed_type2, null, false);
                    viewHoler1=new ViewHoler1();
                    viewHoler1.mSuptertextMybettingDedailedType2= (SuperTextView) view.findViewById(R.id.suptertext_mybetting_dedailed_type2);
                    view.setTag(viewHoler1);
                    break;
            }
        } else {

            switch (type){
                case TYPE_1:
                    viewHolder = (ViewHolder) view.getTag();
                    break;
                case  TYPE_2:
                    viewHoler1 = (ViewHoler1) view.getTag();
                    break;
            }
        }
        WinBean.BizContentBean bizContentBean = mList.get(i);
        switch (type){
            case TYPE_1:
                viewHolder.mSuptertextMybettingDedailedType1.setLeftString("用户ID");
                viewHolder.mSuptertextMybettingDedailedType1.setCenterString("投注额");
                viewHolder.mSuptertextMybettingDedailedType1.setRightString("获得魔豆");
                break;
            case TYPE_2:
                viewHoler1.mSuptertextMybettingDedailedType2.setLeftString(bizContentBean.getNickname());
                viewHoler1.mSuptertextMybettingDedailedType2.setCenterString(bizContentBean.getMoney()+"");
                viewHoler1.mSuptertextMybettingDedailedType2.setRightString(bizContentBean.getLuckresult()+"");
                break;
        }
        return view;
    }

    static class ViewHoler1{
        protected  SuperTextView mSuptertextMybettingDedailedType2;
    }

    static class ViewHolder {
        protected SuperTextView mSuptertextMybettingDedailedType1;
    }
}
