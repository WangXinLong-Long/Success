package com.silianchuangye.sumao.success.ShangYou.CaiGouMVP.model;

import com.silianchuangye.sumao.success.ShangYou.CaiGouMVP.bean.CaiGouBean;

/**
 * Created by Administrator on 2016/9/14 0014.
 */
public interface CaiGouCallback {
    void callbackCaiGou(CaiGouBean caiGouBean,String pageNum,String planStartDate,
                        String planEndDate, String planState, String warehouseName,
                        String logisticsID);
}
