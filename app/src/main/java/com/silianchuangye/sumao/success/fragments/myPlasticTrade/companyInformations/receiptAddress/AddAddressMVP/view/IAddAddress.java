package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.AddAddressMVP.view;

import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.AddAddressMVP.bean.AddAddressBean;

/**
 * Created by Administrator on 2016/8/2 0002.
 */
public interface IAddAddress {
//    给地址赋值
    void setAddressInText(String address);
//    上传服务器成功显示的结果

    void sendAddAddressToServerSuccess();
    //    上传服务器失败显示的结果
    void sendAddAddressToServerFaild();
    //    上传服务器的操作
    void sendAddAddressToServer();

}
