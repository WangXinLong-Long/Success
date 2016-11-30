package com.silianchuangye.sumao.success.fragments.homepage.auction.AuctionMVP.bean;

import java.io.Serializable;

/**
 * Created by Jobs Created on 2016/11/23.
 */

public  class ProductItemBean implements Serializable{
    /**
     * startDate : 1479873000000
     * wareHouse : 上海阳超库
     * status : 0
     * skuId : sku990005
     * endDate : 1479970800000
     * quantity : 111
     * type : englishAuctionProduct
     * productName : 三菱M7026U
     * supplier : 中国石油天然气股份有限公司西北化工销售分公司
     * productId : prod1080006
     */

    private String startDate;
    private String wareHouse;
    private String status;
    private String skuId;
    private String endDate;
    private String quantity;
    private String type;
    private String productName;
    private String supplier;
    private String productId;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getWareHouse() {
        return wareHouse;
    }

    public void setWareHouse(String wareHouse) {
        this.wareHouse = wareHouse;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
