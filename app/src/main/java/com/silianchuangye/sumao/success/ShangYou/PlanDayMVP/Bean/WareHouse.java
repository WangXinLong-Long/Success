package com.silianchuangye.sumao.success.ShangYou.PlanDayMVP.Bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/9/12 0012.
 */
public class WareHouse implements Serializable{
    String wareHouseName;
    String wareHouseID;

    public String getWareHouseName() {
        return wareHouseName;
    }

    public void setWareHouseName(String wareHouseName) {
        this.wareHouseName = wareHouseName;
    }

    public String getWareHouseID() {
        return wareHouseID;
    }

    public void setWareHouseID(String wareHouseID) {
        this.wareHouseID = wareHouseID;
    }
}
