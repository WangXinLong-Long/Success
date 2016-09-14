package com.silianchuangye.sumao.success.ShangYou.PlanDayMVP.Bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/9/12 0012.
 */
public class PlanDayBean implements Serializable {
    private VipWareHouseAndDeliveryMethodSearch vipWareHouseAndDeliveryMethodSearch;
    private String info;

    public VipWareHouseAndDeliveryMethodSearch getVipWareHouseAndDeliveryMethodSearch() {
        return vipWareHouseAndDeliveryMethodSearch;
    }

    public void setVipWareHouseAndDeliveryMethodSearch(VipWareHouseAndDeliveryMethodSearch vipWareHouseAndDeliveryMethodSearch) {
        this.vipWareHouseAndDeliveryMethodSearch = vipWareHouseAndDeliveryMethodSearch;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
