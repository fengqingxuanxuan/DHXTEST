package com.tiantianle.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.tiantianle.BaseActivity;
import com.tiantianle.Bean.ModelManagementBean;
import com.tiantianle.R;
import com.tiantianle.adapter.ModeiManagementAdapter;
import com.tiantianle.utils.Constant;
import com.tiantianle.utils.HttpApi;
import com.tiantianle.utils.ToastUtils;
import com.tiantianle.view.PullToRefreshView;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/2/22.
 * <p>
 * 模式管理
 */
public class ModelManagementActivity extends BaseActivity implements ModeiManagementAdapter.Callback {

    private static final int YES = 1;

    private ImageView img_back_title;   //返回
    private TextView tv_title_title;    //title

    private Button bt_model_management; //新建模式
    private PullToRefreshView ll_pull; //刷新框架
    private ListView lv_mode;


    private ModelManagementBean bean;
    private ModeiManagementAdapter adapter;
    private List<ModelManagementBean.BizContentBean> list = new ArrayList<ModelManagementBean.BizContentBean>();

    private Boolean Tag = true;    //标记显示 删除(false)dialog 还是改名(true)dialog
    private AlertDialog dialog; //删除，改名 dialog
    private TextView tv_dialog_title; //dialog title
    private EditText et_trends_query;   //修改内容
    private TextView tv_delete_name;    //删除内容
    private TextView tv_cancel;         //取消
    private TextView tv_determine;      //确定
    private String mode_name; //确认修改的模式名

    private int page = 1; //页数
    private int type = 0; //模式 彩票类型 （10：北京28  11：加拿大28）

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case YES:

                    setData();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_managemnt);
        showDialog(ModelManagementActivity.this, "努力加载中...", false);

        type = getIntent().getIntExtra("mode",10);
        InitView();
        getHttpData();

    }

    private void setData() {
        if (list == null) {
            list = new ArrayList<ModelManagementBean.BizContentBean>();
        }

        list.addAll(bean.getBiz_content());

        if (adapter != null) {
            adapter.notifyDataSetChanged();
        } else {
            adapter = new ModeiManagementAdapter(ModelManagementActivity.this, list);
            lv_mode.setAdapter(adapter);
        }
    }


    private void InitView() {

        img_back_title = (ImageView) findViewById(R.id.img_back_title);
        tv_title_title = (TextView) findViewById(R.id.tv_title_title);
        bt_model_management = (Button) findViewById(R.id.bt_model_management);
        ll_pull = (PullToRefreshView) findViewById(R.id.ll_pull);
        lv_mode = (ListView) findViewById(R.id.lv_mode);

        tv_title_title.setText("模式管理");

        img_back_title.setOnClickListener(new MyOnClickListener());
        bt_model_management.setOnClickListener(new MyOnClickListener());

        ll_pull.setLastUpdated(getTimes());//更新刷新时间
        ll_pull.setOnFooterRefreshListener(new MyOnFooterRefreshListener());//设置上拉加载监听
        ll_pull.setOnHeaderRefreshListener(new MyOnFooterRefreshListener());//设置下拉刷新监听
    }


    //改名回调
    @Override
    public void ModifyClick(View v) {

        Tag = true;
        showModelDiaLog((int) v.getTag());
    }


    //删除回调
    @Override
    public void DeleteClick(View v) {

        Tag = false;
        showModelDiaLog((int) v.getTag());
    }
    private class MyOnFooterRefreshListener implements PullToRefreshView.OnFooterRefreshListener
            ,PullToRefreshView.OnHeaderRefreshListener{

        //上拉加载
        @Override
        public void onFooterRefresh(PullToRefreshView view) {

            page++;
            getHttpData();
        }

        //下拉刷新
        @Override
        public void onHeaderRefresh(PullToRefreshView view) {

            page = 1;
            list.clear();
            getHttpData();
        }
    }

    private class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.img_back_title:

                    finish();
                    break;
                case R.id.bt_model_management:  //新建模式


                    break;
            }
        }
    }

    //获取当前系统时间
    private CharSequence getTimes() {

        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd HH:mm:ss");//设置日期显示格式
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String time = formatter.format(curDate);// 将时间装换为设置好的格式
        return time;
    }

    private void showModelDiaLog(final int position) {

        WindowManager wm = this.getWindowManager();
        int width = wm.getDefaultDisplay().getWidth();
        if (dialog == null) {
            dialog = new AlertDialog.Builder(this).create();
        }
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        dialog.show();

        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = width;
        params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;// 就是这个属性导致不能获取焦点,默认的是FLAG_NOT_FOCUSABLE,故名思义不能获取输入
        dialog.getWindow().setAttributes(params);
        View contentView = LayoutInflater.from(this).inflate(R.layout.dialog_modelmangement, null);

        dialog.setContentView(contentView);

        tv_dialog_title = (TextView) contentView.findViewById(R.id.tv_dialog_title);
        et_trends_query = (EditText) contentView.findViewById(R.id.et_trends_query);
        tv_delete_name = (TextView) contentView.findViewById(R.id.tv_delete_name);
        tv_cancel = (TextView) contentView.findViewById(R.id.tv_cancel);
        tv_determine = (TextView) contentView.findViewById(R.id.tv_determine);

        if (Tag) {    //改名模式

            tv_dialog_title.setText(getString(R.string.Modify_Mode_Name));
            et_trends_query.setVisibility(View.VISIBLE);
            et_trends_query.setText(list.get(position).getName());
            tv_delete_name.setVisibility(View.GONE);
        } else {     //删除模式

            tv_dialog_title.setText(getString(R.string.Delete_Mode));
            et_trends_query.setVisibility(View.GONE);
            tv_delete_name.setVisibility(View.VISIBLE);
            tv_delete_name.setText(getString(R.string.Confirm_Delete_Mode)
                    + "\"" + list.get(position).getName() + "\"");
        }

        tv_cancel.setOnClickListener(new View.OnClickListener() {   //取消
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                dialog = null;
            }
        });
        tv_determine.setOnClickListener(new View.OnClickListener() {    //确定
            @Override
            public void onClick(View v) {

                if (Tag) {  //修改
                    mode_name = et_trends_query.getText().toString();
                    setHttpModify(position, mode_name);
                } else {    //删除
                    setHttpDelete(position);
                }

                dialog.dismiss();
                dialog = null;
            }
        });
    }

    //获取模式记录
    private void getHttpData() {

        if(type == 0){
            ToastUtils.show(ModelManagementActivity.this,"数据出现错误,请重新进入！",3000);
            return;
        }
        RequestParams params = new RequestParams(HttpApi.MODEL_RECORDS);
        params.addParameter("account", Constant.Config.account);
        params.addParameter("imei", Constant.Config.imei);
        params.addParameter("type", type);
        params.addParameter("page", page);


        x.http().post(params, new Callback.CommonCallback<String>() {

            //请求成功
            @Override
            public void onSuccess(String result) {

                LogUtil.e("联网成功 == " + result.toString());


                try {
                    JSONObject jsonObject = new JSONObject(result);


                    if (jsonObject.get("state").equals("success")) {
                        bean = gs.fromJson(result, ModelManagementBean.class);
                        if (bean.getBiz_content() != null && bean.getBiz_content().size() > 0) {
                            Message mg = Message.obtain(mHandler, YES);
                            mg.sendToTarget();
                        }

                    } else {
                        ToastUtils.show(ModelManagementActivity.this, jsonObject.get("biz_content").toString(), 3000);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            //请求失败
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e("联网失败 == " + ex.getMessage());
                ToastUtils.showShort(ModelManagementActivity.this, "网络不给力！");
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
                onLoad();
            }
        });
    }
    // 获得数据后一定要调用onLoad()方法，否则刷新会一直进行，根本停不下来
    private void onLoad() {
        ll_pull.onHeaderRefreshComplete();
        ll_pull.onFooterRefreshComplete();
    }

    //改名请求
    private void setHttpModify(final int position, String name) {

        RequestParams params = new RequestParams(HttpApi.MODIFY_NAME);
        params.addParameter("account", Constant.Config.account);
        params.addParameter("imei", Constant.Config.imei);
        params.addParameter("name", name);
        params.addParameter("mid", list.get(position).getMid());
        showDialog(ModelManagementActivity.this, "努力加载中...", false);

        x.http().post(params, new Callback.CommonCallback<String>() {

            //请求成功
            @Override
            public void onSuccess(String result) {

                LogUtil.e("联网成功 == " + result.toString());

                try {
                    JSONObject jsonObject = new JSONObject(result);

                    if(jsonObject.get("state").equals("success")){
                        list.get(position).setName(mode_name);  //修改成功修改数据
                        adapter.notifyDataSetChanged();
                    }

                    ToastUtils.show(ModelManagementActivity.this,
                            jsonObject.get("biz_content").toString(), 3000);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            //请求失败
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e("联网失败 == " + ex.getMessage());
                ToastUtils.showShort(ModelManagementActivity.this, "网络不给力！");
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

    //删除请求
    private void setHttpDelete(final int position) {

        RequestParams params = new RequestParams(HttpApi.DELETE_MODEL);
        params.addParameter("account", Constant.Config.account);
        params.addParameter("imei", Constant.Config.imei);
        params.addParameter("status",9);
        params.addParameter("mid", list.get(position).getMid());
        showDialog(ModelManagementActivity.this, "努力加载中...", false);

        x.http().post(params, new Callback.CommonCallback<String>() {

            //请求成功
            @Override
            public void onSuccess(String result) {

                LogUtil.e("联网成功 == " + result.toString());

                try {
                    JSONObject jsonObject = new JSONObject(result);

                    if(jsonObject.get("state").equals("success")){

                        list.remove(position);
                        adapter.notifyDataSetChanged();
                    }

                    ToastUtils.show(ModelManagementActivity.this,
                            jsonObject.get("biz_content").toString(), 3000);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            //请求失败
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e("联网失败 == " + ex.getMessage());
                ToastUtils.showShort(ModelManagementActivity.this, "网络不给力！");
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
