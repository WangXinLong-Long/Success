package com.silianchuangye.sumao.success.fragments.homepage.auction.openAuction.bean;

import java.io.Serializable;

/**
 * Created by Jobs Created on 2017/1/5.
 */
public class OpenAuctionStateBean implements Serializable{

    /**
     * deposit_state : s2
     * flag : no
     * can_submit : n
     * can_deposit : n
     * msg : 竞拍结束，保证金未支付。
     */

    private String deposit_state;
    private String flag;
    private String can_submit;
    private String can_deposit;
    private String msg;

    public String getDeposit_state() {
        return deposit_state;
    }

    public void setDeposit_state(String deposit_state) {
        this.deposit_state = deposit_state;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCan_submit() {
        return can_submit;
    }

    public void setCan_submit(String can_submit) {
        this.can_submit = can_submit;
    }

    public String getCan_deposit() {
        return can_deposit;
    }

    public void setCan_deposit(String can_deposit) {
        this.can_deposit = can_deposit;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
