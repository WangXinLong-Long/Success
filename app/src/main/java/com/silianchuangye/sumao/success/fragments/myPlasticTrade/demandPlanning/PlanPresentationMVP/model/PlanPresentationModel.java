package com.silianchuangye.sumao.success.fragments.myPlasticTrade.demandPlanning.PlanPresentationMVP.model;

/**
 * Created by Jobs Created on 2017/2/10.
 */
public class PlanPresentationModel implements IPlanPresentationModel {

    @Override
    public void getPlanPresentationResuls(PlanPresentationCallback callback) {
        String result = null;
        callback.CallbackPlanPresentationResuls(result);
    }
}
