package com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.orderStaypayBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jobs on 2016/11/15 0015.
 */
public class ToBePaid implements Serializable {
    private String count;
    private List<Order> order;
    private String pages;
    private String info;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
