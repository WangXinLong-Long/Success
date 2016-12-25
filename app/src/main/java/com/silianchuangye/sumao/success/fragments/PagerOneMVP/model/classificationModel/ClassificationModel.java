package com.silianchuangye.sumao.success.fragments.PagerOneMVP.model.classificationModel;

import com.google.gson.Gson;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.bean.ClassificationBean;
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
public class ClassificationModel implements IClassificationModel {
String transactionType;

    public ClassificationModel(String transactionType) {
        this.transactionType = transactionType;
    }

    String url = SuMaoConstant.SUMAO_IP+"/rest/model/atg/screen/screenShowProductListActor/AppSearchAppAndCatetoryfl";
    RequestParams requestParams = new RequestParams(url);
    @Override
    public void getClassificationData(final ClassificationCallback callback) {


        x.http().request(HttpMethod.POST, requestParams, new Callback.CacheCallback<String>() {

            private PreSaleBean preSaleBean;

            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                LogUtils.log("分类--->" + result + "<---分类");
                Gson gson = new Gson();
                ClassificationBean classificationBean = gson.fromJson(result, ClassificationBean.class);

                callback.callbackClassification(classificationBean,transactionType);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtils.log("--------->分类" + "3.2+onError" +ex.toString()+ "<-----------");
            }

            @Override
            public void onCancelled(CancelledException cex) {
                LogUtils.log("--------->分类" + "3.2+onCancelled" + "<-----------");
            }

            @Override
            public void onFinished() {
                LogUtils.log("--------->分类" + "3.2+onFinished" + "<-----------");
            }
        });
    }
}
