package com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.SettingMVP.bean;

import org.apache.log4j.xml.SAXErrorHandler;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/9/23 0023.
 */
public class FunctionIntroductionBean implements Serializable {
    String start;
    List<ResultBean> result;

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }
}
