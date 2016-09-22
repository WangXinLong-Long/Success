package com.silianchuangye.sumao.success.ShangYou.PlanDayMVP.Bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/9/12 0012.
 */
public class DeliveryMethods implements Serializable{
    String deliveryMethodName;
    String deliveryMethodID;

    public String getDeliveryMethodName() {
        return deliveryMethodName;
    }

    public void setDeliveryMethodName(String deliveryMethodName) {
        this.deliveryMethodName = deliveryMethodName;
    }

    public String getDeliveryMethodID() {
        return deliveryMethodID;
    }

    public void setDeliveryMethodID(String deliveryMethodID) {
        this.deliveryMethodID = deliveryMethodID;
    }
}
