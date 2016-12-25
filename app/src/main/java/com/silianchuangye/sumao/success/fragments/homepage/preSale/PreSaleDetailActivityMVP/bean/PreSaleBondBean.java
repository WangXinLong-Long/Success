package com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jobs Created on 2016/12/24.
 */
public class PreSaleBondBean implements Serializable {

    /**
     * bankList : [{"amount":"112447.35","balance":"112218.49","accountNumber":"11014967969003","bankType":"1","bankName":"平安银行"}]
     * info : sucess
     */

    private String info;
    private List<BankListBean> bankList;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<BankListBean> getBankList() {
        return bankList;
    }

    public void setBankList(List<BankListBean> bankList) {
        this.bankList = bankList;
    }

    public static class BankListBean implements Serializable {
        /**
         * amount : 112447.35
         * balance : 112218.49
         * accountNumber : 11014967969003
         * bankType : 1
         * bankName : 平安银行
         */

        private String amount;
        private String balance;
        private String accountNumber;
        private String bankType;
        private String bankName;

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public void setAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
        }

        public String getBankType() {
            return bankType;
        }

        public void setBankType(String bankType) {
            this.bankType = bankType;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }
    }
}
