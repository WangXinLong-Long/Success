package com.silianchuangye.sumao.success.ShangYou.SeePlanMVP.view;

import com.silianchuangye.sumao.success.ShangYou.CaiGouMVP.bean.CaiGouBean;

/**
 * Created by Administrator on 2016/9/18 0018.
 */
public interface ISeePlanView {
    void getSeePlanInfoInFragment(CaiGouBean caiGouBean,String pageNum, String planStartDate,
                                  String planEndDate, String planState, String warehouseName,
                                  String logisticsID);
}
