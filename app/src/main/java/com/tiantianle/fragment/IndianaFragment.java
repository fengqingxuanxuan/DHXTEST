package com.tiantianle.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.tiantianle.Bean.IndianaBuy;
import com.tiantianle.Bean.IndinanBean;
import com.tiantianle.R;
import com.tiantianle.activity.IndianaRember;
import com.tiantianle.adapter.IndianaAdapter;
import com.tiantianle.intface.MyInterface;
import com.tiantianle.utils.Constant;
import com.tiantianle.utils.HttpApi;
import com.tiantianle.utils.ToastUtils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * Created by wyj on 2017/1/22.
 * 夺宝系统
 */

public class IndianaFragment extends Fragment implements View.OnClickListener {
    protected View rootView;
    protected TextView mTextIndiana;
    protected ImageView mBighMenu;
    protected TextView mBighText;
    protected TextView mTextIndianaRecord;
    protected SlidingMenu mMenu;
    protected GridView mGridFramIndiana;
    private IndianaAdapter mIndianaAdapter;
    private int[] img = {R.mipmap.modou1,R.mipmap.modou2,R.mipmap.modou3,R.mipmap.modou4};
    private PopupWindow mPopupWindow;
    private Button  mButtonAdd;
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
    // private String imei;
    private List<IndinanBean.BizContentBean> mList;
    private  double totalfee=0;
    private String content="";
    private  Dialog dialog;
    private CharSequence temp;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.item_fram_indiana, null, false);
        mMenu = (SlidingMenu) getActivity().findViewById(R.id.activity_main);
        initView(rootView);
        HttpData();
      /*  Bundle arguments = getArguments();
        imei = arguments.getString("iemi");*/
        return rootView;
    }

    //添加商品
    private void HttpData() {
        RequestParams params = new RequestParams(HttpApi.INDIANAFRAGMENT);
        params.addParameter("account", Constant.Config.account);
        params.addParameter("page", 1+ "");
        params.addParameter("imei", Constant.Config.imei);
        params.addParameter("type", Constant.Config.type1);

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson=new Gson();
                IndinanBean riNiMa = gson.fromJson(result, IndinanBean.class);
                if(riNiMa.getState().equals("success")){
                    mList=riNiMa.getBiz_content();
                    mIndianaAdapter=new IndianaAdapter(mList,  new MyInterface() {
                        @Override
                        public void showPopuwindow(int changeid,String issuenum,String warecode,String warename,String speccode,String specname,double price,int type,String ordercode,int usernum,int playnum) {
                            goods_storage=usernum-playnum;
                            Log.e("这是剩余的",goods_storage+"");
                            showpopuWindow(changeid,issuenum,warecode,warename,speccode,specname,price,type,ordercode);
                        }
                    });
                    mGridFramIndiana.setAdapter(mIndianaAdapter);
                    mIndianaAdapter.notifyDataSetChanged();

                }else if(riNiMa.getState().equals("error")){
                    ToastUtils.showShort(getContext(),"缺少请求参数");
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
        private void showpopuWindow(final int changeid, final String issuenum, final String warecode, final String warename, final String speccode, final String specname, final double price, final int type, final String ordercode) {
             dialog=new Dialog(getContext(),R.style.CustomDialog);
            Window window = dialog.getWindow();
            window.setGravity(Gravity.BOTTOM);
            dialog.show();
            WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
            params.width =WindowManager.LayoutParams.MATCH_PARENT;
            //params.height=WindowManager.LayoutParams.WRAP_CONTENT;
           params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            window.setAttributes(params);
            View inflate1 = LayoutInflater.from(getContext()).inflate(R.layout.item_popu_indinan_buy, null);
           // mPopupWindow = new PopupWindow();
          //  View inflate1 = View.inflate(getContext(), R.layout.item_popu_indinan_buy, null);
          //  mPopupWindow.setContentView(inflate1);
          //  mPopupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);让软键盘在popuwindow前面
            mEditTextNum= (EditText) inflate1.findViewById(R.id.eid_popu_indinan_buy);
            mEditTextNum.setFocusable(true);
            mEditTextNum.setSelection(mEditTextNum.getText().length());
            mEditTextNum.setFocusableInTouchMode(true);
            mEditTextNum.requestFocus();
            mEditTextNum.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }
                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
                @Override
                public void afterTextChanged(Editable editable) {

                    editextIsEmpty(mEditTextNum);


                    }
            });

            mButtonAdd= (Button) inflate1.findViewById(R.id.btn_popu_indina_buy_add);
            mButtonAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    amount = Integer.parseInt( mEditTextNum.getText().toString());
                    if (amount <goods_storage) {
                        amount++;
                        mEditTextNum.setText(amount + "");
                        mEditTextNum.setSelection(mEditTextNum.getText().length());
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
                        mEditTextNum.setSelection(mEditTextNum.getText().length());
                    }
                }
            });

            mFive= (Button) inflate1.findViewById(R.id.btn_popu_indina_but_5);
            mFive.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    amount=5;
                    if( amount>goods_storage){
                        mEditTextNum.setText(goods_storage+"");
                        mEditTextNum.setSelection(mEditTextNum.getText().length());
                    }else{

                        mEditTextNum.setText(amount+"");
                        mEditTextNum.setSelection(mEditTextNum.getText().length());
                    }
                }
            });
            mTwenty= (Button) inflate1.findViewById(R.id.btn_popu_indina_but_20);
            mTwenty.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    amount=20;
                    if( amount>goods_storage){
                        mEditTextNum.setText(goods_storage+"");
                        mEditTextNum.setSelection(mEditTextNum.getText().length());
                    }else{

                        mEditTextNum.setText(amount+"");
                        mEditTextNum.setSelection(mEditTextNum.getText().length());
                    }
                }
            });
            mFifty= (Button) inflate1.findViewById(R.id.btn_popu_indina_but_50);
            mFifty.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    amount=50;
                    if( amount>goods_storage){
                        mEditTextNum.setText(goods_storage+"");
                        mEditTextNum.setSelection(mEditTextNum.getText().length());
                    }else{
                        mEditTextNum.setText(amount+"");
                        mEditTextNum.setSelection(mEditTextNum.getText().length());
                    }
                }
            });
            mHundred= (Button) inflate1.findViewById(R.id.btn_popu_indina_but_100);
            mHundred.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    amount=100;
                    if( amount>goods_storage){
                        mEditTextNum.setText(goods_storage+"");
                        mEditTextNum.setSelection(mEditTextNum.getText().length());
                    }else{
                        mEditTextNum.setText(amount+"");
                        mEditTextNum.setSelection(mEditTextNum.getText().length());
                    }
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
                            Gson  gson=new Gson();
                            IndianaBuy indianaBuy = gson.fromJson(result, IndianaBuy.class);
                            if(indianaBuy.getState().equals("success")){
                                ToastUtils.showShort(getContext(),indianaBuy.getBiz_content());
                                HttpData();

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

                    dialog.dismiss();

                }
            });
            mCloss= (ImageView) inflate1.findViewById(R.id.img_popu_indina_buy_closs);

            InputMethodManager inputMethodManager = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            //inputMethodManager.hideSoftInputFromWindow(mEditTextNum.getWindowToken(),0);
            inputMethodManager.toggleSoftInput(0, InputMethodManager.RESULT_SHOWN);
            mCloss.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /*mPopupWindow.dismiss();*/
                   dialog.dismiss();
                }
            });
            mEditTextNum.setSelection(mEditTextNum.length());
            /*mPopupWindow.setHeight(WindowManager.LayoutParams.MATCH_PARENT);
            mPopupWindow.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
            mPopupWindow.setTouchable(true);
           // mPopupWindow.setOutsideTouchable(true);
            mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));*/
            LinearLayout viewById = (LinearLayout) inflate1.findViewById(R.id.ll_diss);
            viewById.setBackgroundColor(Color.BLACK);
            viewById.setAlpha(0.6F);
            viewById.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //mPopupWindow.dismiss();
                    dialog.dismiss();
                }
            });
         //   mPopupWindow.showAtLocation(getActivity().findViewById(R.id.radiob_trend_main), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0);

            dialog.setContentView(inflate1);
        }

    //判断Editext输入的内容是否为空
    private boolean editextIsEmpty(EditText ed){
        boolean empty = TextUtils.isEmpty(ed.getText());
        if(empty){
            return  true;
        }
        String s=ed.getText().toString();
        amount=Integer.parseInt(ed.getText().toString());
        for (char c : s.toCharArray()) {
            if (c != 32) {

                return false;
            }
        }
        return true;



    }
    private void initView(View rootView) {

        mBighMenu = (ImageView) rootView.findViewById(R.id.bigh_menu);
        mBighMenu.setOnClickListener(IndianaFragment.this);
        mBighText = (TextView) rootView.findViewById(R.id.bigh_text);
        mTextIndianaRecord = (TextView) rootView.findViewById(R.id.text_indiana_record);
        mTextIndianaRecord.setOnClickListener(IndianaFragment.this);
        mGridFramIndiana = (GridView) rootView.findViewById(R.id.grid_fram_indiana);

    }
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.bigh_menu) {
            mMenu.showMenu();
        } else if (view.getId() == R.id.text_indiana_record) {
            Intent intent=new Intent(getContext(), IndianaRember.class);
            startActivity(intent);
        }
    }

}
