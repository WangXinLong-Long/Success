package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressDetailMVP.model.ModifyReceiptInformation;

import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2016/8/10 0010.
 */
public class ModifyReceiptInformationModel implements IModifyReceiptInformationModel {

    String name;
    String region;
    String address;
    String postcode;
    String mobile;
    String phone;
    String addr;
    String sessionId;

    public ModifyReceiptInformationModel(String name, String region, String address, String postcode, String mobile, String phone, String addr,String sessionId) {
        this.name = name;
        this.region = region;
        this.address = address;
        this.postcode = postcode;
        this.mobile = mobile;
        this.phone = phone;
        this.addr = addr;
        this.sessionId = sessionId;
    }

    @Override
    public void sendModifyReceiptInformationRequest(final ModifyReceiptInformationCallback callback) {
        String url = SuMaoConstant.SUMAO_IP+"/rest/model/atg/userprofiling/ProfileActor/addresEdit";
        RequestParams requestParams = new RequestParams(url);
        requestParams.setCharset("utf-8");
        requestParams.setAsJsonContent(true);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name",name);
            jsonObject.put("province",region.substring(0,4));
            jsonObject.put("city",region.substring(0,6));
            jsonObject.put("county",region);
            jsonObject.put("address",address);
            jsonObject.put("postcode",postcode);
            jsonObject.put("mobile",mobile);
            jsonObject.put("phone",phone);
            jsonObject.put("addr",addr);
            jsonObject.put("_dynSessConf",sessionId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        requestParams.setBodyContent(jsonObject.toString());
        try {
            x.http().request(HttpMethod.POST, requestParams, new Callback.CacheCallback<String>() {


                @Override
                public boolean onCache(String result) {
                    return false;
                }

                @Override
                public void onSuccess(String result) {
                    LogUtils.log("ModifyReceiptInformationModel：result--->" + result + "<---ModifyReceiptInformationModel：result");
                    callback.callbackModifyReceiptInformation(result);
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
