package com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.InformationPageFragmentMVP.model;

import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Jobs Created on 2016/12/26.
 */
public class InformationPageFragmentModel implements IInformationPageFragmentModel {

    @Override
    public void getInformationPageFragmentResult(final InformationPageFragmentCallback callback) {
        String url = SuMaoConstant.SUMAO_IP + "/rest/model/atg/commerce/news/NewsAnalyzeActor/SumaoConsultIndex";
        RequestParams rp = new RequestParams(url);
        LogUtils.log("资讯首页的URL--->"+rp);
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtils.log("资讯首页的result--->"+ result);

                callback.callbackInformationPageFragmentResult(  result);


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
