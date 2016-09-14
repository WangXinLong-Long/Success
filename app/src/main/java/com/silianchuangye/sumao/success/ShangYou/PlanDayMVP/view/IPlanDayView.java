package com.silianchuangye.sumao.success.ShangYou.PlanDayMVP.view;

import com.silianchuangye.sumao.success.ShangYou.PlanDayMVP.Bean.PlanDayBean;

/**
 * Created by Administrator on 2016/9/8 0008.
 */
public interface IPlanDayView {
    void getProductWarehouse(PlanDayBean planDayBean);
    void returnSubmissionPlanState(String result);
}
