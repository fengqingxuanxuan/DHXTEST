package com.tiantianle.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.tiantianle.Bean.NoLotteryBean;
import com.tiantianle.R;
import com.tiantianle.TimeCountDownTextView;
import com.tiantianle.intface.TimeDao;
import com.tiantianle.intface.UserTimDown;

import java.util.List;

/**
 * Created by PengBo  on 2017/2/22.
 */

public class NoLotteryAdapter extends BaseAdapter {
    private List<NoLotteryBean.BizContentBean> mBeanList;
    private UserTimDown mUserTimDown;

    public NoLotteryAdapter(TimeDao timeDao) {
        mUserTimDown = new UserTimDown();
        mUserTimDown.setUserDown(timeDao);

    }

    public void setBeanList(List<NoLotteryBean.BizContentBean> beanList) {
        mBeanList = beanList;
    }

    @Override
    public int getCount() {
        return mBeanList == null ? 0 : mBeanList.size();
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
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jianada_nolottery, null, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        NoLotteryBean.BizContentBean bizContentBean = mBeanList.get(i);
        mUserTimDown.UserTimeShow(bizContentBean.getAllsec() * 1000, viewHolder.mTextDaotime4Fram, viewHolder.mBtnTouzhu4JisuList);
        viewHolder.mTextQishu4Fram.setText("第 " + bizContentBean.getExpect() + " 期");
        String time = bizContentBean.getAddtime();
        String times[] = time.split(" ");//年月日   时分秒
        String timess[] = times[1].split(":");//时分
        viewHolder.mTextTiem4Fram.setText(timess[0] + ":" + timess[1]);
        //取消当前到时任务,(清除倒时任务,再启动,解除冲突)
        /*viewHolder.mTextDaotime4Fram.setCountDownTimes(bizContentBean.getAllsec() * 1000);
        viewHolder.mTextDaotime4Fram.start();*/

        return view;
    }


    static class ViewHolder {
        protected TimeCountDownTextView mTextDaotime4Fram;
        protected TextView mTextJianchi4Fram;
        protected TextView mTextJiangchishuliang4;
        protected TextView mTextQishu4Fram;
        protected TextView mTextTiem4Fram;
        protected TextView mText4JisuList;
        protected Button mBtnTouzhu4JisuList;

        ViewHolder(View rootView) {
            initView(rootView);
        }

        private void initView(View rootView) {
            mTextDaotime4Fram = (TimeCountDownTextView) rootView.findViewById(R.id.text_daotime_4_fram);
            mTextJianchi4Fram = (TextView) rootView.findViewById(R.id.text_jianchi_4_fram);
            mTextJiangchishuliang4 = (TextView) rootView.findViewById(R.id.text_jiangchishuliang_4);
            mTextQishu4Fram = (TextView) rootView.findViewById(R.id.text_qishu_4_fram);
            mTextTiem4Fram = (TextView) rootView.findViewById(R.id.text_tiem_4_fram);
            mText4JisuList = (TextView) rootView.findViewById(R.id.text_4_jisu_list);
            mBtnTouzhu4JisuList = (Button) rootView.findViewById(R.id.btn_touzhu_4_jisu_list);
        }
    }
}
