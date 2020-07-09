package com.zhao.shoppingcart.app;

import android.content.Context;
import android.support.multidex.MultiDex;

import cn.appoa.aframework.app.AfApplication;
import cn.appoa.aframework.utils.AESUtils;
import cn.appoa.aframework.utils.AtyUtils;


public class MyApplication extends AfApplication {

    public static final String addData = "<style> img { max-width:100% ; height:auto;} </style>" + "<div style=\"margin:0 8px;\">";

    // 测试支付
    public static boolean isTestPay = false;

    // 定位数据
    public static String province = "";
    public static String city = "";
    public static String district = "";
    public static String street = "";
    public static String address = "";
    public static double latitude;
    public static double longitude;

    @Override
    public void initApplication() {
        AtyUtils.isDebug = false;
        // 分包
        MultiDex.install(this);
        //AES加密
        AESUtils.init("bWFsbHB3ZA==WNST");
        //Json解析
        //JsonUtils.init(3, "status", "msg", "data");
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // 分包
        MultiDex.install(base);
    }

}
