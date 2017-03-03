package com.tiantianle.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tiantianle.Bean.TheLotteryBean;
import com.tiantianle.R;
import com.tiantianle.intface.MingDetailed;
import com.tiantianle.intface.UserDuiHuanRemb;
import com.tiantianle.intface.UserMingDetaile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by PengBo  on 2017/2/9.
 */

public class TheLotteryAdapter extends BaseAdapter {
    private List<TheLotteryBean.BizContentBean> mList;
    private List<Map<String,String >>mMaps=new ArrayList<>();


    private UserMingDetaile mUserMingDetaile;
  /*  public TheLotteryAdapter(MingDetailed detailed){
        mUserMingDetaile=new UserMingDetaile();
        mUserMingDetaile.setUserMingDetaile(detailed);

    }*/
    public void setList(List<TheLotteryBean.BizContentBean> list) {
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
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listview_adapter_jisu, null, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();

        }
        TheLotteryBean.BizContentBean bizContentBean = mList.get(i);
        viewHolder.mTextQishuJisuList.setText(bizContentBean.getExpect());
        viewHolder.mTextResultnumAdapter.setText(bizContentBean.getResultnum());
        String time = bizContentBean.getAddtime();
        String times[]=time.split(" ");//年月日   时分秒
        String timess[]=times[1].split(":");//时分
        String timesss=timess[0]+":"+timess[1];
        viewHolder.mTextTimeJisuList.setText(timess[0]+":"+timess[1]);
       /* Map<String ,String > map=new HashMap<>();
        map.put("expect",bizContentBean.getExpect());
        map.put("type",bizContentBean.getType()+"");
        map.put("frist",bizContentBean.getFirst());
        map.put("second",bizContentBean.getSecond());
        map.put("three",bizContentBean.getThree());
        map.put("resultnum",bizContentBean.getResultnum());
        map.put("time",timesss);
        mMaps.add(map);*/
        //mUserMingDetaile.UserDetaie(mMaps);


        return view;
    }

    static class ViewHolder {
        protected TextView mTextResultnumAdapter;
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
            mTextResultnumAdapter = (TextView) rootView.findViewById(R.id.text_resultnum_adapter);
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
