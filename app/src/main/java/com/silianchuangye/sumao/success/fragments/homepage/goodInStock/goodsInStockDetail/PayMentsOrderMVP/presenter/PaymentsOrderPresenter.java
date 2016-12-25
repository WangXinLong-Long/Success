package com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.PayMentsOrderMVP.presenter;

import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.PayMentsOrderMVP.model.getListOfBankModel.GetListOfBankCallback;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.PayMentsOrderMVP.model.getListOfBankModel.GetListOfBankModel;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.PayMentsOrderMVP.model.getListOfBankModel.IGetListOfBankModel;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.PayMentsOrderMVP.model.paymentModel.IPaymentModel;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.PayMentsOrderMVP.model.paymentModel.PaymentCallback;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.PayMentsOrderMVP.model.paymentModel.PaymentModel;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.PayMentsOrderMVP.view.IPaymentsOrderView;

/**
 * Created by Jobs Created on 2016/12/19.
 */
public class PaymentsOrderPresenter {
    IPaymentsOrderView paymentsOrderView;

    public PaymentsOrderPresenter(IPaymentsOrderView paymentsOrderView) {
        this.paymentsOrderView = paymentsOrderView;
    }

//    从服务器获取银行列表
    public void getBankListFromService(String order, String roderIds){
        IGetListOfBankModel getListOfBankModel = new GetListOfBankModel( order,  roderIds);
        getListOfBankModel.getListOfBankResult(new GetListOfBankCallback() {
            @Override
            public void callbackListOfBank(String result) {
                paymentsOrderView.setBankListResult( result);
            }
        });
    }

//获取支付后信息
    public void getPaymentInfoFromService(String orderIds, String platform, String _dynSessConf){
        IPaymentModel paymentModel = new PaymentModel( orderIds,  platform,  _dynSessConf);
        paymentModel.getPaymentResult(new PaymentCallback() {
            @Override
            public void callbakcPayment(String result) {
                paymentsOrderView.getPaymentResult(result);
            }
        });
    }
}
