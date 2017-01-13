package com.silianchuangye.sumao.success.fragments.homepage.auction.AuctionMVP.model;

import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Jobs Created on 2016/12/27.
 */
public class AuctionSessionModel implements IAuctionSessionModel {
    private String pageNum;
    private String sellerCompanyId;
    private String _dynSessConf;

    public AuctionSessionModel(String pageNum, String sellerCompanyId, String _dynSessConf) {
        this.pageNum = pageNum;
        this.sellerCompanyId = sellerCompanyId;
        this._dynSessConf = _dynSessConf;
    }

    @Override
    public void getAuctionSessionResult(final AuctionSessionCallback callback) {
        String url = SuMaoConstant.SUMAO_IP + "/rest/model/com/sumao/mobile/order/purchase/PlanOrderActor/directEnterpriseAuctionProductList";
        RequestParams rp = new RequestParams(url);
        rp.addParameter("pageNum", pageNum);
        rp.addParameter("sellerCompanyId", sellerCompanyId);
        rp.addParameter("_dynSessConf", _dynSessConf);
        LogUtils.log("竞拍专场的URL--->"+rp);
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtils.log("竞拍专场的Result--->"+result);
                callback.callbackAuctionSession(result);


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
