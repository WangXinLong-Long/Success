package com.silianchuangye.sumao.success.fragments.myPlasticTrade.qurchaseQualification.PurchaseQualificationMVP.model;

import android.util.Log;

import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Jobs Created on 2017/2/13.
 */
public class PurchaseQualificationModel implements IPurchaseQualificationModel {
    private String unique;

    public PurchaseQualificationModel(String unique) {
        this.unique = unique;
    }

    @Override
    public void getPurchaseQualificationResult(final PurchaseQualificationCallback callback) {
        String url = SuMaoConstant.SUMAO_IP + "/rest/model/atg/userprofiling/ProfileActor/getOrg";
        RequestParams requestParams = new RequestParams(url);
        requestParams.addParameter("_dynSessConf",unique);
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtils.log("购买资质申请result--->"+result);
                callback.callbackPurchaseQualificationResult(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("test","购买资质申请onError--->" + ex);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e("test","购买资质申请onCancelled--->" + cex);
            }

            @Override
            public void onFinished() {
                LogUtils.log("购买资质申请onFinished--->");
            }
        });
    }
}
