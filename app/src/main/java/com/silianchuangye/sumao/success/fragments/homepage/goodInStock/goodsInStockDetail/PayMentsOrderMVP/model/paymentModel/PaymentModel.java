package com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.PayMentsOrderMVP.model.paymentModel;

import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Jobs Created on 2016/12/20.
 */
public class PaymentModel implements IPaymentModel {
    private String orderIds;
    private String platform;
    private String _dynSessConf;

    public PaymentModel(String orderIds, String platform, String _dynSessConf) {
        this.orderIds = orderIds;
        this.platform = platform;
        this._dynSessConf = _dynSessConf;
    }

    @Override
    public void getPaymentResult(final PaymentCallback callback) {
//        callback.callbakcPayment("{\"paymentStatus\":0}");
        String url = SuMaoConstant.SUMAO_IP + "/rest/model/atg/commerce/payment/OrderPayment/orderPayment";
        RequestParams rp = new RequestParams(url);
        rp.addParameter("orderIds", orderIds);
        rp.addParameter("platform", platform);
        rp.addParameter("_dynSessConf", _dynSessConf);
        LogUtils.log("支付请求URL---->"+rp);
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtils.log("支付返回的值---->"+result);
                callback.callbakcPayment(result);
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
