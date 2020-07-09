package com.zhao.shoppingcart.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 商品列表
 */
public class GoodsList implements Serializable {

    public String id;
    public String img;
    public String name;
    public String type;
    public double price;
    public String num;
    public boolean isSelected;//是否选中

    public List<ShoppingCartList> shoppingCartListList;


    public GoodsList() {
    }

}
