package com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.openAuctionModel.getPublicAuctionOrderDetailsModel;

import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Jobs Created on 2017/1/5.
 */
public class GetPublicAuctionOrderDetailsModel implements IGetPublicAuctionOrderDetailsModel {
    String orderId;

    public GetPublicAuctionOrderDetailsModel(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public void getAuctionOrderDetailsResult(final GetPublicAuctionOrderDetailsCallback callback) {
        String url = SuMaoConstant.SUMAO_IP + "/rest/model/atg/userprofiling/ProfileActor/orderDetail";
        RequestParams rp = new RequestParams(url);
        rp.addParameter("orderId", orderId);
        LogUtils.log("竞拍成功订单详情URL--->"+rp);
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtils.log("竞拍成功订单详情resutl--->"+result);
                callback.callbackGetPublicAuctionOrderDetails(result);

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
