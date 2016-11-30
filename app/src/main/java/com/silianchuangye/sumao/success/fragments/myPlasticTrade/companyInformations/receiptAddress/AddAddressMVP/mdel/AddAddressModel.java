package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.AddAddressMVP.mdel;

import com.google.gson.Gson;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.AddAddressMVP.bean.AddAddressBean;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2016/8/3 0003.
 */
public class AddAddressModel implements IAddAddressModel {
    Gson gson = new Gson();
    String address;
    String consignee;
    String phone;
    String zip_code;
    String mobile;
    String detail;
    String sessionId;
    public AddAddressModel(String address,String sessionId,String consignee,String phone,String zip_code,String mobile,String detail) {
       this.address = address;
        this.sessionId = sessionId;
        this.consignee = consignee;
        this.phone = phone;
        this.zip_code = zip_code;
        this.mobile =mobile;
        this.detail =detail;
    }

    @Override
    public void sendAddAddressInfoToServer(final IAddAddressCallback callback) {
//            String[] addressinfo = info.split(",");
//        for (String s : addressinfo) {
//            LogUtils.log("AddAddressMdel：s--->" + s + "<---s：AddAddressMdel");
//        }
        String url = SuMaoConstant.SUMAO_IP+"/rest/model/atg/userprofiling/ProfileActor/createAddress" ;
        RequestParams requestParams = new RequestParams(url);
        requestParams.setCharset("UTF-8");
        requestParams.setAsJsonContent(true);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name",consignee.trim());
            jsonObject.put("address",detail.trim());
            jsonObject.put("province",address.substring(0,4).trim());
            jsonObject.put("city",address.substring(0,6).trim());
            jsonObject.put("county",address.trim());
            jsonObject.put("_dynSessConf",sessionId.trim());
            jsonObject.put("mobile",mobile.trim());
            jsonObject.put("postcode",zip_code);
//            jsonObject.put("phone",phone);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        requestParams.addParameter("phone",phone);
        requestParams.setBodyContent(jsonObject.toString());
        LogUtils.log("AddAddressMdel：url--->" + requestParams + "<---url：AddAddressMdel");
        try {
            x.http().request(HttpMethod.POST, requestParams, new Callback.CacheCallback<String>() {

                @Override
                public boolean onCache(String result) {
                    return false;
                }

                private AddAddressBean addAddress;

                @Override
                public void onSuccess(String result) {
                    LogUtils.log("ReceiptAddressModel：result--->" + result + "<---Province：ReceiptAddressModel");
                        callback.addAddresscallback(result);
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    LogUtils.log("--------->" + "----ReceiptAddressModel-->3.2+onError" + ex.toString() + "<-----------");
                }

                @Override
                public void onCancelled(CancelledException cex) {
                    LogUtils.log("--------->" + "----ReceiptAddressModel-->3.2+onCancelled" + "<-----------");
                }

                @Override
                public void onFinished() {
                    LogUtils.log("--------->" + "----ReceiptAddressModel-->3.2+onFinished" + "<-----------");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.log("--------->" + "----ReceiptAddressModel-->3.2+printStackTrace" + e.toString() + "<-----------");
        }

    }
}
