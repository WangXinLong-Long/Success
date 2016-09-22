package com.silianchuangye.sumao.success.fragments.myPlasticTrade.register.RegisterPhoneMVP.RegisterPhoneModel;

import com.silianchuangye.sumao.success.model.DifferentTypes;
import com.silianchuangye.sumao.success.model.EnterpriseInformation;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2016/9/20 0020.
 */
public class RegisterPhoneModel implements IRegisterPhoneModel {
    String telephone;

    public RegisterPhoneModel(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public void getRegisterPhoneState(final RegisterPhoneCallback callback) {

        String url = SuMaoConstant.SUMAO_IP+"/rest/model/atg/store/profile/RegistrationActor/userVerify";
        RequestParams requestParams = new RequestParams(url);
        requestParams.setAsJsonContent(true);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("type", "mobile");
            jsonObject.put("attr", telephone);
        } catch (JSONException e) {
            e.printStackTrace();
        }
       requestParams.setBodyContent(jsonObject.toString());
        LogUtils.log(requestParams.toString());
        try {
            x.http().request(HttpMethod.POST, requestParams, new Callback.CacheCallback<String>() {


                @Override
                public boolean onCache(String result) {
                    return false;
                }

                @Override
                public void onSuccess(String result) {
                    LogUtils.log("result--->手机号" + result + "手机号<---result");
//                    differentTypes = gson.fromJson(result,DifferentTypes.class);
                    callback.callbackRegisterPhone( result+"");

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
