package com.silianchuangye.sumao.success.fragments.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/7/8 0008.
 */
public class LogisticsListChild implements Serializable {
//    产品名称
private String ProductName;
//    数量
    private String Number;
//    分类
    private String classification;
//    产品订单编号
    private String ProductOrderNumber;

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductOrderNumber() {
        return ProductOrderNumber;
    }

    public void setProductOrderNumber(String productOrderNumber) {
        ProductOrderNumber = productOrderNumber;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }
}
