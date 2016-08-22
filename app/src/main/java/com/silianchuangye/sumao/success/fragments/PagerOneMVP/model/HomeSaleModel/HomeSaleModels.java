package com.silianchuangye.sumao.success.fragments.PagerOneMVP.model.HomeSaleModel;

import android.graphics.drawable.Drawable;

import com.google.gson.Gson;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.bean.AnnounceBean;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleMVP.bean.PreSaleBean;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.ImageManager;
import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

/**
 * Created by Administrator on 2016/8/19 0019.
 */
public class HomeSaleModels implements IHomeSaleModel {

    @Override
    public void getHomeSaleInfo(final HomeSaleCallback callback) {
        String url = SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/catalog/ProductCatalogActor/homeForwardProduct";
        RequestParams requestParams = new RequestParams(url);
        try {
            x.http().request(HttpMethod.POST, requestParams, new Callback.CacheCallback<String>() {

                private PreSaleBean preSaleBean;

                @Override
                public boolean onCache(String result) {
                    return false;
                }

                @Override
                public void onSuccess(String result) {
                    LogUtils.log("首页预售--->" + result + "<---首页预售");
                    Gson gson = new Gson();
                    preSaleBean = gson.fromJson(result, PreSaleBean.class);
                    callback.callbackHomeSale(preSaleBean);
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    LogUtils.log("--------->PagerOneModel" + "3.2+onError" +ex.toString()+ "<-----------");
                }

                @Override
                public void onCancelled(CancelledException cex) {
                    LogUtils.log("--------->PagerOneModel" + "3.2+onCancelled" + "<-----------");
                }

                @Override
                public void onFinished() {
                    LogUtils.log("--------->PagerOneModel" + "3.2+onFinished" + "<-----------");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.log("--------->PagerOneModel" + "3.2+printStackTrace"+e.toString() + "<-----------");
        }
    }
}
