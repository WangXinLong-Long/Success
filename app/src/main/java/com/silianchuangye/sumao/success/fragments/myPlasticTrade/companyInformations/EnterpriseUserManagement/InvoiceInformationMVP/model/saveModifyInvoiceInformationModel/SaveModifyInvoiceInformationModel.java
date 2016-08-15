package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.EnterpriseUserManagement.InvoiceInformationMVP.model.saveModifyInvoiceInformationModel;

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
public class SaveModifyInvoiceInformationModel implements IsaveModifyInvoiceInformationModel  {
    private String tax;
    private String address;
    private String phoneNO;
    private String bank;
    private String bankAccount;
    private String notesRecipientName;
    private String notesRecipientMobile;
    private String notesRecipientPhone;
    private String notesRecipientProvince;
    private String notesRecipientCity;
    private String notesRecipientCounty;
    private String notesRecipientAddress;
    private String notesRecipientZipCode;
    private String sessionId;

    public SaveModifyInvoiceInformationModel(String tax, String address, String phoneNO, String bank,
                                             String bankAccount, String notesRecipientName, String notesRecipientMobile,
                                             String notesRecipientPhone, String notesRecipientProvince,
                                             String notesRecipientCity, String notesRecipientCounty,
                                             String notesRecipientAddress, String notesRecipientZipCode,
                                             String sessionId) {
        this.tax = tax;
        this.address = address;
        this.phoneNO = phoneNO;
        this.bank = bank;
        this.bankAccount = bankAccount;
        this.notesRecipientName = notesRecipientName;
        this.notesRecipientMobile = notesRecipientMobile;
        this.notesRecipientPhone = notesRecipientPhone;
        this.notesRecipientProvince = notesRecipientProvince;
        this.notesRecipientCity = notesRecipientCity;
        this.notesRecipientCounty = notesRecipientCounty;
        this.notesRecipientAddress = notesRecipientAddress;
        this.notesRecipientZipCode = notesRecipientZipCode;
        this.sessionId = sessionId;
    }

    @Override
    public void SaveModifyInvoiceInformationToServer(final SaveModifyInvoiceInformationCallback callback) {
        String url = SuMaoConstant.SUMAO_IP+"/rest/model/atg/userprofiling/ProfileActor/invoiceEdit";

        RequestParams requestParams = new RequestParams(url);
        requestParams.setAsJsonContent(true);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("tax",tax);
            jsonObject.put("address",address);
            jsonObject.put("phoneNO",phoneNO);
            jsonObject.put("bank",bank);
            jsonObject.put("bankAccount",bankAccount);

            jsonObject.put("notesRecipientName",notesRecipientName);
            jsonObject.put("notesRecipientMobile",notesRecipientMobile);
            jsonObject.put("notesRecipientPhone",notesRecipientPhone);
            jsonObject.put("notesRecipientProvince",notesRecipientProvince);
            jsonObject.put("notesRecipientCity",notesRecipientCity);

            jsonObject.put("notesRecipientCounty",notesRecipientCounty);
            jsonObject.put("notesRecipientAddress",notesRecipientAddress);
            jsonObject.put("notesRecipientZipCode",notesRecipientZipCode);
            jsonObject.put("_dynSessConf",sessionId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        requestParams.setBodyContent(jsonObject.toString());
        LogUtils.log("修改开票信息："+requestParams);
        try {
            x.http().request(HttpMethod.POST, requestParams, new Callback.CacheCallback<String>() {


                @Override
                public boolean onCache(String result) {
                    return false;
                }

                @Override
                public void onSuccess(String result) {
                    LogUtils.log("保存修改后的开票信息--->" + result + "<---保存修改后的开票信息");
                    callback.callbackSaveModifyInvoiceInformation(result);
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
