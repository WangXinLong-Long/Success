package com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.orderStaypayBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jobs on 2016/11/15 0015.
 */
public class Order implements Serializable {
    private String shippingGroupState;
    private List<OrderCl> cl;
    private String state;
    private String submittedDate;
    private String owner;
    private String type;
    private String orderId;

    public String getShippingGroupState() {
        return shippingGroupState;
    }

    public void setShippingGroupState(String shippingGroupState) {
        this.shippingGroupState = shippingGroupState;
    }

    public List<OrderCl> getCl() {
        return cl;
    }

    public void setCl(List<OrderCl> cl) {
        this.cl = cl;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSubmittedDate() {
        return submittedDate;
    }

    public void setSubmittedDate(String submittedDate) {
        this.submittedDate = submittedDate;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
