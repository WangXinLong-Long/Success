package com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.SpotOrderMVP.view;

/**
 * Created by Jobs Created on 2016/12/28.
 */
public interface ISpotOrderView {

//    获取银行列表
    void setBankListResult(String result);

    void getPaymentResult(String result, String orderIds);

//    付款信息
    void setPaymentInformationInActivity(String result);

//    发票信息
    void setInvoiceInformationInActivity(String result);

//    合同信息
    void setContractInformationInActivity(String result);
}
