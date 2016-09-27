package com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.SettingMVP.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/9/23 0023.
 */
public class ResultBean implements Serializable {
    String helpsort;
    List<InformationBean> information;

    public String getHelpsort() {
        return helpsort;
    }

    public void setHelpsort(String helpsort) {
        this.helpsort = helpsort;
    }

    public List<InformationBean> getInformation() {
        return information;
    }

    public void setInformation(List<InformationBean> information) {
        this.information = information;
    }
}
