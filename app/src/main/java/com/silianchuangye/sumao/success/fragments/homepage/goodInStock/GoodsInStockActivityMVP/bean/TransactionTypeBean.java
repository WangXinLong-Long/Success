package com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.bean;

import java.io.Serializable;

/**
 * Created by Jobs Created on 2016/12/21.
 */
public class TransactionTypeBean implements Serializable {
    private String name;
    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
