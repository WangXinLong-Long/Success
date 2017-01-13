package com.silianchuangye.sumao.success.fragments.pagerThreeMVP.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jobs Created on 2017/1/11.
 */
public class OrderErrorBean implements Serializable {

    /**
     * formError : true
     * formExceptions : [{"localizedMessage":"【三菱M7026U】已下架，请删除后重新提交购物车","errorCode":"atg.droplet.DropletException"}]
     */

    private boolean formError;
    private List<FormExceptionsBean> formExceptions;

    public boolean isFormError() {
        return formError;
    }

    public void setFormError(boolean formError) {
        this.formError = formError;
    }

    public List<FormExceptionsBean> getFormExceptions() {
        return formExceptions;
    }

    public void setFormExceptions(List<FormExceptionsBean> formExceptions) {
        this.formExceptions = formExceptions;
    }

    public static class FormExceptionsBean implements Serializable{
        /**
         * localizedMessage : 【三菱M7026U】已下架，请删除后重新提交购物车
         * errorCode : atg.droplet.DropletException
         */

        private String localizedMessage;
        private String errorCode;

        public String getLocalizedMessage() {
            return localizedMessage;
        }

        public void setLocalizedMessage(String localizedMessage) {
            this.localizedMessage = localizedMessage;
        }

        public String getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }
    }
}
