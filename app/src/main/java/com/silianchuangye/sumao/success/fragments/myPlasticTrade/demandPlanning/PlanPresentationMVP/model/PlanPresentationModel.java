package com.silianchuangye.sumao.success.fragments.myPlasticTrade.demandPlanning.PlanPresentationMVP.model;

import android.util.Log;

import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Jobs Created on 2017/2/10.
 */
public class PlanPresentationModel implements IPlanPresentationModel {
    private String scheduleDate;
    private String productId;
    private String productName;
    private String warehouseID;
    private String planOrderQuantity;
    private String logisticsID;
    private String notes;
    private String _dynSessConf;
    private String sellerCompanyId;

    public PlanPresentationModel() {
        this.scheduleDate = scheduleDate;
        this.productId = productId;
        this._dynSessConf = _dynSessConf;
        this.notes = notes;
        this.warehouseID = warehouseID;
        this.planOrderQuantity = planOrderQuantity;
        this.logisticsID = logisticsID;
        this.productName = productName;
        this.sellerCompanyId = sellerCompanyId;
    }

    public PlanPresentationModel(String scheduleDate, String productId, String productName, String warehouseID, String planOrderQuantity, String logisticsID, String notes, String _dynSessConf, String sellerCompanyId) {
        this.scheduleDate = scheduleDate;
        this.productId = productId;
        this.productName = productName;
        this.warehouseID = warehouseID;
        this.planOrderQuantity = planOrderQuantity;
        this.logisticsID = logisticsID;
        this.notes = notes;
        this._dynSessConf = _dynSessConf;
        this.sellerCompanyId = sellerCompanyId;
    }

    @Override
    public void getPlanPresentationResuls(final PlanPresentationCallback callback) {
//        String result = null;
//        callback.CallbackPlanPresentationResuls(result);
        String url= SuMaoConstant.SUMAO_IP+"/rest/model/com/sumao/mobile/order/purchase/PlanOrderActor/demandScheduleInOrderAll";
        RequestParams rp=new RequestParams(url);
        rp.setAsJsonContent(true);


        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("sellerCompanyId",sellerCompanyId);
            jsonObject.put("_dynSessConf",_dynSessConf);
            jsonObject.put("productId",productId);
            jsonObject.put("scheduleDate",scheduleDate);
            jsonObject.put("productName",productName);
            jsonObject.put("warehouseID",warehouseID);
            jsonObject.put("planOrderQuantity",planOrderQuantity);
            jsonObject.put("logisticsID",logisticsID);
            jsonObject.put("notes",notes);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        rp.setBodyContent(jsonObject.toString());
        Log.d("传的值",jsonObject.toString()+"");
        Log.d("rp的值",rp+"");
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("需求计划的返回值",""+result);
                callback.CallbackPlanPresentationResuls(result);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

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
