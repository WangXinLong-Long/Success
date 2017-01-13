package com.silianchuangye.sumao.success.fragments.homepage.auction.AuctionMVP.presenter;

import com.silianchuangye.sumao.success.fragments.PagerOneMVP.model.auctionListModel.AuctionListCallback;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.model.auctionListModel.AuctionListModel;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.model.auctionListModel.IAuctionListModel;
import com.silianchuangye.sumao.success.fragments.homepage.auction.AuctionMVP.model.AuctionSessionCallback;
import com.silianchuangye.sumao.success.fragments.homepage.auction.AuctionMVP.model.AuctionSessionModel;
import com.silianchuangye.sumao.success.fragments.homepage.auction.AuctionMVP.model.IAuctionSessionModel;
import com.silianchuangye.sumao.success.fragments.homepage.auction.AuctionMVP.view.IAuctionView;
import com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.openAuctionModel.openAuctionOrSealedBidModel.IOpenAuctionOrSealedBidModel;
import com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.openAuctionModel.openAuctionOrSealedBidModel.OpenAuctionOrSealedBidCallback;
import com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.openAuctionModel.openAuctionOrSealedBidModel.OpenAuctionOrSealedBidModel;

/**
 * Created by Jobs Created on 2016/12/26.
 */
public class AuctionPresenter {
    private IAuctionView auctionView;

    public AuctionPresenter(IAuctionView auctionView) {
        this.auctionView = auctionView;
    }

    //    获取竞拍列表数据
    public void getAuctionListResult(String pageNum,String updateOrLoad) {
        IAuctionListModel auctionListModel = new AuctionListModel( pageNum,updateOrLoad);
        auctionListModel.getAuctionListResult(new AuctionListCallback() {
            @Override
            public void callbackAuctionList(String result,String updateOrLoad) {
                auctionView.setAucionListResult(result,updateOrLoad);
            }
        });
    }
    //      请求公开竞拍或者密封报价的数据
    public void getOpenAuctionOrSealedBid(String type_way, String productId,String proType) {
        IOpenAuctionOrSealedBidModel openAuctionOrSealedBidModel = new OpenAuctionOrSealedBidModel( type_way,  productId, proType);
        openAuctionOrSealedBidModel.getOpenAuctionOrSealedBidResult(new OpenAuctionOrSealedBidCallback() {
            @Override
            public void callbackOpenAuctionOrSealedBidResult(String result,String type_way) {
                auctionView.setOpenAuctionOrSealedBidResult(result, type_way);
            }
        });

    }

//    竞拍专场
    public void getAuctionSessionResult(String pageNum, String sellerCompanyId, String _dynSessConf){
        IAuctionSessionModel auctionSessionModel = new AuctionSessionModel( pageNum,  sellerCompanyId,  _dynSessConf);
        auctionSessionModel.getAuctionSessionResult(new AuctionSessionCallback() {
            @Override
            public void callbackAuctionSession(String result) {
//                auctionView.setAuctionSessionResult(result);
            }
        });
    }
}
