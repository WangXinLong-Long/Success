package com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.openAuctionModel.myOfferResult;

import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Jobs Created on 2017/1/9.
 */
public class MyOfferResultModel implements IMyOfferResultModel {
    private String productId;
    private String type;
    private String _dynSessConf;
    private boolean offer;
    private  String bianhao;
    public MyOfferResultModel(String _dynSessConf, String productId, String type, boolean offer) {
        this.productId = productId;
        this.type = type;
        this._dynSessConf = _dynSessConf;
        this.offer = offer;
    }
  public MyOfferResultModel(String _dynSessConf, String productId, String type, String bianhao) {
        this.productId = productId;
        this.type = type;
        this._dynSessConf = _dynSessConf;
        this.bianhao = bianhao;
    }

    @Override
    public void getMyOfferResult(final MyOfferResultCallback callback) {
        String url = "";
        if (type.equals("sealedAuctionProduct")) {//密封竞拍我的报价
            url = SuMaoConstant.SUMAO_IP + "/rest/model/atg/commerce/catalog/ProductCatalogActor/sealedAuctionProductPrice";
        } else if (type.equals("englishAuctionProduct")) {//公开竞拍我的报价
            url = SuMaoConstant.SUMAO_IP + "/rest/model/atg/commerce/catalog/ProductCatalogActor/myAuctionProductPrice";
        }
        RequestParams rp = new RequestParams(url);
        rp.addParameter("productId", productId);
        rp.addParameter("_dynSessConf", _dynSessConf);
        //LogUtils.log("公开竞拍/密封竞拍 我的报价URL---->" + rp);
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtils.log("公开竞拍/密封报价 我的报价--->" + result);
                if (bianhao == null){
                    callback.callbackMyOfferResult(result, offer);
                }
              else {
                    callback.callbackMyOfferResult(result, bianhao);
                }

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
