package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.ReceiptAddressDetailMVP.view;

/**
 * Created by Administrator on 2016/8/4 0004.
 */
public interface IReceiptAddressDetailView {
//    设为默认收货地址
    void defaultReceivingAddress(String result);
//    删除收货地址
    void removeReceiptAddress(String result);
//    请求地址，设置到text上
    void setDisplayAddressInAddress(String result);
//    将修改的结果保存到服务器后，成功的处理结果，页面回到上一级
    void savaModifyReceiptInformationSuccess();
    //    将修改的结果保存到服务器后，失败的处理结果，吐司框
    void savaModifyReceiptInformationFailed();
}
