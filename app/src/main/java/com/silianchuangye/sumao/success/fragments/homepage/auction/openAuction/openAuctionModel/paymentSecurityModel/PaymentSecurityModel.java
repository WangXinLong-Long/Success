package com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.openAuctionModel.paymentSecurityModel;

import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Jobs Created on 2016/12/26.
 */
public class PaymentSecurityModel implements IPaymentAuctionSecurityModel {

    private  String depositAmount;
    private  String productId;
    private  String paymentPlatform;
    private  String _dynSessConf;

    public PaymentSecurityModel(String depositAmount, String productId, String paymentPlatform, String _dynSessConf) {
        this.depositAmount = depositAmount;
        this.productId = productId;
        this.paymentPlatform = paymentPlatform;
        this._dynSessConf = _dynSessConf;
    }

    @Override
    public void getPaymentSecurityResult(final PaymentSecurityCallback callback) {

        String url = SuMaoConstant.SUMAO_IP + "/rest/model/atg/commerce/payment/OrderPayment/auctionDeposit";
        RequestParams rp = new RequestParams(url);
        rp.addParameter("depositAmount", depositAmount);
        rp.addParameter("productId", productId);
        rp.addParameter("paymentPlatform", paymentPlatform);

        rp.addParameter("_dynSessConf", _dynSessConf);
        LogUtils.log("竞拍支付保证金URL--->"+rp);
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtils.log("支付保证金返回值result--->"+"" + result);
                callback.callbackPaymentSecurity(result);
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
