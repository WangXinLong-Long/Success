package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.SelectProvinceAreaMVP.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/7/28 0028.
 */
public class AreaResult implements Serializable {
    List<Area> result;
    String success;

    public List<Area> getResult() {
        return result;
    }

    public void setResult(List<Area> result) {
        this.result = result;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}
