package com.silianchuangye.sumao.success.fragments.myPlasticTrade.setting.ScoreMVP.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/10/17 0017.
 */
public class ScoreInfoBean implements Serializable {
    private String username;
    private String userscore;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserscore() {
        return userscore;
    }

    public void setUserscore(String userscore) {
        this.userscore = userscore;
    }
}
