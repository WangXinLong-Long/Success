package com.silianchuangye.sumao.success.fragments.pagerThreeMVP.model;

import android.util.Log;

import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Jobs Created on 2016/12/23.
 */
public class PagerThreeModel implements IPagerThreeModel {
String _dynSessConf;

    public PagerThreeModel(String _dynSessConf) {
        this._dynSessConf = _dynSessConf;
    }

    @Override
    public void getPagerThreeResult(final PagerThreeCallback callback) {
        String url = SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/ShoppingCartActor/shoppingCartDetail";
        RequestParams rp = new RequestParams(url);

        rp.addParameter("_dynSessConf", _dynSessConf);
        Log.d("购物车的rp的值", rp.toString());
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("result", result);
                LogUtils.log("购物车result---->"+result+"<----购物车result");
                callback.callbackPagerThree(result);

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
