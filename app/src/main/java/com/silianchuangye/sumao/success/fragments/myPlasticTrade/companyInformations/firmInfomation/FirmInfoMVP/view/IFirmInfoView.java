package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.FirmInfoMVP.view;

import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.FirmInfoUpdateMVP.bean.FirmInfoUpdateActivityBean;
import com.silianchuangye.sumao.success.model.DifferentTypes;

/**
 * Created by Administrator on 2016/9/7 0007.
 */
public interface IFirmInfoView {
    //    获取企业信息
    void setInfoInActivity(FirmInfoUpdateActivityBean firmInfoUpdateActivityBean);
    //    设置收货地址字
    void setStringInText(String result);
    //    获取值列表
    void initFirmInfoTypeActivityView(DifferentTypes differentTypes);
}
