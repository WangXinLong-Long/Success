package com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jobs Created on 2017/1/9.
 */
public class RealTimeMardet implements Serializable {

    private List<AuctionPricelistBean> auctionPricelist;

    public List<AuctionPricelistBean> getAuctionPricelist() {
        return auctionPricelist;
    }

    public void setAuctionPricelist(List<AuctionPricelistBean> auctionPricelist) {
        this.auctionPricelist = auctionPricelist;
    }

    public static class AuctionPricelistBean implements Serializable {
        /**
         * bidPrice : 25
         * submitDate : 1483963166162
         * bidQut : 3
         * buyerId : 210026
         * formatSequenceNum : 0003
         */

        private String bidPrice;
        private String submitDate;
        private String dealQut;
        private String bidQut;
        private String buyerId;
        private String formatSequenceNum;

        public String getDealQut() {
            return dealQut;
        }

        public void setDealQut(String dealQut) {
            this.dealQut = dealQut;
        }

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

        public String getBuyerId() {
            return buyerId;
        }

        public void setBuyerId(String buyerId) {
            this.buyerId = buyerId;
        }

        public String getFormatSequenceNum() {
            return formatSequenceNum;
        }

        public void setFormatSequenceNum(String formatSequenceNum) {
            this.formatSequenceNum = formatSequenceNum;
        }
    }
}
