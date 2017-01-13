package com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.openAuctionModel.amountPaidedModel;

import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Jobs Created on 2016/12/26.
 */
public class AmountPaidedModel implements IAmountPaidedModel {
    private String productId;
    private String _dynSessConf;

    public AmountPaidedModel(String productId, String _dynSessConf) {
        this.productId = productId;
        this._dynSessConf = _dynSessConf;
    }

    @Override
    public void getAmountPaidedResult(final AmountPaidedCallback callback) {
        String url = SuMaoConstant.SUMAO_IP + "/rest/model/atg/commerce/catalog/ProductCatalogActor/AuctionDeposit";
        RequestParams rp = new RequestParams(url);
        rp.addParameter("productId", productId);
        rp.addParameter("_dynSessConf", _dynSessConf);
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtils.log("获取已经支付的保证金金额-->"+result);
               callback.callbackAmountPaided(result);
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
