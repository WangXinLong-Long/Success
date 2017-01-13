package com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.openAuctionView;

/**
 * Created by Jobs Created on 2016/12/26.
 */
public interface IOpenAuctionView {

    //    公开竞拍报价
    void getOpenAuctionResult(String result);

    //    密封竞拍报价
    void getSealedBidResult(String result);

    //    获取银行列表
    void setBankListResult(String result);

    //    支付保证金接口
    void setPaymentSecurityResult(String result);

    //    已支付保证金
    void setAmountPaidedResult(String result);

    //    获取竞拍结果
    void setAuctionResultResult(String result);

    //    竞拍的状态
    void setStateOfPublicBiddingResult(String result,boolean needToPayTheMargin);

    //    支付完成后返回的信息
    void getPaymentResult(String result, String orderIds);
    //    获取订单详情
    void getOpenAuctionOrderDetail(String result);

//    我的报价
    void setMyOfferResult(String result,boolean offer);

//    实时行情
    void setRealTimeQuotesResult(String result,boolean offer);

//    点击参加竞拍
    void setParticipateInTheAuction(String result);
}
