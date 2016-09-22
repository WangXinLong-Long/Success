package com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockDetailActivityMVP.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/9/21 0021.
 */
public class Orders implements Serializable{
    String total;
                      //commerceItem
    List<CommerceItems> commerceItem;
    String remainingTime;
    String orderId;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<CommerceItems> getCommerceItem() {
        return commerceItem;
    }

    public void setCommerceItem(List<CommerceItems> commerceItem) {
        this.commerceItem = commerceItem;
    }

    public String getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(String remainingTime) {
        this.remainingTime = remainingTime;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
