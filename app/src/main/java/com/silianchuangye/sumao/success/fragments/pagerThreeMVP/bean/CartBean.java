package com.silianchuangye.sumao.success.fragments.pagerThreeMVP.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jobs Created on 2016/12/23.
 */
public class CartBean implements Serializable {

    /**
     * total : 30
     * commerceItem : [{"minPurchaseQuantity":1,"parentCategories":"LLDPE","salesCompanyDisplayName":"中国石油天然气股份有限公司西北化工销售分公司","warehouse":"上海优月库","amount":20,"amountUnitScale":1000,"manufacturer":"蒲城","quantity":2,"salePrice":10,"gradeNumber":"7042","commerceItemId":"ci2282000010","surplus":100,"productName":"蒲城7042","quantityAdjustmentUnit":1},{"minPurchaseQuantity":1,"parentCategories":"PPH","salesCompanyDisplayName":"北京橡胶","warehouse":"保定库","amount":10,"amountUnitScale":1000,"manufacturer":"中原石化","quantity":1,"salePrice":10,"gradeNumber":"PPR-MT20","commerceItemId":"ci2282000012","surplus":100,"productName":"中原PPR-MT20","quantityAdjustmentUnit":1}]
     * orderid : 10215800000016
     * info : sucess
     */

    private String total;
    private String orderid;
    private String info;
    private List<CommerceItemBean> commerceItem;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<CommerceItemBean> getCommerceItem() {
        return commerceItem;
    }

    public void setCommerceItem(List<CommerceItemBean> commerceItem) {
        this.commerceItem = commerceItem;
    }

    public static class CommerceItemBean implements Serializable {
        /**
         * minPurchaseQuantity : 1
         * parentCategories : LLDPE
         * salesCompanyDisplayName : 中国石油天然气股份有限公司西北化工销售分公司
         * warehouse : 上海优月库
         * amount : 20
         * amountUnitScale : 1000
         * manufacturer : 蒲城
         * quantity : 2
         * salePrice : 10
         * gradeNumber : 7042
         * commerceItemId : ci2282000010
         * surplus : 100
         * productName : 蒲城7042
         * quantityAdjustmentUnit : 1
         */

        private String minPurchaseQuantity;
        private String parentCategories;
        private String salesCompanyDisplayName;
        private String warehouse;
        private String amount;
        private String amountUnitScale;
        private String manufacturer;
        private String quantity;
        private String salePrice;
        private String gradeNumber;
        private String commerceItemId;
        private String surplus;
        private String productName;
        private String quantityAdjustmentUnit;
        private boolean selectFlag;

        public boolean isSelectFlag() {
            return selectFlag;
        }

        public void setSelectFlag(boolean selectFlag) {
            this.selectFlag = selectFlag;
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

        public String getManufacturer() {
            return manufacturer;
        }

        public void setManufacturer(String manufacturer) {
            this.manufacturer = manufacturer;
        }


        public String getGradeNumber() {
            return gradeNumber;
        }

        public void setGradeNumber(String gradeNumber) {
            this.gradeNumber = gradeNumber;
        }

        public String getCommerceItemId() {
            return commerceItemId;
        }

        public void setCommerceItemId(String commerceItemId) {
            this.commerceItemId = commerceItemId;
        }


        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getMinPurchaseQuantity() {
            return minPurchaseQuantity;
        }

        public void setMinPurchaseQuantity(String minPurchaseQuantity) {
            this.minPurchaseQuantity = minPurchaseQuantity;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getAmountUnitScale() {
            return amountUnitScale;
        }

        public void setAmountUnitScale(String amountUnitScale) {
            this.amountUnitScale = amountUnitScale;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public String getSalePrice() {
            return salePrice;
        }

        public void setSalePrice(String salePrice) {
            this.salePrice = salePrice;
        }

        public String getSurplus() {
            return surplus;
        }

        public void setSurplus(String surplus) {
            this.surplus = surplus;
        }

        public String getQuantityAdjustmentUnit() {
            return quantityAdjustmentUnit;
        }

        public void setQuantityAdjustmentUnit(String quantityAdjustmentUnit) {
            this.quantityAdjustmentUnit = quantityAdjustmentUnit;
        }
    }
}
