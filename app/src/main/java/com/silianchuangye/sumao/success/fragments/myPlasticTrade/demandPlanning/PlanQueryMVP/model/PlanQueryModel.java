package com.silianchuangye.sumao.success.fragments.myPlasticTrade.demandPlanning.PlanQueryMVP.model;

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
public class PlanQueryModel implements IPlanQueryModel {
    private String pageNum;
    private String pageSize;
    private String productName;
    private String planStartDate;
    private String planEndDate;
    private String planState;
    private String enterpriseName;
    private String demandId;
    private String warehouseName;
    private String _dynSessConf;
    private boolean updateLoad;

    public PlanQueryModel(String pageNum, String pageSize, String productName, String planStartDate, String planEndDate, String planState, String enterpriseName, String demandId, String warehouseName, String _dynSessConf, boolean updateLoad) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.productName = productName;
        this.planStartDate = planStartDate;
        this.planEndDate = planEndDate;
        this.planState = planState;
        this.enterpriseName = enterpriseName;
        this.demandId = demandId;
        this.warehouseName = warehouseName;
        this._dynSessConf = _dynSessConf;
        this.updateLoad = updateLoad;
    }

    @Override
    public void getPlanQueryResult(final PlanQueryCallback callback) {
//        String result = null;
//        callback.callbackPlanQueryResult(result);
        String url= SuMaoConstant.SUMAO_IP+"/rest/model/com/sumao/mobile/order/purchase/PlanOrderActor/vipPlanOrderQuery";
        RequestParams rp=new RequestParams(url);

        rp.setAsJsonContent(true);
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("pageNum",pageNum);
            jsonObject.put("pageSize",pageSize);
            Log.d("produnctName",productName);
            if (!productName.equals("")) {
              jsonObject.put("productName",productName);
            }
            if (!planStartDate.equals("")){
                jsonObject.put("planStartDate",planStartDate);
            }
            if (!planEndDate.equals("")){
                jsonObject.put("planEndDate",planEndDate);
            }
           // if (!planState.equals("")||planState!=null){
               jsonObject.put("planState",planState);
           // }
            if (!enterpriseName.equals("")){
                jsonObject.put("enterpriseName",enterpriseName);
            }
            if (!demandId.equals("")){
                jsonObject.put("demandId",demandId);
            }
            if (!warehouseName.equals("")){
                jsonObject.put("warehouseName",warehouseName);
            }
            jsonObject.put("_dynSessConf",_dynSessConf);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        rp.setBodyContent(jsonObject+"");
        Log.d("查询的rp",rp+"");
        Log.d("输入的参数的值",jsonObject+"");
        x.http().post(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                callback.callbackPlanQueryResult(result,updateLoad);
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
