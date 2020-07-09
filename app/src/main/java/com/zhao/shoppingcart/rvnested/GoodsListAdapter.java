package com.zhao.shoppingcart.rvnested;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhao.shoppingcart.MainActivity;
import com.zhao.shoppingcart.R;
import com.zhao.shoppingcart.bean.ShoppingCartList;
import com.zhao.shoppingcart.utils.ToastUtil;

import java.util.List;


public class GoodsListAdapter extends BaseQuickAdapter<ShoppingCartList, BaseViewHolder> {

    public GoodsListAdapter(int layoutResId, List<ShoppingCartList> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final ShoppingCartList item) {
        final TextView tvGoodsCount = helper.getView(R.id.tv_goods_count);
        Glide.with(mContext).load(R.drawable.eat_watermelon_girl).into((ImageView) helper.getView(R.id.iv_goods_img));
        helper.setText(R.id.tv_goods_name, item.name);
        helper.setText(R.id.tv_goods_price, "¥ " + /*AtyUtils.get2Point*/(item.price));
        helper.setChecked(R.id.rb_select_goods, item.isSelected);

        //选中商品
        helper.getView(R.id.rb_select_goods).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.isSelected = !item.isSelected;
                helper.setChecked(R.id.rb_select_goods, item.isSelected);

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
                    ToastUtil.showToast(mContext, "宝贝不能在减少了哦！");
                }
                else {
                    MainActivity.editGoodsCount(mContext, item, 2, 1, tvGoodsCount);
                }
            }
        });

        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(new Intent(mContext, MainActivity.class));
            }
        });
    }
}
