package com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.model.paymentSecurityModel;

import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Jobs Created on 2016/12/24.
 */
public class PaymentSecurityModel implements IPaymentSecurityModel {
    private String skuId;
    private String productId;
    private String quantity;
    private String _dynSessConf;
    private String paymentPlatform;

    public PaymentSecurityModel(String skuId, String productId, String quantity, String _dynSessConf, String paymentPlatform) {
        this.skuId = skuId;
        this.productId = productId;
        this.quantity = quantity;
        this._dynSessConf = _dynSessConf;
        this.paymentPlatform = paymentPlatform;
    }

    @Override
    public void getPaymentSecurityResult(final PaymentSecurityCallback callback) {
//        TODO 修改支付保证金接口
        RequestParams params = new RequestParams(SuMaoConstant.SUMAO_IP + "/rest/model/atg/commerce/payment/OrderPayment/goPresale");
        params.addParameter("skuId", skuId);
        params.addParameter("productId", productId);
        params.addParameter("quantity", quantity);//保证金
        params.addParameter("_dynSessConf", _dynSessConf);
        params.addParameter("paymentPlatform", paymentPlatform);
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
