package com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.OrderDetails.OrderDetailsMVP.view;

/**
 * Created by Jobs Created on 2016/12/31.
 */
public interface IOrderDetailsView {

    //    获取银行列表
    void setBankListResult(String result);

    void getPaymentResult(String result, String orderIds);
}
