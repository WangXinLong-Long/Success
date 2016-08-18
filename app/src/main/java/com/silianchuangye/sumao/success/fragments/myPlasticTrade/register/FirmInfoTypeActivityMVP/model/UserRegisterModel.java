package com.silianchuangye.sumao.success.fragments.myPlasticTrade.register.FirmInfoTypeActivityMVP.model;

import android.util.Log;

import com.google.gson.Gson;
import com.silianchuangye.sumao.success.model.DifferentTypes;
import com.silianchuangye.sumao.success.model.EnterpriseInformation;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2016/7/26 0026.
 */
public class UserRegisterModel implements IUserRegisterModel {
    Gson gson = new Gson();
    DifferentTypes types;
    String result;

    @Override
    public void getEntInfo(int position,IEnterpriseInformationCallback callback) {
        getStringFromURL(position,callback);
    }





    public  void  getStringFromURL(final int position, final IEnterpriseInformationCallback callback){
        String url = SuMaoConstant.SUMAO_IP+"/rest/model/atg/store/profile/RegistrationActor/userVerify/getEntInfo";
        RequestParams requestParams = new RequestParams(url);
        try {
            x.http().request(HttpMethod.POST, requestParams, new Callback.CacheCallback<String>() {
                private List<EnterpriseInformation> types;
                private DifferentTypes differentTypes;

                @Override
                public boolean onCache(String result) {
                    return false;
                }

                @Override
                public void onSuccess(String result) {
                    LogUtils.log("result--->" + result + "<---result");
                    differentTypes = gson.fromJson(result,DifferentTypes.class);
                    switch (position)
                    {
                        case 1:
                            types = differentTypes.getCl_leixing();
                            break;
                        case 2:
                            types = differentTypes.getCl_nashuiren();
                            break;
                        case 3:

                    }

                    callback.setData(types);
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
