package com.silianchuangye.sumao.success.fragments.myPlasticTrade.PhysicalDistributionManagement.logistics.createLogisticsMVP.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/11/17.
 */
public class Order implements Serializable{

    /**
     * id : 10098400000042
     * cl_gongsiId : 200021
     * cl : [{"vailableQuantity":1,"cl_mingcheng":"LG 121H","commerceId":"ci1970000014","deliveryStartDate":1478188800000,"cl_gongsi":"中国石油天然气股份有限公司西北化工销售分公司","shippingMethod":"自提","deliveryEndDate":1478448000000,"cl_jine":0,"cl_fenlei":"ABS","cl_jituan":"LG","cl_cangku":"524仓库","cl_shuliang":1}]
     * submittedDate : 1477898352490
     * cl_gongsi : 中国石油天然气股份有限公司西北化工销售分公司
     * firstName : dengdongshan
     */

    private String id;
    private String cl_gongsiId;
    private long submittedDate;
    private String cl_gongsi;
    private String firstName;
    private List<ClBean> cl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCl_gongsiId() {
        return cl_gongsiId;
    }

    public void setCl_gongsiId(String cl_gongsiId) {
        this.cl_gongsiId = cl_gongsiId;
    }

    public long getSubmittedDate() {
        return submittedDate;
    }

    public void setSubmittedDate(long submittedDate) {
        this.submittedDate = submittedDate;
    }

    public String getCl_gongsi() {
        return cl_gongsi;
    }

    public void setCl_gongsi(String cl_gongsi) {
        this.cl_gongsi = cl_gongsi;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public List<ClBean> getCl() {
        return cl;
    }

    public void setCl(List<ClBean> cl) {
        this.cl = cl;
    }

    public static class ClBean implements Serializable{
        /**
         * vailableQuantity : 1
         * cl_mingcheng : LG 121H
         * commerceId : ci1970000014
         * deliveryStartDate : 1478188800000
         * cl_gongsi : 中国石油天然气股份有限公司西北化工销售分公司
         * shippingMethod : 自提
         * deliveryEndDate : 1478448000000
         * cl_jine : 0
         * cl_fenlei : ABS
         * cl_jituan : LG
         * cl_cangku : 524仓库
         * cl_shuliang : 1
         */

        private String vailableQuantity;
        private String cl_mingcheng;
        private String commerceId;
        private long deliveryStartDate;
        private String cl_gongsi;
        private String shippingMethod;
        private long deliveryEndDate;
        private String cl_jine;
        private String cl_fenlei;
        private String cl_jituan;
        private String cl_cangku;
        private String cl_shuliang;

        public String getCl_shuliang() {
            return cl_shuliang;
        }

        public String getVailableQuantity() {
            return vailableQuantity;
        }

        public void setVailableQuantity(String vailableQuantity) {
            this.vailableQuantity = vailableQuantity;
        }

        public String getCl_mingcheng() {
            return cl_mingcheng;
        }

        public void setCl_mingcheng(String cl_mingcheng) {
            this.cl_mingcheng = cl_mingcheng;
        }

        public String getCommerceId() {
            return commerceId;
        }

        public void setCommerceId(String commerceId) {
            this.commerceId = commerceId;
        }

        public long getDeliveryStartDate() {
            return deliveryStartDate;
        }

        public void setDeliveryStartDate(long deliveryStartDate) {
            this.deliveryStartDate = deliveryStartDate;
        }

        public String getCl_gongsi() {
            return cl_gongsi;
        }

        public void setCl_gongsi(String cl_gongsi) {
            this.cl_gongsi = cl_gongsi;
        }

        public String getShippingMethod() {
            return shippingMethod;
        }

        public void setShippingMethod(String shippingMethod) {
            this.shippingMethod = shippingMethod;
        }

        public long getDeliveryEndDate() {
            return deliveryEndDate;
        }

        public void setDeliveryEndDate(long deliveryEndDate) {
            this.deliveryEndDate = deliveryEndDate;
        }

        public String getCl_jine() {
            return cl_jine;
        }

        public void setCl_jine(String cl_jine) {
            this.cl_jine = cl_jine;
        }

        public String getCl_fenlei() {
            return cl_fenlei;
        }

        public void setCl_fenlei(String cl_fenlei) {
            this.cl_fenlei = cl_fenlei;
        }

        public String getCl_jituan() {
            return cl_jituan;
        }

        public void setCl_jituan(String cl_jituan) {
            this.cl_jituan = cl_jituan;
        }

        public String getCl_cangku() {
            return cl_cangku;
        }

        public void setCl_cangku(String cl_cangku) {
            this.cl_cangku = cl_cangku;
        }

        public void setCl_shuliang(String cl_shuliang) {
            this.cl_shuliang = cl_shuliang;
        }



    }
}
