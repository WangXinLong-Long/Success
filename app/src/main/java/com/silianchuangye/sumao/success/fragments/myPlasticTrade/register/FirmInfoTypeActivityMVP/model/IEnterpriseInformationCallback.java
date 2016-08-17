package com.silianchuangye.sumao.success.fragments.myPlasticTrade.register.FirmInfoTypeActivityMVP.model;

import com.silianchuangye.sumao.success.model.EnterpriseInformation;

import java.util.List;

/**
 * Created by Administrator on 2016/7/26 0026.
 */
public interface IEnterpriseInformationCallback {
    void setData( List<EnterpriseInformation> cl_leixing);

    //void setAddData(List<EnterpriseInformation> cl_state);
}
