package com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.HelpAndFeedbackMVP.model;

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
 * Created by Administrator on 2016/9/27 0027.
 */
public class HelpAndFeedbackModel implements IHelpAndFeedbackModel {
    String telephone;
    String message;
    String userName;

    String url = SuMaoConstant.SUMAO_IP+"";

    public HelpAndFeedbackModel(String telephone, String message, String userName) {
        this.telephone = telephone;
        this.message = message;
        this.userName = userName;
    }

    @Override

    public void sendMessageHelpAndFeedback(final HelpAndFeedbackCallback callback) {
        RequestParams rp = new RequestParams(url);
        rp.setAsJsonContent(true);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("_dynSessConf",telephone);//TODO 修改
        } catch (JSONException e) {
            e.printStackTrace();
        }
        rp.setBodyContent(jsonObject.toString());
        x.http().request(HttpMethod.POST, rp, new Callback.CommonCallback<String>() {
            private UpstreamDirectorySellingBean upstreamDirectorySellingBean;

            @Override
            public void onSuccess(String result) {
                LogUtils.log("帮助与反馈-->"+result);
                Gson gson = new Gson();
                callback.callbackHelpAndFeedback();
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
    }
}
