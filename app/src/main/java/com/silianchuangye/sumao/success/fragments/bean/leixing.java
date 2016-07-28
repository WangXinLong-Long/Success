package com.silianchuangye.sumao.success.fragments.bean;

import com.silianchuangye.sumao.success.model.EnterpriseInformation;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/7/27 0027.
 */
class LeiXing implements Serializable {
    List<EnterpriseInformation> cl_leixing;

    public List<EnterpriseInformation> getCl_leixing() {
        return cl_leixing;
    }

    public void setCl_leixing(List<EnterpriseInformation> cl_leixing) {
        this.cl_leixing = cl_leixing;
    }
}
