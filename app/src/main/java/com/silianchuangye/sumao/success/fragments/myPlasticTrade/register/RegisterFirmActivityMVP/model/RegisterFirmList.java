package com.silianchuangye.sumao.success.fragments.myPlasticTrade.register.RegisterFirmActivityMVP.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/9/2 0002.
 */
public class RegisterFirmList implements Serializable{
    String name;
    String value;

    public RegisterFirmList(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
