package com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.PayMentsOrderMVP.model.paymentOrderModel;

import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.GoodsInStockDetailActivityMVP.bean.Orders;

/**
 * Created by Jobs Created on 2016/12/23.
 */
public interface PaymentOrderCallback {
    void callbackPaymentOrder(  Orders orderList);
}
