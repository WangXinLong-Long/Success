package com.silianchuangye.sumao.success.fragments.myPlasticTrade.PhysicalDistributionManagement.ViewLogisticsDemands.ViewLogisticsDemandMVP.model;

import android.util.Log;

import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Jobs Created on 2016/12/21.
 */
public class ViewLogisticsDemandModel implements IViewLogisticsDemandModel {
    private String _dynSessConf;
    private String logisticsId;
    private String logisticsType;
    private String shippingMethod;
    private String orderId;
    private String createDate;
    private String deliveryStartDate;
    private String deliveryEndDate;

    public ViewLogisticsDemandModel(String _dynSessConf, String logisticsId, String logisticsType,
                                    String shippingMethod, String orderId, String createDate,
                                    String deliveryStartDate, String deliveryEndDate) {
        this._dynSessConf = _dynSessConf;
        this.logisticsId = logisticsId;
        this.logisticsType = logisticsType;
        this.shippingMethod = shippingMethod;
        this.orderId = orderId;
        this.createDate = createDate;
        this.deliveryStartDate = deliveryStartDate;
        this.deliveryEndDate = deliveryEndDate;
    }

    @Override
    public void getViewLogisticsDemandResult(final ViewLogisticsDemandCallback callback) {
         RequestParams params=new RequestParams(SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/order/logistics/logisticsActor/logisticsInfoSearch");
        params.addParameter("_dynSessConf", _dynSessConf);
        params.addParameter("logisticsId",logisticsId);
        params.addParameter("logisticsType",logisticsType);
        params.addParameter("shippingMethod",shippingMethod);
        params.addParameter("orderId",orderId);
        params.addParameter("createDate",createDate);
        params.addParameter("deliveryStartDate",deliveryStartDate);
        params.addParameter("deliveryEndDate",deliveryEndDate);
        Log.e("TAG","params=="+params);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("TAG","result=="+result);
               callback.callbackViewLogisticsDemand(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("TAG","ex="+ex);
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
