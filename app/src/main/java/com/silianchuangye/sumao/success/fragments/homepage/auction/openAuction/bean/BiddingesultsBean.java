package com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.bean;

import java.io.Serializable;

/**
 * Created by Jobs Created on 2017/1/5.
 */
public class BiddingesultsBean implements Serializable{

    /**
     * bidPrice : 2
     * dealQut : 100
     * totalAmount : 200
     * state : QUOTED
     * date : 1483519879066
     * orderId : 10241700000005
     */

    private String bidPrice;
    private String dealQut;
    private String totalAmount;
    private String state;
    private String date;
    private String orderId;


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(String bidPrice) {
        this.bidPrice = bidPrice;
    }

    public String getDealQut() {
        return dealQut;
    }

    public void setDealQut(String dealQut) {
        this.dealQut = dealQut;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
