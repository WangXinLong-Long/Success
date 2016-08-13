package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressMVP.model;

import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressMVP.bean.ReAddress;

import java.util.List;

/**
 * Created by Administrator on 2016/7/28 0028.
 */
public interface IreceiptAddressCallback {
    void callbackReceiptAddressInfo( List<ReAddress> address);
}
