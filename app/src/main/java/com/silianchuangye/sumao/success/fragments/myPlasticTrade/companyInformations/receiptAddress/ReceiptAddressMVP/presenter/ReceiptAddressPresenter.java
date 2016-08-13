package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressMVP.presenter;

import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressMVP.bean.ReAddress;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressMVP.model.IreceiptAddressCallback;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressMVP.model.IreceiptAddressModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressMVP.model.ReceiptAddressModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressMVP.view.IReceiptAddressView;

import java.util.List;

/**
 * Created by Administrator on 2016/7/28 0028.
 */
public class ReceiptAddressPresenter {

    IReceiptAddressView receiptAddressView ;

    public ReceiptAddressPresenter(IReceiptAddressView receiptAddressView) {
        this.receiptAddressView = receiptAddressView;
    }
    public void setReceiptAddressListView(String sessinoId){
        IreceiptAddressModel receiptAddressModel = new ReceiptAddressModel(sessinoId);
        receiptAddressModel.getReceiptAddressInfo(new IreceiptAddressCallback() {
            @Override
            public void callbackReceiptAddressInfo(List<ReAddress> address) {
                receiptAddressView.initReceiptAddressListView(address);
            }
        });
    }

    public  void onReceiptAddressListViewClick(int position){
        receiptAddressView.onReceiptAddressListViewClick(position);
    }
}
