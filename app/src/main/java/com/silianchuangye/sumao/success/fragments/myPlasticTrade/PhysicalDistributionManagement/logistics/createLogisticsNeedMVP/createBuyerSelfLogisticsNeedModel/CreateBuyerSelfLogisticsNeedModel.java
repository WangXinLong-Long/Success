package com.silianchuangye.sumao.success.fragments.myPlasticTrade.PhysicalDistributionManagement.logistics.createLogisticsNeedMVP.createBuyerSelfLogisticsNeedModel;

import android.util.Log;

import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Jobs Created on 2016/12/21.
 */
public class CreateBuyerSelfLogisticsNeedModel implements ICreateBuyerSelfLogisticsNeedModel {
    private String quantity;
    private String commerceJson;
    private String shippingMethod;
    private String shippingDate;
    private String deliveryerTel;
    private String idCard;
    private String wareHouse;
    private String licensePlateNo;
    private String deliveryer;
    private String remarks;

    public CreateBuyerSelfLogisticsNeedModel(String quantity, String commerceJson,
                                             String shippingMethod, String shippingDate,
                                             String deliveryerTel, String idCard,
                                             String wareHouse, String licensePlateNo,
                                             String deliveryer, String remarks) {
        this.quantity = quantity;
        this.commerceJson = commerceJson;
        this.shippingMethod = shippingMethod;
        this.shippingDate = shippingDate;
        this.deliveryerTel = deliveryerTel;
        this.idCard = idCard;
        this.wareHouse = wareHouse;
        this.licensePlateNo = licensePlateNo;
        this.deliveryer = deliveryer;
        this.remarks = remarks;
    }

    @Override
    public void getCreateLogisticsNeedResult(final CreateBuyerSelfLogisticsNeedCallback callback) {
        RequestParams params = new RequestParams(SuMaoConstant.SUMAO_IP + "/rest/model/atg/commerce/order/logistics/logisticsActor/createNewLogistics");
        params.addParameter("quantity", quantity);//本次发货数量
        params.addParameter("commerceJson", commerceJson);
        params.addParameter("shippingMethod", shippingMethod);
        params.addParameter("shippingDate", shippingDate);//提货时间
        params.addParameter("deliveryerTel", deliveryerTel);//联系电话
        params.addParameter("idCard", idCard);//联系人身份证
        params.addParameter("wareHouse", wareHouse);
        params.addParameter("licensePlateNo", licensePlateNo);
        params.addParameter("deliveryer", deliveryer);
        params.addParameter("remarks", remarks);
        JSONObject job = new JSONObject();
        params.setBodyContent(job.toString());
        Log.e("TAG", "创建物流params-----" + params);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("TAG", "result======" + result);
                callback.callbackCreateLogisticsNeed(result);
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
