package com.tiantianle.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.allen.library.SuperTextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.tiantianle.BaseActivity;
import com.tiantianle.R;
import com.tiantianle.utils.Constant;
import com.tiantianle.utils.FileUtil;
import com.tiantianle.utils.HttpApi;
import com.tiantianle.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;

/**
 * Created by Administrator on 2017/2/16.
 * <p>
 * 修改个人信息
 */
public class ModifyInformationActivity extends BaseActivity {

    private static final String IMAGE_FILE_NAME = "ivhead.jpg";
    private static final int REQUESTCODE_PICK = 0;
    private static final int REQUESTCODE_TAKE = 1;
    private static final int REQUESTCODE_CUTTING = 2;

    private ImageView img_my_userHeard; //头像
    private SuperTextView suptertext_magicBean;  //魔豆
    private EditText et_nickname;// 昵称
    private EditText et_signature;// 签名
    private EditText et_trade_password;// 交易密码
    private Button bt_confirm_change; // 确认修改

    private LinearLayout ll_trade_password; // 交易密码布局
    private View view; //底部线条

    private AlertDialog dialog;
    private TextView tv_cancle;  //取消
    private TextView tv_photo;  //拍照
    private TextView tv_photograph_Album;  //相冊
    private String urlpath; //用系统裁剪后的地址

    private String balance = "0.0";// 魔豆数量


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifyinformation);

        balance = getIntent().getStringExtra("stringContent");

        IntenView();
        setData();
    }

    private void setData() {

        img_my_userHeard.setOnClickListener(new MyOnClickListener());
        bt_confirm_change.setOnClickListener(new MyOnClickListener());

        if( !TextUtils.isEmpty(Constant.Config.Head)){

            Glide.with(ModifyInformationActivity.this).load(Constant.Config.Head)
                    .asBitmap()
                    .centerCrop()
                    .placeholder(R.mipmap.tx)
                    .error(R.mipmap.tx)
                    .into(new BitmapImageViewTarget(img_my_userHeard) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(ModifyInformationActivity.this.getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    img_my_userHeard.setImageDrawable(circularBitmapDrawable);
                }
            });
        }

        suptertext_magicBean.setRightString(balance);

    }

    private void IntenView() {

        img_my_userHeard = (ImageView) findViewById(R.id.img_my_userHeard);
        suptertext_magicBean = (SuperTextView) findViewById(R.id.suptertext_magicBean);
        et_nickname = (EditText) findViewById(R.id.et_nickname);
        et_signature = (EditText) findViewById(R.id.et_signature);
        et_trade_password = (EditText) findViewById(R.id.et_trade_password);
        ll_trade_password = (LinearLayout) findViewById(R.id.ll_trade_password);
        view = findViewById(R.id.view);
        bt_confirm_change = (Button) findViewById(R.id.bt_confirm_change);

    }

    private class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.img_my_userHeard: //头像

                    showModifyDialog();  //显示拍照相册dialog
                    break;

                case R.id.bt_confirm_change: //确认修改

                    setHttpUserinfo();
                    break;

                case R.id.tv_photo: //拍照

                    Intent takeIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    takeIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                            Uri.fromFile(new File(Environment.getExternalStorageDirectory(), IMAGE_FILE_NAME)));
                    startActivityForResult(takeIntent, REQUESTCODE_TAKE);
                    dialog.dismiss();

                    break;
                case R.id.tv_photograph_Album: //相冊

                    Intent pickIntent = new Intent(Intent.ACTION_PICK, null);
                    pickIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                    startActivityForResult(pickIntent, REQUESTCODE_PICK);
                    dialog.dismiss();


                    break;
                case R.id.tv_cancle: //取消

                    dialog.dismiss();
                    break;
            }
        }
    }

    //更新昵称 签名
    private void setHttpUserinfo() {

        String nickname = et_nickname.getText().toString();
        String signature = et_signature.getText().toString();

        RequestParams params = new RequestParams(HttpApi.UPDATE_INFORMATION);
        params.addParameter("account", Constant.Config.account);
        params.addParameter("nickname", nickname);
        params.addParameter("signname", signature);
        params.addParameter("imei", Constant.Config.imei);


        showDialog(ModifyInformationActivity.this,"正在努力加载中....",true);
        x.http().post(params, new Callback.CommonCallback<String>() {

            //请求成功
            @Override
            public void onSuccess(String result) {

                LogUtil.e("联网成功 == " + result.toString());

                try {
                    JSONObject jsonObject = new JSONObject(result);
                    if (jsonObject.get("state").toString().equals("success")) {

                        ToastUtils.showShort(ModifyInformationActivity.this, "修改成功!");
                        finish();

                    } else {
                        ToastUtils.showShort(ModifyInformationActivity.this, jsonObject.get("biz_content").toString());
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    LogUtil.e("解析异常！");
                }

            }

            //请求失败
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e("联网失败 == " + ex.getMessage());
                ToastUtils.showShort(ModifyInformationActivity.this, "上传失败！");
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
                closeDialog();
            }
        });

    }



    private void showModifyDialog() {

        WindowManager wm = this.getWindowManager();

        int width = wm.getDefaultDisplay().getWidth();

        dialog = new AlertDialog.Builder(this).create();
        dialog.show();
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = width - 60;
        dialog.getWindow().setAttributes(params);

        View contentView = LayoutInflater.from(this).inflate(R.layout.dialog_modifytx, null);
        tv_cancle = (TextView) contentView.findViewById(R.id.tv_cancle);// 取消
        tv_photo = (TextView) contentView.findViewById(R.id.tv_photo);// 拍照
        tv_photograph_Album = (TextView) contentView.findViewById(R.id.tv_photograph_Album);// 相册

        tv_cancle.setOnClickListener(new MyOnClickListener());
        tv_photo.setOnClickListener(new MyOnClickListener());
        tv_photograph_Album.setOnClickListener(new MyOnClickListener());

        dialog.setContentView(contentView);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUESTCODE_PICK:// 调用系统相册后返回
                try {
                    startPhotoZoom(data.getData());
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
                break;
            case REQUESTCODE_TAKE:// 相机调用后返回
                File temp = new File(Environment.getExternalStorageDirectory() + "/" + IMAGE_FILE_NAME);
                startPhotoZoom(Uri.fromFile(temp));
                break;
            case REQUESTCODE_CUTTING:// 裁剪后调用
                if (data != null) {
                    setPicToView(data);
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    // 头像上传服务器
    private void setPicToView(Intent picdata) {

        Bundle extras = picdata.getExtras();
        Bitmap photo = extras.getParcelable("data");
        Drawable drawable = new BitmapDrawable(null, photo);
        //将Drawable图片文件转为字符串
        urlpath = FileUtil.saveFile(ModifyInformationActivity.this, "temphead.jpg", photo);
        img_my_userHeard.setImageDrawable(drawable);
        setHttpData();
    }

    // 图片裁剪
    private void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, REQUESTCODE_CUTTING);
    }

    private void setHttpData() {

        RequestParams params = new RequestParams(HttpApi.UPDATE_AVATAR);
        params.addParameter("account", Constant.Config.account);
        params.addParameter("imei", Constant.Config.imei);
        params.addParameter("img" + Constant.Config.imei, new File(urlpath));

        showDialog(ModifyInformationActivity.this,"正在努力上传中....",true);
        x.http().post(params, new Callback.CommonCallback<String>() {

            //请求成功
            @Override
            public void onSuccess(String result) {

                LogUtil.e("联网成功 == " + result.toString());

                try {
                    JSONObject jsonObject = new JSONObject(result);
                    if (jsonObject.get("state").toString().equals("success")) {

                        ToastUtils.showShort(ModifyInformationActivity.this, "头像修改成功!");

                    } else {
                        ToastUtils.showShort(ModifyInformationActivity.this, jsonObject.get("biz_content").toString());
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    LogUtil.e("解析异常！");
                }

            }

            //请求失败
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e("联网失败 == " + ex.getMessage());
                ToastUtils.showShort(ModifyInformationActivity.this, "上传失败！");
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
                closeDialog();
            }
        });

    }
}
