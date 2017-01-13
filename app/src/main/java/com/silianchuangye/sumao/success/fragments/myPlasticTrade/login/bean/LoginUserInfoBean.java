package com.silianchuangye.sumao.success.fragments.myPlasticTrade.login.bean;

import java.io.Serializable;

/**
 * Created by Jobs Created on 2017/1/11.
 */
public class LoginUserInfoBean implements Serializable {

    /**
     * U_type : 外部客户
     * U_name : 邓东山
     * U_group : 50
     * loginStatus : YES
     * U_clientID : 210005
     * U_user_status : 31
     * info : sucess
     */

    private String U_type;
    private String U_name;
    private String U_group;
    private String loginStatus;
    private String U_clientID;
    private String U_user_status;
    private String info;

    public String getU_type() {
        return U_type;
    }

    public void setU_type(String U_type) {
        this.U_type = U_type;
    }

    public String getU_name() {
        return U_name;
    }

    public void setU_name(String U_name) {
        this.U_name = U_name;
    }

    public String getU_group() {
        return U_group;
    }

    public void setU_group(String U_group) {
        this.U_group = U_group;
    }

    public String getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(String loginStatus) {
        this.loginStatus = loginStatus;
    }

    public String getU_clientID() {
        return U_clientID;
    }

    public void setU_clientID(String U_clientID) {
        this.U_clientID = U_clientID;
    }

    public String getU_user_status() {
        return U_user_status;
    }

    public void setU_user_status(String U_user_status) {
        this.U_user_status = U_user_status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
