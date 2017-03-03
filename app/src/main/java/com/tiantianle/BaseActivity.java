package com.tiantianle;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.gson.Gson;
import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * Created by wyj on 2017/1/22.
 */

public class BaseActivity extends AppCompatActivity {
    public Gson gs;
    private Dialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gs = new Gson();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏ll
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            // 激活状态栏
            tintManager.setStatusBarTintEnabled(true);
            // enable navigation bar tint 激活导航栏
            tintManager.setNavigationBarTintEnabled(true);
            //设置系统栏设置颜色
            //tintManager.setTintColor(R.color.red);
            //给状态栏设置颜色
            tintManager.setStatusBarTintResource(R.color.colorBackMemu);
            //Apply the specified drawable or color resource to the system navigation bar.
            //给导航栏设置资源

        }

    }

    //打开Dialog
    public void showDialog(Context context, String str,Boolean bool) {
        if(progressDialog != null){
            return;
        }
        progressDialog = new Dialog(context);
        progressDialog.setContentView(R.layout.dolog);
        progressDialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent);
        TextView msg = (TextView) progressDialog
                .findViewById(R.id.tv_loadingmsg);
        msg.setText(str);
        if (bool) {

            //设置dialog点击返回键也不会消失
            progressDialog.setCancelable(false);
        }

        progressDialog.setCanceledOnTouchOutside(false);

        progressDialog.show();
    }

    //关闭Dialog
    public void closeDialog() {
        if(progressDialog !=null){
            System.out.println("关闭Dialog！");
            progressDialog.dismiss();
            progressDialog = null;
        }
    }
}
