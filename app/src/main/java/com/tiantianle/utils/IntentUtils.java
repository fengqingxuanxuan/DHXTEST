package com.tiantianle.utils;

import android.content.Context;
import android.content.Intent;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/2/8.
 *
 * 将跳转的功能进行一个封装
 *
 */

public class IntentUtils {


    public static void goTo(Context packageContext, Class<?> cls) {
        Intent intent = new Intent(packageContext, cls);
        packageContext.startActivity(intent);
    }

    public static void goTo(Context packageContext, Class<?> cls, String ss) {
        Intent intent = new Intent(packageContext, cls);
        intent.putExtra("stringContent", ss);
        packageContext.startActivity(intent);
    }

    public static void goTo(Context packageContext, Class<?> cls, String ss, String num) {
        Intent intent = new Intent(packageContext, cls);
        intent.putExtra("stringContent", ss);
        intent.putExtra("stringNum", num);
        packageContext.startActivity(intent);
    }

    public static void goTo(Context packageContext, Class<?> cls, String ss, String num, String three) {
        Intent intent = new Intent(packageContext, cls);
        intent.putExtra("stringContent", ss);
        intent.putExtra("stringNum", num);
        intent.putExtra("three", three);
        packageContext.startActivity(intent);
    }

    public static void goTo(Context packageContext, Class<?> cls, int ss, String num, String three) {
        Intent intent = new Intent(packageContext, cls);
        intent.putExtra("stringContent", ss);
        intent.putExtra("stringNum", num);
        intent.putExtra("three", three);
        packageContext.startActivity(intent);
    }
    //传递类对象  类一定要实现序列化Serializable
    public static void goTo(Context packageContext, Class<?> cls, Boolean bool) {
        Intent intent = new Intent(packageContext, cls);
        intent.putExtra("bool",bool);
        packageContext.startActivity(intent);
    }

    //传递类对象  类一定要实现序列化Serializable
    public static void goTo(Context packageContext, Class<?> cls, Object object) {
        Intent intent = new Intent(packageContext, cls);
        intent.putExtra("class",(Serializable)object);
        packageContext.startActivity(intent);
    }


}

