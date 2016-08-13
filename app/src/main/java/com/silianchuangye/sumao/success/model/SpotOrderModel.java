package com.silianchuangye.sumao.success.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/5/4 0004.
 */
public class SpotOrderModel implements Serializable {
//    单价
    private double univalent;
//    数量
    private double number;
//    生产企业
    private String  enterprise;
//    总价
    private double totalMoney;
//    仓库
    private String warehouse;
//   公司
    private String company;
//    产品型号
    private String productModel;

    public double getUnivalent() {
        return univalent;
    }

    public void setUnivalent(double univalent) {
        this.univalent = univalent;
    }

    public double getNumber() {
        return number;
    }

    public void setNumber(double number) {
        this.number = number;
    }

    public String getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(String enterprise) {
        this.enterprise = enterprise;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getProductModel() {
        return productModel;
    }

    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }
}

