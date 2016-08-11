package com.silianchuangye.sumao.success.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/7/6 0006.
 */

/**
 * 订单详情
 */
public class OrderDeatilsModel implements Serializable {
//    产品名称
    private String title;
//    分类
    private String classification;
//    数量
    private double number;
//    配送方式：
    private String distributionMode;
//    仓库
    private String  warehouse;
//    产品单价
    private String productUnitPrice;
//    产品总价
    private String totalProductPrice;
//    交货时间
    private String deliveryTime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public double getNumber() {
        return number;
    }

    public void setNumber(double number) {
        this.number = number;
    }

    public String getDistributionMode() {
        return distributionMode;
    }

    public void setDistributionMode(String distributionMode) {
        this.distributionMode = distributionMode;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public String getProductUnitPrice() {
        return productUnitPrice;
    }

    public void setProductUnitPrice(String productUnitPrice) {
        this.productUnitPrice = productUnitPrice;
    }

    public String getTotalProductPrice() {
        return totalProductPrice;
    }

    public void setTotalProductPrice(String totalProductPrice) {
        this.totalProductPrice = totalProductPrice;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }
}
