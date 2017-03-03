package com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.SpotOrderMVP.model.invoiceInformationModel;

import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Jobs Created on 2017/2/21.
 * 开票信息
 */
public class InvoiceInformationModel implements IInvoiceInformationModel {
    String orderId;
    String _dynSessConf;

    public InvoiceInformationModel(String orderId, String _dynSessConf) {
        this.orderId = orderId;
        this._dynSessConf = _dynSessConf;
    }

    @Override
    public void getInvoiceInformationResult(final InvoiceInformationCallback callback) {
        String url = SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/order/myorder/OrderPaymentInfo/checkInfo";
        RequestParams requestParams = new RequestParams(url);
        requestParams.addParameter("orderId",orderId);
        requestParams.addParameter("_dynSessConf",_dynSessConf);
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtils.log("发票信息onSuccess--->"+result);
                callback.callbackInvoiceInformation(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtils.log("发票信息onError--->");
            }

            @Override
            public void onCancelled(CancelledException cex) {
                LogUtils.log("发票信息onCancelled--->");
            }

            @Override
            public void onFinished() {
                LogUtils.log("发票信息onFinished--->");
            }
        });
    }
}
