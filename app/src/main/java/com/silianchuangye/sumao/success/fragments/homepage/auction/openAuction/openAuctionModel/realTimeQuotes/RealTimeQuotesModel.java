package com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.openAuctionModel.realTimeQuotes;

import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Jobs Created on 2017/1/9.
 */
public class RealTimeQuotesModel implements IRealTimeQuotesModel{
    String productId;
    boolean offer;
    public RealTimeQuotesModel(String productId,boolean offer) {
        this.productId = productId;
        this.offer =offer;
    }

    @Override
    public void getRealTimeQuotesResult(final RealTimeQuotesCallback callback) {
     String   url= SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/catalog/ProductCatalogActor/auctionProductPriceList";
    RequestParams rp = new RequestParams(url);
    rp.addParameter("productId", productId);
    LogUtils.log("公开竞拍 实时行情URL---->"+rp);
    x.http().post(rp, new Callback.CommonCallback<String>() {
        @Override
        public void onSuccess(String result) {
            LogUtils.log("公开竞拍 实时行情--->"+result);
            callback.callbackRealTimeQuotes(result, offer);

        }

        @Override
        public void onError(Throwable ex, boolean isOnCallback) {

        }

        @Override
        public void onCancelled(Callback.CancelledException cex) {

        }

        @Override
        public void onFinished() {

        }
    });

    }
}
