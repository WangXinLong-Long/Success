package com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.preSell.presellallFragmentMVP.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jobs Created on 2016/12/28.
 */
public class PreSellAllBean implements Serializable {


    /**
     * count : 862
     * order : [{"shippingGroupState":"REMOVED","cl":[{"cl_mingcheng":"中原PPR-MT20","cl_gongsiId":"1700025","deliveryStartDate":1482422400000,"cl_gongsi":"北京四联创业化工集团有限公司","deliveryEndDate":1488211200000,"cl_jine":1.8,"cl_id":"prod1450010","cl_amount":1.8,"cl_fenlei":"中原PPR-MT20","cl_jituan":"中石化","cl_cangku":"上游配送库-西北","cl_shuliang":1}],"state":"REMOVED","submittedDate":1483160035734,"owner":"邓东山","type":"fixedPricingOrder","cl_fangshi":"自提","orderId":"10237800000001"},{"shippingGroupState":"INITIAL","cl":[{"cl_mingcheng":"茂名F501","cl_gongsiId":"100001","deliveryStartDate":1482681600000,"cl_gongsi":"北京橡胶","deliveryEndDate":1483113600000,"cl_jine":13,"cl_id":"prod1470010","cl_amount":13,"cl_fenlei":"茂名F501","cl_jituan":"中石化","cl_cangku":"太原库","cl_shuliang":1}],"state":"SUBMITTED","submittedDate":1483113607215,"owner":"邓东山","type":"sealedAuctionOrder","cl_fangshi":"自提","orderId":"10233900000009"},{"shippingGroupState":"INITIAL","cl":[{"cl_mingcheng":"SABIC 511MK10T","cl_gongsiId":"100001","deliveryStartDate":1477497600000,"cl_gongsi":"北京橡胶","deliveryEndDate":1485792000000,"cl_jine":222,"cl_id":"prod940003","cl_amount":222,"cl_fenlei":"SABIC 511MK10T","cl_jituan":"sabic","cl_cangku":"上游配送库-华北","cl_shuliang":1}],"state":"QUOTED","submittedDate":1483083740991,"owner":"邓东山","type":"fixedPricingOrder","cl_fangshi":"卖家配送","orderId":"10237300000002"},{"shippingGroupState":"REMOVED","cl":[{"cl_mingcheng":"SABIC 511MK10T","cl_gongsiId":"100001","deliveryStartDate":1480521600000,"cl_gongsi":"北京橡胶","deliveryEndDate":1483113600000,"cl_jine":10,"cl_id":"prod1180009","cl_amount":10,"cl_fenlei":"SABIC 511MK10T","cl_jituan":"sabic","cl_cangku":"上游配送库-东北","cl_shuliang":1}],"state":"REMOVED","submittedDate":1483082859589,"owner":"邓东山","type":"fixedPricingOrder","cl_fangshi":"自提","orderId":"10235600000045"},{"shippingGroupState":"REMOVED","cl":[{"cl_mingcheng":"中原PPR-MT20","cl_gongsiId":"100001","deliveryStartDate":1482422400000,"cl_gongsi":"北京橡胶","deliveryEndDate":1483113600000,"cl_jine":10,"cl_id":"prod1450014","cl_amount":10,"cl_fenlei":"中原PPR-MT20","cl_jituan":"中石化","cl_cangku":"保定库","cl_shuliang":1}],"state":"REMOVED","submittedDate":1483082785619,"owner":"邓东山","type":"fixedPricingOrder","cl_fangshi":"自提","orderId":"10235600000044"},{"shippingGroupState":"REMOVED","cl":[{"cl_mingcheng":"中原PPR-MT20","cl_gongsiId":"100001","deliveryStartDate":1482422400000,"cl_gongsi":"北京橡胶","deliveryEndDate":1483113600000,"cl_jine":10,"cl_id":"prod1450014","cl_amount":10,"cl_fenlei":"中原PPR-MT20","cl_jituan":"中石化","cl_cangku":"保定库","cl_shuliang":1}],"state":"REMOVED","submittedDate":1483081272151,"owner":"邓东山","type":"fixedPricingOrder","cl_fangshi":"自提","orderId":"10237100000005"},{"shippingGroupState":"REMOVED","cl":[{"cl_mingcheng":"中原PPR-MT20","cl_gongsiId":"100001","deliveryStartDate":1482422400000,"cl_gongsi":"北京橡胶","deliveryEndDate":1483113600000,"cl_jine":10,"cl_id":"prod1450014","cl_amount":10,"cl_fenlei":"中原PPR-MT20","cl_jituan":"中石化","cl_cangku":"保定库","cl_shuliang":1}],"state":"REMOVED","submittedDate":1483077003272,"owner":"邓东山","type":"fixedPricingOrder","cl_fangshi":"自提","orderId":"10235600000042"},{"shippingGroupState":"REMOVED","cl":[{"cl_mingcheng":"中原PPR-MT20","cl_gongsiId":"100001","deliveryStartDate":1482422400000,"cl_gongsi":"北京橡胶","deliveryEndDate":1483113600000,"cl_jine":10,"cl_id":"prod1450014","cl_amount":70,"cl_fenlei":"中原PPR-MT20","cl_jituan":"中石化","cl_cangku":"保定库","cl_shuliang":7}],"state":"REMOVED","submittedDate":1483076987269,"owner":"邓东山","type":"fixedPricingOrder","cl_fangshi":"自提","orderId":"10235600000041"}]
     * pages : 108
     * info : sucess
     */

    private String count;
    private String pages;
    private String info;
    private List<OrderBean> order;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<OrderBean> getOrder() {
        return order;
    }

    public void setOrder(List<OrderBean> order) {
        this.order = order;
    }

    public static class OrderBean implements Serializable {
        /**
         * shippingGroupState : REMOVED
         * cl : [{"cl_mingcheng":"中原PPR-MT20","cl_gongsiId":"1700025","deliveryStartDate":1482422400000,"cl_gongsi":"北京四联创业化工集团有限公司","deliveryEndDate":1488211200000,"cl_jine":1.8,"cl_id":"prod1450010","cl_amount":1.8,"cl_fenlei":"中原PPR-MT20","cl_jituan":"中石化","cl_cangku":"上游配送库-西北","cl_shuliang":1}]
         * state : REMOVED
         * submittedDate : 1483160035734
         * owner : 邓东山
         * type : fixedPricingOrder
         * cl_fangshi : 自提
         * orderId : 10237800000001
         */

        private String shippingGroupState;
        private String state;
        private String submittedDate;
        private String owner;
        private String type;
        private String cl_fangshi;
        private String orderId;
        private List<ClBean> cl;

        public String getShippingGroupState() {
            return shippingGroupState;
        }

        public void setShippingGroupState(String shippingGroupState) {
            this.shippingGroupState = shippingGroupState;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getOwner() {
            return owner;
        }

        public void setOwner(String owner) {
            this.owner = owner;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCl_fangshi() {
            return cl_fangshi;
        }

        public void setCl_fangshi(String cl_fangshi) {
            this.cl_fangshi = cl_fangshi;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public List<ClBean> getCl() {
            return cl;
        }

        public void setCl(List<ClBean> cl) {
            this.cl = cl;
        }

        public String getSubmittedDate() {
            return submittedDate;
        }

        public void setSubmittedDate(String submittedDate) {
            this.submittedDate = submittedDate;
        }

        public static class ClBean implements Serializable {
            /**
             * cl_mingcheng : 中原PPR-MT20
             * cl_gongsiId : 1700025
             * deliveryStartDate : 1482422400000
             * cl_gongsi : 北京四联创业化工集团有限公司
             * deliveryEndDate : 1488211200000
             * cl_jine : 1.8
             * cl_id : prod1450010
             * cl_amount : 1.8
             * cl_fenlei : 中原PPR-MT20
             * cl_jituan : 中石化
             * cl_cangku : 上游配送库-西北
             * cl_shuliang : 1
             */

            private String cl_mingcheng;
            private String cl_gongsiId;
            private String deliveryStartDate;
            private String cl_gongsi;
            private String deliveryEndDate;
            private String cl_jine;
            private String cl_id;
            private String cl_amount;
            private String cl_fenlei;
            private String cl_jituan;
            private String cl_cangku;
            private String cl_shuliang;
            private String paymentEndDate;

            public String getPaymentEndDate() {
                return paymentEndDate;
            }

            public void setPaymentEndDate(String paymentEndDate) {
                this.paymentEndDate = paymentEndDate;
            }

            public String getCl_mingcheng() {
                return cl_mingcheng;
            }

            public void setCl_mingcheng(String cl_mingcheng) {
                this.cl_mingcheng = cl_mingcheng;
            }

            public String getCl_gongsiId() {
                return cl_gongsiId;
            }

            public void setCl_gongsiId(String cl_gongsiId) {
                this.cl_gongsiId = cl_gongsiId;
            }

            public String getCl_gongsi() {
                return cl_gongsi;
            }

            public void setCl_gongsi(String cl_gongsi) {
                this.cl_gongsi = cl_gongsi;
            }

            public String getCl_id() {
                return cl_id;
            }

            public void setCl_id(String cl_id) {
                this.cl_id = cl_id;
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

            public String getDeliveryStartDate() {
                return deliveryStartDate;
            }

            public void setDeliveryStartDate(String deliveryStartDate) {
                this.deliveryStartDate = deliveryStartDate;
            }

            public String getDeliveryEndDate() {
                return deliveryEndDate;
            }

            public void setDeliveryEndDate(String deliveryEndDate) {
                this.deliveryEndDate = deliveryEndDate;
            }

            public String getCl_jine() {
                return cl_jine;
            }

            public void setCl_jine(String cl_jine) {
                this.cl_jine = cl_jine;
            }

            public String getCl_amount() {
                return cl_amount;
            }

            public void setCl_amount(String cl_amount) {
                this.cl_amount = cl_amount;
            }

            public String getCl_shuliang() {
                return cl_shuliang;
            }

            public void setCl_shuliang(String cl_shuliang) {
                this.cl_shuliang = cl_shuliang;
            }
        }
    }
}
