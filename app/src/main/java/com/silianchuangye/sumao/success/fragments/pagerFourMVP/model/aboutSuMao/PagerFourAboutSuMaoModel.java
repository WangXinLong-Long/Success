package com.silianchuangye.sumao.success.fragments.pagerFourMVP.model.aboutSuMao;

import android.util.Log;

import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Jobs Created on 2016/12/27.
 */
public class PagerFourAboutSuMaoModel implements IPagerFourAboutSuMaoModel {
    @Override
    public void getPagerFourAboutSuMaoResult(final PagerFourAboutSuMaoCallback callback) {
        String url = SuMaoConstant.SUMAO_IP + "/rest/model/atg/commerce/news/SumaoNewsActor/CMSNewsDroplet";
        RequestParams rp = new RequestParams(url);
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("关于塑贸的result--->", result);

                callback.callbackPagerFourResult(  result);


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
