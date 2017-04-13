package com.silianchuangye.sumao.success.db;

import java.io.Serializable;

/**
 * Created by junjun on 2017/4/5.
 */
public class Search implements Serializable {
    private String name;

    public Search() {

    }

    public Search(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
