package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.EnterpriseUserManagement.InvoiceInformationMVP.model;

import android.util.Log;

import com.google.gson.Gson;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.EnterpriseUserManagement.InvoiceInformationMVP.bean.InvoiceInformationBean;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2016/8/11 0011.
 */
public class InvoiceInformationModel implements IInvoiceInformationModel {
    String sessionId;

    public InvoiceInformationModel(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public void getInvoiceInformationFromService(final InvoiceInformationCallback callback) {

        String url = SuMaoConstant.SUMAO_IP+"/rest/model/atg/userprofiling/ProfileActor/invoiceInfo";
        LogUtils.log("开票信息URL----->"+url+"<-----开票信息URL");
        RequestParams requestParams = new RequestParams(url);
        requestParams.addParameter("_dynSessConf",sessionId);
        try {
            x.http().request(HttpMethod.POST, requestParams, new Callback.CacheCallback<String>() {


                @Override
                public boolean onCache(String result) {
                    return false;
                }

                @Override
                public void onSuccess(String result) {
                    LogUtils.log("开票信息--->" + result + "<---开票信息");
                    if (result.contains("invoice")) {
                        Gson gson = new Gson();
                        InvoiceInformationBean bean = gson.fromJson(result, InvoiceInformationBean.class);
                        callback.callbackInvoiceInformation(bean);
                    }else {
                        try {
                            JSONObject jsonObject=new JSONObject(result);
                            String cl_nsrleixing=jsonObject.getString("cl_nsrleixing");
                            String cl_mingcheng=jsonObject.getString("cl_mingcheng");
                            String info=jsonObject.getString("info");
                            String cl_id=jsonObject.getString("cl_id");
                            InvoiceInformationBean bean=new InvoiceInformationBean();
                            bean.setCl_id(cl_id);
                            bean.setCl_nsrleixing(cl_nsrleixing);
                            bean.setCl_mingcheng(cl_mingcheng);
                            bean.setInfo(info);
                            Log.d("开票的信息",bean.getCl_id()+bean.getCl_nsrleixing()+bean.getCl_mingcheng()+bean.getInfo());
                            callback.callbackInvoiceInformation(bean);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    LogUtils.log("--------->" + "3.2+onError" + ex.toString() + "<-----------");
                    ex.toString();
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
            LogUtils.log("--------->" + "3.2+printStackTrace" + e.toString() + "<-----------");
        }

    }
}
