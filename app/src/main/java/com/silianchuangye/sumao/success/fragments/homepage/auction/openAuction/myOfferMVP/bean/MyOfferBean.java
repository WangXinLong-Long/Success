package com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.myOfferMVP.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jobs Created on 2017/1/11.
 */
public class MyOfferBean implements Serializable {

    /**
     * myPrice : [{"bidPrice":56,"submitDate":1484041850367,"bidQut":47,"formatSequenceNum":"0050"},{"bidPrice":56,"submitDate":1483933027797,"bidQut":46,"formatSequenceNum":"0048"},{"bidPrice":8,"submitDate":1483713603330,"bidQut":5,"formatSequenceNum":"0002"},{"bidPrice":7,"submitDate":1483713548324,"bidQut":5,"formatSequenceNum":"0001"}]
     * mybidQut : 47
     */

    private String mybidQut;
    private List<MyPriceBean> myPrice;

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

    public static class MyPriceBean  implements Serializable{
        /**
         * bidPrice : 56
         * submitDate : 1484041850367
         * bidQut : 47
         * formatSequenceNum : 0050
         */

        private String bidPrice;
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

        public String getFormatSequenceNum() {
            return formatSequenceNum;
        }

        public void setFormatSequenceNum(String formatSequenceNum) {
            this.formatSequenceNum = formatSequenceNum;
        }
    }
}
