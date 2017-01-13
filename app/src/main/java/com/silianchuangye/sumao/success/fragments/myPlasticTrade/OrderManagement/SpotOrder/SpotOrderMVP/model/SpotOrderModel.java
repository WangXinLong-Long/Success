package com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.SpotOrderMVP.model;

import android.util.Log;

import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Jobs Created on 2016/12/28.
 */
public class SpotOrderModel implements ISpotOrderModel {
    private String pageNum;
    private String searchOrderId;

    public SpotOrderModel(String searchOrderId, String pageNum) {
        this.searchOrderId = searchOrderId;
        this.pageNum = pageNum;
    }

    @Override
    public void getSpotOrderResult(final SpotOrderCallback callback) {
        RequestParams params = new RequestParams(SuMaoConstant.SUMAO_IP + "/rest/model/atg/userprofiling/ProfileActor/myOrders");
        params.addParameter("pageNum", "1");
        params.addParameter("searchOrderId", searchOrderId);

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtils.log("待支付订单result--->"+result);
                callback.callbackSpotOrderResult(result);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

                Log.e("TAG", "失败呢ex------" + ex);
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
