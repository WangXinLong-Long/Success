package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressMVP.model;


import com.google.gson.Gson;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressMVP.bean.ReAddress;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressMVP.bean.ReceiptAddress;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2016/7/28 0028.
 */
public class ReceiptAddressModel implements IReceiptAddressModel {
    String sessionId;
    List<String> addressDisplays;

    public ReceiptAddressModel(String sessionId) {
        this.sessionId = sessionId;
    }

    Gson gson = new Gson();
    @Override
    public void getReceiptAddressInfo(final IReceiptAddressCallback callback) {


        String url = SuMaoConstant.SUMAO_IP+"/rest/model/atg/userprofiling/ProfileActor/EntAddress";
        LogUtils.log("ReceiptAddressModel：sessionId--->" + sessionId + "<---sessionId：ReceiptAddressModel");
        RequestParams requestParams = new RequestParams(url);
        requestParams.addParameter("_dynSessConf" , sessionId);
        try {
            x.http().request(HttpMethod.POST, requestParams, new Callback.CommonCallback<String>() {

                private List<ReAddress> address;
                private ReceiptAddress receiptAddress;

                @Override
                public void onSuccess(String result) {
                    LogUtils.log("收货地址：result--->" + result + "<---Province：收货地址");
                    receiptAddress = gson.fromJson(result, ReceiptAddress.class);
                    address = receiptAddress.getAddress();
                    callback.callbackReceiptAddressInfo(address);
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
