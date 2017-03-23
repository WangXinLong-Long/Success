package com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.AnalysisFragmentMVP.model;

import android.util.Log;

import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Jobs Created on 2016/12/27.
 */
public class AnalysisFragmentModel implements IAnalysisFragmentModel {


    @Override
    public void getAnalysisFragmentResult(final AnalysisFragmentCallback callback) {
        String url = SuMaoConstant.SUMAO_IP + "/rest/model/atg/commerce/news/NewsAnalyzeActor/NewsAnalyzeDateList";
        RequestParams rp = new RequestParams(url);
//        if (dateType!=null){
//            rp.addParameter("dateType",dateType);
//        }
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("资讯分析的result--->", result);
                callback.callbackAnalysisFragmentResult(result);
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
