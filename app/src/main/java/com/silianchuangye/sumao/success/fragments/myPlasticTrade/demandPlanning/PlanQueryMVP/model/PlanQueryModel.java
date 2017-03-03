package com.silianchuangye.sumao.success.fragments.myPlasticTrade.demandPlanning.PlanQueryMVP.model;

/**
 * Created by Jobs Created on 2017/2/10.
 */
public class PlanQueryModel implements IPlanQueryModel {
    @Override
    public void getPlanQueryResult(PlanQueryCallback callback) {
        String result = null;
        callback.callbackPlanQueryResult(result);
    }
}
