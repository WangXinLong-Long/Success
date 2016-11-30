package com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.HelpAndFeedbackMVP.bean;

import java.io.Serializable;
import java.util.List;


/**
 * Created by Administrator on 2016/10/17 0017.
 */
public class HelpAndFeedbackBean implements Serializable {
    private String start;
    private List<HelpAndFeedbackInfo> result;

    public List<HelpAndFeedbackInfo> getResult() {
        return result;
    }

    public void setResult(List<HelpAndFeedbackInfo> result) {
        this.result = result;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }
}
