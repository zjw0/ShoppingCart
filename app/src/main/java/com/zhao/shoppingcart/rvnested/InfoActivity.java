package com.zhao.shoppingcart.rvnested;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhao.shoppingcart.MainActivity;
import com.zhao.shoppingcart.R;
import com.zhao.shoppingcart.app.MyApplication;
import com.zhao.shoppingcart.base.BaseActivity;
import com.zhao.shoppingcart.bean.GoodsList;
import com.zhao.shoppingcart.bean.ShoppingCartList;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InfoActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.rv_food_list)
    RecyclerView rv_food_list;
    @BindView(R.id.iv_top)
    ImageView iv_top;
    @BindView(R.id.rl_top)
    FrameLayout rl_top;
    @BindView(R.id.iv_finish)
    ImageView iv_finish;
    @BindView(R.id.iv_cart)
    ImageView iv_cart;
    @BindView(R.id.rl_title)
    RelativeLayout rl_title;
    @BindView(R.id.fl_title)
    FrameLayout fl_title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.barLayout)
    AppBarLayout barLayout;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.web_view)
    WebView web_view;
    private GoodsKindListAdapter goodsKindListAdapter;

    @Override
    public void setView() {
        setContentView(R.layout.activity_info);
    }

    @Override
    public void initView() {
        iv_finish.setOnClickListener(this);
        iv_cart.setOnClickListener(this);
        initKindList();
        initWebView();
        barLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if (state == State.EXPANDED) {
                    //展开状态
                    rl_title.setVisibility(View.VISIBLE);
                    fl_title.setVisibility(View.GONE);
//                    tv_name.visibility =View.VISIBLE
                    //状态栏字体变为白色
//                    StatusBarUtils.setStatusBarDarkTheme(this@GoodsInfoActivity, false)
                } else if (state == State.COLLAPSED) {
                    //折叠状态
                    rl_title.setVisibility(View.GONE);
                    fl_title.setVisibility(View.VISIBLE);
//                    tv_name.visibility =View.INVISIBLE
                    //状态栏字体变为深色
//                    StatusBarUtils.setStatusBarDarkTheme(this@GoodsInfoActivity, true)
                } else {
                    //中间状态
                    rl_title.setVisibility(View.VISIBLE);
                    fl_title.setVisibility(View.GONE);
//                    tv_name.visibility =View.VISIBLE
                    //状态栏字体变为深色
//                    StatusBarUtils.setStatusBarDarkTheme(this@GoodsInfoActivity, false)
                }
            }
        });
    }

    @Override
    public void initData() {
    }

    private void initKindList() {
        //模拟数据
        List<GoodsList> datas = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            GoodsList data = new GoodsList();
            data.id = i + 1 + "";
            data.img = "https://b-ssl.duitang.com/uploads/item/201608/05/20160805174631_wKmaj.jpeg";
            data.name = i + 1 + "号店";
            data.price = 99.98;

            data.shoppingCartListList = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                ShoppingCartList carData = new ShoppingCartList();
                carData.id = j + 1 + "";
                carData.img = "https://b-ssl.duitang.com/uploads/item/201608/05/20160805174631_wKmaj.jpeg";
                carData.name = "吃瓜少女";
                carData.price = 99.98;
                data.shoppingCartListList.add(carData);
            }
            datas.add(data);
        }

        rv_food_list.setLayoutManager(new LinearLayoutManager(this));
        goodsKindListAdapter = new GoodsKindListAdapter(R.layout.item_kind_goods_list, datas);
        rv_food_list.setAdapter(goodsKindListAdapter);
        goodsKindListAdapter.setNewData(datas);
    }

    private void initWebView() {

//        web_view.loadDataWithBaseURL("", MyApplication.addData + "https://www.baidu.com/",
//                "text/html", "UTF-8", null);

        WebSettings webSettings = web_view.getSettings();
        //设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);
        //设置可以访问文件
        webSettings.setAllowFileAccess(true);
        //设置支持缩放
        webSettings.setBuiltInZoomControls(true);
        //加载需要显示的网页
        web_view.loadUrl("http://www.baidu.com");
        //设置Web视图
        web_view.setWebViewClient(new webViewClient());
    }

    //Web视图
    private class webViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_finish:
                finish();
                break;
            case R.id.iv_cart:
                startActivity(new Intent(this, MainActivity.class));
                break;
            default:
                break;
        }
    }
}
