package com.silianchuangye.sumao.success.fragments.myPlasticTrade.demandPlanning.PlanQueryMVP.model;

import android.util.Log;

import com.silianchuangye.sumao.success.utils.SuMaoConstant;

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
    private String sellerEnterpriseId;
    private String _dynSessConf;
    private boolean updateLoad;

    public PlanQueryModel(String pageNum, String pageSize, String productName, String planStartDate, String planEndDate, String planState, String enterpriseName, String demandId, String sellerEnterpriseId, String _dynSessConf, boolean updateLoad) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.productName = productName;
        this.planStartDate = planStartDate;
        this.planEndDate = planEndDate;
        this.planState = planState;
        this.enterpriseName = enterpriseName;
        this.demandId = demandId;
        this.sellerEnterpriseId = sellerEnterpriseId;
        this._dynSessConf = _dynSessConf;
        this.updateLoad = updateLoad;
    }

    @Override
    public void getPlanQueryResult(final PlanQueryCallback callback) {
//        String result = null;
//        callback.callbackPlanQueryResult(result);
        String url= SuMaoConstant.SUMAO_IP+"/rest/model/com/sumao/mobile/order/purchase/PlanOrderActor/vipPlanOrderQuery";
        RequestParams rp=new RequestParams(url);
        rp.addParameter("pageNum",pageNum);
        rp.addParameter("pageSize",pageSize);
        rp.addParameter("productName",productName);
        rp.addParameter("planStartDate",planStartDate);
        rp.addParameter("planEndDate",planEndDate);
        rp.addParameter("planState",planState);
        rp.addParameter("enterpriseName",enterpriseName);
        rp.addParameter("demandId",demandId);
        rp.addParameter("sellerEnterpriseId",sellerEnterpriseId);
        rp.addParameter("_dynSessConf",_dynSessConf);
        Log.d("查询的rp",rp+"");
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
