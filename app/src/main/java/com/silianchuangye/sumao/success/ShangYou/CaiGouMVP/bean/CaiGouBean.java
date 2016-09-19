package com.silianchuangye.sumao.success.ShangYou.CaiGouMVP.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/9/14 0014.
 */
public class CaiGouBean implements Serializable {
    private VipPlanOrder vipPlanOrder;
    private String info;

    public VipPlanOrder getVipPlanOrder() {
        return vipPlanOrder;
    }

    public void setVipPlanOrder(VipPlanOrder vipPlanOrder) {
        this.vipPlanOrder = vipPlanOrder;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
