package com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.SpotOrderMVP.bean;

import java.io.Serializable;

/**
 * Created by Jobs Created on 2017/2/23.
 */
public class InvoiceInformationBean implements Serializable {

    /**
     * cl_kaipiao : 1
     * cl_kdgongsi : 1234567
     * cl_beizhu : dsbcfhsdjkf
     * cl_piaoju : 10290200000005_1487297868264_541.png,10290200000005_1487303624173_683.jpg
     * cl_id : 10290200000005
     * cl_kddanhao : 1234567890
     */

    private String cl_kaipiao;
    private String cl_kdgongsi;
    private String cl_beizhu;
    private String cl_piaoju;
    private String cl_id;
    private String cl_kddanhao;

    public String getCl_kaipiao() {
        return cl_kaipiao;
    }

    public void setCl_kaipiao(String cl_kaipiao) {
        this.cl_kaipiao = cl_kaipiao;
    }

    public String getCl_kdgongsi() {
        return cl_kdgongsi;
    }

    public void setCl_kdgongsi(String cl_kdgongsi) {
        this.cl_kdgongsi = cl_kdgongsi;
    }

    public String getCl_beizhu() {
        return cl_beizhu;
    }

    public void setCl_beizhu(String cl_beizhu) {
        this.cl_beizhu = cl_beizhu;
    }

    public String getCl_piaoju() {
        return cl_piaoju;
    }

    public void setCl_piaoju(String cl_piaoju) {
        this.cl_piaoju = cl_piaoju;
    }

    public String getCl_id() {
        return cl_id;
    }

    public void setCl_id(String cl_id) {
        this.cl_id = cl_id;
    }

    public String getCl_kddanhao() {
        return cl_kddanhao;
    }

    public void setCl_kddanhao(String cl_kddanhao) {
        this.cl_kddanhao = cl_kddanhao;
    }
}
