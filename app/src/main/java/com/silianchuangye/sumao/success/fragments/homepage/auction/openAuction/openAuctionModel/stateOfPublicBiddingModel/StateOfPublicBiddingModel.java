package com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.openAuctionModel.stateOfPublicBiddingModel;

import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Jobs Created on 2016/12/26.
 */
public class StateOfPublicBiddingModel implements IStateOfPublicBiddingModel {
    private String productId;
    private String _dynSessConf;
    private String typeWay;
    private boolean needToPayTheMargin;

    public StateOfPublicBiddingModel(boolean needToPayTheMargin, String typeWay, String productId, String _dynSessConf) {
        this.productId = productId;
        this._dynSessConf = _dynSessConf;
        this.typeWay = typeWay;
        this.needToPayTheMargin = needToPayTheMargin;
    }

    @Override
    public void getStateOfPublicBiddingResult(final StateOfPublicBiddingCallback callback) {
        String url = "";
        if (typeWay.equals("公开竞拍")) {
            url = SuMaoConstant.SUMAO_IP + "/rest/model/atg/commerce/catalog/ProductCatalogActor/depositState";
        } else {
            //密封竞拍的状态
            url = SuMaoConstant.SUMAO_IP + "/rest/model/atg/commerce/catalog/ProductCatalogActor/sealdepositState";
        }


        RequestParams rp = new RequestParams(url);
        rp.addParameter("productId", productId);
        rp.addParameter("_dynSessConf", _dynSessConf);
        LogUtils.log("竞拍的状态的URL--->" + rp);
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtils.log("竞拍的状态--->" + result);
                LogUtils.log("竞拍的状态needToPayTheMargin--->" + needToPayTheMargin);
                callback.callbackStateOfPublicBiddingResult(result,needToPayTheMargin);
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
