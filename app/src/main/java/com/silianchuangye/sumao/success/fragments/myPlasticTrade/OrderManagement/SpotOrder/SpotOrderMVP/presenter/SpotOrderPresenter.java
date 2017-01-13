package com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.SpotOrderMVP.presenter;

import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.PayMentsOrderMVP.model.getListOfBankModel.GetListOfBankCallback;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.PayMentsOrderMVP.model.getListOfBankModel.GetListOfBankModel;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.PayMentsOrderMVP.model.getListOfBankModel.IGetListOfBankModel;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.PayMentsOrderMVP.model.paymentModel.IPaymentModel;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.PayMentsOrderMVP.model.paymentModel.PaymentCallback;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.PayMentsOrderMVP.model.paymentModel.PaymentModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.SpotOrderMVP.model.ISpotOrderModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.SpotOrderMVP.model.SpotOrderCallback;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.SpotOrderMVP.model.SpotOrderModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.SpotOrderMVP.view.ISpotOrderView;

/**
 * Created by Jobs Created on 2016/12/28.
 */
public class SpotOrderPresenter {

    ISpotOrderView spotOrderView;

    public SpotOrderPresenter(ISpotOrderView spotOrderView) {
        this.spotOrderView = spotOrderView;
    }

    public void getSpotOrderResult(String searchOrderId, String pageNum){
        ISpotOrderModel spotOrderModel = new SpotOrderModel( searchOrderId,  pageNum);
        spotOrderModel.getSpotOrderResult(new SpotOrderCallback() {
            @Override
            public void callbackSpotOrderResult(String result) {
//                spotOrderView.setSpotOrderResult(result);
            }
        });
    }


    //    从服务器获取银行列表
    public void getBankListFromService(String order, String roderIds){
        IGetListOfBankModel getListOfBankModel = new GetListOfBankModel( order,  roderIds);
        getListOfBankModel.getListOfBankResult(new GetListOfBankCallback() {
            @Override
            public void callbackListOfBank(String result) {
                spotOrderView.setBankListResult( result);
            }
        });
    }

    //      获取支付后信息
    public void getPaymentInfoFromService( String orderIds, String platform, String _dynSessConf){
        IPaymentModel paymentModel = new PaymentModel( orderIds,  platform,  _dynSessConf);
        paymentModel.getPaymentResult(new PaymentCallback() {
            @Override
            public void callbakcPayment(String result,String orderIds) {
                spotOrderView.getPaymentResult(result,orderIds);
            }
        });
    }
}
