package com.silianchuangye.sumao.success.fragments.myPlasticTrade.demandPlanning.PlanQueryMVP.bean;

/**
 * Created by Jobs Created on 2017/2/10.
 */
public class PlanQueryBean {
//    需求或订单
    String orderOrdemand;
//    需求或订单号
    String orderOrdemandNum;
//    需求日期
    String demand_date_num;
//    提报日期
    String date_of_presentation_num;
//    分类
    String classification_content;
//    产品名称
    String product_name_content;

    public String getOrderOrdemand() {
        return orderOrdemand;
    }

    public void setOrderOrdemand(String orderOrdemand) {
        this.orderOrdemand = orderOrdemand;
    }

    public String getOrderOrdemandNum() {
        return orderOrdemandNum;
    }

    public void setOrderOrdemandNum(String orderOrdemandNum) {
        this.orderOrdemandNum = orderOrdemandNum;
    }

    public String getDemand_date_num() {
        return demand_date_num;
    }

    public void setDemand_date_num(String demand_date_num) {
        this.demand_date_num = demand_date_num;
    }

    public String getDate_of_presentation_num() {
        return date_of_presentation_num;
    }

    public void setDate_of_presentation_num(String date_of_presentation_num) {
        this.date_of_presentation_num = date_of_presentation_num;
    }

    public String getClassification_content() {
        return classification_content;
    }

    public void setClassification_content(String classification_content) {
        this.classification_content = classification_content;
    }

    public String getProduct_name_content() {
        return product_name_content;
    }

    public void setProduct_name_content(String product_name_content) {
        this.product_name_content = product_name_content;
    }
}
