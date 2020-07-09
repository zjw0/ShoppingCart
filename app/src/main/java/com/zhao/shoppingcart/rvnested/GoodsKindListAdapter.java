package com.zhao.shoppingcart.rvnested;

import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhao.shoppingcart.R;
import com.zhao.shoppingcart.bean.GoodsList;

import java.util.List;


public class GoodsKindListAdapter extends BaseQuickAdapter<GoodsList, BaseViewHolder> {

    public GoodsKindListAdapter(int layoutResId, List<GoodsList> datas) {
        super(layoutResId, datas);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final GoodsList item) {
        helper.setText(R.id.tv_food_kind, item.name);
        new GoodsListView(0, mContext, (RecyclerView) helper.getView(R.id.rv_kind_list), item.shoppingCartListList);
    }
}
