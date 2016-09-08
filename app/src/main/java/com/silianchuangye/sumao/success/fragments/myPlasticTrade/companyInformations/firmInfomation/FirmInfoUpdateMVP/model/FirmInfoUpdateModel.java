package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.FirmInfoUpdateMVP.model;

import com.google.gson.Gson;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.FirmInfoUpdateMVP.bean.FirmInfoUpdateActivityBean;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.FirmInfoUpdateMVP.model.IFirmInfoUpdateModel;
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
 * Created by Administrator on 2016/9/6 0006.
 */
public class FirmInfoUpdateModel implements IFirmInfoUpdateModel {

    @Override
    public void getFirmInfoUpdateInfo(final FirmInfoUpdateCallback callback) {
//      获取企业信息
        String url = SuMaoConstant.SUMAO_IP+"/rest/model/atg/userprofiling/ProfileActor/enterpriseInfo";
        RequestParams requestParams = new RequestParams(url);
        try {
            x.http().request(HttpMethod.POST, requestParams, new Callback.CacheCallback<String>() {
                private FirmInfoUpdateActivityBean firmInfoUpdateActivityBean;
                private List<EnterpriseInformation> types;
                private DifferentTypes differentTypes;

                @Override
                public boolean onCache(String result) {
                    return false;
                }

                @Override
                public void onSuccess(String result) {
                    LogUtils.log("获取企业信息result--->" + result + "<---result获取企业信息");
                    Gson gson = new Gson();
                    firmInfoUpdateActivityBean = gson.fromJson(result,FirmInfoUpdateActivityBean.class);
                    callback.callbackFirmInfoUpdate(firmInfoUpdateActivityBean);
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
