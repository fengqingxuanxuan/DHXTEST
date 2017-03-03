package com.tiantianle.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.tiantianle.Bean.HongBaoFragmentBean;
import com.tiantianle.Bean.RedRECORDBean;
import com.tiantianle.MainActivity;
import com.tiantianle.R;
import com.tiantianle.activity.HongBaoRember;
import com.tiantianle.adapter.HongBaoListviewAdapter;
import com.tiantianle.utils.Constant;
import com.tiantianle.utils.HttpApi;
import com.tiantianle.utils.ToastUtils;
import com.tiantianle.view.MyListview;
import com.tiantianle.view.PullToRefreshView;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.iwgang.countdownview.CountdownView;

/**
 * Created by wyj on 2017/1/22.
 * 整点红包
 */

public class HongBaoFragment extends Fragment {

    private final int YES = 1;
    private final int REDYES = 2;
    protected View rootView;
    private SlidingMenu mMenu;
    private MyOnClickListener OnClickListener;
    private Gson gs;


    private int packeid = -1;  //订单ID
    private int detailid = -1;  //明细ID
    private int page = 1;

    protected ImageView mBighMenu;  //侧滑
    protected TextView mBighText;   //title整点抢红包
    protected TextView mTextHongbaoRecord; //红包记录
    protected CountdownView mTextCountdownvHongbaoFram;  //倒计时

    private String[] state = {"未开始", "进行中", "已结束"};  //三个状态
    private int[] bg = {R.drawable.shape_red_envelope_bg_red, R.drawable.shape_red_envelope_bg_red
            , R.drawable.shape_red_envelope_bg_gray};  //三个状态对应背景颜色

    private PullToRefreshView ll_hongbao; //刷新框架

    private LinearLayout ll_scene_one;//第一场
    private LinearLayout ll_scene_two;//第二场
    private LinearLayout ll_scene_three;//第三场

    private TextView tv_one_static; //第一场开始时间
    private TextView tv_one_end; //第一场结束时间
    private TextView tv_one_state; //第一场状态

    private TextView tv_two_static; //第二场开始时间
    private TextView tv_two_end; //第二场结束时间
    private TextView tv_two_state; //第二场状态

    private TextView tv_three_static; //第三场开始时间
    private TextView tv_three_end; //第三场结束时间
    private TextView tv_three_state; //第三场状态

    private HongBaoFragmentBean bean;     //红包列表
    private List<HongBaoFragmentBean.BizContentBean> list;  //所有红包

    private RedRECORDBean redbean; // 中奖红包列表bean
    private List<RedRECORDBean.BizContentBean> redlist = new ArrayList<RedRECORDBean.BizContentBean>(); // 所有中奖信息

    private MyListview mMyListview;   //listview
    private HongBaoListviewAdapter mHongBaoListviewAdapter;  //adapter
    protected List<String> mList;
    private String ha[] = {"移动联通", "加固100元", "是的30元卡尔", "移动联通", "加固100元", "是的30元卡尔", "移动联通", "加固100元", "是的30元卡尔"};

    private Handler mHnaHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case YES:

                    ll_scene_one.setVisibility(View.GONE);
                    ll_scene_two.setVisibility(View.GONE);
                    ll_scene_three.setVisibility(View.GONE);

                    if (list != null && list.size() > 0) {
                        setData();
                    }

                    break;
                case REDYES:


                    if (mHongBaoListviewAdapter != null) {

                        mHongBaoListviewAdapter.notifyDataSetChanged();
                    } else {
                        mHongBaoListviewAdapter = new HongBaoListviewAdapter(getActivity(), redlist);
                        mMyListview.setAdapter(mHongBaoListviewAdapter);
                    }

                    break;
            }

        }
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.item_fram_hongbao, null, false);
        mMenu = (SlidingMenu) getActivity().findViewById(R.id.activity_main);
        gs = new Gson();

        initView(rootView);

        return rootView;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            getHttpData();
            getHttpListData();
        } else {
            bean = null;
            if (list != null)
                list.clear();
        }
    }


    //设置数据
    private void setData() {

        int i = 0;//状态

        int location = -1; //能抢红包的位置

        int NotStartingPosition = -1; //第一个未开始红包的位置

        //第一个红包
        ll_scene_one.setVisibility(View.VISIBLE);
        i = list.get(0).getDetailstatus();
        tv_one_static.setText(list.get(0).getBtime());
        tv_one_end.setText(list.get(0).getEtime());
        tv_one_state.setText(state[i]);
        ll_scene_one.setBackgroundResource(bg[i]);

        //设置进行中的能点击
        if (i == 1) {
            ll_scene_one.setClickable(true);
            location = 0;
        } else {
            ll_scene_one.setClickable(false);
            if (i == 0 && NotStartingPosition == -1) {
                //未开始状态
                NotStartingPosition = 0;
            }
        }


        if (list.size() < 2) {
            return;
        }

        //第二个红包
        ll_scene_two.setVisibility(View.VISIBLE);
        i = list.get(1).getDetailstatus();  //状态
        tv_two_static.setText(list.get(1).getBtime());
        tv_two_end.setText(list.get(1).getEtime());
        tv_two_state.setText(state[i]);
        ll_scene_two.setBackgroundResource(bg[i]);

        //设置进行中的能点击
        if (i == 1) {
            ll_scene_two.setClickable(true);
            location = 1;
        } else {
            ll_scene_two.setClickable(false);
            if (i == 0 && NotStartingPosition == -1) {
                //未开始状态
                NotStartingPosition = 1;
            }
        }


        if (list.size() < 3) {
            return;
        }

        //第三个红包
        ll_scene_three.setVisibility(View.VISIBLE);
        i = list.get(2).getDetailstatus();  //状态
        tv_three_static.setText(list.get(2).getBtime());
        tv_three_end.setText(list.get(2).getEtime());
        tv_three_state.setText(state[i]);
        ll_scene_three.setBackgroundResource(bg[i]);

        //设置进行中的能点击
        if (i == 1) {
            ll_scene_three.setClickable(true);
            location = 2;
        } else {
            ll_scene_three.setClickable(false);
            if (i == 0 && NotStartingPosition == -1) {
                //未开始状态
                NotStartingPosition = 2;
            }
        }

        if (location != -1) {
            //有红包可以抢  //同时间段内只能有一个可抢

            if (location == list.size() - 1) {

                //最后一个红包
                setCountdownvTime(1, location);
            } else if (location == 2) {

                //不是最后一个红包 并且在界面最后的位置(第三个位置)
                setCountdownvTime(2, 3);
            }

        } else {
            //没有红包可以抢
            if (NotStartingPosition != -1) {

                //有未开始状态
                setCountdownvTime(2, NotStartingPosition);

            } else {
                //全部结束
                setCountdownvTime(1, 0);
            }


        }

    }

    private void setCountdownvTime(int location, int position) {

        String CurrentTime = null;  //系统当前时间
        Long tian = null;   //距离下场红包时间

        switch (location) {
            case 1:         //只有一个红包  距离下场开奖时间 = 明天的开奖时间 - 当前时间

                CurrentTime = getTimeHHMM();    //当前系统时间
                tian = Long.valueOf(24 * 60 * 60 * 1000) + getRedTime(list.get(position).getBtime())
                        - getRedTime(CurrentTime);
                mTextCountdownvHongbaoFram.start(tian);

                break;
            case 2:     //界面最后一个红包距离下一个红包开奖时间   距离下场开奖时间 = 下场开奖时间 - 当前系统时间
                CurrentTime = getTimeHHMM();    //当前系统时间
                tian = getRedTime(list.get(position).getBtime()) - getRedTime(CurrentTime);
                mTextCountdownvHongbaoFram.start(tian);

                break;
        }
    }

    //获取红包数据
    private void getHttpData() {

        RequestParams params = new RequestParams(HttpApi.RED_LIST);
        params.addParameter("account", Constant.Config.account);
        params.addParameter("imei", Constant.Config.imei);
        params.addParameter("page", "1");
        params.addParameter("detailid", "");

        MainActivity.MancloseDialog();

        x.http().post(params, new Callback.CommonCallback<String>() {

            //请求成功
            @Override
            public void onSuccess(String result) {

                LogUtil.e("联网成功 == " + result.toString());

                bean = gs.fromJson(result, HongBaoFragmentBean.class);
                if (bean.getState().equals("success")) {

                    list = bean.getBiz_content();
                    Message mg = Message.obtain(mHnaHandler, YES);
                    mg.sendToTarget();

                } else {
                    ToastUtils.showShort(getActivity(), bean.getBiz_content().toString());
                }


            }

            //请求失败
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e("联网失败 == " + ex.getMessage());
                ToastUtils.showShort(getActivity(), "网络不给力！");
            }

            //主动调用取消请求的回调方法
            @Override
            public void onCancelled(CancelledException cex) {
                LogUtil.e("onCancelled == " + cex.getMessage());
            }

            // 不管成功或者失败最后都会回调该接口
            @Override
            public void onFinished() {
                LogUtil.e("onFinished == ");
                onLoad();
                MainActivity.MancloseDialog();
            }
        });


    }

    // 获得数据后一定要调用onLoad()方法，否则刷新会一直进行，根本停不下来
    private void onLoad() {
        ll_hongbao.onHeaderRefreshComplete();
        ll_hongbao.onFooterRefreshComplete();
    }


    private void initView(View rootView) {

        mBighMenu = (ImageView) rootView.findViewById(R.id.bigh_menu);
        mBighText = (TextView) rootView.findViewById(R.id.bigh_text);
        mTextHongbaoRecord = (TextView) rootView.findViewById(R.id.text_hongbao_record);
        ll_hongbao = (PullToRefreshView) rootView.findViewById(R.id.ll_hongbao);

        ll_scene_one = (LinearLayout) rootView.findViewById(R.id.ll_scene_one);
        ll_scene_two = (LinearLayout) rootView.findViewById(R.id.ll_scene_two);
        ll_scene_three = (LinearLayout) rootView.findViewById(R.id.ll_scene_three);

        tv_one_static = (TextView) rootView.findViewById(R.id.tv_one_static);
        tv_one_end = (TextView) rootView.findViewById(R.id.tv_one_end);
        tv_one_state = (TextView) rootView.findViewById(R.id.tv_one_state);

        tv_two_static = (TextView) rootView.findViewById(R.id.tv_two_static);
        tv_two_end = (TextView) rootView.findViewById(R.id.tv_two_end);
        tv_two_state = (TextView) rootView.findViewById(R.id.tv_two_state);

        tv_three_static = (TextView) rootView.findViewById(R.id.tv_three_static);
        tv_three_end = (TextView) rootView.findViewById(R.id.tv_three_end);
        tv_three_state = (TextView) rootView.findViewById(R.id.tv_three_state);

        mTextCountdownvHongbaoFram = (CountdownView) rootView.findViewById(R.id.text_countdownv_hongbao_fram);
        mMyListview = (MyListview) rootView.findViewById(R.id.mliseview_hongbao_fram);

        mMyListview.setClickable(false);

        OnClickListener = new MyOnClickListener();
        mBighMenu.setOnClickListener(OnClickListener);
        mTextHongbaoRecord.setOnClickListener(OnClickListener);
        ll_scene_one.setOnClickListener(OnClickListener);
        ll_scene_two.setOnClickListener(OnClickListener);
        ll_scene_three.setOnClickListener(OnClickListener);

        ll_hongbao.setLastUpdated(getTimes());//更新刷新时间
        ll_hongbao.setOnFooterRefreshListener(new MyOnFooterRefreshListener());//设置上拉加载监听
        ll_hongbao.setOnHeaderRefreshListener(new MyOnFooterRefreshListener());//设置下拉刷新监听

    }


    //下拉刷新 上拉加载监听
    private class MyOnFooterRefreshListener implements PullToRefreshView.OnFooterRefreshListener, PullToRefreshView.OnHeaderRefreshListener {


        //上拉加载
        @Override
        public void onFooterRefresh(PullToRefreshView view) {
            page++;
            getHttpListData();
        }

        //下拉刷新
        @Override
        public void onHeaderRefresh(PullToRefreshView view) {

            ll_hongbao.setLastUpdated(getTimes());//更新刷新时间
            page = 1;
            list.clear();
            redlist.clear();

            getHttpData();  //获取所用红包
            getHttpListData();//获取所有中奖用户信息

        }
    }


    //获取当前系统时间
    private CharSequence getTimes() {

        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd HH:mm:ss");//设置日期显示格式
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String time = formatter.format(curDate);// 将时间装换为设置好的格式
        return time;
    }

    //获取当前系统时间 HH:mm格式
    private String getTimeHHMM() {

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");//设置日期显示格式
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String time = formatter.format(curDate);// 将时间装换为设置好的格式
        return time;
    }

    private class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.bigh_menu: //侧滑
                    mMenu.showMenu();
                    break;

                case R.id.text_hongbao_record: //红包记录

                    Intent intent = new Intent(getContext(), HongBaoRember.class);
                    startActivity(intent);
                    break;

                case R.id.ll_scene_one: //第一场

                    packeid = list.get(0).getPackeid();
                    detailid = list.get(0).getDetailid();
                    getHttpGrab_RedEnvelope();
                    break;

                case R.id.ll_scene_two: //第二场

                    packeid = list.get(1).getPackeid();
                    detailid = list.get(1).getDetailid();
                    getHttpGrab_RedEnvelope();
                    break;

                case R.id.ll_scene_three: //第三场

                    packeid = list.get(2).getPackeid();
                    detailid = list.get(2).getDetailid();
                    getHttpGrab_RedEnvelope();
                    break;

            }
        }
    }


    //抢红包
    private void getHttpGrab_RedEnvelope() {

        if (packeid == -1 || detailid == -1) {

            ToastUtils.showShort(getActivity(), "订单异常，请重试！");
            return;
        }

        MainActivity.ManshowDialog(getActivity(), "正在提交数据抢红包...", true);
        RequestParams params = new RequestParams(HttpApi.GRAD_RED_ENVELOPE);
        params.addParameter("account", Constant.Config.account);
        params.addParameter("imei", Constant.Config.imei);
        params.addParameter("packeid", packeid);
        params.addParameter("detailid", detailid);

        x.http().post(params, new Callback.CommonCallback<String>() {

            //请求成功
            @Override
            public void onSuccess(String result) {

                LogUtil.e("联网成功 == " + result.toString());

                try {
                    JSONObject js = new JSONObject(result);

                    if (js.get("state").equals("success")) {

                        ToastUtils.showShort(getActivity(), js.get("biz_content").toString());
                    } else {

                        ToastUtils.showShort(getActivity(), js.get("biz_content").toString());
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            //请求失败
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e("联网失败 == " + ex.getMessage());
                ToastUtils.showShort(getActivity(), "网络不给力！");
            }

            //主动调用取消请求的回调方法
            @Override
            public void onCancelled(CancelledException cex) {
                LogUtil.e("onCancelled == " + cex.getMessage());
            }

            // 不管成功或者失败最后都会回调该接口
            @Override
            public void onFinished() {
                LogUtil.e("onFinished == ");
                MainActivity.MancloseDialog();
            }

        });
    }

    //获取最新红包记录
    private void getHttpListData() {

        if (page < 1) {
            page = 1;
            ToastUtils.showShort(getActivity(), "数据异常，请重试！");
            return;
        }

        RequestParams params = new RequestParams(HttpApi.RED_ENVELOPE_RECORD);
        params.addParameter("account", Constant.Config.account);
        params.addParameter("imei", Constant.Config.imei);
        params.addParameter("page", page);
        params.addParameter("ismyself", "0");

        x.http().post(params, new Callback.CommonCallback<String>() {

            //请求成功
            @Override
            public void onSuccess(String result) {

                LogUtil.e("联网成功 == " + result.toString());

                redbean = gs.fromJson(result, RedRECORDBean.class);

                if (redbean.getState().equals("success")) {

                    if (redlist == null) {
                        redlist = new ArrayList<RedRECORDBean.BizContentBean>();
                    }


                    if (redbean.getBiz_content() != null && redbean.getBiz_content().size() > 0) {

                        redlist.addAll(redbean.getBiz_content());
                        Message mg = Message.obtain(mHnaHandler, REDYES);
                        mg.sendToTarget();
                    }else{
                        ToastUtils.showShort(getActivity(),"没有更多数据!");
                    }


                }

            }

            //请求失败
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e("联网失败 == " + ex.getMessage());
                ToastUtils.showShort(getActivity(), "网络不给力！");
            }

            //主动调用取消请求的回调方法
            @Override
            public void onCancelled(CancelledException cex) {
                LogUtil.e("onCancelled == " + cex.getMessage());
            }

            // 不管成功或者失败最后都会回调该接口
            @Override
            public void onFinished() {
                LogUtil.e("onFinished == ");
                onLoad();
            }

        });
    }

    //获取当前时间long
    private Long getRedTime(String strl) {

        Date d = null;
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        try {
            d = sdf.parse(strl);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        long s = d.getTime();
        return s;
    }
}
