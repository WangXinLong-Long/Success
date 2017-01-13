package com.silianchuangye.sumao.success.fragments.homepage.auction.AuctionMVP.view;

/**
 * Created by Jobs Created on 2016/12/26.
 */
public interface IAuctionView {

//    获取刷新请求下来的数据
    void setAucionListResult(String result,String updateOrLoad);

//    获取竞拍详情的数据
    void setOpenAuctionOrSealedBidResult(String result,String type_way);

//    获取竞拍专场的数据
//    void setAuctionSessionResult(String result);
}
