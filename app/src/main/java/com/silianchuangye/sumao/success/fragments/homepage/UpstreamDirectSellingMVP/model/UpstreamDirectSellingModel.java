package com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.model;

import com.google.gson.Gson;
import com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.bean.UpstreamDirectorySellingBean;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2016/9/8 0008.
 */
public class UpstreamDirectSellingModel implements IUpstreamDirectSellingModel {
    String sessionId;
String url = SuMaoConstant.SUMAO_IP+"/rest/model/com/sumao/mobile/order/purchase/PlanOrderActor/directEnterpriseDetail";
    public UpstreamDirectSellingModel(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public void getUpstreamDirectSellingInfo(final UpstreamDirectSellingCallback callback) {
        RequestParams rp = new RequestParams(url);
        rp.setAsJsonContent(true);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("_dynSessConf",sessionId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        rp.setBodyContent(jsonObject.toString());
        x.http().request(HttpMethod.POST, rp, new Callback.CommonCallback<String>() {
            private UpstreamDirectorySellingBean upstreamDirectorySellingBean;

            @Override
            public void onSuccess(String result) {
                LogUtils.log("上游直销-->"+result);
                Gson gson = new Gson();
                upstreamDirectorySellingBean = gson.fromJson(result,UpstreamDirectorySellingBean.class);
                callback.callbackUpstreamDirectSelling( upstreamDirectorySellingBean);
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
