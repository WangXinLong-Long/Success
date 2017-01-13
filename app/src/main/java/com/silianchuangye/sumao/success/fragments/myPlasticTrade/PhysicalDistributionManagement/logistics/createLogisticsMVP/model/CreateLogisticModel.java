package com.silianchuangye.sumao.success.fragments.myPlasticTrade.PhysicalDistributionManagement.logistics.createLogisticsMVP.model;

import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Jobs Created on 2016/12/21.
 */
public class CreateLogisticModel implements ICreateLogisticModel {
    private String _dynSessConf;

    public CreateLogisticModel(String _dynSessConf) {
        this._dynSessConf = _dynSessConf;
    }

    @Override
    public void getCreateLogisticResult(final CreateLogisticCallback callback) {
        String url = SuMaoConstant.SUMAO_IP + "/rest/model/atg/commerce/order/logistics/logisticsActor/logisticsInfo";
        RequestParams rp = new RequestParams(url);
        rp.addParameter("_dynSessConf", _dynSessConf);
//        LogUtils.log("_dynSessConf--->"+_dynSessConf);
        LogUtils.log("创建物流需求的URL-->" + rp);
        rp.setConnectTimeout(30*1000);
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtils.log("创建物流需求result-->" + result);
                callback.callbackCreateLogistic(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtils.log("onError--->"+ex);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                LogUtils.log("onCancelled--->"+cex);
            }

            @Override
            public void onFinished() {
                LogUtils.log("onFinished--->");
            }
        });

    }
}
