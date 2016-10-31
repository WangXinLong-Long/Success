package com.silianchuangye.sumao.success.fragments.bean;

import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/7/8 0008.
 */
public class LogisticsListParent implements Serializable {
//    物流需求号
private String logisticsDemand;
//    配送方式
    private String  distributionMode;
//    状态
    private String state;
//    提货车号
    private String  deliveryNumber;
//    提货人
    private String pickUpPerson;
//    联系方式
    private String contactInformation;
//    身份证号码
    private String idCardNumber;
//    备注
    private String remarks;
//    子列表
    private List<LogisticsListChild> logisticsListChildren;

    private String unloadingArea2;//卸货区域
    private String dischargeAddress2;//卸货地址
    private String unloadingContact2;//卸货联系人
    private String dischargeContactPhone2;//卸货联系电话
    private String expectedTimeOfReceipt2;//期望收货时间
    private String receivingCompany2;//收货公司
    private String shipperContact2;//托运联系人
    private String shipperContactInformation2;//托运人联系方式
    private String sellerRemarks2;//卖家配送备注

    public String getUnloadingArea2() {
        return unloadingArea2;
    }

    public void setUnloadingArea2(String unloadingArea2) {
        this.unloadingArea2 = unloadingArea2;
    }

    public String getDischargeAddress2() {
        return dischargeAddress2;
    }

    public void setDischargeAddress2(String dischargeAddress2) {
        this.dischargeAddress2 = dischargeAddress2;
    }

    public String getUnloadingContact2() {
        return unloadingContact2;
    }

    public void setUnloadingContact2(String unloadingContact2) {
        this.unloadingContact2 = unloadingContact2;
    }

    public String getDischargeContactPhone2() {
        return dischargeContactPhone2;
    }

    public void setDischargeContactPhone2(String dischargeContactPhone2) {
        this.dischargeContactPhone2 = dischargeContactPhone2;
    }

    public String getExpectedTimeOfReceipt2() {
        return expectedTimeOfReceipt2;
    }

    public void setExpectedTimeOfReceipt2(String expectedTimeOfReceipt2) {
        this.expectedTimeOfReceipt2 = expectedTimeOfReceipt2;
    }

    public String getReceivingCompany2() {
        return receivingCompany2;
    }

    public void setReceivingCompany2(String receivingCompany2) {
        this.receivingCompany2 = receivingCompany2;
    }

    public String getShipperContact2() {
        return shipperContact2;
    }

    public void setShipperContact2(String shipperContact2) {
        this.shipperContact2 = shipperContact2;
    }

    public String getShipperContactInformation2() {
        return shipperContactInformation2;
    }

    public void setShipperContactInformation2(String shipperContactInformation2) {
        this.shipperContactInformation2 = shipperContactInformation2;
    }

    public String getSellerRemarks2() {
        return sellerRemarks2;
    }

    public void setSellerRemarks2(String sellerRemarks2) {
        this.sellerRemarks2 = sellerRemarks2;
    }

    public List<LogisticsListChild> getLogisticsListChildren() {
        return logisticsListChildren;
    }

    public void setLogisticsListChildren(List<LogisticsListChild> logisticsListChildren) {
        this.logisticsListChildren = logisticsListChildren;
    }

    public String getLogisticsDemand() {
        return logisticsDemand;
    }

    public void setLogisticsDemand(String logisticsDemand) {
        this.logisticsDemand = logisticsDemand;
    }

    public String getDistributionMode() {
        return distributionMode;
    }

    public void setDistributionMode(String distributionMode) {
        this.distributionMode = distributionMode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDeliveryNumber() {
        return deliveryNumber;
    }

    public void setDeliveryNumber(String deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
    }

    public String getPickUpPerson() {
        return pickUpPerson;
    }

    public void setPickUpPerson(String pickUpPerson) {
        this.pickUpPerson = pickUpPerson;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
