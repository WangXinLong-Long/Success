package com.silianchuangye.sumao.success.ShangYou.PlanDayMVP.presenter;

import com.silianchuangye.sumao.success.ShangYou.PlanDayMVP.Bean.PlanDayBean;
import com.silianchuangye.sumao.success.ShangYou.PlanDayMVP.model.SubmissionPlanModel.ISubmissionPlanModel;
import com.silianchuangye.sumao.success.ShangYou.PlanDayMVP.model.SubmissionPlanModel.SubmissionPlanCallback;
import com.silianchuangye.sumao.success.ShangYou.PlanDayMVP.model.SubmissionPlanModel.SubmissionPlanModel;
import com.silianchuangye.sumao.success.ShangYou.PlanDayMVP.model.Warehousemodel.IWarehouseModel;
import com.silianchuangye.sumao.success.ShangYou.PlanDayMVP.model.Warehousemodel.WarehouseCallback;
import com.silianchuangye.sumao.success.ShangYou.PlanDayMVP.model.Warehousemodel.WarehouseModel;
import com.silianchuangye.sumao.success.ShangYou.PlanDayMVP.view.IPlanDayView;

/**
 * Created by Administrator on 2016/9/12 0012.
 */
public class PlanDayPresenter {
    IPlanDayView planDayView ;

    public PlanDayPresenter(IPlanDayView planDayView) {
        this.planDayView = planDayView;
    }
    public void getPlanDayWarehouse(String sessionID,String productId){
        IWarehouseModel warehouseModel = new WarehouseModel(sessionID,productId);
        warehouseModel.getWarehouseInfo(new WarehouseCallback() {
            @Override
            public void callbackWarehouse(PlanDayBean planDayBean) {
                planDayView.getProductWarehouse( planDayBean);
            }
        });
    }

    public void SubmissionPlan(String scheduleDate, String productName, String warehouseID, String planOrderQuantity, String logisticsID, String notes, String productId, String _dynSessConf){
        ISubmissionPlanModel submissionPlanModel = new SubmissionPlanModel( scheduleDate,  productName,  warehouseID,  planOrderQuantity,  logisticsID,  notes,  productId,  _dynSessConf);
        submissionPlanModel.sendSubmisssionPlan(new SubmissionPlanCallback() {
            @Override
            public void callbackSubmissionPlan(String result) {
                planDayView.returnSubmissionPlanState(result);
            }
        });

    }
}
