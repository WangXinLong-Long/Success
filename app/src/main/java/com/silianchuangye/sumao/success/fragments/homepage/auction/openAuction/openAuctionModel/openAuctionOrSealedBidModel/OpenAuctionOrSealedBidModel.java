package com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.openAuctionModel.openAuctionOrSealedBidModel;

import android.util.Log;

import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Jobs Created on 2016/12/26.
 */
public class OpenAuctionOrSealedBidModel implements IOpenAuctionOrSealedBidModel {
    private String type_way;
    private String productId;
    private String proType;

    public OpenAuctionOrSealedBidModel(String type_way, String productId,String proType) {
        this.type_way = type_way;
        this.productId = productId;
        this.proType = proType;
    }

    @Override
    public void getOpenAuctionOrSealedBidResult(final OpenAuctionOrSealedBidCallback callback) {
        String url = "";
        if (type_way.equals("公开竞拍")) {
            url = SuMaoConstant.SUMAO_IP + "/rest/model/atg/commerce/catalog/ProductCatalogActor/auctionProduct";
        } else if (type_way.equals("密封报价")) {
            url = SuMaoConstant.SUMAO_IP + "/rest/model/atg/commerce/catalog/ProductCatalogActor/sealAuctionProduct";
        }
        RequestParams rp = new RequestParams(url);
        rp.addParameter("productId", productId);
        rp.addParameter("proType", proType);
        LogUtils.log("竞拍详情的URL--->"+rp);
        Log.d("竞拍详情的URL",""+rp);
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtils.log("竞拍详情的result--->"+result);
                callback.callbackOpenAuctionOrSealedBidResult(result,type_way);
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
