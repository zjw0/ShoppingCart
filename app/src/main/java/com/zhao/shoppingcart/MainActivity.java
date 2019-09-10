package com.zhao.shoppingcart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhao.shoppingcart.adapter.ShoppingCartListAdapter;
import com.zhao.shoppingcart.bean.ShoppingCartList;
import com.zhao.shoppingcart.interfaces.OnShoppingCartListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,OnShoppingCartListener {

    @BindView(R.id.tv_title_text)
    TextView tvTitleText;
    @BindView(R.id.tv_title_right_text)
    TextView tvTitleRightText;
    @BindView(R.id.rv_shopping_car)
    RecyclerView rvShoppingCar;
    @BindView(R.id.rb_all_select_goods)
    RadioButton rbAllSelectGoods;
    @BindView(R.id.rl_to_pay_money)
    RelativeLayout rlToPayMoney;
    @BindView(R.id.rl_delete_goods)
    RelativeLayout rlDeleteGoods;
    @BindView(R.id.tv_he_ji)
    TextView tvHeJi;
    @BindView(R.id.tv_money_number)
    TextView tvMoneyNumber;
    @BindView(R.id.tv_all_money)
    TextView tvAllMoney;
    @BindView(R.id.tv_to_collect)
    TextView tvToCollect;
    @BindView(R.id.tv_to_delete)
    TextView tvToDelete;


    private ShoppingCartListAdapter shoppingCartListAdapter;
    //显示编辑完成
    private int temp = 1;
    //商品数据
    private List<ShoppingCartList> datas;
    //是否全选
    private boolean isSelectedAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initData();

    }

    private void initView() {
        tvTitleRightText.setOnClickListener(this);
        rbAllSelectGoods.setOnClickListener(this);
        tvToCollect.setOnClickListener(this);
        tvToDelete.setOnClickListener(this);
        tvAllMoney.setOnClickListener(this);

    }

    private void initData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);//垂直
        rvShoppingCar.setLayoutManager(layoutManager);

        List<ShoppingCartList> carDatas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ShoppingCartList carData = new ShoppingCartList();
            carData.id = i + 1 + "";
            carData.img = "https://b-ssl.duitang.com/uploads/item/201608/05/20160805174631_wKmaj.jpeg";
            carData.name = "想要吃瓜的少女";
            carData.price = 99.98;
            carDatas.add(carData);
        }
        setShoppingCartList(carDatas);
    }

    //购物车商品
    //@Override
    public void setShoppingCartList(List<ShoppingCartList> data) {
        this.datas = data;
        if (shoppingCartListAdapter == null) {
            shoppingCartListAdapter = new ShoppingCartListAdapter(R.layout.item_shopping_cart_list, datas);
            shoppingCartListAdapter.setOnShoppingCartListener(this);
            rvShoppingCar.setAdapter(shoppingCartListAdapter);
        } else {
            shoppingCartListAdapter.setNewData(datas);
        }
        if (datas.size() > 0) {
            if (isSelectedAll) {
                // 有新数据时，如果现在是全选，设为不全选
                setSelectedAll(false, false);
            }
        }
    }

    /**
     * 设置全选
     *
     * @param isSelectedAll 是否全选
     * @param isRefresh     是否刷新列表
     */
    public void setSelectedAll(boolean isSelectedAll, boolean isRefresh) {
        this.isSelectedAll = isSelectedAll;
        if (rbAllSelectGoods != null) {
            rbAllSelectGoods.setChecked(isSelectedAll);
        }
        if (isRefresh) {
            for (int i = 0; i < datas.size(); i++) {
                datas.get(i).isSelected = isSelectedAll;
            }
            if (shoppingCartListAdapter != null) {
                shoppingCartListAdapter.notifyDataSetChanged();
            }
        }
    }

    public static void editGoodsCount(Context context, final ShoppingCartList t, final int type, final int sum, final TextView tv) {
        int count = TextUtils.isEmpty(t.num) ? 0 : Integer.parseInt(t.num);
        switch (type) {
            case 1:
                count = count + sum;
                break;
            case 2:
                count = count - sum;
                break;
        }
        t.num = String.valueOf(count);
        tv.setText(t.num);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_title_right_text:
                temp++;
                if (temp%2==0) {
                    tvTitleRightText.setText("完成");
                    rlToPayMoney.setVisibility(View.GONE);
                    rlDeleteGoods.setVisibility(View.VISIBLE);
                }else{
                    tvTitleRightText.setText("编辑");
                    rlToPayMoney.setVisibility(View.VISIBLE);
                    rlDeleteGoods.setVisibility(View.GONE);
                }
                break;
            //全选商品
            case R.id.rb_all_select_goods:
                setSelectedAll(!isSelectedAll, true);
                break;
            //移入收藏
            case R.id.tv_to_collect:

                break;
            //删除商品
            case R.id.tv_to_delete:
                break;
            //确认订单
            case R.id.tv_all_money:
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }

    }

    @Override
    public void onShoppingCartListener() {
        boolean isAll = true;
        for (int i = 0; i < datas.size(); i++) {
            ShoppingCartList data = datas.get(i);
            if (!data.isSelected) {
                isAll = false;
                break;
            }
        }
        setSelectedAll(isAll, false);
    }
}
