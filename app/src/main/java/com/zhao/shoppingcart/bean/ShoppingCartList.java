package com.zhao.shoppingcart.bean;

import java.io.Serializable;

/**
 * 购物车商品列表
 */
public class ShoppingCartList implements Serializable {

    public String id;
    public String img;
    public String name;
    public String type;
    public double price;
    public String num;
    public boolean isSelected;//是否选中


    public ShoppingCartList(String id, String img, String name, String type, double price, String num, boolean isSelected) {
        this.id = id;
        this.img = img;
        this.name = name;
        this.type = type;
        this.price = price;
        this.num = num;
        this.isSelected = isSelected;
    }

    public ShoppingCartList() {
    }

}
