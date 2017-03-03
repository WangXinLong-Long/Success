package com.silianchuangye.sumao.success.fragments.myPlasticTrade.qurchaseQualification.purchaseQualificationDetail.PurchaseQualificationDetailMVP.model;

import android.util.Log;

import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Jobs Created on 2017/2/24.
 */
public class PurchaseQualificationDetailModel implements IPurchaseQualificationDetailModel {


    private String _dynSessConf;
    private String up;

    public PurchaseQualificationDetailModel(String _dynSessConf, String up) {
        this._dynSessConf = _dynSessConf;
        this.up = up;
    }

    @Override
    public void getPurchaseQualificationDetailResult(final PurchaseQualificationDetailCallback callback) {
        String url  = SuMaoConstant.SUMAO_IP+"/rest/model/atg/userprofiling/ProfileActor/upresource";

        RequestParams requestParams = new RequestParams(url);

        requestParams.addBodyParameter("_dynSessConf",_dynSessConf);
        requestParams.addBodyParameter("up",up);
        LogUtils.log("_dynSessConf-->"+_dynSessConf+"   up-->"+up);
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtils.log("申请成为上游资源方状态--->"+result);
                callback.callbackPurchaseQualificationDetail(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("test","申请成为上游资源方状态onError错误信息--->"+ex);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e("test","申请成为上游资源方状态onCancelled错误信息--->"+cex);
            }

            @Override
            public void onFinished() {
                LogUtils.log("申请成为上游资源方状态--->onFinished");
            }
        });
    }
}
