package com.silianchuangye.sumao.success.fragments.myPlasticTrade.demandPlanning.PlanQueryMVP.presenter;

import com.silianchuangye.sumao.success.fragments.myPlasticTrade.demandPlanning.PlanPresentationMVP.model.IPlanPresentationModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.demandPlanning.PlanPresentationMVP.model.PlanPresentationModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.demandPlanning.PlanQueryMVP.model.IPlanQueryModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.demandPlanning.PlanQueryMVP.model.PlanQueryCallback;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.demandPlanning.PlanQueryMVP.model.PlanQueryModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.demandPlanning.PlanQueryMVP.view.IPlanQueryView;

/**
 * Created by Jobs Created on 2017/2/10.
 */
public class PlanQueryPresenter {
    IPlanQueryView planQueryView;

    public PlanQueryPresenter(IPlanQueryView planQueryView) {
        this.planQueryView = planQueryView;
    }

    public void getList(String pageNum, String pageSize, String productName, String planStartDate, String planEndDate, String planState, String warehouseName, String logisticsID, String sellerEnterpriseId,String _dynSessConf,boolean updateLoad){
        IPlanQueryModel iPlanPresentationModel=new PlanQueryModel(pageNum,pageSize,productName,planStartDate,planEndDate,planState,warehouseName,logisticsID,sellerEnterpriseId,_dynSessConf,updateLoad);
        iPlanPresentationModel.getPlanQueryResult(new PlanQueryCallback() {
            @Override
            public void callbackPlanQueryResult(String result, boolean updateLoad) {
                planQueryView.setPlan(result,updateLoad);


            }
        });

    }


}
