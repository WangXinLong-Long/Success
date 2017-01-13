package com.silianchuangye.sumao.success.fragments.PagerOneMVP.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jobs Created on 2017/1/7.
 */
public class SupplierBean implements Serializable {

    private List<String> in_gysname;

    public List<String> getIn_gysname() {
        return in_gysname;
    }

    public void setIn_gysname(List<String> in_gysname) {
        this.in_gysname = in_gysname;
    }
}
