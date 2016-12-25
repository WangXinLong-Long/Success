package com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.GoodsInStockDetailActivityMVP.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jobs on 2016/9/21 0021.
 */
public class Orders implements Serializable{

    private List<OrderIdListBean> orderIdList;

    public List<OrderIdListBean> getOrderIdList() {
        return orderIdList;
    }

    public void setOrderIdList(List<OrderIdListBean> orderIdList) {
        this.orderIdList = orderIdList;
    }

    public static class OrderIdListBean implements Serializable{
        /**
         * total : 1.11
         * commerceItem : [{"amount":1.11,"amountUnitScale":1000,"manufacturer":"浙江万凯","commerceItemId":"ci2282000002","gradeNumber":"WK-821","salePrice":1.11,"quantity":1,"parentCategories":"PET","salesCompanyDisplayName":"中国石油天然气股份有限公司西北化工销售分公司","warehouse":"524仓库","productName":"万凯WK-821"}]
         * remainingTime : 3599
         * orderId : 10215800000004
         */

        private String total;
        private String remainingTime;
        private String orderId;
        private List<CommerceItemBean> commerceItem;

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getRemainingTime() {
            return remainingTime;
        }

        public void setRemainingTime(String remainingTime) {
            this.remainingTime = remainingTime;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public List<CommerceItemBean> getCommerceItem() {
            return commerceItem;
        }

        public void setCommerceItem(List<CommerceItemBean> commerceItem) {
            this.commerceItem = commerceItem;
        }

        public static class CommerceItemBean implements Serializable{
            /**
             * amount : 1.11
             * amountUnitScale : 1000
             * manufacturer : 浙江万凯
             * commerceItemId : ci2282000002
             * gradeNumber : WK-821
             * salePrice : 1.11
             * quantity : 1
             * parentCategories : PET
             * salesCompanyDisplayName : 中国石油天然气股份有限公司西北化工销售分公司
             * warehouse : 524仓库
             * productName : 万凯WK-821
             */

            private String amount;
            private String amountUnitScale;
            private String manufacturer;
            private String commerceItemId;
            private String gradeNumber;
            private String salePrice;
            private String quantity;
            private String parentCategories;
            private String salesCompanyDisplayName;
            private String warehouse;
            private String productName;

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

            public String getSalePrice() {
                return salePrice;
            }

            public void setSalePrice(String salePrice) {
                this.salePrice = salePrice;
            }

            public String getQuantity() {
                return quantity;
            }

            public void setQuantity(String quantity) {
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
    }
}
