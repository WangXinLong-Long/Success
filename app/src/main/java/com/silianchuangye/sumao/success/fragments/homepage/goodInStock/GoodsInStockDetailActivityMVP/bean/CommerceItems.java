package com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockDetailActivityMVP.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/9/21 0021.
 */
public class CommerceItems implements Serializable {
    int amount;
    int amountUnitScale;
    String manufacturer;
    String commerceItemId;
    String gradeNumber;
    int salePrice;
    int quantity;
    String parentCategories;
    String salesCompanyDisplayName;
    String warehouse;
    String productName;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmountUnitScale() {
        return amountUnitScale;
    }

    public void setAmountUnitScale(int amountUnitScale) {
        this.amountUnitScale = amountUnitScale;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getCommerceItemId() {
        return commerceItemId;
    }

    public void setCommerceItemId(String commerceItemId) {
        this.commerceItemId = commerceItemId;
    }

    public String getGradeNumber() {
        return gradeNumber;
    }

    public void setGradeNumber(String gradeNumber) {
        this.gradeNumber = gradeNumber;
    }

    public int getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(int salePrice) {
        this.salePrice = salePrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getParentCategories() {
        return parentCategories;
    }

    public void setParentCategories(String parentCategories) {
        this.parentCategories = parentCategories;
    }

    public String getSalesCompanyDisplayName() {
        return salesCompanyDisplayName;
    }

    public void setSalesCompanyDisplayName(String salesCompanyDisplayName) {
        this.salesCompanyDisplayName = salesCompanyDisplayName;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
