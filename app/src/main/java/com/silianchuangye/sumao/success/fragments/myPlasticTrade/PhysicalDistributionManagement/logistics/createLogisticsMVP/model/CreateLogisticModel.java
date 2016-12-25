package com.silianchuangye.sumao.success.fragments.myPlasticTrade.PhysicalDistributionManagement.logistics.createLogisticsMVP.model;

import android.util.Log;

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
//        String result = "{\"logisticsInfo\": [{\"orderInfo\": [{ \"categoryName\": \"ABS\",\"quantity\": 1,\"productName\": \"LG 121H\",\"orderId\":\"10079600000146\"}],\"licensePlateNo\": \"苏F33333\",\"deliveryer\": \"432432\",\"idCard\": \"320682198511234567\",\"logisticsId\": \"LGS2016102613545567\",\"deliveryerTel\": \"432423423\",\"state\": \"confirmed\",\"remarks\": \"423423\",\"shippingMethod\": \"pickUp\",\"shippingDate\": 1477670400000},{\"contactPhone\": \"432423423432\",\"shippingMethod\": \"seller\",\"shippingContact\": \"423423\",\"detailAddress\": \"432423\",\"repeiptCompany\": \"43242\",\"orderInfo\": [{\"categoryName\": \"丁苯胶\",\"quantity\": 0,\"productName\": \"高桥丁苯等外胶\",\"orderId\": \"10079600000154\"},{\"categoryName\": \"丁苯胶\",\"quantity\": 0,\"productName\": \"高桥丁苯等外胶\",\"orderId\": \"10079600000150\"},{\"categoryName\": \"丁苯胶\",\"quantity\": 0,\"productName\": \"高桥丁苯等外胶\",\"orderId\": \"10079600000132\"}],\"pickUpDateStart\": 1477497600000,\"logisticsId\": \"LGS2016102613452543\",\"pickUpDateEnd\": 1478188800000,\"shippingContactTel\": \"432423\",\"contactPerson\": \"432432\"}]}";

//        callback.callbackCreateLogistic(result);
        RequestParams rp = new RequestParams(SuMaoConstant.SUMAO_IP + "/rest/model/atg/commerce/order/logistics/logisticsActor/logisticsInfo");
        rp.addBodyParameter("_dynSessConf", _dynSessConf);
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
//                 Jobs Created
                LogUtils.log("创建物流需求-->" + result);
                callback.callbackCreateLogistic(result);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("TAG", "ex-----" + ex);
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
