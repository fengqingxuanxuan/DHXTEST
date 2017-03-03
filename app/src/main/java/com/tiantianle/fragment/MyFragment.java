package com.tiantianle.fragment;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.allen.library.SuperTextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.google.gson.Gson;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.makeramen.roundedimageview.RoundedImageView;
import com.tiantianle.Bean.UserInformationBean;
import com.tiantianle.MainActivity;
import com.tiantianle.R;
import com.tiantianle.activity.LoginActivity;
import com.tiantianle.activity.MessageActivity;
import com.tiantianle.activity.ModifyInformationActivity;
import com.tiantianle.activity.PasswordActivity;
import com.tiantianle.utils.Constant;
import com.tiantianle.utils.HttpApi;
import com.tiantianle.utils.IntentUtils;
import com.tiantianle.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by wyj on 2017/1/22.
 * <p>
 * 我的fragment
 */

public class MyFragment extends Fragment implements View.OnClickListener {

    private Dialog progressDialog;

    private CallBackValue callBackValue; //获取用户信息成功后回调口

    protected View rootView;
    protected RoundedImageView mImgMyUserHeard; //头像

    protected TextView tv_name; //昵称
    protected TextView tv_signature; //签名
    protected Button mBtnBack;
    protected SuperTextView mSuptertextMagicBean;  //魔豆
    protected SuperTextView mSupertextMessage;
    protected SuperTextView mSupertextPassword;
    protected SuperTextView mSuptertextRecharges;
    protected SuperTextView mSuptertextAbout;
    private SlidingMenu menu;
    private String balance = "0.0";

    private UserInformationBean userinfobean; //用户信息
    private Gson gs = new Gson();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.item_fram_my, null, false);
        initView(rootView);
        FragmentActivity activity = getActivity();
        menu = (SlidingMenu) activity.findViewById(R.id.activity_main);
        menu.setSelected(false);
        callBackValue = (CallBackValue) getActivity();
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        System.out.println("item = " + Constant.Config.imei);
        System.out.println("item = " + TextUtils.isEmpty(Constant.Config.account));
        if (!TextUtils.isEmpty(Constant.Config.account)) {
            getHttpData(); //获取头像 签名 昵称
            getHttpHomeData();// 获取魔豆
        } else {
            Glide.with(getActivity())
                    .load(R.mipmap.tx)
                    .into(mImgMyUserHeard);
            tv_name.setText("点击登录");
            tv_signature.setText("");
            mSuptertextMagicBean.setRightString("0.00");
            callBackValue.SendMessageValue();
        }
    }


    private void initView(View rootView) {
        mImgMyUserHeard = (RoundedImageView) rootView.findViewById(R.id.img_my_userHeard);
        mImgMyUserHeard.setOnClickListener(MyFragment.this);
        tv_name = (TextView) rootView.findViewById(R.id.tv_name);
        tv_signature = (TextView) rootView.findViewById(R.id.tv_signature);
        mBtnBack = (Button) rootView.findViewById(R.id.btn_back);
        mBtnBack.setOnClickListener(MyFragment.this);
        mSuptertextMagicBean = (SuperTextView) rootView.findViewById(R.id.suptertext_magicBean);
        mSuptertextMagicBean.setOnClickListener(MyFragment.this);
        mSupertextMessage = (SuperTextView) rootView.findViewById(R.id.supertext_message);
        mSupertextMessage.setOnClickListener(MyFragment.this);
        mSupertextPassword = (SuperTextView) rootView.findViewById(R.id.supertext_password);
        mSupertextPassword.setOnClickListener(MyFragment.this);
        mSuptertextRecharges = (SuperTextView) rootView.findViewById(R.id.suptertext_recharges);
        mSuptertextRecharges.setOnClickListener(MyFragment.this);
        mSuptertextAbout = (SuperTextView) rootView.findViewById(R.id.suptertext_about);
        mSuptertextAbout.setOnClickListener(MyFragment.this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.img_my_userHeard: //头像
                if (TextUtils.isEmpty(Constant.Config.account)) {
                    IntentUtils.goTo(getActivity(), LoginActivity.class);
                } else {
                    IntentUtils.goTo(getActivity(), ModifyInformationActivity.class, balance);
                }

                break;

            case R.id.suptertext_magicBean: //魔豆

                break;
            case R.id.supertext_message: //消息通知

                if (TextUtils.isEmpty(Constant.Config.account)) {
                    IntentUtils.goTo(getActivity(), LoginActivity.class);
                } else {
                    IntentUtils.goTo(getActivity(), MessageActivity.class);
                }

                break;
            case R.id.supertext_password: //密码设置

                if (TextUtils.isEmpty(Constant.Config.account)) {
                    IntentUtils.goTo(getActivity(), LoginActivity.class);
                } else {
                    IntentUtils.goTo(getActivity(), PasswordActivity.class);
                }

                break;
            case R.id.suptertext_recharges: //魔豆充值

                break;
            case R.id.suptertext_about: //关于

                break;
            case R.id.btn_back: //退出

                Constant.Config.account = null;
                Constant.Config.password = null;
                Constant.Config.MagicBeans = null;
                Constant.Config.nickname = null;
                Constant.Config.Head = null;

                SharedPreferences.Editor editor = Constant.Config.sp.edit();
                editor.putString("account", null);
                editor.putString("password", null);
                editor.commit();

                IntentUtils.goTo(getActivity(), LoginActivity.class);

                break;

        }
    }

    // 获取并设置用户信息
    private void getHttpData() {

        if (TextUtils.isEmpty(Constant.Config.account) ||
                TextUtils.isEmpty(Constant.Config.imei)) {
            return;
        }

        RequestParams params = new RequestParams(HttpApi.USER_INFOTMATION);
        params.addParameter("account", Constant.Config.account);
        params.addParameter("imei", Constant.Config.imei);

        MainActivity.ManshowDialog(getActivity(), "努力加载中...", false);

        x.http().post(params, new Callback.CommonCallback<String>() {

            //请求成功
            @Override
            public void onSuccess(String result) {

                LogUtil.e("联网成功 == " + result.toString());


                userinfobean = gs.fromJson(result, UserInformationBean.class);

                if (userinfobean.getState().equals("success")) {

                    setData();

                } else {
                    ToastUtils.showShort(getActivity(), userinfobean.getBiz_content().toString());
                }

            }

            //请求失败
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e("联网失败 == " + ex.getMessage());
                ToastUtils.showShort(getActivity(), "获取用户信息失败!");
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
                callBackValue.SendMessageValue();
                MainActivity.MancloseDialog();
            }
        });

    }

    //设置用户信息
    private void setData() {

        if (userinfobean.getBiz_content().getAvatar() != null) {

            Glide.with(getActivity()).load(userinfobean.getBiz_content().getAvatar())
                    .asBitmap()
                    .centerCrop()
                    .placeholder(R.mipmap.tx)
                    .error(R.mipmap.tx)
                    .into(new BitmapImageViewTarget(mImgMyUserHeard) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.create(getActivity().getResources(), resource);
                            circularBitmapDrawable.setCircular(true);
                            mImgMyUserHeard.setImageDrawable(circularBitmapDrawable);
                        }
                    });

            Constant.Config.Head = userinfobean.getBiz_content().getAvatar();
            Constant.Config.nickname=userinfobean.getBiz_content().getNickname();
        }

        tv_name.setText(userinfobean.getBiz_content().getNickname());
        tv_signature.setText(userinfobean.getBiz_content().getSignname());
    }





    private void getHttpHomeData() {

        if (TextUtils.isEmpty(Constant.Config.account) ||
                TextUtils.isEmpty(Constant.Config.imei)) {
            return;
        }

        RequestParams params = new RequestParams(HttpApi.BALANCE);
        params.addParameter("account", Constant.Config.account);
        params.addParameter("imei", Constant.Config.imei);

         MainActivity.ManshowDialog(getActivity(),"努力加载中...",true);

        x.http().post(params, new Callback.CommonCallback<String>() {

            //请求成功
            @Override
            public void onSuccess(String result) {

                LogUtil.e("联网成功 == " + result.toString());

                try {
                    JSONObject js = new JSONObject(result);
                    if (js.get("state").equals("success")) {

                        JSONObject jsonObject = js.getJSONObject("biz_content");
                        balance = jsonObject.get("money").toString();
                        mSuptertextMagicBean.setRightString(balance);
                        Constant.Config.MagicBeans = balance;
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
                ToastUtils.showShort(getActivity(), "获取用户信息失败!");
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
                callBackValue.SendMessageValue();
                MainActivity.MancloseDialog();
            }
        });

    }

    //定义一个回调接口
    public interface CallBackValue{
        public void SendMessageValue();
    }
}
