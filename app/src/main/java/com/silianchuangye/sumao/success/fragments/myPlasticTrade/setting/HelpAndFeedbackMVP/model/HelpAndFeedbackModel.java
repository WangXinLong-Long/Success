package com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.HelpAndFeedbackMVP.model;

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
public class HelpAndFeedbackModel implements IHelpAndFeedbackModel {
    String telephone;
    String message;
    String userName;

    String url = SuMaoConstant.APP_IP_HELP+"/pcoAppService/feedback/addFeed";

    public HelpAndFeedbackModel(String telephone, String message, String userName) {
        this.telephone = telephone;
        this.message = message;
        this.userName = userName;
    }

    @Override

    public void sendMessageHelpAndFeedback(final HelpAndFeedbackCallback callback) {
        RequestParams rp = new RequestParams(url);
        rp.addBodyParameter("username",userName);
        rp.addBodyParameter("userphone",telephone);
        rp.addBodyParameter("content",message);
//        rp.setAsJsonContent(true);
//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put("username",userName);
//            jsonObject.put("userphone",telephone);
//            jsonObject.put("content",message);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        rp.setBodyContent(jsonObject.toString());
        LogUtils.log(rp.getBodyContent()+"..."+rp.getUri()+"..."+rp.getCharset()+"...");
        x.http().request(HttpMethod.POST, rp, new Callback.CommonCallback<String>() {
            private HelpAndFeedbackBean helpAndFeedbackBean;

            @Override
            public void onSuccess(String result) {
                LogUtils.log("帮助与反馈-->"+result);
                Gson gson = new Gson();
                helpAndFeedbackBean = gson.fromJson(result, HelpAndFeedbackBean.class);
                callback.callbackHelpAndFeedback( helpAndFeedbackBean);
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
