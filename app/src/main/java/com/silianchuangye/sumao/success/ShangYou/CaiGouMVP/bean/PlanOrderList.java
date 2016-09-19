package com.silianchuangye.sumao.success.ShangYou.CaiGouMVP.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/9/14 0014.
 */
public class PlanOrderList implements Serializable {
    String logisticsChangeName;
    String productNameChange;
    String warehouseName;
    String scheduleDateChange;
    String warehouseChangeName;
    String scheduleDate;
    String demandScheduleState;
    String logisticsName;
    String planID;
    String quantity;
    String quantityChange;
    String productName;
    String orderId;

    public String getLogisticsChangeName() {
        return logisticsChangeName;
    }

    public void setLogisticsChangeName(String logisticsChangeName) {
        this.logisticsChangeName = logisticsChangeName;
    }

    public String getProductNameChange() {
        return productNameChange;
    }

    public void setProductNameChange(String productNameChange) {
        this.productNameChange = productNameChange;
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
