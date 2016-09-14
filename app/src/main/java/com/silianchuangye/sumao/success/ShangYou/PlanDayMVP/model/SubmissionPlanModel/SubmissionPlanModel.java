package com.silianchuangye.sumao.success.ShangYou.PlanDayMVP.model.SubmissionPlanModel;

import com.google.gson.Gson;
import com.silianchuangye.sumao.success.ShangYou.PlanDayMVP.Bean.PlanDayBean;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2016/9/12 0012.
 */
public class SubmissionPlanModel implements ISubmissionPlanModel {
    String scheduleDate;
    String productName;
    String warehouseID;
    String planOrderQuantity;
    String logisticsID;
    String notes;
    String productId;
    String _dynSessConf;

    String url = SuMaoConstant.SUMAO_IP+"/rest/model/com/sumao/mobile/order/purchase/PlanOrderActor/demandScheduleInOrder";

    public SubmissionPlanModel(String scheduleDate, String productName, String warehouseID, String planOrderQuantity, String logisticsID, String notes, String productId, String _dynSessConf) {
        this.scheduleDate = scheduleDate;
        this.productName = productName;
        this.warehouseID = warehouseID;
        this.planOrderQuantity = planOrderQuantity;
        this.logisticsID = logisticsID;
        this.notes = notes;

        this.productId = productId;
        this._dynSessConf = _dynSessConf;

    }

    @Override
    public void sendSubmisssionPlan(final SubmissionPlanCallback callback) {
        RequestParams rp = new RequestParams(url);
        rp.setAsJsonContent(true);
        JSONObject jsonObject = new JSONObject();
        try {
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
        x.http().request(HttpMethod.POST, rp, new Callback.CommonCallback<String>() {
            private PlanDayBean planDayBean;

            @Override
            public void onSuccess(String result) {
                LogUtils.log("上游直销提交计划-->"+result);
                Gson gson = new Gson();
//                planDayBean = gson.fromJson(result,PlanDayBean.class);
                callback.callbackSubmissionPlan(result);
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
//
    }
}
