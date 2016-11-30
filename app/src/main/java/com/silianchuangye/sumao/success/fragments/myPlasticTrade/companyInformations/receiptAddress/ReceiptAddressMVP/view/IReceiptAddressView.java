package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressMVP.view;

import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressMVP.bean.ReAddress;

import java.util.List;

/**
 * Created by Administrator on 2016/7/28 0028.
 */
public interface IReceiptAddressView {
   void  initReceiptAddressListView(List<ReAddress> address);
    void onReceiptAddressListViewClick(int position);
}
