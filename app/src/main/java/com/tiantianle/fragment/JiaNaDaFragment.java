package com.tiantianle.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.tiantianle.Bean.NoLotteryBean;
import com.tiantianle.Bean.TheLotteryBean;
import com.tiantianle.R;
import com.tiantianle.TimeCountDownTextView;
import com.tiantianle.activity.AutoBetting;
import com.tiantianle.activity.Betting;
import com.tiantianle.activity.Detailed;
import com.tiantianle.adapter.NoLotteryAdapter;
import com.tiantianle.adapter.TheLotteryAdapter;
import com.tiantianle.intface.MingDetailed;
import com.tiantianle.intface.TimeDao;
import com.tiantianle.utils.Constant;
import com.tiantianle.utils.HttpApi;
import com.tiantianle.utils.IntentUtils;
import com.tiantianle.utils.ToastUtils;
import com.tiantianle.view.MyListview;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wyj on 2017/1/22.
 */

public class JiaNaDaFragment extends Fragment implements View.OnClickListener {
    protected View rootView;
    protected ImageView mBighMenu;
    protected TextView mBighText;
    protected MyListview mListview;
    protected CheckBox mCbJianaFragm;
    protected TextView mTextJianaFram;
    protected PullToRefreshScrollView mPulljianadaRefreshScrollview;
    protected MyListview mJianaNolotteryListviewFram;


    private SlidingMenu mMenu;
    private PopupWindow mPopupWindow;
    private List<TheLotteryBean.BizContentBean> mBizContentBeen = new ArrayList<>();
    private List<TheLotteryBean.BizContentBean> mList;
    private TheLotteryAdapter mJiSuLiseFragmentAdapter;
    private LinearLayout mNewModle;
    private LinearLayout mModelManagement;
    private LinearLayout mMyPaLoss;
    private LinearLayout aotu;

    private NoLotteryAdapter mNoLotteryAdapter;
    private int page = 1;
    private List<NoLotteryBean.BizContentBean> mBeanList;
    private List<Map<String  ,String >>mMapList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.item_fram_jianada, null, false);
        initView(rootView);
        HttpData();
        HttpData1(false);
        popufrsh();
        mMapList=new ArrayList<>();
        FragmentActivity activity = getActivity();
        mMenu = (SlidingMenu) activity.findViewById(R.id.activity_main);
        mListview.setDividerHeight(0);
        mJianaNolotteryListviewFram.setDividerHeight(0);
        mCbJianaFragm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    mCbJianaFragm.setBackground(getResources().getDrawable(R.mipmap.up));
                    showpopuWindow();
                } else {
                    mCbJianaFragm.setBackground(getResources().getDrawable(R.mipmap.down));
                    mPopupWindow.dismiss();
                }
            }
        });
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String time = mList.get(i).getAddtime();
                String times[]=time.split(" ");//年月日   时分秒
                String timess[]=times[1].split(":");//时分
                String timesss=timess[0]+":"+timess[1];
                Intent intent=new Intent(getContext(),Detailed.class);
                intent.putExtra("expect", mList.get(i).getExpect());
                intent.putExtra("type",mList.get(i).getType()+"");
                intent.putExtra("frist", mList.get(i).getFirst());
                intent.putExtra("second", mList.get(i).getSecond());
                intent.putExtra("three",mList.get(i).getThree());
                intent.putExtra("resultnum",mList.get(i).getResultnum());
                intent.putExtra("time",timesss);
                startActivity(intent);
            }
        });


        return rootView;
    }


    private void popufrsh() {

        mPulljianadaRefreshScrollview.setMode(PullToRefreshBase.Mode.BOTH);
        mPulljianadaRefreshScrollview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {

                page = 1;
                HttpData1(false);
                mJiSuLiseFragmentAdapter.notifyDataSetChanged();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {

                page++;
                HttpData1(true);
                mJiSuLiseFragmentAdapter.notifyDataSetChanged();
            }
        });
       /* mJiSuLiseFragmentAdapter=new TheLotteryAdapter(new MingDetailed() {
            @Override
            public void MDetailed(List<Map<String, String>> mMaps) {

                mMapList=mMaps;
            }
        });*/
        mJiSuLiseFragmentAdapter=new TheLotteryAdapter();
        mListview.setAdapter(mJiSuLiseFragmentAdapter);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());
        String time = format.format(curDate);
        ILoadingLayout loadingLayoutProxy = mPulljianadaRefreshScrollview.getLoadingLayoutProxy();
        loadingLayoutProxy.setRefreshingLabel("正在刷新..."); // 刷新时
        loadingLayoutProxy.setPullLabel("下拉刷新"); // 刚下拉时，显示的提示
        //loadingLayoutProxy.setLoadingDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
        loadingLayoutProxy.setLastUpdatedLabel(time); //一般是上次刷新的时间
        loadingLayoutProxy.setReleaseLabel("松手开始刷新");

    }

    private void HttpData1(final boolean isUP) {
        //已开奖
        RequestParams entity = new RequestParams(HttpApi.JIANADA);
        entity.addParameter("account", Constant.Config.account);
        entity.addParameter("imei", Constant.Config.imei);
        entity.addParameter("type", Constant.Config.type11);
        entity.addParameter("page", page);
        entity.addParameter("pagesize", 15);
        entity.addParameter("status", Constant.Config.status2);
        x.http().post(entity, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("这是已开奖", result);
                Gson gson = new Gson();
                TheLotteryBean theLotteryBean = gson.fromJson(result, TheLotteryBean.class);
                if (theLotteryBean.getState().equals("success")) {
                    mList = theLotteryBean.getBiz_content();
                    if (isUP) {
                        mBizContentBeen.addAll(mList);
                        mJiSuLiseFragmentAdapter.setList(mBizContentBeen);
                        mJiSuLiseFragmentAdapter.notifyDataSetChanged();
                        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                String time = mBizContentBeen.get(i).getAddtime();
                                String times[]=time.split(" ");//年月日   时分秒
                                String timess[]=times[1].split(":");//时分
                                String timesss=timess[0]+":"+timess[1];
                                Intent intent=new Intent(getContext(),Detailed.class);
                                intent.putExtra("expect", mBizContentBeen.get(i).getExpect());
                                intent.putExtra("type",mBizContentBeen.get(i).getType()+"");
                                intent.putExtra("frist", mBizContentBeen.get(i).getFirst());
                                intent.putExtra("second", mBizContentBeen.get(i).getSecond());
                                intent.putExtra("three",mBizContentBeen.get(i).getThree());
                                intent.putExtra("resultnum",mBizContentBeen.get(i).getResultnum());
                                intent.putExtra("time",timesss);
                                startActivity(intent);
                            }
                        });
                    } else {

                        mJiSuLiseFragmentAdapter.setList(mList);
                        mJiSuLiseFragmentAdapter.notifyDataSetChanged();
                        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                String time = mList.get(i).getAddtime();
                                String times[]=time.split(" ");//年月日   时分秒
                                String timess[]=times[1].split(":");//时分
                                String timesss=timess[0]+":"+timess[1];
                                Intent intent=new Intent(getContext(),Detailed.class);
                                intent.putExtra("expect", mList.get(i).getExpect());
                                intent.putExtra("type",mList.get(i).getType()+"");
                                intent.putExtra("frist", mList.get(i).getFirst());
                                intent.putExtra("second", mList.get(i).getSecond());
                                intent.putExtra("three",mList.get(i).getThree());
                                intent.putExtra("resultnum",mList.get(i).getResultnum());
                                intent.putExtra("time",timesss);
                                startActivity(intent);
                            }
                        });
                    }
                    mPulljianadaRefreshScrollview.onRefreshComplete();


                } else if (theLotteryBean.getState().equals("error")) {

                    ToastUtils.showShort(getContext(), "暂无记录");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("onError加拿大28已开奖", ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void HttpData() {
        //未开奖
        RequestParams entity = new RequestParams(HttpApi.JIANADA);
        entity.addParameter("account", Constant.Config.account);
        entity.addParameter("imei", Constant.Config.imei);
        entity.addParameter("type", Constant.Config.type11);
        entity.addParameter("page", 1);
        entity.addParameter("pagesize", 5);
        entity.addParameter("status", "0,1");
        x.http().post(entity, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("未开奖的数据", result);
                Gson gson = new Gson();
                NoLotteryBean noLotteryBean = gson.fromJson(result, NoLotteryBean.class);
                if (noLotteryBean.getState().equals("success")) {
                    mBeanList = noLotteryBean.getBiz_content();
                    mNoLotteryAdapter = new NoLotteryAdapter(new TimeDao() {
                        @Override
                        public void DaoTime(long time, final TimeCountDownTextView textView, final Button btn) {
                            textView.setCountDownTimes(time);
                            textView.start();
                            textView.setOnCountDownFinishListener(new TimeCountDownTextView.onCountDownFinishListener() {
                                @Override
                                public void onFinish() {
                                    textView.setText("刷新");
                                    textView.setBackgroundResource(R.mipmap.backyellow);
                                    textView.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            HttpData();
                                        }
                                    });
                                    btn.setText("开奖中");
                                    btn.setClickable(false);
                                    btn.setBackgroundResource(R.drawable.btn_kaijingzhong);
                                }
                            });
                            btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    IntentUtils.goTo(getContext(), Betting.class);
                                }
                            });
                        }
                    });
                    mNoLotteryAdapter.setBeanList(mBeanList);
                    mJianaNolotteryListviewFram.setAdapter(mNoLotteryAdapter);
                    mNoLotteryAdapter.notifyDataSetChanged();
                } else if (noLotteryBean.getState().equals("error")) {
                    ToastUtils.showShort(getContext(), "暂无记录");
                }
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("onError加拿大28未开奖", ex.getMessage());
            }
            @Override
            public void onCancelled(CancelledException cex) {
            }
            @Override
            public void onFinished() {
            }
        });
    }
    private void initView(View rootView) {
        mBighMenu = (ImageView) rootView.findViewById(R.id.bigh_menu);
        mBighMenu.setOnClickListener(JiaNaDaFragment.this);
        mBighText = (TextView) rootView.findViewById(R.id.bigh_text);
        mJianaNolotteryListviewFram = (MyListview) rootView.findViewById(R.id.jiana_nolottery_listview_fram);
        mListview = (MyListview) rootView.findViewById(R.id.jiana_listview_fram);
        mCbJianaFragm = (CheckBox) rootView.findViewById(R.id.cb_jiana_fragm);
        mCbJianaFragm.setOnClickListener(JiaNaDaFragment.this);
        mTextJianaFram = (TextView) rootView.findViewById(R.id.text_jiana_fram);
        mPulljianadaRefreshScrollview = (PullToRefreshScrollView) rootView.findViewById(R.id.pull_jianada_refresh_scrollview);
    }
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.bigh_menu) {
            mCbJianaFragm.setBackground(getResources().getDrawable(R.mipmap.down));
            mMenu.showMenu();
        }
    }
    private void showpopuWindow() {
        mPopupWindow = new PopupWindow();
        View inflate1 = View.inflate(getContext(), R.layout.item_popuwindow, null);
        mPopupWindow.setContentView(inflate1);
        aotu = (LinearLayout) inflate1.findViewById(R.id.ll_auto);
        aotu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUtils.goTo(getContext(), AutoBetting.class);
            }
        });
        mNewModle = (LinearLayout) inflate1.findViewById(R.id.ll_newPattern);
        mNewModle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "新建模式", Toast.LENGTH_SHORT).show();
            }
        });
        mModelManagement = (LinearLayout) inflate1.findViewById(R.id.ll_modelManagement);
        mModelManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "模式管理", Toast.LENGTH_SHORT).show();
            }
        });
        mMyPaLoss = (LinearLayout) inflate1.findViewById(R.id.ll_myPaLoss);
        mMyPaLoss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "我的盈亏", Toast.LENGTH_SHORT).show();
            }
        });
        mPopupWindow.setHeight(WindowManager.LayoutParams.MATCH_PARENT);
        mPopupWindow.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        LinearLayout viewById = (LinearLayout) inflate1.findViewById(R.id.ll_dimiss);
        viewById.setBackgroundColor(Color.BLACK);
        viewById.setAlpha(0.6F);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
                mCbJianaFragm.setBackground(getResources().getDrawable(R.mipmap.down));

            }
        });
        mPopupWindow.showAsDropDown(mCbJianaFragm);
    }


}
