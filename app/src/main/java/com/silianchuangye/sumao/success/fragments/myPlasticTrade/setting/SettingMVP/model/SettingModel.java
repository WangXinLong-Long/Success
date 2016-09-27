package com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.SettingMVP.model;

import com.google.gson.Gson;
import com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.bean.UpstreamDirectorySellingBean;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.SettingMVP.bean.FunctionIntroductionBean;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2016/9/23 0023.
 */
public class SettingModel implements ISettingModel {
    String url = SuMaoConstant.APP_IP+"/pcoAppService/help/myHelpPage";
    @Override
    public void getSettingInfo(final SettingCallback callback) {
        RequestParams rp = new RequestParams(url);

        x.http().request(HttpMethod.POST, rp, new Callback.CommonCallback<String>() {

            private FunctionIntroductionBean functionIntroductionBean;

            @Override
            public void onSuccess(String result) {
                LogUtils.log("APP功能介绍-->"+result);
                Gson gson = new Gson();
                functionIntroductionBean = gson.fromJson(result,FunctionIntroductionBean.class);
                callback.callbackSetting(functionIntroductionBean);
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
//
    }
}
