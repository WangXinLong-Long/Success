package com.silianchuangye.sumao.success.ShangYou.PlanDayMVP.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/9/12 0012.
 */
public class VipWareHouseAndDeliveryMethodSearch implements Serializable {
    List<WareHouse> wareHouse;
    List<DeliveryMethods> deliveryMethods;

    public List<WareHouse> getWareHouse() {
        return wareHouse;
    }

    public void setWareHouse(List<WareHouse> wareHouse) {
        this.wareHouse = wareHouse;
    }

    public List<DeliveryMethods> getDeliveryMethods() {
        return deliveryMethods;
    }

    public void setDeliveryMethods(List<DeliveryMethods> deliveryMethods) {
        this.deliveryMethods = deliveryMethods;
    }
}
