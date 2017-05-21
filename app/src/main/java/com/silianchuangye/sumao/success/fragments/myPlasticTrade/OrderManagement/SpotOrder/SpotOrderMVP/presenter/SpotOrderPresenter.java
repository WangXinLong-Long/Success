package com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.SpotOrderMVP.presenter;


import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.GoodsInStockDetailActivityMVP.PayMentsOrderMVP.model.getListOfBankModel.GetListOfBankCallback;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.GoodsInStockDetailActivityMVP.PayMentsOrderMVP.model.getListOfBankModel.GetListOfBankModel;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.GoodsInStockDetailActivityMVP.PayMentsOrderMVP.model.getListOfBankModel.IGetListOfBankModel;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.GoodsInStockDetailActivityMVP.PayMentsOrderMVP.model.paymentModel.IPaymentModel;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.GoodsInStockDetailActivityMVP.PayMentsOrderMVP.model.paymentModel.PaymentCallback;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.GoodsInStockDetailActivityMVP.PayMentsOrderMVP.model.paymentModel.PaymentModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.SpotOrderMVP.model.ISpotOrderModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.SpotOrderMVP.model.SpotOrderCallback;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.SpotOrderMVP.model.SpotOrderModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.SpotOrderMVP.model.contractInformationModel.ContractInformationCallback;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.SpotOrderMVP.model.contractInformationModel.ContractInformationModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.SpotOrderMVP.model.contractInformationModel.IContractInformationModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.SpotOrderMVP.model.invoiceInformationModel.IInvoiceInformationModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.SpotOrderMVP.model.invoiceInformationModel.InvoiceInformationCallback;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.SpotOrderMVP.model.invoiceInformationModel.InvoiceInformationModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.SpotOrderMVP.model.paymentInformationModel.IPaymentInformationModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.SpotOrderMVP.model.paymentInformationModel.PaymentInformationCallback;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.SpotOrderMVP.model.paymentInformationModel.PaymentInformationModel;
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

//    获取付款信息的内容
    public void getPaymentInformationResult(String orderId,String _dynSessConf){
        IPaymentInformationModel paymentInformationModel = new PaymentInformationModel(orderId,_dynSessConf);
        paymentInformationModel.getPaymentInformationResult(new PaymentInformationCallback() {
            @Override
            public void callbackPaymentInformation(String result) {
                spotOrderView.setPaymentInformationInActivity(result);
            }
        });
    }

//    获取发票信息
    public void getInvoiceInformationResult(String orderId, String _dynSessConf) {
        IInvoiceInformationModel invoiceInformationModel = new InvoiceInformationModel(orderId,_dynSessConf);
        invoiceInformationModel.getInvoiceInformationResult(new InvoiceInformationCallback() {
            @Override
            public void callbackInvoiceInformation(String result) {
                spotOrderView.setInvoiceInformationInActivity(result);
            }
        });
    }

//    合同信息
    public void getContractInformationResult(String orderId, String _dynSessConf) {
        IContractInformationModel contractInformationModel = new ContractInformationModel(orderId,_dynSessConf);
        contractInformationModel.getContractInformationResult(new ContractInformationCallback() {
            @Override
            public void callbackContractInformation(String result) {
                spotOrderView.setContractInformationInActivity(result);
            }
        });
    }
}
