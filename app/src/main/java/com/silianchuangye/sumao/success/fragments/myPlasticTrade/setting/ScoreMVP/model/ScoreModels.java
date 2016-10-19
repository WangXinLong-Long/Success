package com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.ScoreMVP.model;

import com.google.gson.Gson;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.HelpAndFeedbackMVP.bean.HelpAndFeedbackBean;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2016/9/27 0027.
 */
public class ScoreModels implements IScoreModels {

    String number;String userName;

    public ScoreModels(String number, String userName) {
        this.number = number;
        this.userName = userName;
    }
///pcoAppService/userscore/addScore?username=wangwei&userscore=4.5
    String url = SuMaoConstant.APP_IP_HELP+"/pcoAppService/userscore/addScore";
    @Override
    public void sendScoreInServer(final ScoreCallback callback) {
        RequestParams rp = new RequestParams(url);
        rp.addBodyParameter("username",userName);
        rp.addBodyParameter("userscore",number);
//        rp.setAsJsonContent(true);
//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put("_dynSessConf",number);//TODO 修改
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        rp.setBodyContent(jsonObject.toString());
        x.http().request(HttpMethod.POST, rp, new Callback.CommonCallback<String>() {
            private HelpAndFeedbackBean helpAndFeedbackBean;

            @Override
            public void onSuccess(String result) {
                LogUtils.log("评分-->"+result);
                Gson gson = new Gson();
                helpAndFeedbackBean = gson.fromJson(result, HelpAndFeedbackBean.class);
                callback.callbackScore( helpAndFeedbackBean);
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
