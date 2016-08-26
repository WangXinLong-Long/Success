package com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleMVP.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/8/19 0019.
 */
public class PreSaleBean implements Serializable {
    private List<Forward> forward;

    public List<Forward> getForward() {
        return forward;
    }

    public void setForward(List<Forward> forward) {
        this.forward = forward;
    }
}
