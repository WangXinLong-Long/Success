package com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.openAuctionModel.auctionResultModel;

import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Jobs Created on 2016/12/26.
 */
public class AuctionResultModel implements IAuctionResultModel {
    private String productId;
    private String _dynSessConf;

    public AuctionResultModel(String productId, String _dynSessConf) {
        this.productId = productId;
        this._dynSessConf = _dynSessConf;
    }

    @Override
    public void getAuctionResultResult(final AuctionResultCallback callback) {
        String url = SuMaoConstant.SUMAO_IP + "/rest/model/atg/commerce/catalog/ProductCatalogActor/englishAuctionResult";
      //  String url = SuMaoConstant.SUMAO_IP + "/rest/model/atg/commerce/catalog/ProductCatalogActor/englishAuctionResult";
        RequestParams rp = new RequestParams(url);
        rp.addParameter("productId", productId);
        rp.addParameter("_dynSessConf", _dynSessConf);
        LogUtils.log("竞拍成功获取信息的URL--->"+rp);
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtils.log("竞拍成功获取信息的结果--->"+result);
                callback.callbackAuctionResult(result);

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
