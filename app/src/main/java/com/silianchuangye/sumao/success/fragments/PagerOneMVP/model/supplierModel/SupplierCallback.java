package com.silianchuangye.sumao.success.fragments.PagerOneMVP.model.supplierModel;

import com.silianchuangye.sumao.success.fragments.PagerOneMVP.bean.SupplierBean;

/**
 * Created by Jobs Created on 2017/1/7.
 */
public interface SupplierCallback {
    void callbackSupplier( SupplierBean supplierBean,String transactionType);
}
