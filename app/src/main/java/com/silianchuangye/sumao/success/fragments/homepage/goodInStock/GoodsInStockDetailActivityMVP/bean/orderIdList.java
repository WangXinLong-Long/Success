package com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockDetailActivityMVP.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/9/21 0021.
 */
public class orderIdList implements Serializable{
    List<Orders> orderIdList;

    public List<Orders> getOrderIdList() {
        return orderIdList;
    }

    public void setOrderIdList(List<Orders> orderIdList) {
        this.orderIdList = orderIdList;
    }
}
