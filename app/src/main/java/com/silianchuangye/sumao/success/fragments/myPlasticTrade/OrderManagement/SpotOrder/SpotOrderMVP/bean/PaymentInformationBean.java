package com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.SpotOrderMVP.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jobs Created on 2017/2/21.
 */
public class PaymentInformationBean implements Serializable{

    /**
     * cl_shoukyh : 平安银行
     * serviceFee : 0
     * orderState : QUOTED
     * cl_shoukzh : 888800117356999
     * orderType : fixedPricingOrder
     * cl_shoukf : 北京君知晓包装制品有限公司
     * cl_fukzh : 888800117356979
     * cl_fukf : 深圳前海钻库金融服务有限公司
     * in : [{"amount":1.33,"cl_type":"payTail","cl_yidrq":1487658505924,"cl_yidsj":1487658505924}]
     * cl_fukyh : 平安银行
     * cl_sxfrq:手续费时间
     * cl_sxfsj：手续费日期
     * cl_id : 10309100000041
     */

    private String cl_shoukyh;
    private String serviceFee;
    private String orderState;
    private String cl_shoukzh;
    private String orderType;
    private String cl_shoukf;
    private String cl_fukzh;
    private String cl_fukf;
    private String cl_fukyh;
    private String cl_id;
    private String needDeposit;
    private String cl_sxfrq;
    private String cl_sxfsj;
    private List<InBean> in;

    public String getCl_sxfrq() {
        return cl_sxfrq;
    }

    public void setCl_sxfrq(String cl_sxfrq) {
        this.cl_sxfrq = cl_sxfrq;
    }

    public String getCl_sxfsj() {
        return cl_sxfsj;
    }

    public void setCl_sxfsj(String cl_sxfsj) {
        this.cl_sxfsj = cl_sxfsj;
    }

    public String getNeedDeposit() {
        return needDeposit;
    }

    public void setNeedDeposit(String needDeposit) {
        this.needDeposit = needDeposit;
    }

    public String getCl_shoukyh() {
        return cl_shoukyh;
    }

    public void setCl_shoukyh(String cl_shoukyh) {
        this.cl_shoukyh = cl_shoukyh;
    }

    public String getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(String serviceFee) {
        this.serviceFee = serviceFee;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public String getCl_shoukzh() {
        return cl_shoukzh;
    }

    public void setCl_shoukzh(String cl_shoukzh) {
        this.cl_shoukzh = cl_shoukzh;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getCl_shoukf() {
        return cl_shoukf;
    }

    public void setCl_shoukf(String cl_shoukf) {
        this.cl_shoukf = cl_shoukf;
    }

    public String getCl_fukzh() {
        return cl_fukzh;
    }

    public void setCl_fukzh(String cl_fukzh) {
        this.cl_fukzh = cl_fukzh;
    }

    public String getCl_fukf() {
        return cl_fukf;
    }

    public void setCl_fukf(String cl_fukf) {
        this.cl_fukf = cl_fukf;
    }

    public String getCl_fukyh() {
        return cl_fukyh;
    }

    public void setCl_fukyh(String cl_fukyh) {
        this.cl_fukyh = cl_fukyh;
    }

    public String getCl_id() {
        return cl_id;
    }

    public void setCl_id(String cl_id) {
        this.cl_id = cl_id;
    }

    public List<InBean> getIn() {
        return in;
    }

    public void setIn(List<InBean> in) {
        this.in = in;
    }

    public static class InBean implements Serializable{
        /**
         * amount : 1.33
         * cl_type : payTail
         * cl_yidrq : 1487658505924
         * cl_yidsj : 1487658505924
         */

        private String amount;
        private String cl_type;
        private String cl_yidrq;
        private String cl_yidsj;

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getCl_yidrq() {
            return cl_yidrq;
        }

        public void setCl_yidrq(String cl_yidrq) {
            this.cl_yidrq = cl_yidrq;
        }

        public String getCl_yidsj() {
            return cl_yidsj;
        }

        public void setCl_yidsj(String cl_yidsj) {
            this.cl_yidsj = cl_yidsj;
        }

        public String getCl_type() {
            return cl_type;
        }

        public void setCl_type(String cl_type) {
            this.cl_type = cl_type;
        }
    }
}
