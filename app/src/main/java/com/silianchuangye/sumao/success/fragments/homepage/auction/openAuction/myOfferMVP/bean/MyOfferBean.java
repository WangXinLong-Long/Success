package com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.myOfferMVP.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jobs Created on 2017/1/11.
 */
public class MyOfferBean implements Serializable {


    private List<MyPriceBean> myPrice;
private  String mybidQut;

    public String getMybidQut() {
        return mybidQut;
    }

    public void setMybidQut(String mybidQut) {
        this.mybidQut = mybidQut;
    }

    public List<MyPriceBean> getMyPrice() {
        return myPrice;
    }

    public void setMyPrice(List<MyPriceBean> myPrice) {
        this.myPrice = myPrice;
    }

    public static class MyPriceBean {
        /**
         * bidPrice : 12000
         * bidObjId : 19900001
         * submitDate : 1484301038410
         * bidQut : 1
         * formatSequenceNum : 0009
         */

        private String bidPrice;
        private String bidObjId;
        private String submitDate;
        private String bidQut;
        private String formatSequenceNum;

        public String getBidPrice() {
            return bidPrice;
        }

        public void setBidPrice(String bidPrice) {
            this.bidPrice = bidPrice;
        }

        public String getSubmitDate() {
            return submitDate;
        }

        public void setSubmitDate(String submitDate) {
            this.submitDate = submitDate;
        }

        public String getBidQut() {
            return bidQut;
        }

        public void setBidQut(String bidQut) {
            this.bidQut = bidQut;
        }

        public String getBidObjId() {
            return bidObjId;
        }

        public void setBidObjId(String bidObjId) {
            this.bidObjId = bidObjId;
        }

        public String getFormatSequenceNum() {
            return formatSequenceNum;
        }

        public void setFormatSequenceNum(String formatSequenceNum) {
            this.formatSequenceNum = formatSequenceNum;
        }
    }
}
