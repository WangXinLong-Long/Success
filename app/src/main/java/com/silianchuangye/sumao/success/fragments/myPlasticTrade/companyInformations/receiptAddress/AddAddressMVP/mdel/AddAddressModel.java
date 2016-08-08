package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.AddAddressMVP.mdel;

import com.google.gson.Gson;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.AddAddressMVP.bean.AddAddressBean;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressMVP.bean.ReAddress;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressMVP.bean.ReceiptAddress;
import com.silianchuangye.sumao.success.utils.LogUtils;

import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

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
        String url = "http://192.168.32.126:7023/rest/model/atg/userprofiling/ProfileActor/createAddress" ;
        RequestParams requestParams = new RequestParams(url);
        requestParams.setCharset("UTF-8");
        requestParams.addParameter("province",address.substring(0,4).trim());
        requestParams.addParameter("city",address.substring(0,6).trim());
        requestParams.addParameter("county",address.trim());
        requestParams.addParameter("_dynSessConf",sessionId.trim());
        requestParams.addParameter("address",detail.trim());
        requestParams.addParameter("name",consignee.trim());
        requestParams.addParameter("mobile",mobile.trim());
        requestParams.addParameter("postcode",zip_code);
        requestParams.addParameter("phone",phone);
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
