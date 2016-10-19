package com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.FunctionIntroduction.FunctionIntroductionMVP.model;

import com.google.gson.Gson;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.FunctionIntroduction.FunctionIntroductionMVP.bean.FunctionIntroductionDetailBean;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2016/9/23 0023.
 */
public class FunctionIntroductionModel implements IFunctionIntroductionModel {
    String helpid;

    public FunctionIntroductionModel(String helpid) {
        this.helpid = helpid;
    }

    @Override
    public void getFunctionIntroductionDetailInfo(final FunctionIntroductionCallback callback) {
        String url = SuMaoConstant.APP_IP+"/pcoAppService/help/myHelpPage";
        RequestParams requestParams = new RequestParams(url);
//        requestParams.setAsJsonContent(true);
//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put("helpid", helpid);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        requestParams.setBodyContent(jsonObject.toString());
//
//        LogUtils.log(jsonObject.toString()+"<---");
        LogUtils.log(helpid+"<---");
        requestParams.addParameter("helpid",helpid);
        LogUtils.log(requestParams+"<---");
LogUtils.log(requestParams.getUri());
        try {
            x.http().request(HttpMethod.POST, requestParams, new Callback.CacheCallback<String>() {


                private FunctionIntroductionDetailBean functionIntroductionDetailBean;

                @Override
                public boolean onCache(String result) {
                    return false;
                }

                @Override
                public void onSuccess(String result) {
                    LogUtils.log("帮助详情--->" + result + "<---帮助详情");
                    Gson gson = new Gson();
                    functionIntroductionDetailBean = gson.fromJson(result,FunctionIntroductionDetailBean.class);
                    callback.callbackFunctionIntroduction( functionIntroductionDetailBean);
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
