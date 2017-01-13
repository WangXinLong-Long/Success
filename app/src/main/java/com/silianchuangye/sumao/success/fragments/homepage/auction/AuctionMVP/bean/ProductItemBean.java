package com.silianchuangye.sumao.success.fragments.homepage.auction.AuctionMVP.bean;

import java.io.Serializable;

/**
 * Created by Jobs Created on 2016/11/23.
 */

public  class ProductItemBean implements Serializable{

    /**
     * id : prod1510002
     * startDate : 1482722100000
     * cl_zongliang : 100
     * cl_mingcheng : 兰化7042
     * status : 2
     * cl_qipai : 10
     * cl_cangku : 保定库
     * endDate : 1482722469060
     * cl_gongsi : 北京橡胶
     * type : englishAuctionProduct
     * List : [{"@class":"com.sumao.commerce.product.bean.AuctionBean","max":"16.0","min":"12.0","pNumber":"2","quty":"100.0"}]
     */

    private String productId;
    private String productName;
    private String endDate;
    private String quantity;
    private String wareHouse;
    private String supplier;
    private String status;
    private String type;
    private String startDate;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}
