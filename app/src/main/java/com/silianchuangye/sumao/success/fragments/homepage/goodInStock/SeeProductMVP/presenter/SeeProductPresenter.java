package com.silianchuangye.sumao.success.fragments.homepage.goodInStock.SeeProductMVP.presenter;

import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.SeeProductMVP.bean.SeeProductBean;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.SeeProductMVP.model.ISeeProductModel;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.SeeProductMVP.model.SeeProductCallback;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.SeeProductMVP.model.SeeProductModel;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.SeeProductMVP.view.ISeeProductView;

/**
 * Created by Administrator on 2016/8/15 0015.
 */
public class SeeProductPresenter {
    ISeeProductView seeProductView ;

    public SeeProductPresenter(ISeeProductView seeProductView) {
        this.seeProductView = seeProductView;
    }
   public void  getSeeProductInfo(String sessionId){
       ISeeProductModel seeProductModel = new SeeProductModel(sessionId);
       seeProductModel.getSeeProductResult(new SeeProductCallback() {
           @Override
           public void callbackSeeProduct(SeeProductBean seeProductBean) {
               seeProductView.setDataInSeeProductActiity(seeProductBean);
           }
       });
   }
}
