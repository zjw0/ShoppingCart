package com.zhao.shoppingcart.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {

    /**
     * 设置布局文件
     */
    public abstract void setView();

    /**
     * 初始化控件
     */
    public abstract void initView();

    /**
     * 初始化数据
     */
    public abstract void initData();

    public Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView();
        // 绑定注解
        mUnbinder = ButterKnife.bind(this);
        initView();
        initData();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 解绑注解
        mUnbinder.unbind();
    }
}
