package com.silianchuangye.sumao.success.fragments.pagerFourMVP.model.customerServiceGuide;

import android.util.Log;

import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Jobs Created on 2016/12/27.
 */
public class PagerFourCustomerServiceGuideModel implements IPagerFourCustomerServiceGuideModel {
    @Override
    public void getPagerFourCustomerServiceGuideResult(final PagerFourCustomerServiceGuideCallback callback) {
        String url = SuMaoConstant.SUMAO_IP + "/rest/model/atg/commerce/news/SumaoNewsActor/customerHelp";
        RequestParams rp = new RequestParams(url);
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("塑贸客服的result--->", result);

                callback.callbackPagerFourCustomerServiceGuide(  result);


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
