package com.silianchuangye.sumao.success.fragments.PagerOneMVP.model.supplierModel;

import com.google.gson.Gson;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.bean.SupplierBean;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Jobs Created on 2017/1/7.
 */
public class SupplierModel implements ISupplierModel {
    String transactionType;

    public SupplierModel(String transactionType) {
        this.transactionType = transactionType;
    }

    @Override
    public void getSupplierResult(final SupplierCallback callback) {
        String url = SuMaoConstant.SUMAO_IP+"/rest/model/atg/screen/screenShowProductListActor/AppSearchAppAndCatetorygys";
        RequestParams requestParams = new RequestParams(url);
        x.http().request(HttpMethod.POST, requestParams, new Callback.CacheCallback<String>() {


            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                LogUtils.log("供应商--->" + result + "<---供应商");
                Gson gson = new Gson();
                SupplierBean supplierBean = gson.fromJson(result, SupplierBean.class);
                callback.callbackSupplier( supplierBean,transactionType );
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
