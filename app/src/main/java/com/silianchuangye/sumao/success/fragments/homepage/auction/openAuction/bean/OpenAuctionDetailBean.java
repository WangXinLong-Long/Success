package com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.bean;

import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.preSell.presellallFragmentMVP.bean.PreSellAllBean;

import java.io.Serializable;

/**
 * Created by Jobs Created on 2017/1/6.
 */
public class OpenAuctionDetailBean implements Serializable {

    /**
     * order : {"shippingGroupState":"INITIAL","price":1.88,"contract":"/crsdocroot","cl":[],"submittedDate":1483609818203,"state":"QUOTED","owner":"邓东山","type":"openAuctionOrder","cl_fangshi":"自提","orderId":"10244700000032"}
     */

    private PreSellAllBean.OrderBean order;

    public PreSellAllBean.OrderBean getOrder() {
        return order;
    }

    public void setOrder(PreSellAllBean.OrderBean order) {
        this.order = order;
    }


}
