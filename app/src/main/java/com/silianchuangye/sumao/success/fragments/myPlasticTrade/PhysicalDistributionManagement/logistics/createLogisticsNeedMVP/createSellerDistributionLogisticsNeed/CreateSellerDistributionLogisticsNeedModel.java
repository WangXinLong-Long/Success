package com.silianchuangye.sumao.success.fragments.myPlasticTrade.PhysicalDistributionManagement.logistics.createLogisticsNeedMVP.createSellerDistributionLogisticsNeed;

import android.util.Log;

import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Jobs Created on 2016/12/21.
 */
public class CreateSellerDistributionLogisticsNeedModel implements ICreateSellerDistributionLogisticsNeedModel {
    private String quantity;
    private String commerceJson;
    private String shippingMethod;
    private String provinceId;
    private String cityId;
    private String countyId;
    private String contactPhone;
    private String pickUpDateStart;
    private String pickUpDateEnd;
    private String shippingContactTel;
    private String detailAddress;
    private String contactPerson;
    private String repeiptCompany;
    private String shippingContact;

    public CreateSellerDistributionLogisticsNeedModel(String quantity, String commerceJson,
                                                      String shippingMethod, String provinceId,
                                                      String cityId, String countyId,
                                                      String contactPhone, String pickUpDateStart,
                                                      String pickUpDateEnd, String shippingContactTel,
                                                      String detailAddress, String contactPerson,
                                                      String repeiptCompany, String shippingContact) {
        this.quantity = quantity;
        this.commerceJson = commerceJson;
        this.shippingMethod = shippingMethod;
        this.provinceId = provinceId;
        this.cityId = cityId;
        this.countyId = countyId;
        this.contactPhone = contactPhone;
        this.pickUpDateStart = pickUpDateStart;
        this.pickUpDateEnd = pickUpDateEnd;
        this.shippingContactTel = shippingContactTel;
        this.detailAddress = detailAddress;
        this.contactPerson = contactPerson;
        this.repeiptCompany = repeiptCompany;
        this.shippingContact = shippingContact;
    }

    @Override
    public void getCreateSellerDistribuotionLogisticsNeedResult(final CreateSellerDistributionLogisticsNeedCallback callback) {
        RequestParams params = new RequestParams(SuMaoConstant.SUMAO_IP + "/rest/model/atg/commerce/order/logistics/logisticsActor/createNewLogistics");
        params.addParameter("quantity", quantity);//本次发货数量
        params.addParameter("commerceJson", commerceJson);
        params.addParameter("shippingMethod", shippingMethod);
        params.addParameter("provinceId", provinceId);
        params.addParameter("cityId", cityId);
        params.addParameter("countyId", countyId);
        params.addParameter("contactPhone", contactPhone);//联系电话
        params.addParameter("pickUpDateStart", pickUpDateStart);
        params.addParameter("pickUpDateEnd", pickUpDateEnd);
        params.addParameter("shippingContactTel", shippingContactTel);
        params.addParameter("detailAddress", detailAddress);
        JSONObject job = new JSONObject();
        try {
//                job.put("commerceJson",megList);
            job.put("contactPerson", contactPerson);
            job.put("repeiptCompany", repeiptCompany);
            job.put("shippingContact", shippingContact);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        params.setBodyContent(job.toString());
        Log.e("TAG", "创建物流params-----" + params);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("TAG", "result======" + result);
                callback.callbackCreateSellerDistributionLogisticsNeed(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("TAG", "ex------" + ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}
