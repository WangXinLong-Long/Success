package com.silianchuangye.sumao.success.fragments.PagerOneMVP.model.classificationModel;

import com.silianchuangye.sumao.success.fragments.PagerOneMVP.bean.ClassificationBean;

/**
 * Created by Jobs Created on 2016/12/22.
 */
public interface ClassificationCallback {
    void callbackClassification(ClassificationBean classificationBean,String transactionType);
}
