package com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.openAuctionModel.sealedBidModel;

import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Jobs Created on 2016/12/26.
 */
public class SealedBidModel implements ISealedBidModel {

    private String productId;
    private String bidPrice;
    private String bidQuantity;
    private String _dynSessConf;

    public SealedBidModel(String productId, String bidPrice, String bidQuantity, String _dynSessConf) {
        this.productId = productId;
        this.bidPrice = bidPrice;
        this.bidQuantity = bidQuantity;
        this._dynSessConf = _dynSessConf;
    }

    @Override
    public void getSealedBidResult(final SealedBidCallback callback) {

        String url = SuMaoConstant.SUMAO_IP + "/rest/model/atg/commerce/catalog/ProductCatalogActor/submitSealAuction";
        RequestParams rp = new RequestParams(url);
        rp.addParameter("productId", productId);
        rp.addParameter("bidPrice", bidPrice);
        rp.addParameter("bidQuantity",bidQuantity);
        rp.addParameter("_dynSessConf", _dynSessConf);
        LogUtils.log("密封报价参数--->"+ productId+"---"+bidPrice+"---"+bidQuantity+"---"+_dynSessConf);
        LogUtils.log("密封报价URL--->"+ rp);
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtils.log("密封报价的返回的result--->"+ result);
                callback.callbackSealedBid(result);

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
