package com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.PayMentsOrderMVP.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jobs Created on 2016/12/19.
 */
public class BankListBean implements Serializable {

    /**
     * bankResult : [{"bankBalance":"112321.16","openedAccount":"true","bankName":"平安银行"}]
     * balance : 10
     * orderUrlType : fixedPricingOrder
     * remainingTime : 3596
     * payfor : order
     * info : ture
     */

    private String balance;
    private String orderUrlType;
    private String remainingTime;
    private String payfor;
    private String info;
    private List<BankResultBean> bankResult;

    public String getOrderUrlType() {
        return orderUrlType;
    }

    public void setOrderUrlType(String orderUrlType) {
        this.orderUrlType = orderUrlType;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(String remainingTime) {
        this.remainingTime = remainingTime;
    }

    public String getPayfor() {
        return payfor;
    }

    public void setPayfor(String payfor) {
        this.payfor = payfor;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<BankResultBean> getBankResult() {
        return bankResult;
    }

    public void setBankResult(List<BankResultBean> bankResult) {
        this.bankResult = bankResult;
    }

    public static class BankResultBean implements Serializable{
        /**
         * bankBalance : 112321.16
         * openedAccount : true
         * bankName : 平安银行
         */

        private String bankBalance;
        private String openedAccount;
        private String bankName;

        public String getBankBalance() {
            return bankBalance;
        }

        public void setBankBalance(String bankBalance) {
            this.bankBalance = bankBalance;
        }

        public String getOpenedAccount() {
            return openedAccount;
        }

        public void setOpenedAccount(String openedAccount) {
            this.openedAccount = openedAccount;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }
    }
}
