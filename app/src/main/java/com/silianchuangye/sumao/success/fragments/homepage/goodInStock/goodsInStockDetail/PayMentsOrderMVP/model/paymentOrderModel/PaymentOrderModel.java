package com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.PayMentsOrderMVP.model.paymentOrderModel;

import android.util.Log;

import com.google.gson.Gson;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.GoodsInStockDetailActivityMVP.bean.Orders;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Jobs Created on 2016/12/23.
 */
public class PaymentOrderModel implements IPaymentOrderModel {

    private String strCommerceItemIds;
    private String _dynSessConf;

    public PaymentOrderModel(String strCommerceItemIds, String _dynSessConf) {
        this.strCommerceItemIds = strCommerceItemIds;
        this._dynSessConf = _dynSessConf;
    }

    @Override
    public void getPaymentOrderResult(final PaymentOrderCallback callback) {
            String url = SuMaoConstant.SUMAO_IP + "/rest/model/atg/commerce/ShoppingCartActor/cartExpressCheckout";
            RequestParams rp = new RequestParams(url);

        rp.addParameter("strCommerceItemIds", strCommerceItemIds);
        rp.addParameter("_dynSessConf", _dynSessConf);
            x.http().post(rp, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    Log.d("提交订单的result--->", result);
                    Gson gson = new Gson();
                    Orders orderList = gson.fromJson(result, Orders.class);

                    callback.callbackPaymentOrder(  orderList);


                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {

                }

                @Override
                public void onCancelled(CancelledException cex) {

                }

                @Override
                public void onFinished() {

                }
            });
        }
}
