package com.silianchuangye.sumao.success.fragments.myPlasticTrade.demandPlanning.PlanPresentationMVP.presenter;

import com.silianchuangye.sumao.success.fragments.myPlasticTrade.demandPlanning.PlanPresentationMVP.model.IPlanPresentationModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.demandPlanning.PlanPresentationMVP.model.PlanPresentationCallback;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.demandPlanning.PlanPresentationMVP.model.PlanPresentationModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.demandPlanning.PlanPresentationMVP.view.IPlanPresentationView;
import com.silianchuangye.sumao.success.utils.loginStatusUtils.AspectJLoginStatus;

/**
 * Created by Jobs Created on 2017/2/10.
 */
public class PlanPresentationPresenter {
    IPlanPresentationView planPresentationView;

    public PlanPresentationPresenter(IPlanPresentationView planPresentationView) {
        this.planPresentationView = planPresentationView;
    }
    @AspectJLoginStatus("")
    public void setSubmitPlan(){
        IPlanPresentationModel planPresentationModel = new PlanPresentationModel();
        planPresentationModel.getPlanPresentationResuls(new PlanPresentationCallback() {
            @Override
            public void CallbackPlanPresentationResuls(String result) {
                planPresentationView.getSubmitResults(result);
            }
        });
    }
}
