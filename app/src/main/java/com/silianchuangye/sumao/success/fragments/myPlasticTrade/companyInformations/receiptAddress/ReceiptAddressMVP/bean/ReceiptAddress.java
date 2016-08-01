package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressMVP.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/7/28 0028.
 */
public class ReceiptAddress implements Serializable {
     List<ReAddress> address;
    String info;

    public List<ReAddress> getAddress() {
        return address;
    }

    public void setAddress(List<ReAddress> address) {
        this.address = address;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
