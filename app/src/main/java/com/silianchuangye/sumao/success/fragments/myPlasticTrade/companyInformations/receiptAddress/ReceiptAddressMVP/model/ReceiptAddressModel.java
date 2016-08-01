package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressMVP.model;


import com.google.gson.Gson;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.addressDisplayMVP.presenter.AddressDisplayPresenter;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressMVP.bean.ReAddress;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressMVP.bean.ReceiptAddress;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.SelectCountyAreaMVP.model.ICountyAreaCallbackListener;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.SelectProvinceAreaMVP.bean.Area;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.SelectProvinceAreaMVP.bean.AreaResult;
import com.silianchuangye.sumao.success.utils.LogUtils;

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


        String url = "http://192.168.32.126:7023/rest/model/atg/userprofiling/ProfileActor/EntAddress" + "?_dynSessConf=" + sessionId;
        RequestParams requestParams = new RequestParams(url);
        try {
            x.http().request(HttpMethod.POST, requestParams, new Callback.CommonCallback<String>() {

                private List<ReAddress> address;
                private ReceiptAddress receiptAddress;

                @Override
                public void onSuccess(String result) {
                    LogUtils.log("ReceiptAddressModel：result--->" + result + "<---Province：ReceiptAddressModel");
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
