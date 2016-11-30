package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.EnterpriseUserManagement.InvoiceInformationMVP.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/8/11 0011.
 */
public class InvoiceInformationBean implements Serializable {
//    纳税人类型
    private String cl_nsrleixing;
//    公司名称
    private String cl_mingcheng;

    private Invoice invoice;
//状态
    private String info;
//    公司id
    private String cl_id;

    public String getCl_nsrleixing() {
        return cl_nsrleixing;
    }

    public void setCl_nsrleixing(String cl_nsrleixing) {
        this.cl_nsrleixing = cl_nsrleixing;
    }

    public String getCl_mingcheng() {
        return cl_mingcheng;
    }

    public void setCl_mingcheng(String cl_mingcheng) {
        this.cl_mingcheng = cl_mingcheng;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getCl_id() {
        return cl_id;
    }

    public void setCl_id(String cl_id) {
        this.cl_id = cl_id;
    }
}
