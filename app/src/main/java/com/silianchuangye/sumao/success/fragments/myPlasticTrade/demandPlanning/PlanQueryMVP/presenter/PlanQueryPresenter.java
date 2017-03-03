package com.silianchuangye.sumao.success.fragments.myPlasticTrade.demandPlanning.PlanQueryMVP.presenter;

import com.silianchuangye.sumao.success.fragments.myPlasticTrade.demandPlanning.PlanQueryMVP.view.IPlanQueryView;

/**
 * Created by Jobs Created on 2017/2/10.
 */
public class PlanQueryPresenter {
    IPlanQueryView planQueryView;

    public PlanQueryPresenter(IPlanQueryView planQueryView) {
        this.planQueryView = planQueryView;
    }


}
