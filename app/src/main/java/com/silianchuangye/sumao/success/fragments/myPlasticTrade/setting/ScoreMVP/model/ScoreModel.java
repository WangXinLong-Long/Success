package com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.ScoreMVP.model;

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
public class ScoreModel implements IScoreModel {

    String number;String userName;

    public ScoreModel(String number, String userName) {
        this.number = number;
        this.userName = userName;
    }

    String url = SuMaoConstant.SUMAO_IP+"";
    @Override
    public void sendScoreInServer(final ScoreCallback callback) {
        RequestParams rp = new RequestParams(url);
        rp.setAsJsonContent(true);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("_dynSessConf",number);//TODO 修改
        } catch (JSONException e) {
            e.printStackTrace();
        }
        rp.setBodyContent(jsonObject.toString());
        x.http().request(HttpMethod.POST, rp, new Callback.CommonCallback<String>() {
            private UpstreamDirectorySellingBean upstreamDirectorySellingBean;

            @Override
            public void onSuccess(String result) {
                LogUtils.log("评分-->"+result);
                Gson gson = new Gson();
                callback.callbackScore();
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
