package com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.SpotOrderMVP.model.paymentInformationModel;

import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Jobs Created on 2017/2/21.
 * 付款信息
 */
public class PaymentInformationModel implements IPaymentInformationModel {
    String orderId;
    String _dynSessConf;

    public PaymentInformationModel(String orderId, String _dynSessConf) {
        this.orderId = orderId;
        this._dynSessConf = _dynSessConf;
    }

    @Override
    public void getPaymentInformationResult(final PaymentInformationCallback callback) {
        String uri = SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/order/myorder/OrderPaymentInfo/orderPayment";
        RequestParams requestParams = new RequestParams(uri);
        requestParams.addParameter("orderId",orderId);
        requestParams.addParameter("_dynSessConf",_dynSessConf);
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtils.log("付款信息onSuccess--->"+result);
                callback.callbackPaymentInformation(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtils.log("付款信息onError");
        }

            @Override
            public void onCancelled(CancelledException cex) {
                LogUtils.log("付款信息onCancelled");
            }

            @Override
            public void onFinished() {
                LogUtils.log("付款信息onFinished");
            }
        });
    }
}
