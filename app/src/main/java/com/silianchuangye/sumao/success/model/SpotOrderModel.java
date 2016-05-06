package com.silianchuangye.sumao.success.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/5/4 0004.
 */
public class SpotOrderModel implements Serializable {
//    单价
    private int univalent;
//    数量
    private int number;
//    生产企业
    private String  enterprise;
//    总价
    private int totalMoney;
//    仓库
    private String warehouse;
//   公司
    private String company;
//    产品型号
    private String productModel;

    public int getUnivalent() {
        return univalent;
    }

    public void setUnivalent(int univalent) {
        this.univalent = univalent;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(String enterprise) {
        this.enterprise = enterprise;
    }

    public int getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(int totalMoney) {
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

