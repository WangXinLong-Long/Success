package com.silianchuangye.sumao.success.fragments.homepage.groupbuying.GroupBuyingMVP.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jobs Created on 2017/1/11.
 */
public class GroupBuyingListBean implements Serializable {

    /**
     * allCount : 187
     * prod : [{"groupPercent":"0.8571428571","result":"sold","cl_mingcheng":"巴陵YH-791","cl_groupQuantity":40,"cl_gongsi":"北京橡胶","type":"groupProduct","cl_jine":5,"id":"prod1560077","cl_zongliang":70,"groupStartDate":1484064000000,"groupEndDate":1484841600000,"cl_cangku":"中央储备粮临沂直属库","sumAddQty":"60.0","runing":"end"},{"groupPercent":"0.8571428571","result":"sold","cl_mingcheng":"兰化7042","cl_groupQuantity":40,"cl_gongsi":"北京橡胶","type":"groupProduct","cl_jine":5,"id":"prod1560076","cl_zongliang":70,"groupStartDate":1484064000000,"groupEndDate":1484323200000,"cl_cangku":"上游配送库-西南","sumAddQty":"60.0","runing":"end"},{"groupPercent":"0.8571428571","result":"gone","cl_mingcheng":"巴陵YH-792E","cl_groupQuantity":40,"cl_gongsi":"北京橡胶","type":"groupProduct","cl_jine":5,"id":"prod1560075","cl_zongliang":70,"groupStartDate":1484064000000,"groupEndDate":1484928000000,"cl_cangku":"上游配送库-西北","sumAddQty":"60.0","runing":"end"},{"groupPercent":"0.54","cl_mingcheng":"大庆PA14D合格品","cl_groupQuantity":10,"cl_gongsi":"北京橡胶","type":"groupProduct","cl_jine":1.25,"id":"prod1560030","cl_zongliang":100,"groupStartDate":1483632000000,"groupEndDate":1485792000000,"cl_cangku":"上游配送库-西南","sumAddQty":"54.0","runing":"true"},{"groupPercent":"0.0","result":"gone","cl_mingcheng":"燕山1301","cl_groupQuantity":60,"cl_gongsi":"北京橡胶","type":"groupProduct","cl_jine":10,"id":"prod1470009","cl_zongliang":100,"groupStartDate":1482662040000,"groupEndDate":1482662400000,"cl_cangku":"天津化轻库","sumAddQty":"0.0","runing":"end"},{"groupPercent":"1.0","result":"sold","cl_mingcheng":"抚顺7028","cl_groupQuantity":60,"cl_gongsi":"北京橡胶","type":"groupProduct","cl_jine":10,"id":"prod1470007","cl_zongliang":100,"groupStartDate":1482661500000,"groupEndDate":1483113600000,"cl_cangku":"保定库","sumAddQty":"100.0","runing":"end"},{"groupPercent":"0.0","result":"gone","cl_mingcheng":"巴陵YH-791-FP","cl_groupQuantity":60,"cl_gongsi":"北京橡胶","type":"groupProduct","cl_jine":10,"id":"prod1470006","cl_zongliang":100,"groupStartDate":1483027200000,"groupEndDate":1483113600000,"cl_cangku":"临沂华派克物流","sumAddQty":"0.0","runing":"end"},{"groupPercent":"0.0","result":"gone","cl_mingcheng":"巴陵YH-791","cl_groupQuantity":60,"cl_gongsi":"北京橡胶","type":"groupProduct","cl_jine":10,"id":"prod1470005","cl_zongliang":100,"groupStartDate":1482661200000,"groupEndDate":1483182000000,"cl_cangku":"中央储备粮临沂直属库","sumAddQty":"0.0","runing":"end"},{"groupPercent":"0.0","result":"gone","cl_mingcheng":"巴陵YH-791","cl_groupQuantity":50,"cl_gongsi":"北京橡胶","type":"groupProduct","cl_jine":10,"id":"prod1460007","cl_zongliang":100,"groupStartDate":1482548400000,"groupEndDate":1483113600000,"cl_cangku":"上游配送库-西南","sumAddQty":"0.0","runing":"end"},{"groupPercent":"0.0","cl_mingcheng":"茂名F503","cl_groupQuantity":50,"cl_gongsi":"北京橡胶","type":"groupProduct","cl_jine":10,"id":"prod1460006","cl_zongliang":100,"groupStartDate":1483027200000,"groupEndDate":1485792000000,"cl_cangku":"上游配送库-西北","sumAddQty":"0.0","runing":"true"}]
     */

    private String allCount;
    private List<ProdBean> prod;


    public List<ProdBean> getProd() {
        return prod;
    }

    public void setProd(List<ProdBean> prod) {
        this.prod = prod;
    }

    public static class ProdBean implements Serializable{
        /**
         * groupPercent : 0.8571428571
         * result : sold
         * cl_mingcheng : 巴陵YH-791
         * cl_groupQuantity : 40
         * cl_gongsi : 北京橡胶
         * type : groupProduct
         * cl_jine : 5
         * id : prod1560077
         * cl_zongliang : 70
         * groupStartDate : 1484064000000
         * groupEndDate : 1484841600000
         * cl_cangku : 中央储备粮临沂直属库
         * sumAddQty : 60.0
         * runing : end
         */

        private String groupPercent;
        private String result;
        private String cl_mingcheng;
        private String cl_groupQuantity;
        private String cl_gongsi;
        private String type;
        private String cl_jine;
        private String id;
        private String cl_zongliang;
        private String groupStartDate;
        private String groupEndDate;
        private String cl_cangku;
        private String sumAddQty;
        private String runing;

        public String getGroupPercent() {
            return groupPercent;
        }

        public void setGroupPercent(String groupPercent) {
            this.groupPercent = groupPercent;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public String getCl_mingcheng() {
            return cl_mingcheng;
        }

        public void setCl_mingcheng(String cl_mingcheng) {
            this.cl_mingcheng = cl_mingcheng;
        }

        public String getCl_gongsi() {
            return cl_gongsi;
        }

        public void setCl_gongsi(String cl_gongsi) {
            this.cl_gongsi = cl_gongsi;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCl_cangku() {
            return cl_cangku;
        }

        public void setCl_cangku(String cl_cangku) {
            this.cl_cangku = cl_cangku;
        }

        public String getSumAddQty() {
            return sumAddQty;
        }

        public void setSumAddQty(String sumAddQty) {
            this.sumAddQty = sumAddQty;
        }

        public String getRuning() {
            return runing;
        }

        public void setRuning(String runing) {
            this.runing = runing;
        }

        public String getCl_groupQuantity() {
            return cl_groupQuantity;
        }

        public void setCl_groupQuantity(String cl_groupQuantity) {
            this.cl_groupQuantity = cl_groupQuantity;
        }

        public String getCl_jine() {
            return cl_jine;
        }

        public void setCl_jine(String cl_jine) {
            this.cl_jine = cl_jine;
        }

        public String getCl_zongliang() {
            return cl_zongliang;
        }

        public void setCl_zongliang(String cl_zongliang) {
            this.cl_zongliang = cl_zongliang;
        }

        public String getGroupStartDate() {
            return groupStartDate;
        }

        public void setGroupStartDate(String groupStartDate) {
            this.groupStartDate = groupStartDate;
        }

        public String getGroupEndDate() {
            return groupEndDate;
        }

        public void setGroupEndDate(String groupEndDate) {
            this.groupEndDate = groupEndDate;
        }
    }
}
