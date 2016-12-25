package com.silianchuangye.sumao.success.fragments.myPlasticTrade.PhysicalDistributionManagement.logistics.createLogisticsMVP.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jobs Created on 2016/12/21.
 */
public class CreateLogisticBean implements Serializable {

    private List<OrderBean> order;

    public List<OrderBean> getOrder() {
        return order;
    }

    public void setOrder(List<OrderBean> order) {
        this.order = order;
    }

    public static class OrderBean {
        /**
         * id : 10095200000003
         * cl_gongsiId : 200021
         * cl : [{"vailableQuantity":1.666,"cl_mingcheng":"上海041","commerceId":"ci1966000002","deliveryStartDate":1477497600000,"cl_gongsi":"中国石油天然气股份有限公司西北化工销售分公司","shippingMethod":"卖家配送","deliveryEndDate":1477843200000,"cl_jine":0,"cl_fenlei":"HDPE","cl_jituan":"中石化","cl_cangku":"524仓库","cl_shuliang":1.666}]
         * submittedDate : 1477553417114
         * cl_gongsi : 中国石油天然气股份有限公司西北化工销售分公司
         * firstName : 陈小俊1
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

        public static class ClBean {
            /**
             * vailableQuantity : 1.666
             * cl_mingcheng : 上海041
             * commerceId : ci1966000002
             * deliveryStartDate : 1477497600000
             * cl_gongsi : 中国石油天然气股份有限公司西北化工销售分公司
             * shippingMethod : 卖家配送
             * deliveryEndDate : 1477843200000
             * cl_jine : 0
             * cl_fenlei : HDPE
             * cl_jituan : 中石化
             * cl_cangku : 524仓库
             * cl_shuliang : 1.666
             */

            private double vailableQuantity;
            private String cl_mingcheng;
            private String commerceId;
            private long deliveryStartDate;
            private String cl_gongsi;
            private String shippingMethod;
            private long deliveryEndDate;
            private int cl_jine;
            private String cl_fenlei;
            private String cl_jituan;
            private String cl_cangku;
            private double cl_shuliang;

            public double getVailableQuantity() {
                return vailableQuantity;
            }

            public void setVailableQuantity(double vailableQuantity) {
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

            public int getCl_jine() {
                return cl_jine;
            }

            public void setCl_jine(int cl_jine) {
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

            public double getCl_shuliang() {
                return cl_shuliang;
            }

            public void setCl_shuliang(double cl_shuliang) {
                this.cl_shuliang = cl_shuliang;
            }
        }
    }
}
