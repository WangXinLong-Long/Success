package com.silianchuangye.sumao.success.fragments.PagerOneMVP.model.applicationModel;

import com.google.gson.Gson;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.bean.ApplicationBean;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleMVP.bean.PreSaleBean;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Jobs Created on 2016/12/22.
 */
public class ApplicationModel implements IApplicationModel {
    String transactionType;

    public ApplicationModel(String transactionType) {
        this.transactionType = transactionType;
    }

    String url = SuMaoConstant.SUMAO_IP+"/rest/model/atg/screen/screenShowProductListActor/AppSearchAppAndCatetoryyy";
    RequestParams requestParams = new RequestParams(url);
    @Override
    public void getCalssificationResult(final ApplicationCallback callback) {
        x.http().request(HttpMethod.POST, requestParams, new Callback.CacheCallback<String>() {

            private PreSaleBean preSaleBean;

            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                LogUtils.log("应用--->" + result + "<---应用");
                Gson gson = new Gson();
                ApplicationBean applicationBean = gson.fromJson(result, ApplicationBean.class);
                callback.callbackClassificationModel(  applicationBean,transactionType);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtils.log("--------->应用" + "3.2+onError" +ex.toString()+ "<-----------");
            }

            @Override
            public void onCancelled(CancelledException cex) {
                LogUtils.log("--------->应用" + "3.2+onCancelled" + "<-----------");
            }

            @Override
            public void onFinished() {
                LogUtils.log("--------->应用" + "3.2+onFinished" + "<-----------");
            }
        });
    }
}
