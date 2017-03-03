package com.tiantianle.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
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

public class BeiJingFragment extends Fragment implements View.OnClickListener {
    protected View rootView;
    protected ImageView mBighMenu;
    protected TextView mBighText;
    protected SlidingMenu mMenu;

    protected MyListview mListviewFram;
    protected CheckBox mCbBeijingFragm;
    protected TextView mTextBejingFram;
    protected PullToRefreshScrollView mPullBeijingRefreshScrollview;
    protected MyListview mBeijingNolotteryListviewFram;
    private PopupWindow mPopupWindow;
    private int is[] = {333, 444, 555, 622, 513};
    private List<TheLotteryBean.BizContentBean> mList;
    private List<TheLotteryBean.BizContentBean> mBizContentBeen = new ArrayList<>();
    private TheLotteryAdapter mJiSuLiseFragmentAdapter;
    private LinearLayout mNewModle;
    private LinearLayout mModelManagement;
    private LinearLayout mMyPaLoss;
    private LinearLayout aotu;
    private int page = 1;
    private List<NoLotteryBean.BizContentBean> mBeanList;
    private NoLotteryAdapter mLotteryAdapter;
    private List<Map<String  ,String >>mMapList;
    private List<Map<String  ,String >>mMapsres;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.item_fram_beijing28, null, false);
        initView(rootView);
        HttpData1(false);
        HttpData();
        pullfresh();

     //   mMapList=new ArrayList<>();
        mMapsres=new ArrayList<>();
        FragmentActivity activity = getActivity();
        mMenu = (SlidingMenu) activity.findViewById(R.id.activity_main);

        mListviewFram.setDividerHeight(0);
        mBeijingNolotteryListviewFram.setDividerHeight(0);
        mCbBeijingFragm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    mCbBeijingFragm.setBackground(getResources().getDrawable(R.mipmap.up));
                    showpopuWindow();

                } else {
                    mCbBeijingFragm.setBackground(getResources().getDrawable(R.mipmap.down));
                    mPopupWindow.dismiss();
                }
            }
        });
        return rootView;
    }

    private void pullfresh() {
        mPullBeijingRefreshScrollview.setMode(PullToRefreshBase.Mode.BOTH);
        mPullBeijingRefreshScrollview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                page = 1;
                HttpData1(false);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {

                page++;
                HttpData1(true);
            }
        });

        /*mJiSuLiseFragmentAdapter=new TheLotteryAdapter(new MingDetailed() {
            @Override
            public void MDetailed(List<Map<String, String>> mMaps) {
                mMapList=mMaps;
                mListviewFram.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                       Map<String, String> map = mMapList.get(i);
                        Intent intent=new Intent(getContext(),Detailed.class);
                        intent.putExtra("expect",map.get("expect"));
                        intent.putExtra("type",map.get("type"));
                        intent.putExtra("frist",map.get("frist"));
                        intent.putExtra("second",map.get("second"));
                        intent.putExtra("three",map.get("three"));
                        intent.putExtra("resultnum",map.get("resultnum"));
                        intent.putExtra("time",map.get("time"));
                        startActivity(intent);
                    }
                });

            }
        });*/
        mJiSuLiseFragmentAdapter=new TheLotteryAdapter();
        mListviewFram.setAdapter(mJiSuLiseFragmentAdapter);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:hh:ss");
        Date curDate = new Date(System.currentTimeMillis());
        String time = simpleDateFormat.format(curDate);
        ILoadingLayout loadingLayoutProxy = mPullBeijingRefreshScrollview.getLoadingLayoutProxy();
        loadingLayoutProxy.setRefreshingLabel("正在刷新..."); // 刷新时
        loadingLayoutProxy.setPullLabel("下拉刷新"); // 刚下拉时，显示的提示
        //loadingLayoutProxy.setLoadingDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
        loadingLayoutProxy.setLastUpdatedLabel(time); //一般是上次刷新的时间
        loadingLayoutProxy.setReleaseLabel("松手开始刷新");


    }

    private void HttpData1(final boolean isUp) {
        //已开奖
        RequestParams entity = new RequestParams(HttpApi.JIANADA);
        entity.addParameter("account", Constant.Config.account);
        entity.addParameter("imei", Constant.Config.imei);
        entity.addParameter("type", Constant.Config.type10);
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
                    if (isUp) {
                        mBizContentBeen.addAll(mList);
                        mJiSuLiseFragmentAdapter.setList(mBizContentBeen);
                        mJiSuLiseFragmentAdapter.notifyDataSetChanged();
                        mListviewFram.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
                        mListviewFram.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
                    mPullBeijingRefreshScrollview.onRefreshComplete();//刷新完成


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
        RequestParams entity = new RequestParams(HttpApi.JIANADA);
        entity.addParameter("account", Constant.Config.account);
        entity.addParameter("imei", Constant.Config.imei);
        entity.addParameter("type", Constant.Config.type10);
        entity.addParameter("page", page);
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
                    mLotteryAdapter = new NoLotteryAdapter(new TimeDao() {
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
                    mLotteryAdapter.setBeanList(mBeanList);
                    mBeijingNolotteryListviewFram.setAdapter(mLotteryAdapter);
                    mLotteryAdapter.notifyDataSetChanged();


                } else if (noLotteryBean.getState().equals("error")) {

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
        mBighMenu.setOnClickListener(BeiJingFragment.this);
        mBighText = (TextView) rootView.findViewById(R.id.bigh_text);
        mListviewFram = (MyListview) rootView.findViewById(R.id.beijng_listview_fram);
        mCbBeijingFragm = (CheckBox) rootView.findViewById(R.id.cb_beijing_fragm);
        mCbBeijingFragm.setOnClickListener(BeiJingFragment.this);
        mTextBejingFram = (TextView) rootView.findViewById(R.id.text_bejing_fram);
        mPullBeijingRefreshScrollview = (PullToRefreshScrollView) rootView.findViewById(R.id.pull_beijing_refresh_scrollview);
        mBeijingNolotteryListviewFram = (MyListview) rootView.findViewById(R.id.beijing_nolottery_listview_fram);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.bigh_menu) {
            mCbBeijingFragm.setBackground(getResources().getDrawable(R.mipmap.down));
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
                mCbBeijingFragm.setBackground(getResources().getDrawable(R.mipmap.down));
            }
        });
        mPopupWindow.showAsDropDown(mCbBeijingFragm);
    }
}
