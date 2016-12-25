package com.silianchuangye.sumao.success.fragments.PagerOneMVP.model.auctionModel;

import android.util.Log;

import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Jobs Created on 2016/12/25.
 */
public class AuctionModel implements IAuctionModel {
    String productId;

    public AuctionModel(String productId) {
        this.productId = productId;
    }

    @Override
    public void getAuctionModel(final AuctionCallback callback) {
        String uri = SuMaoConstant.SUMAO_IP + "/rest/model/atg/commerce/catalog/ProductCatalogActor/auctionResultList";
        RequestParams rp = new RequestParams(uri);
        rp.addParameter("productId", productId);
        LogUtils.log("竞拍的列表的URL"+rp);
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("竞拍的返回值", result);
                callback.callbackAuction(result);
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
