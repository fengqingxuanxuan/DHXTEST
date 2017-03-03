package com.tiantianle.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.tiantianle.Bean.NewDongTaiBean;
import com.tiantianle.Bean.QueRenDuihuan;
import com.tiantianle.Bean.YidongBean;
import com.tiantianle.R;
import com.tiantianle.adapter.MianZhiAdapter;
import com.tiantianle.adapter.YiDongKaMiAdapter;
import com.tiantianle.intface.DuiHuan;
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

public class YiDongKaMi extends Activity implements View.OnClickListener {

    protected ImageView mImgYidongkamiBack;
    protected TextView mTextSuoXuModouYidongkami;
    protected EditText mEdittextYidongkami;
    protected Button mBtnQuerenYidongkami;
    protected LinearLayout mActivityYiDongKaMi;
    protected TextView mTextTatilnaleYidong;
    protected PullToRefreshListView mPullRefreshListDuihanNewdongtai;
    protected GridView mGridViewYidongkmi;
    private List<String> mList;
    private String userid[] = {"1105", "1103", "1103", "1103", "1103", "1103", "1103", "1103"};
    private YiDongKaMiAdapter mYiDongKaMiAdapter;
    private List<YidongBean.BizContentBean> mBizContentBeen = new ArrayList<>();
    private List<NewDongTaiBean.BizContentBean> mBeen = new ArrayList<>();
    private List<NewDongTaiBean.BizContentBean> res;
    private String warename;
    private String warecode;
    private int page = 1;
    private int yvshu;
    private int shang;
    private int  selectorPosition ;
    private MianZhiAdapter mMianZhiAdapter;
     private String warecodew;
    private String warenamew;
    private String speccode;
    private String specname;
    private  double price;
    private  int num=1;
    private String time;
    private String content;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_yi_dong_ka_mi);
        initView();
        Intent intent = getIntent();
        warename = intent.getStringExtra("warename");
        warecode = intent.getStringExtra("warecode");
        HttpDate();
        HttpData2(false);
        Getime();
        Pullto();
        mTextTatilnaleYidong.setText(warename);

        mGridViewYidongkmi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mMianZhiAdapter.changeState(i);
                selectorPosition=i;
                mTextSuoXuModouYidongkami.setText(mBizContentBeen.get(selectorPosition).getPrice()+"");
                warecodew=mBizContentBeen.get(selectorPosition).getWarecode();
                warenamew=mBizContentBeen.get(selectorPosition).getWarename();
                speccode=mBizContentBeen.get(selectorPosition).getSpeccode();
                specname=mBizContentBeen.get(selectorPosition).getSpecname();
                price=mBizContentBeen.get(selectorPosition).getPrice();

            }
        });
    }

    private void HttpDate() {
        RequestParams entity = new RequestParams(HttpApi.MINGXI);
        entity.addParameter("account", Constant.Config.account);
        entity.addParameter("imei", Constant.Config.imei);
        entity.addParameter("type", Constant.Config.tyoe0);
        entity.addParameter("warecode", warecode);
        x.http().post(entity, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("这是面值",result);
                Gson gson = new Gson();
                YidongBean yidongBean = gson.fromJson(result, YidongBean.class);
                if (yidongBean.getState().equals("success")) {
                    mBizContentBeen = yidongBean.getBiz_content();
                    mMianZhiAdapter=new MianZhiAdapter();
                    mMianZhiAdapter.setBizContentBeen(mBizContentBeen);
                    mGridViewYidongkmi.setAdapter(mMianZhiAdapter);
                    mMianZhiAdapter.notifyDataSetChanged();
                } else if (yidongBean.getState().equals("error")) {

                }
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

                Log.e("onError卡密", ex.getMessage());
            }
            @Override
            public void onCancelled(CancelledException cex) {
            }
            @Override
            public void onFinished() {
            }
        });

    }

    private void HttpData2(final boolean isUP) {
        RequestParams entity = new RequestParams(HttpApi.REMB_ALL);
        entity.addParameter("account", Constant.Config.account);
        entity.addParameter("imei", Constant.Config.imei);
        entity.addParameter("page", page);
        entity.addParameter("type", Constant.Config.tyoe0);
        entity.addParameter("status", -1);
        entity.addParameter("ismyself", 2);
        x.http().post(entity, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("这是兑换新动态", result);
                Gson gson = new Gson();
                NewDongTaiBean newDongTaiBean = gson.fromJson(result, NewDongTaiBean.class);
                if (newDongTaiBean.getState().equals("success")) {
                    res = newDongTaiBean.getBiz_content();
                    if (isUP) {
                        mBeen.addAll(res);
                        mYiDongKaMiAdapter.setList(mBeen);
                        mYiDongKaMiAdapter.notifyDataSetChanged();
                    } else {
                        mYiDongKaMiAdapter.setList(res);
                        mYiDongKaMiAdapter.notifyDataSetChanged();
                    }
                    mPullRefreshListDuihanNewdongtai.onRefreshComplete();
                } else if (newDongTaiBean.getState().equals("error")) {
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
            }
            @Override
            public void onCancelled(CancelledException cex) {
            }
            @Override
            public void onFinished() {
            }
        });
    }

    private void Getime(){
        SimpleDateFormat format=new SimpleDateFormat("yyyyMMddHHmmss");
        Date curDate    =   new Date(System.currentTimeMillis());
        time=format.format(curDate);
    }
    private void Pullto() {
        mPullRefreshListDuihanNewdongtai.setMode(PullToRefreshBase.Mode.BOTH);
        mPullRefreshListDuihanNewdongtai.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                //开始下拉  我们做Http请求
                page=1;
                HttpData2(false);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                if (page <= shang) {
                    page++;
                }
                HttpData2(true);
            }
        });
        mYiDongKaMiAdapter = new YiDongKaMiAdapter(new DuiHuan() {
            @Override
            public void DuiHuanRemb(int totalcounte) {
                yvshu = totalcounte % 15;
                if (yvshu != 0) {
                    shang = totalcounte / 15 + 1;
                }
            }
        });
       mPullRefreshListDuihanNewdongtai.setAdapter(mYiDongKaMiAdapter);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());
        String time = format.format(curDate);
        ILoadingLayout loadingLayoutProxy = mPullRefreshListDuihanNewdongtai.getLoadingLayoutProxy();
        loadingLayoutProxy.setRefreshingLabel("正在刷新..."); // 刷新时
        loadingLayoutProxy.setPullLabel("下拉刷新"); // 刚下拉时，显示的提示
        //loadingLayoutProxy.setLoadingDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
        loadingLayoutProxy.setLastUpdatedLabel(time); //一般是上次刷新的时间
        loadingLayoutProxy.setReleaseLabel("松手开始刷新");
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.img_yidongkami_back) {
            finish();
        } else if (view.getId() == R.id.btn_queren_yidongkami) {
            content=mEdittextYidongkami.getText().toString();
            RequestParams entity = new RequestParams(HttpApi.BUY_INDIANAFRAGMENT);
            entity.addParameter("account", Constant.Config.account);
            entity.addParameter("imei", Constant.Config.imei);
            entity.addParameter("type", Constant.Config.tyoe0);
            entity.addParameter("changeid",0);
            entity.addParameter("issuenum","");
            entity.addParameter("warename",warenamew);
            entity.addParameter("warecode",warecodew);
            entity.addParameter("speccode",speccode);
            entity.addParameter("specname",specname);
            entity.addParameter("price",price);
            entity.addParameter("num",num);
            entity.addParameter("content",content);
            entity.addParameter("totalfee",price);
            entity.addParameter("ordercode",time);
            x.http().post(entity, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {

                    Log.e("确认兑换",result);
                    Gson gson=new Gson();
                    QueRenDuihuan queRenDuihuan = gson.fromJson(result, QueRenDuihuan.class);
                    if(queRenDuihuan.getState().equals("success")){
                        ToastUtils.showShort(getApplicationContext(),queRenDuihuan.getBiz_content());
                    }else if(queRenDuihuan.getState().equals("error")){
                        ToastUtils.showShort(getApplicationContext(),queRenDuihuan.getBiz_content());
                    }

                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {

                }

                @Override
                public void onCancelled(CancelledException cex) {

                }

                @Override
                public void onFinished() {

                }
            });

        }
    }

    private void initView() {
        mImgYidongkamiBack = (ImageView) findViewById(R.id.img_yidongkami_back);
        mImgYidongkamiBack.setOnClickListener(YiDongKaMi.this);
        mTextSuoXuModouYidongkami = (TextView) findViewById(R.id.text_suoXu_modou_yidongkami);
        mEdittextYidongkami = (EditText) findViewById(R.id.edittext_yidongkami);
        mBtnQuerenYidongkami = (Button) findViewById(R.id.btn_queren_yidongkami);
        mBtnQuerenYidongkami.setOnClickListener(YiDongKaMi.this);
        mActivityYiDongKaMi = (LinearLayout) findViewById(R.id.activity_yi_dong_ka_mi);
        mTextTatilnaleYidong = (TextView) findViewById(R.id.text_tatilnale_yidong);
        mPullRefreshListDuihanNewdongtai = (PullToRefreshListView) findViewById(R.id.pull_refresh_list_duihan_newdongtai);
        mGridViewYidongkmi = (GridView) findViewById(R.id.gridView_yidongkmi);
    }
}
