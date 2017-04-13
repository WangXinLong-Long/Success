package com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.openAuctionPresenter;

import com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.openAuctionModel.OpenBiddingModel.IOpenBiddingModel;
import com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.openAuctionModel.OpenBiddingModel.OpenBiddingCallback;
import com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.openAuctionModel.OpenBiddingModel.OpenBiddingModel;
import com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.openAuctionModel.amountPaidedModel.AmountPaidedCallback;
import com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.openAuctionModel.amountPaidedModel.AmountPaidedModel;
import com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.openAuctionModel.amountPaidedModel.IAmountPaidedModel;
import com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.openAuctionModel.auctionResultModel.AuctionResultCallback;
import com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.openAuctionModel.auctionResultModel.AuctionResultModel;
import com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.openAuctionModel.auctionResultModel.IAuctionResultModel;
import com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.openAuctionModel.getPublicAuctionOrderDetailsModel.GetPublicAuctionOrderDetailsCallback;
import com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.openAuctionModel.getPublicAuctionOrderDetailsModel.GetPublicAuctionOrderDetailsModel;
import com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.openAuctionModel.getPublicAuctionOrderDetailsModel.IGetPublicAuctionOrderDetailsModel;
import com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.openAuctionModel.myOfferResult.IMyOfferResultModel;
import com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.openAuctionModel.myOfferResult.MyOfferResultCallback;
import com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.openAuctionModel.myOfferResult.MyOfferResultModel;
import com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.openAuctionModel.participateInTheAuctionModel.IParticipateInTheAuctionModel;
import com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.openAuctionModel.participateInTheAuctionModel.ParticipateInTheAuctionCallback;
import com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.openAuctionModel.participateInTheAuctionModel.ParticipateInTheAuctionModel;
import com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.openAuctionModel.paymentSecurityModel.IPaymentAuctionSecurityModel;
import com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.openAuctionModel.paymentSecurityModel.PaymentSecurityCallback;
import com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.openAuctionModel.paymentSecurityModel.PaymentSecurityModel;
import com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.openAuctionModel.realTimeQuotes.IRealTimeQuotesModel;
import com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.openAuctionModel.realTimeQuotes.RealTimeQuotesCallback;
import com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.openAuctionModel.realTimeQuotes.RealTimeQuotesModel;
import com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.openAuctionModel.sealedBidModel.ISealedBidModel;
import com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.openAuctionModel.sealedBidModel.SealedBidCallback;
import com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.openAuctionModel.sealedBidModel.SealedBidModel;
import com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.openAuctionModel.stateOfPublicBiddingModel.IStateOfPublicBiddingModel;
import com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.openAuctionModel.stateOfPublicBiddingModel.StateOfPublicBiddingCallback;
import com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.openAuctionModel.stateOfPublicBiddingModel.StateOfPublicBiddingModel;
import com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.openAuctionView.IOpenAuctionView;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.PayMentsOrderMVP.model.getListOfBankModel.GetListOfBankCallback;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.PayMentsOrderMVP.model.getListOfBankModel.GetListOfBankModel;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.PayMentsOrderMVP.model.getListOfBankModel.IGetListOfBankModel;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.PayMentsOrderMVP.model.paymentModel.IPaymentModel;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.PayMentsOrderMVP.model.paymentModel.PaymentCallback;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.PayMentsOrderMVP.model.paymentModel.PaymentModel;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.model.preSaleBondModel.IPreSaleBondModel;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.model.preSaleBondModel.PreSaleBondCallback;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.model.preSaleBondModel.PreSaleBondModel;

/**
 * Created by Jobs Created on 2016/12/26.
 */
public class OpenAuctionPresenter {

    private IOpenAuctionView openAuctionView;

    public OpenAuctionPresenter(IOpenAuctionView openAuctionView) {
        this.openAuctionView = openAuctionView;
    }

    //    公开竞拍报价
    public void getOpenBiddingResult(String productId, String bidPrice, String bidQuantity, String _dynSessConf,String qigou) {
        IOpenBiddingModel openBiddingModel = new OpenBiddingModel(productId, bidPrice, bidQuantity, _dynSessConf,qigou);
        openBiddingModel.getOpenBiddingResult(new OpenBiddingCallback() {
            @Override
            public void callbackOpenBidding(String result) {
                openAuctionView.getOpenAuctionResult(result);
            }
        });
    }

    //    密封竞拍报价
    public void getSealedBidResult(String productId, String bidPrice, String bidQuantity, String _dynSessConf,String qigou) {
        ISealedBidModel sealedBidModel = new SealedBidModel(productId, bidPrice, bidQuantity, _dynSessConf,qigou);
        sealedBidModel.getSealedBidResult(new SealedBidCallback() {
            @Override
            public void callbackSealedBid(String result) {
                openAuctionView.getSealedBidResult(result);
            }
        });
    }


    //    支付保证金
    public void getPaymentSecurityResult(String depositAmount, String productId, String paymentPlatform, String _dynSessConf) {
        IPaymentAuctionSecurityModel paymentAuctionSecurityModel = new PaymentSecurityModel(depositAmount, productId, paymentPlatform, _dynSessConf);
        paymentAuctionSecurityModel.getPaymentSecurityResult(new PaymentSecurityCallback() {
            @Override
            public void callbackPaymentSecurity(String result) {
                openAuctionView.setPaymentSecurityResult(result);
            }
        });
    }

    //    获取已支付保证金金额
    public void getAmountPaidedResult(String productId, String _dynSessConf) {
        IAmountPaidedModel amountPaidedModel = new AmountPaidedModel(productId, _dynSessConf);
        amountPaidedModel.getAmountPaidedResult(new AmountPaidedCallback() {
            @Override
            public void callbackAmountPaided(String result) {
                openAuctionView.setAmountPaidedResult(result);
            }
        });
    }


    //    获取竞拍结果
    public void getAuctionResult(String productId, String _dynSessConf) {
        IAuctionResultModel auctionResultModel = new AuctionResultModel(productId, _dynSessConf);
        auctionResultModel.getAuctionResultResult(new AuctionResultCallback() {
            @Override
            public void callbackAuctionResult(String result) {
                openAuctionView.setAuctionResultResult(result);
            }
        });
    }


    //    竞拍的状态
    public void getStateOfPublicBidding(boolean needToPayTheMargin, String typeWay, String productId, String _dynSessConf) {
        IStateOfPublicBiddingModel stateOfPublicBiddingModel = new StateOfPublicBiddingModel(needToPayTheMargin, typeWay, productId, _dynSessConf);
        stateOfPublicBiddingModel.getStateOfPublicBiddingResult(new StateOfPublicBiddingCallback() {
            @Override
            public void callbackStateOfPublicBiddingResult(String result, boolean needToPayTheMargin) {
                openAuctionView.setStateOfPublicBiddingResult(result, needToPayTheMargin);
            }
        });
    }

    //   获取支付保证金时的银行列表
    public void getBankListResult(String productId) {
        IPreSaleBondModel preSaleBondModel = new PreSaleBondModel(productId);
        preSaleBondModel.getPresaleBondResult(new PreSaleBondCallback() {
            @Override
            public void callbakcPreSale(String result) {
                openAuctionView.setBankListResult(result);
            }
        });
    }

    //    从服务器获取银行列表
    public void getBankListFromService(String order, String roderIds) {
        IGetListOfBankModel getListOfBankModel = new GetListOfBankModel(order, roderIds);
        getListOfBankModel.getListOfBankResult(new GetListOfBankCallback() {
            @Override
            public void callbackListOfBank(String result) {
                openAuctionView.setBankListResult(result);
            }
        });
    }

    //      获取支付后信息
    public void getPaymentInfoFromService(String orderIds, String platform, String _dynSessConf) {
        IPaymentModel paymentModel = new PaymentModel(orderIds, platform, _dynSessConf);
        paymentModel.getPaymentResult(new PaymentCallback() {
            @Override
            public void callbakcPayment(String result, String orderIds) {
                openAuctionView.getPaymentResult(result, orderIds);
            }
        });
    }

    //    获取支付后订单详情
    public void getOpenAuctionOrderDetail(String orderId) {
        IGetPublicAuctionOrderDetailsModel getPublicAuctionOrderDetailsModel = new GetPublicAuctionOrderDetailsModel(orderId);
        getPublicAuctionOrderDetailsModel.getAuctionOrderDetailsResult(new GetPublicAuctionOrderDetailsCallback() {
            @Override
            public void callbackGetPublicAuctionOrderDetails(String result) {
                openAuctionView.getOpenAuctionOrderDetail(result);
            }
        });
    }

    //    获取我的报价
    public void getMyOfferResult(String _dynSessConf, String productId, String type, boolean offer) {
        IMyOfferResultModel myOfferResultModel = new MyOfferResultModel(_dynSessConf, productId, type, offer);
        myOfferResultModel.getMyOfferResult(new MyOfferResultCallback() {
            @Override
            public void callbackMyOfferResult(String result, boolean offer) {
                openAuctionView.setMyOfferResult(result, offer);
            }

            @Override
            public void callbackMyOfferResult(String result, String bianhao) {

            }
        });
    }


    /**
     * //    实时行情
     *
     * @param productId
     * @param offer     是否需要报价
     */
    public void getRealTimeQuotes(String productId, boolean offer) {
        IRealTimeQuotesModel realTimeQuotesModel = new RealTimeQuotesModel(productId, offer);
        realTimeQuotesModel.getRealTimeQuotesResult(new RealTimeQuotesCallback() {
            @Override
            public void callbackRealTimeQuotes(String result, boolean offer) {
                openAuctionView.setRealTimeQuotesResult(result, offer);
            }
        });
    }

    //    参与竞拍接口
    public void getParticipateInTheAuction(String productId, String skuId, String _dynSessConf) {
        IParticipateInTheAuctionModel participateInTheAuctionModel = new ParticipateInTheAuctionModel(productId, skuId, _dynSessConf);
        participateInTheAuctionModel.getParticipateInTheAuctionResult(new ParticipateInTheAuctionCallback() {
            @Override
            public void callbackParticipateInTheAuction(String result) {
                openAuctionView.setParticipateInTheAuction(result);
            }
        });
    }
}
