package com.silianchuangye.sumao.success.adapter;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/7/19 0019.
 */
public class HavaPaidSellerOrderDetailsModel implements Serializable {
//    标题
    private String title;

    //    分类
    private String classification;

    //    产品名称
    private String productName;
    //    原单价
    private String originalUnitPrice;
    //    数量
    private String number;
    //    新单价
    private String newUnitPrice;
    //    产品总价
    private String totalProductPrice;
    //    仓库
    private String warehouse;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getOriginalUnitPrice() {
        return originalUnitPrice;
    }

    public void setOriginalUnitPrice(String originalUnitPrice) {
        this.originalUnitPrice = originalUnitPrice;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNewUnitPrice() {
        return newUnitPrice;
    }

    public void setNewUnitPrice(String newUnitPrice) {
        this.newUnitPrice = newUnitPrice;
    }

    public String getTotalProductPrice() {
        return totalProductPrice;
    }

    public void setTotalProductPrice(String totalProductPrice) {
        this.totalProductPrice = totalProductPrice;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }
}
