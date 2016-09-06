package com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleMVP.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/8/26 0026.
 */
public class Auction implements Serializable{
    private String cl_mingcheng;
    private String cl_diqu;
    private String cl_jine;
    private String cl_id;

    public String getCl_mingcheng() {
        return cl_mingcheng;
    }

    public void setCl_mingcheng(String cl_mingcheng) {
        this.cl_mingcheng = cl_mingcheng;
    }

    public String getCl_diqu() {
        return cl_diqu;
    }

    public void setCl_diqu(String cl_diqu) {
        this.cl_diqu = cl_diqu;
    }

    public String getCl_jine() {
        return cl_jine;
    }

    public void setCl_jine(String cl_jine) {
        this.cl_jine = cl_jine;
    }

    public String getCl_id() {
        return cl_id;
    }

    public void setCl_id(String cl_id) {
        this.cl_id = cl_id;
    }
}
