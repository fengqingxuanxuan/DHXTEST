package com.tiantianle.fragment;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.tiantianle.Bean.AllIndianaBean;
import com.tiantianle.Bean.IndianaBuy;
import com.tiantianle.R;
import com.tiantianle.adapter.AllAdapterIndinan;
import com.tiantianle.intface.MyInterface;
import com.tiantianle.intface.ShowTotalcount;
import com.tiantianle.utils.Constant;
import com.tiantianle.utils.HttpApi;
import com.tiantianle.utils.ToastUtils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by PengBo  on 2017/2/5.
 */

public class AllFragmentIndinanrember extends Fragment {
    protected View rootView;
   // protected ListView mListviewAllFramIndinanremb;
    protected PullToRefreshListView mPullRefreshListFramAllIndinan;
    private AllAdapterIndinan mAllAdapterIndinan;
    private List<AllIndianaBean.BizContentBean> mAllIndianaBeen=new ArrayList<>();
    private List<AllIndianaBean.BizContentBean> res;
    private int page=1;
    private int yvshu;
    private int shang;
    private PopupWindow mPopupWindow;
    private Button mButtonAdd;
    private Button  mButtonRed;
    private EditText mEditTextNum;
    private  Button mFive;
    private Button mTwenty;
    private Button mFifty;
    private Button  mHundred;
    private int amount = 1; //购买数量
    private int goods_storage; //商品库存
    private Button mQueren;
    private ImageView mCloss;
    private  double totalfee=0;
    private String content="";
    public static AllFragmentIndinanrember newInstance() {
        Bundle args = new Bundle();
        AllFragmentIndinanrember fragment = new AllFragmentIndinanrember();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.item_all_fram_indinanremb, null, false);
        initView(rootView);
        HttpDate(false);
        Pullto();
        return rootView;

    }
    private void Pullto(){
        mPullRefreshListFramAllIndinan.setMode(PullToRefreshBase.Mode.BOTH);
        mPullRefreshListFramAllIndinan.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                //开始下拉  我们做Http请求
                page=1;
                HttpDate(false);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                if(page<=shang){
                    page++;
                }
                   HttpDate(true);



            }
        });
           mAllAdapterIndinan = new AllAdapterIndinan(new ShowTotalcount() {
               @Override
               public void TotalcountNum(int totalcount) {
                   yvshu = totalcount % 15;
                   if (yvshu != 0) {
                       shang = totalcount / 15 + 1;

                   }
               }
           }, new MyInterface() {
               @Override
               public void showPopuwindow(int changeid, String issuenum, String warecode, String warename, String speccode, String specname, double price, int type, String ordercode, int usernum, int playnum) {
                   goods_storage=usernum-playnum;
                   showpopuWindow(changeid,issuenum,warecode,warename,speccode,specname,price,type,ordercode);
               }
           });
        mPullRefreshListFramAllIndinan.setAdapter(mAllAdapterIndinan);
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate    =   new Date(System.currentTimeMillis());
        String time=format.format(curDate);
        ILoadingLayout loadingLayoutProxy = mPullRefreshListFramAllIndinan.getLoadingLayoutProxy();
        loadingLayoutProxy.setRefreshingLabel("正在刷新..."); // 刷新时
        loadingLayoutProxy.setPullLabel("下拉刷新"); // 刚下拉时，显示的提示
        //loadingLayoutProxy.setLoadingDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
        loadingLayoutProxy.setLastUpdatedLabel(time); //一般是上次刷新的时间
        loadingLayoutProxy.setReleaseLabel("松手开始刷新");
    }

    private void showpopuWindow(final int changeid, final String issuenum, final String warecode, final String warename, final String speccode, final String specname, final double price, final int type, final String ordercode) {
        mPopupWindow = new PopupWindow();
        View inflate1 = View.inflate(getContext(), R.layout.item_popu_indinan_buy, null);
        mPopupWindow.setContentView(inflate1);
        mButtonAdd= (Button) inflate1.findViewById(R.id.btn_popu_indina_buy_add);
        mButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                amount = Integer.parseInt( mEditTextNum.getText().toString());
                if (amount <goods_storage) {

                    amount++;
                    mEditTextNum.setText(amount + "");
                }else if(amount>goods_storage){
                    ToastUtils.showShort(getContext(),"购买上限");
                }
            }
        });
        mButtonRed= (Button) inflate1.findViewById(R.id.btn_popu_indina_buy_red);
        mButtonRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount = Integer.parseInt( mEditTextNum.getText().toString());
                if (amount > 1) {
                    amount--;
                    mEditTextNum.setText(amount + "");
                }
            }
        });
        mEditTextNum= (EditText) inflate1.findViewById(R.id.eid_popu_indinan_buy);
        mFive= (Button) inflate1.findViewById(R.id.btn_popu_indina_but_5);
        mFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount=5;
                mEditTextNum.setText("5");
            }
        });
        mTwenty= (Button) inflate1.findViewById(R.id.btn_popu_indina_but_20);
        mTwenty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount=20;
                mEditTextNum.setText(amount+"");
            }
        });
        mFifty= (Button) inflate1.findViewById(R.id.btn_popu_indina_but_50);
        mFifty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount=50;
                mEditTextNum.setText(amount+"");
            }
        });
        mHundred= (Button) inflate1.findViewById(R.id.btn_popu_indina_but_100);
        mHundred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount=100;
                mEditTextNum.setText(amount+"");
            }
        });
        mQueren= (Button) inflate1.findViewById(R.id.btn_queren_popu_indina);
        mQueren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totalfee=amount*price;
                RequestParams entity = new RequestParams(HttpApi.BUY_INDIANAFRAGMENT);
                entity.addParameter("account", Constant.Config.account);
                entity.addParameter("imei", Constant.Config.imei);
                entity.addParameter("type", type+"");
                entity.addParameter("changeid",changeid);
                entity.addParameter("issuenum",issuenum);
                entity.addParameter("warecode",warecode+"");
                entity.addParameter("warename",warename);
                entity.addParameter("speccode",speccode);
                entity.addParameter("specname",specname);
                entity.addParameter("price",price);
                entity.addParameter("ordercode",ordercode);
                entity.addParameter("num",amount);
                entity.addParameter("content",content);
                entity.addParameter("totalfee",totalfee);
                x.http().post(entity, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Log.e("这是夺宝记录",result);
                        Gson  gson=new Gson();
                        IndianaBuy indianaBuy = gson.fromJson(result, IndianaBuy.class);
                        if(indianaBuy.getState().equals("success")){
                            ToastUtils.showShort(getContext(),indianaBuy.getBiz_content());

                        }else if (indianaBuy.getState().equals("error")){
                            ToastUtils.showShort(getContext(),indianaBuy.getBiz_content());
                        }
                    }
                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        Log.e("onError购买", ex.getMessage());
                    }
                    @Override
                    public void onCancelled(CancelledException cex) {
                    }
                    @Override
                    public void onFinished() {
                    }
                });
                amount=1;
                mEditTextNum.setText(amount+"");
                mPopupWindow.dismiss();
            }
        });
        mCloss= (ImageView) inflate1.findViewById(R.id.img_popu_indina_buy_closs);
        mCloss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.dismiss();
            }
        });
        mPopupWindow.setHeight(WindowManager.LayoutParams.MATCH_PARENT);
        mPopupWindow.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        LinearLayout viewById = (LinearLayout) inflate1.findViewById(R.id.ll_diss);
        viewById.setBackgroundColor(Color.BLACK);
        viewById.setAlpha(0.6F);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });
        mPopupWindow.showAtLocation(getActivity().findViewById(R.id.tab_indianaremb), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0);
    }

    private void HttpDate(final boolean isUP) {
        RequestParams entity = new RequestParams(HttpApi.REMB_ALL);
        entity.addParameter("account", Constant.Config.account);
        entity.addParameter("imei", Constant.Config.imei);
        entity.addParameter("type", Constant.Config.type1);
        entity.addParameter("status", -1);
        entity.addParameter("page", page);
        entity.addParameter("ismyself", 1);
        x.http().post(entity, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                AllIndianaBean allIndianaBean = gson.fromJson(result, AllIndianaBean.class);
                    if (allIndianaBean.getState().equals("success")) {
                        res=allIndianaBean.getBiz_content();
                        if(isUP){
                            mAllIndianaBeen.addAll(res);
                            mAllAdapterIndinan.setList(mAllIndianaBeen);
                            mAllAdapterIndinan.notifyDataSetChanged();
                        }else{
                            mAllAdapterIndinan.setList(res);
                            mAllAdapterIndinan.notifyDataSetChanged();
                        }
                        mPullRefreshListFramAllIndinan.onRefreshComplete();
                    } else if (allIndianaBean.getState().equals("error")) {
                    }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

                Log.e("onError", ex.getMessage());
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
        mPullRefreshListFramAllIndinan = (PullToRefreshListView) rootView.findViewById(R.id.pull_refresh_list_fram_allIndinan);

    }
}
