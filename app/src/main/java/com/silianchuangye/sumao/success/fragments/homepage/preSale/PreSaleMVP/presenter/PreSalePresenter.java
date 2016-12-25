package com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleMVP.presenter;

import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.bean.GoodsInStockActivityBean;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.bean.PreSaleDetailBean;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.model.IPreSaleDetailModel;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.model.PreSaleDetailCallback;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.model.PreSaleDetailModel;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleMVP.model.IPreSaleModel;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleMVP.model.PreSaleCallback;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleMVP.model.PreSaleModel;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleMVP.view.IPreSaleView;

/**
 * Created by Administrator on 2016/8/15 0015.
 */
public class PreSalePresenter {

    IPreSaleView preSaleView;

    public PreSalePresenter(IPreSaleView preSaleView) {
        this.preSaleView = preSaleView;
    }

    public   void getPreSaleInfo(String pattern, String regions, String provinces, String cities,
                                 String categoryNames, String appNames, String gradeNums, String pageNum,String updateOrLoad){
        IPreSaleModel preSaleModel = new PreSaleModel( pattern,  regions,  provinces,  cities,
                categoryNames,  appNames,  gradeNums,  pageNum ,updateOrLoad);
        preSaleModel.getPreSaleResult(new PreSaleCallback() {
            @Override
            public void callbackPreSale(GoodsInStockActivityBean goodsInStockActivityBean,String updateOrLoad) {
                preSaleView.setDataInActivity(goodsInStockActivityBean, updateOrLoad);
            }
        });
    }

    public void getPreSaleDetailInfo(String productId, String skuId, String cl_jiner) {
        IPreSaleDetailModel preSaleDetailModel = new PreSaleDetailModel(skuId, productId);
        preSaleDetailModel.getPreSaleDetailResult(new PreSaleDetailCallback() {
            @Override
            public void callbackPreSaleDetail(PreSaleDetailBean preSaleDetailBean,String skuId) {
                preSaleView.getPreSaleDetailData(preSaleDetailBean,skuId);
            }
        });
    }
}
