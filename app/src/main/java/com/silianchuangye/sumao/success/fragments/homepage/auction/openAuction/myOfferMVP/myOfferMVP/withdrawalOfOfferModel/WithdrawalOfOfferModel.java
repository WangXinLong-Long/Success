package com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.myOfferMVP.myOfferMVP.withdrawalOfOfferModel;

import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Jobs Created on 2017/1/13.
 */
public class WithdrawalOfOfferModel implements IWithdrawalOfOfferModel {
    private String productId;
    private String bidObjId;
    private String _dynSessConf;
    private int position;

    public WithdrawalOfOfferModel(int position,String productId, String bidObjId, String _dynSessConf) {
        this.productId = productId;
        this.bidObjId = bidObjId;
        this._dynSessConf = _dynSessConf;
        this.position = position;
    }

    @Override
    public void getWithdrawalOfOfferResult(final WithdrawalOfOfferCallback callback) {
        String url = SuMaoConstant.SUMAO_IP + "/rest/model/com/sumao/mobile/order/purchase/PlanOrderActor/directEnterpriseAuctionProductList";
        RequestParams rp = new RequestParams(url);
        rp.addParameter("productId", productId);
        rp.addParameter("bidObjId", bidObjId);
        rp.addParameter("_dynSessConf", _dynSessConf);
        LogUtils.log("密封竞拍撤销报价的URL--->"+rp);
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtils.log("密封竞拍撤销报价的Result--->"+result);
                callback.callbackWithdrawalOfOfferModel(result,position);


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
