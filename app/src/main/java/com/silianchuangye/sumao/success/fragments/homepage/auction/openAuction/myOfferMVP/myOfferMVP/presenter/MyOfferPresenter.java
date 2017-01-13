package com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.myOfferMVP.myOfferMVP.presenter;

import com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.myOfferMVP.myOfferMVP.view.IMyOfferView;
import com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.myOfferMVP.myOfferMVP.withdrawalOfOfferModel.IWithdrawalOfOfferModel;
import com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.myOfferMVP.myOfferMVP.withdrawalOfOfferModel.WithdrawalOfOfferCallback;
import com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.myOfferMVP.myOfferMVP.withdrawalOfOfferModel.WithdrawalOfOfferModel;
import com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.openAuctionModel.myOfferResult.IMyOfferResultModel;
import com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.openAuctionModel.myOfferResult.MyOfferResultCallback;
import com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.openAuctionModel.myOfferResult.MyOfferResultModel;

/**
 * Created by Jobs Created on 2017/1/13.
 */
public class MyOfferPresenter {
    IMyOfferView myOfferView;

    public MyOfferPresenter(IMyOfferView myOfferView) {
        this.myOfferView = myOfferView;
    }

//    撤销报价
    public void getWithdrawalOfOffer(int position,String productId, String bidObjId, String _dynSessConf){
        IWithdrawalOfOfferModel withdrawalOfOfferModel = new WithdrawalOfOfferModel(position, productId,  bidObjId,  _dynSessConf);
        withdrawalOfOfferModel.getWithdrawalOfOfferResult(new WithdrawalOfOfferCallback() {
            @Override
            public void callbackWithdrawalOfOfferModel(String result,int position) {
                myOfferView.setMyOfferWithdrawalOfOfferResult(result,position);
            }
        });
    }

    //    获取我的报价
    public void getMyOfferResult(String _dynSessConf, String productId, String type, String bianhao) {
        IMyOfferResultModel myOfferResultModel = new MyOfferResultModel(_dynSessConf, productId, type, bianhao);
        myOfferResultModel.getMyOfferResult(new MyOfferResultCallback() {
            @Override
            public void callbackMyOfferResult(String result, boolean offer) {

            }

            @Override
            public void callbackMyOfferResult(String result, String bianhao) {
                myOfferView.setMyOfferResult(result, bianhao);
            }
        });
    }
}
