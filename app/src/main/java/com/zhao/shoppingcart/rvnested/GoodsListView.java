package com.zhao.shoppingcart.rvnested;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.divider.ListItemDecoration;
import com.zhao.shoppingcart.R;
import com.zhao.shoppingcart.bean.ShoppingCartList;

import java.util.List;

public class GoodsListView {

    private RecyclerView rv;
    private Context mActivity;
    private int type;
    private List<ShoppingCartList> datas;

    public GoodsListView(int type, Context mActivity, RecyclerView rv, List<ShoppingCartList> datas) {
        this.type = type;
        this.mActivity = mActivity;
        this.rv = rv;
        this.datas = datas;
        getGoodList();
    }

    //商品列表
    private void getGoodList() {
        //****初始化****
        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv.setLayoutManager(layoutManager);
        ListItemDecoration itemDecoration = new ListItemDecoration(mActivity);
        itemDecoration.setDecorationHeight(0.5f);
        itemDecoration.setDecorationColor(ContextCompat.getColor(mActivity, R.color.colorAccent));
        rv.addItemDecoration(itemDecoration);
        setGoodData(datas);
    }

    //设置商品列表数据
    private void setGoodData(List<ShoppingCartList> datas) {
        if (datas != null && datas.size() > 0) {
            rv.setAdapter(new GoodsListAdapter(R.layout.item_goods_list, datas));
        }
    }
}
