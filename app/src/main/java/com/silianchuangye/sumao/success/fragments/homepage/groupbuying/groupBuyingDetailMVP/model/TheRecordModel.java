package com.silianchuangye.sumao.success.fragments.homepage.groupbuying.groupBuyingDetailMVP.model;

import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Jobs Created on 2017/1/19.
 */
public class TheRecordModel implements ITheRecordModel {
    private String productId;

    public TheRecordModel(String productId) {
        this.productId = productId;
    }

    @Override
    public void getTheRecordResult(final TheRecordCallback callback) {
        String url = SuMaoConstant.SUMAO_IP + "/rest/model/atg/commerce/catalog/ProductCatalogActor/groupHistory";
        RequestParams requestParams = new RequestParams(url);
        requestParams.addParameter("productId", productId);
        LogUtils.log("实时行情的result--->"+ requestParams);
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtils.log("实时行情的result--->"+ result);
                callback.callbackTheRecord(result);
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
