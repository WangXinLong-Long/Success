package com.silianchuangye.sumao.success.fragments.homepage.goodInStock.SeeProductMVP.model;

import com.google.gson.Gson;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockDetailActivityMVP.bean.GoodsInStockDetailBean;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.SeeProductMVP.bean.SeeProductBean;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2016/8/15 0015.
 */
public class SeeProductModel implements ISeeProductModel {
    String sessionId;

    public SeeProductModel(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public void getSeeProductResult(final SeeProductCallback callback) {

//                                             /rest/model/atg/commerce/catalog/ProductCatalogActor/getProduct
        String url = SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/catalog/ProductCatalogActor/getRecentlyViewedProducts";


        LogUtils.log("URL----->"+url+"<-----URL");
        RequestParams requestParams = new RequestParams(url);
        requestParams.addParameter("_dynSessConf",sessionId+"");
        try {
            x.http().request(HttpMethod.POST, requestParams, new Callback.CacheCallback<String>() {

                private SeeProductBean seeProductBean;

                @Override
                public boolean onCache(String result) {
                    return false;
                }

                @Override
                public void onSuccess(String result) {
                    LogUtils.log("浏览记录--->" + result + "<---浏览记录");
                    Gson gson = new Gson();
                    seeProductBean = gson.fromJson(result,SeeProductBean.class);
                    callback.callbackSeeProduct( seeProductBean);
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    LogUtils.log("--------->" + "3.2+onError" + ex.toString() + "<-----------");
                    ex.toString();
                }

                @Override
                public void onCancelled(CancelledException cex) {
                    LogUtils.log("--------->" + "3.2+onCancelled" + "<-----------");
                }

                @Override
                public void onFinished() {
                    LogUtils.log("--------->" + "3.2+onFinished" + "<-----------");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.log("--------->" + "3.2+printStackTrace" + e.toString() + "<-----------");
        }
    }
}
