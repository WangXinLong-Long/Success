package com.silianchuangye.sumao.success.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/7/15 0015.
 */
public class SellerOrderanagementModel implements Serializable {
//    订单号
    private String orderNumber;
//    订单类型
    private String orderType;
//    订单总额
    private String totalOrder;
//    买方企业名称
    private String nameOfBuyerEnterprise;
//    下单人
    private String placeAnOrderPerson;
//    下单日期
    private String orderDate;
//    订单状态
    private String orderStatus;
//    业务员
    private String salesman;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(String totalOrder) {
        this.totalOrder = totalOrder;
    }

    public String getNameOfBuyerEnterprise() {
        return nameOfBuyerEnterprise;
    }

    public void setNameOfBuyerEnterprise(String nameOfBuyerEnterprise) {
        this.nameOfBuyerEnterprise = nameOfBuyerEnterprise;
    }

    public String getPlaceAnOrderPerson() {
        return placeAnOrderPerson;
    }

    public void setPlaceAnOrderPerson(String placeAnOrderPerson) {
        this.placeAnOrderPerson = placeAnOrderPerson;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getSalesman() {
        return salesman;
    }

    public void setSalesman(String salesman) {
        this.salesman = salesman;
    }
}
