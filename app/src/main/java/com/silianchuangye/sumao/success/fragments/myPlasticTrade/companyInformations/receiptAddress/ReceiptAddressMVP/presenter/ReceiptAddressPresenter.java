package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressMVP.presenter;

import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressMVP.bean.ReAddress;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressMVP.model.IReceiptAddressCallback;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressMVP.model.IReceiptAddressModel;
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
        IReceiptAddressModel receiptAddressModel = new ReceiptAddressModel(sessinoId);
        receiptAddressModel.getReceiptAddressInfo(new IReceiptAddressCallback() {
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
