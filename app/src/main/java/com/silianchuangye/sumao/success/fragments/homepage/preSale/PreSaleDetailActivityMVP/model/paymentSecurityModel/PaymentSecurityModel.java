package com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.model.paymentSecurityModel;

import android.util.Log;

import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Jobs Created on 2016/12/24.
 */
public class PaymentSecurityModel implements IPaymentSecurityModel {


    private String quantity;
    private String productId;
    private String paymentPlatform;
    private String skuId;
    private String price;
    private String _dynSessConf;

    public PaymentSecurityModel(String price, String quantity, String productId, String paymentPlatform, String skuId,String _dynSessConf) {
        this.price = price;
        this.quantity = quantity;
        this.productId = productId;
        this.paymentPlatform = paymentPlatform;
        this.skuId = skuId;
        this._dynSessConf=_dynSessConf;
    }

    @Override
    public void getPaymentSecurityResult(final PaymentSecurityCallback callback) {
//        TODO 修改支付保证金接口
        RequestParams params = new RequestParams(SuMaoConstant.SUMAO_IP + "/rest/model/atg/commerce/payment/OrderPayment/goPresale");
        params.addParameter("quantity", quantity);
        params.addParameter("productId", productId);
        params.addParameter("paymentPlatform", paymentPlatform);
        params.addParameter("skuId", skuId);
        params.addParameter("price", price.substring(0,price.length()-1));
        Log.d("price的值",price.substring(0,price.length()-1));
        params.addParameter("_dynSessConf",_dynSessConf);
        LogUtils.log("支付保证金RUL--->"+params);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtils.log("支付保证金result--->"+result);
                callback.callbackPaymentSecurity(result);
//                    result-----{"orderId":"10094600000005","info":"sucess"}

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtils.log("支付保证金onError--->"+ex);
               // callback.callbackPaymentSecurity("支付失败");
            }

            @Override
            public void onCancelled(CancelledException cex) {
                LogUtils.log("支付保证金onCancelled--->"+cex);
            }

            @Override
            public void onFinished() {
                LogUtils.log("支付保证金onFinished--->");
            }
        });
    }
}
