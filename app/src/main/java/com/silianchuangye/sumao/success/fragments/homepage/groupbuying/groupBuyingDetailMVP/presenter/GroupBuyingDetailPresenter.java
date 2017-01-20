package com.silianchuangye.sumao.success.fragments.homepage.groupbuying.groupBuyingDetailMVP.presenter;

import com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.openAuctionModel.paymentSecurityModel.IPaymentAuctionSecurityModel;
import com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.openAuctionModel.paymentSecurityModel.PaymentSecurityCallback;
import com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.openAuctionModel.paymentSecurityModel.PaymentSecurityModel;
import com.silianchuangye.sumao.success.fragments.homepage.groupbuying.groupBuyingDetailMVP.model.ITheRecordModel;
import com.silianchuangye.sumao.success.fragments.homepage.groupbuying.groupBuyingDetailMVP.model.TheRecordCallback;
import com.silianchuangye.sumao.success.fragments.homepage.groupbuying.groupBuyingDetailMVP.model.TheRecordModel;
import com.silianchuangye.sumao.success.fragments.homepage.groupbuying.groupBuyingDetailMVP.view.IGroupBuyingDetailView;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.model.preSaleBondModel.IPreSaleBondModel;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.model.preSaleBondModel.PreSaleBondCallback;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.model.preSaleBondModel.PreSaleBondModel;

/**
 * Created by Jobs Created on 2017/1/13.
 */
public class GroupBuyingDetailPresenter {
    IGroupBuyingDetailView groupBuyingDetailView;

    public GroupBuyingDetailPresenter(IGroupBuyingDetailView groupBuyingDetailView) {
        this.groupBuyingDetailView = groupBuyingDetailView;
    }

    //   获取支付保证金时的银行列表
    public void getBankListResult(String productId) {
        IPreSaleBondModel preSaleBondModel = new PreSaleBondModel(productId);
        preSaleBondModel.getPresaleBondResult(new PreSaleBondCallback() {
            @Override
            public void callbakcPreSale(String result) {
                groupBuyingDetailView.setBankListResult(result);
            }
        });
    }

    //    支付保证金
    public void getPaymentSecurityResult(String depositAmount, String productId, String paymentPlatform, String _dynSessConf) {
        IPaymentAuctionSecurityModel paymentAuctionSecurityModel = new PaymentSecurityModel(depositAmount, productId, paymentPlatform, _dynSessConf);
        paymentAuctionSecurityModel.getPaymentSecurityResult(new PaymentSecurityCallback() {
            @Override
            public void callbackPaymentSecurity(String result) {
                groupBuyingDetailView.setPaymentSecurityResult(result);
            }
        });
    }

    public void  getTheRecordResult(String productId){
        ITheRecordModel theRecordModel = new TheRecordModel(productId);
        theRecordModel.getTheRecordResult(new TheRecordCallback() {
            @Override
            public void callbackTheRecord(String result) {
                groupBuyingDetailView.setTheRecordResult( result);
            }
        });
    }
}
