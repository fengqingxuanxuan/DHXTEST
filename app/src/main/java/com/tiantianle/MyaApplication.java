package com.tiantianle;

import android.app.Application;

import org.xutils.x;

/**
 * Created by Administrator on 2017/2/8.
 *
 */

public class MyaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this); //初始化xUtils3
        x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志, 开启debug会影响性能.
    }

}
