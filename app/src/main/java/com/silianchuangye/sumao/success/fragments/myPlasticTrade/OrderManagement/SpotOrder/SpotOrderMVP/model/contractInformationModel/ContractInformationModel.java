package com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.SpotOrderMVP.model.contractInformationModel;

import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Jobs Created on 2017/2/21.
 */
public class ContractInformationModel implements IContractInformationModel {
    String orderId;
    String _dynSessConf;

    public ContractInformationModel(String orderId, String _dynSessConf) {
        this.orderId = orderId;
        this._dynSessConf = _dynSessConf;
    }

    @Override
    public void getContractInformationResult(final ContractInformationCallback callback) {
        String uri = SuMaoConstant.SUMAO_IP + "/rest/model/atg/commerce/order/myorder/OrderPaymentInfo/sealContractPath";
        RequestParams requestParams = new RequestParams(uri);
        requestParams.addParameter("orderId", orderId);
        requestParams.addParameter("_dynSessConf", _dynSessConf);
        LogUtils.log("orderId-->" + orderId + "  _dynSessConf-->" + _dynSessConf);
        LogUtils.log("url--->"+requestParams.getUri());
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtils.log("合同信息onSuccess--->" + result);
                callback.callbackContractInformation(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtils.log("合同信息onError--->" + ex);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                LogUtils.log("合同信息onCancelled--->");
            }

            @Override
            public void onFinished() {
                LogUtils.log("合同信息onFinished--->");
            }
        });
    }
}
