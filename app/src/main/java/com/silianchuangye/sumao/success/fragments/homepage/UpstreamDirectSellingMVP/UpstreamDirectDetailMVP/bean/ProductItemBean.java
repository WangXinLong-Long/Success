package com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.UpstreamDirectDetailMVP.bean;

import java.io.Serializable;

/**
 * Created by Jobs Created on 2016/11/21.
 */

public  class ProductItemBean implements Serializable {
    /**
     * salerEnterpriseName : 中国石油天然气股份有限公司西北化工销售分公司
     * wareHouse : 上海优月库
     * skuId : sku960007
     * quantity : 1000
     * type : forward-pricing-product
     * productName : 上海DJ210
     * productId : prod1030006
     */

    private String salerEnterpriseName;
    private String wareHouse;
    private String skuId;
    private String quantity;
    private String type;
    private String productName;
    private String productId;

    public String getSalerEnterpriseName() {
        return salerEnterpriseName;
    }

    public void setSalerEnterpriseName(String salerEnterpriseName) {
        this.salerEnterpriseName = salerEnterpriseName;
    }

    public String getWareHouse() {
        return wareHouse;
    }

    public void setWareHouse(String wareHouse) {
        this.wareHouse = wareHouse;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
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

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
