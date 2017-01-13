package com.silianchuangye.sumao.success.fragments.homepage.auction.AuctionMVP.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jobs Created on 2017/1/13.
 */
public class AuctionSessionBean implements Serializable {

    /**
     * allCount : 1
     * productItem : [{"startDate":1484272800000,"wareHouse":"524仓库","status":"1","skuId":"sku1460003","endDate":1485878400000,"quantity":100,"type":"sealedAuctionProduct","productName":"LG 121H","supplier":"北京君知晓包装制品有限公司","productId":"prod1580003"}]
     */

    private String allCount;
    private List<ProductItemBean> productItem;


    public List<ProductItemBean> getProductItem() {
        return productItem;
    }

    public void setProductItem(List<ProductItemBean> productItem) {
        this.productItem = productItem;
    }

    public String getAllCount() {
        return allCount;
    }

    public void setAllCount(String allCount) {
        this.allCount = allCount;
    }

    public static class ProductItemBean  implements Serializable{
        /**
         * startDate : 1484272800000
         * wareHouse : 524仓库
         * status : 1
         * skuId : sku1460003
         * endDate : 1485878400000
         * quantity : 100
         * type : sealedAuctionProduct
         * productName : LG 121H
         * supplier : 北京君知晓包装制品有限公司
         * productId : prod1580003
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
}
