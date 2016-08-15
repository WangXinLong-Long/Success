package com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.model.CalendarModel;

import com.google.gson.Gson;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.bean.PreSaleDetailBean;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2016/8/15 0015.
 */
public class CalendarModels implements ICalendarModel {
    String productId;

    public CalendarModels(String productId) {
        this.productId = productId;
    }

    @Override
    public void getCalendarInfo(final CalendarCallback callback) {
        String url = SuMaoConstant.SUMAO_IP+"/rest/model/atg/commerce/catalog/ProductCatalogActor/forwardPrduct";


        LogUtils.log("URL----->"+url+"<-----URL");
        RequestParams requestParams = new RequestParams(url);
        requestParams.addParameter("productId",productId+"");
        try {
            x.http().request(HttpMethod.POST, requestParams, new Callback.CacheCallback<String>() {

                private PreSaleDetailBean preSaleDetailBean;

                @Override
                public boolean onCache(String result) {
                    return false;
                }

                @Override
                public void onSuccess(String result) {
                    LogUtils.log("预售日历--->" + result + "<---预售日历");
                    Gson gson = new Gson();
//                    preSaleDetailBean = gson.fromJson(result,PreSaleDetailBean.class);
                    callback.CalendarCallback();
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
