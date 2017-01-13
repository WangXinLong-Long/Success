package com.silianchuangye.sumao.success.fragments.pagerFourMVP.model.pagerFourDetail;

import android.util.Log;

import com.google.gson.Gson;
import com.silianchuangye.sumao.success.fragments.pagerFourMVP.pagerFourDetail.bean.PagerFourDetailBean;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Jobs Created on 2016/12/27.
 */
public class PagerFourDetailModel implements IPagerFourDetailModel {
    private String newsId;

    public PagerFourDetailModel(String newsId) {
        this.newsId = newsId;
    }

    @Override
    public void getPagerFourDetailResult(final PagerFourDetailCallback callback) {
        String url = SuMaoConstant.SUMAO_IP + "/rest/model/atg/commerce/news/SumaoNewsActor/selectDetailDroplet";
        RequestParams rp = new RequestParams(url);
        rp.addParameter("newsId",newsId);
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("塑贸详情的result--->", result);
                Gson gson = new Gson();
                PagerFourDetailBean pagerFourDetailBean = gson.fromJson(result, PagerFourDetailBean.class);
                callback.callbackPagerFourDetail(   pagerFourDetailBean);


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
