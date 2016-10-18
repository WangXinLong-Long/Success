package com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.ScoreMVP.model.scoreInformationModel;

import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2016/10/17 0017.
 */
public class ScoreInformationModel implements IScoreInformationModel {
    String userName ;
String url = SuMaoConstant.APP_IP_HELP+"/pcoAppService/userscore/myscore";
    public ScoreInformationModel(String userName) {
        this.userName = userName;
    }

    @Override
    public void getScoreInformation(final ScoreInformationCallback callback) {
        RequestParams rp = new RequestParams(url);
        rp.addBodyParameter("username",userName);
//        rp.setAsJsonContent(true);
//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put("username",userName);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        rp.setBodyContent(jsonObject.toString());
        x.http().request(HttpMethod.POST, rp, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                LogUtils.log("评分信息-->"+result);

                callback.callbackScoreInformation( result);
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