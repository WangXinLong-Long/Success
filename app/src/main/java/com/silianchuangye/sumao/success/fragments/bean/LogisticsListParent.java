package com.silianchuangye.sumao.success.fragments.bean;

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
