package com.silianchuangye.sumao.success.model;

/**
 * Created by Administrator on 2016/7/14 0014.
 */
public class CustomerApprovalModel {
//    标题
    private String title;
//    审核状态
    private String state;
//    企业编号
    private String number;
//    企业类型
    private String typeOfEnterprise;
//    客户状态
    private String customerStatus;
//    业务员
    private String salesman;
//    企业名称
    private String enterpriseName;
//    办公地址
    private String officeAddress;
//    购买资质
    private String purchasingQualification;
//    未通过审批原因
    private String notApprovedReasons;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTypeOfEnterprise() {
        return typeOfEnterprise;
    }

    public void setTypeOfEnterprise(String typeOfEnterprise) {
        this.typeOfEnterprise = typeOfEnterprise;
    }

    public String getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(String customerStatus) {
        this.customerStatus = customerStatus;
    }

    public String getSalesman() {
        return salesman;
    }

    public void setSalesman(String salesman) {
        this.salesman = salesman;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }

    public String getPurchasingQualification() {
        return purchasingQualification;
    }

    public void setPurchasingQualification(String purchasingQualification) {
        this.purchasingQualification = purchasingQualification;
    }

    public String getNotApprovedReasons() {
        return notApprovedReasons;
    }

    public void setNotApprovedReasons(String notApprovedReasons) {
        this.notApprovedReasons = notApprovedReasons;
    }
}
