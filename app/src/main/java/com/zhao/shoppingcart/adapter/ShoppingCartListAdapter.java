package com.zhao.shoppingcart.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhao.shoppingcart.MainActivity;
import com.zhao.shoppingcart.R;
import com.zhao.shoppingcart.bean.ShoppingCartList;
import com.zhao.shoppingcart.interfaces.OnShoppingCartListener;
import com.zhao.shoppingcart.utils.ToastUtil;

import java.util.List;
import java.util.Map;


public class ShoppingCartListAdapter extends BaseQuickAdapter<ShoppingCartList, BaseViewHolder> {

    public ShoppingCartListAdapter(int layoutResId, @Nullable List<ShoppingCartList> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final ShoppingCartList item) {
        final TextView tvGoodsCount = helper.getView(R.id.tv_goods_count);
        Glide.with(mContext).load(R.drawable.eat_watermelon_girl).into((ImageView) helper.getView(R.id.iv_goods_img));
        //Glide.with(mContext).load(item.img).into((ImageView) helper.getView(R.id.iv_goods_img));
        helper.setText(R.id.tv_goods_name, item.name);
        helper.setText(R.id.tv_goods_price, "¥ " + /*AtyUtils.get2Point*/(item.price));
        helper.setChecked(R.id.rb_select_goods, item.isSelected);

        //选中商品
        helper.getView(R.id.rb_select_goods).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.isSelected = !item.isSelected;
                helper.setChecked(R.id.rb_select_goods, item.isSelected);

                if (onShoppingCartListener != null) {
                    onShoppingCartListener.onShoppingCartListener();
                }
            }
        });
        //增加商品数量
        helper.getView(R.id.iv_jia).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO 增加
                MainActivity.editGoodsCount(mContext, item, 1, 1, tvGoodsCount);
            }
        });
        //减少商品数量
        helper.getView(R.id.iv_jian).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO 减少
                int count = TextUtils.isEmpty(item.num) ? 0 : Integer.parseInt(item.num);
                if (count <= 1) {
//                    Toast.makeText(mContext, "宝贝不能在减少了哦！",
//                            Toast.LENGTH_SHORT).show();
                    ToastUtil.showToast(mContext, "宝贝不能在减少了哦！");
                }
                else {
                    MainActivity.editGoodsCount(mContext, item, 2, 1, tvGoodsCount);
                }
            }
        });
    }


    private OnShoppingCartListener onShoppingCartListener;

    public void setOnShoppingCartListener(OnShoppingCartListener onShoppingCartListener) {
        this.onShoppingCartListener = onShoppingCartListener;
    }
}
