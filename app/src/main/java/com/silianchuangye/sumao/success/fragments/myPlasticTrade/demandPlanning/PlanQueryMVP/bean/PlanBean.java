package com.silianchuangye.sumao.success.fragments.myPlasticTrade.demandPlanning.PlanQueryMVP.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by junjun on 2017/3/17.
 */
public class PlanBean implements Serializable {
    private String pageCount;
    private List<PlanOrderList> planOrderList;
    private String orderCount;
    private String pageNum;

    public String getPageCount() {
        return pageCount;
    }

    public void setPageCount(String pageCount) {
        this.pageCount = pageCount;
    }

    public String getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(String orderCount) {
        this.orderCount = orderCount;
    }

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public List<PlanOrderList> getPlanOrderList() {
        return planOrderList;
    }

    public void setPlanOrderList(List<PlanOrderList> planOrderList) {
        this.planOrderList = planOrderList;
    }

    public PlanBean(List<PlanOrderList> planOrderList) {
        this.planOrderList = planOrderList;
    }
    public static class PlanOrderList implements Serializable{

        private String logisticsChangeName;
        private String productNameChange;
        private String warehouseName;
        private String scheduleDateChange;
        private String warehouseChangeName;
        private String scheduleDate;
        private String demandScheduleState;
        private String logisticsName;
        private String planID;
        private String quantity;
        private String quantityChange;
        private String productName;
        private String orderId;

        public String getProductNameChange() {
            return productNameChange;
        }

        public void setProductNameChange(String productNameChange) {
            this.productNameChange = productNameChange;
        }

        public String getLogisticsChangeName() {
            return logisticsChangeName;
        }

        public void setLogisticsChangeName(String logisticsChangeName) {
            this.logisticsChangeName = logisticsChangeName;
        }

        public String getWarehouseName() {
            return warehouseName;
        }

        public void setWarehouseName(String warehouseName) {
            this.warehouseName = warehouseName;
        }

        public String getScheduleDateChange() {
            return scheduleDateChange;
        }

        public void setScheduleDateChange(String scheduleDateChange) {
            this.scheduleDateChange = scheduleDateChange;
        }

        public String getWarehouseChangeName() {
            return warehouseChangeName;
        }

        public void setWarehouseChangeName(String warehouseChangeName) {
            this.warehouseChangeName = warehouseChangeName;
        }

        public String getScheduleDate() {
            return scheduleDate;
        }

        public void setScheduleDate(String scheduleDate) {
            this.scheduleDate = scheduleDate;
        }

        public String getDemandScheduleState() {
            return demandScheduleState;
        }

        public void setDemandScheduleState(String demandScheduleState) {
            this.demandScheduleState = demandScheduleState;
        }

        public String getLogisticsName() {
            return logisticsName;
        }

        public void setLogisticsName(String logisticsName) {
            this.logisticsName = logisticsName;
        }

        public String getPlanID() {
            return planID;
        }

        public void setPlanID(String planID) {
            this.planID = planID;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public String getQuantityChange() {
            return quantityChange;
        }

        public void setQuantityChange(String quantityChange) {
            this.quantityChange = quantityChange;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }
    }
}
