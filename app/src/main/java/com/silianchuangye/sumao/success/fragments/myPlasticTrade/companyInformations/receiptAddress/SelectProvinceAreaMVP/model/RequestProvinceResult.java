package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.SelectProvinceAreaMVP.model;

import com.google.gson.Gson;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.SelectProvinceAreaMVP.bean.AreaResult;
import com.silianchuangye.sumao.success.utils.LogUtils;

import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2016/7/28 0028.
 */
public class RequestProvinceResult implements IRequestProvinceResult{
Gson gson = new Gson();
    @Override
    public void requestRrovinceResult(IResultCallbackListener callback) {
        getStringFromURL(callback);
    }
    public  void  getStringFromURL(final IResultCallbackListener callback){
        String url = "http://192.168.32.126:7023/rest/model/atg/userprofiling/ProfileActor/addressDroplet?type=prov";
        RequestParams requestParams = new RequestParams(url);
        try {
            x.http().request(HttpMethod.POST, requestParams, new Callback.CacheCallback<String>() {

                private AreaResult provinceResult;

                @Override
                public boolean onCache(String result) {
                    return false;
                }

                @Override
                public void onSuccess(String result) {
                    LogUtils.log("Province：result--->" + result + "<---Province：result");
                    provinceResult = gson.fromJson(result,AreaResult.class);


                    callback.setProvinceData(provinceResult.getResult());
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    LogUtils.log("--------->" + "3.2+onError" +ex.toString()+ "<-----------");
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
            LogUtils.log("--------->" + "3.2+printStackTrace"+e.toString() + "<-----------");
        }

    }

}
