package com.silianchuangye.sumao.success.ShangYou;

/**
 * Created by Administrator on 2016/9/6.
 */
public class DayPlanInfo {
//   类型
    String level;
//    名称
    String name;


    public DayPlanInfo(String level, String name) {
        this.level = level;
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
