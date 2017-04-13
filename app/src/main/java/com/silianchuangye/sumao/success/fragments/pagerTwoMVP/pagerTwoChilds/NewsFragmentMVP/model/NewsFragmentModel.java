package com.silianchuangye.sumao.success.fragments.pagerTwoMVP.pagerTwoChilds.NewsFragmentMVP.model;

import android.util.Log;

import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Jobs Created on 2016/12/27.
 */
public class NewsFragmentModel implements INewsFragmentModel {
    private String dateType;

    public NewsFragmentModel(String dateType) {
        this.dateType = dateType;
    }

    @Override
    public void getNewsFragmentResult(final NewsFragmentCallback callback) {
        String url = SuMaoConstant.SUMAO_IP + "/rest/model/atg/commerce/news/NewsAnalyzeActor/SumaoNewsList";
        RequestParams rp = new RequestParams(url);
        if (dateType!=null){
            rp.addParameter("searchDate",dateType);
        }
        Log.d("资讯中新闻的rp",rp+"");
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("资讯新闻的result--->", result);

                callback.callbackNewsFragmentResult(result);


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
