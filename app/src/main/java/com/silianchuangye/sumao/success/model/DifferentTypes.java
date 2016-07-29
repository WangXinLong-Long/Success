package com.silianchuangye.sumao.success.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/7/26 0026.
 */
public class DifferentTypes implements Serializable{
    List<EnterpriseInformation> cl_leixing;
    List<EnterpriseInformation> cl_zhengjian;
    List<EnterpriseInformation> cl_applyType;
    List<EnterpriseInformation> cl_nashuiren;

    public List<EnterpriseInformation> getCl_leixing() {
        return cl_leixing;
    }

    public void setCl_leixing(List<EnterpriseInformation> cl_leixing) {
        this.cl_leixing = cl_leixing;
    }

    public List<EnterpriseInformation> getCl_zhengjian() {
        return cl_zhengjian;
    }

    public void setCl_zhengjian(List<EnterpriseInformation> cl_zhengjian) {
        this.cl_zhengjian = cl_zhengjian;
    }

    public List<EnterpriseInformation> getCl_applyType() {
        return cl_applyType;
    }

    public void setCl_applyType(List<EnterpriseInformation> cl_applyType) {
        this.cl_applyType = cl_applyType;
    }

    public List<EnterpriseInformation> getCl_nashuiren() {
        return cl_nashuiren;
    }

    public void setCl_nashuiren(List<EnterpriseInformation> cl_nashuiren) {
        this.cl_nashuiren = cl_nashuiren;
    }
}
