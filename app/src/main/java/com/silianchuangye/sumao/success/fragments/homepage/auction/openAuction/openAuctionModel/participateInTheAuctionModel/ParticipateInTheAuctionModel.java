package com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.openAuctionModel.participateInTheAuctionModel;

import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Jobs Created on 2017/1/11.
 */
public class ParticipateInTheAuctionModel implements IParticipateInTheAuctionModel {
    private String productId;
    private String skuId;
    private String _dynSessConf;

    public ParticipateInTheAuctionModel(String productId, String skuId,String _dynSessConf) {
        this.productId = productId;
        this.skuId = skuId;
        this._dynSessConf = _dynSessConf;
    }

    @Override
    public void getParticipateInTheAuctionResult(final ParticipateInTheAuctionCallback callback) {
        String url = SuMaoConstant.SUMAO_IP + "/rest/model//atg/commerce/payment/OrderPayment/attendAuction";
        RequestParams rp = new RequestParams(url);
//        rp.setAsJsonContent(true);
//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put("prodId", productId);
//            jsonObject.put("items[0].catalogRefId", skuId);
//            jsonObject.put("items[0].productId", productId);
//            jsonObject.put("_dynSessConf", _dynSessConf);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        rp.setBodyContent(jsonObject.toString());
        rp.addBodyParameter("prodId", productId);
        rp.addBodyParameter("items[0].catalogRefId", skuId);
        rp.addBodyParameter("items[0].productId", productId);
        rp.addBodyParameter("_dynSessConf", _dynSessConf);
        LogUtils.log("参与竞拍URL--->" + rp);
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtils.log("参与竞拍的result--->" + result);
                callback.callbackParticipateInTheAuction(result);
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
