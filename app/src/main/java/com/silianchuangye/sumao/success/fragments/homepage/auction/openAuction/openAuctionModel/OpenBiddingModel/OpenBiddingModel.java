package com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.openAuctionModel.OpenBiddingModel;

import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Jobs Created on 2016/12/26.
 */
public class OpenBiddingModel implements IOpenBiddingModel {

    private String productId;
    private String bidPrice;
    private String bidQuantity;
    private String _dynSessConf;

    public OpenBiddingModel(String productId, String bidPrice, String bidQuantity, String _dynSessConf) {
        this.productId = productId;
        this.bidPrice = bidPrice;
        this.bidQuantity = bidQuantity;
        this._dynSessConf = _dynSessConf;
    }

    @Override
    public void getOpenBiddingResult(final OpenBiddingCallback callback) {

        String url = SuMaoConstant.SUMAO_IP + "/rest/model/atg/commerce/catalog/ProductCatalogActor/submitEnglishAuction";
        RequestParams rp = new RequestParams(url);
        rp.addParameter("productId", productId);
        rp.addParameter("bidPrice", bidPrice);
        rp.addParameter("bidQuantity",bidQuantity);
        rp.addParameter("_dynSessConf", _dynSessConf);
        LogUtils.log("公开报价URL---->"+rp);
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtils.log("公开竞拍的报价Result--->"+result);
                callback.callbackOpenBidding(result);

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
