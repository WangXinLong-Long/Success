package com.silianchuangye.sumao.success.ShangYou;

/**
 * Created by Administrator on 2016/9/6.
 */
public class DayPlanInfo {
//   类型
    String level;
//    名称
    String name;
//     是否删除
    String delete;

    public DayPlanInfo(String level, String name,String delete) {
        this.level = level;
        this.name = name;
        this.delete = delete;
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

    public String getDelete() {
        return delete;
    }

    public void setDelete(String delete) {
        this.delete = delete;
    }
}
