package com.silianchuangye.sumao.success.fragments.PagerOneMVP.model.auctionListModel;

import com.silianchuangye.sumao.success.utils.Loding;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Jobs Created on 2016/12/26.
 */
public class AuctionListModel implements IAuctionListModel {
    private String pageNum;
    private String updateOrLoad;
    public AuctionListModel(String pageNum,String updateOrLoad) {
        this.pageNum = pageNum;
        this.updateOrLoad = updateOrLoad;
    }

    @Override
    public void getAuctionListResult(final AuctionListCallback callback) {

        String url = SuMaoConstant.SUMAO_IP + "/rest/model/atg/commerce/catalog/ProductCatalogActor/auctionProductlist";
        RequestParams rp = new RequestParams(url);
        rp.addParameter("pageNum", pageNum);
        LogUtils.log("竞拍列表的URL--->"+rp);
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtils.log("竞拍列表的result--->"+result);
                callback.callbackAuctionList(result,updateOrLoad);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                Loding.dis();
            }
        });
    }
}
