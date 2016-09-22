package com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockDetailActivityMVP.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/9/21 0021.
 */
public class Orders implements Serializable{
    int total;
    List<CommerceItems> commerceItem;
    int remainingTime;
    String orderId;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<CommerceItems> getCommerceItem() {
        return commerceItem;
    }

    public void setCommerceItem(List<CommerceItems> commerceItem) {
        this.commerceItem = commerceItem;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
