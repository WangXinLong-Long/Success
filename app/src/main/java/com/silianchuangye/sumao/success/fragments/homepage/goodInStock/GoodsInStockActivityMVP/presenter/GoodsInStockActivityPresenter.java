package com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.presenter;

import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.bean.GoodsInStockActivityBean;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.model.GoodsInStockActivityCallback;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.model.GoodsInStockActivityModel;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.model.IGoodsInStockActivityModel;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.view.IGoodsInStockActivityView;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.GoodsInStockDetailActivityMVP.bean.GoodsInStockDetailBean;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.GoodsInStockDetailActivityMVP.model.GoodsInStockDetailModel;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.GoodsInStockDetailActivityMVP.model.IGoodsInStockDetailModel;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.GoodsInStockDetailActivityMVP.model.goodsInStockDetailCallback;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.bean.PreSaleDetailBean;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.model.IPreSaleDetailModel;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.model.PreSaleDetailCallback;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.model.PreSaleDetailModel;

/**
 * Created by Administrator on 2016/8/12 0012.
 */
public class GoodsInStockActivityPresenter {

IGoodsInStockActivityView goodsInStockActivityView;

    public GoodsInStockActivityPresenter(IGoodsInStockActivityView goodsInStockActivityView) {
        this.goodsInStockActivityView = goodsInStockActivityView;
    }

    public   void getGoodsInStockInfo(String groups,String pattern, String regions, String provinces, String cities,
                                      String categoryNames, String appNames, String gradeNums, String pageNum,String updateOrLoad){
        IGoodsInStockActivityModel goodsInStockActivityModel = new GoodsInStockActivityModel(groups,pattern,  regions,  provinces,  cities,
                categoryNames,  appNames,  gradeNums,  pageNum,updateOrLoad);
        goodsInStockActivityModel.getGoodsInStockActivityResult(new GoodsInStockActivityCallback() {
            @Override
            public void callbackInGoodsInStockActivity(GoodsInStockActivityBean goodsInStockActivityBean,String updateOrLoad,String searchField) {
                goodsInStockActivityView.setDataInActivity(goodsInStockActivityBean,updateOrLoad);
            }
        });
    }

    public void getGoodInStockDetailInfo(String cl_id){
        IGoodsInStockDetailModel goodsInStockDetailModel = new GoodsInStockDetailModel(cl_id);
        goodsInStockDetailModel.getGoodsInStockDetailResult(new goodsInStockDetailCallback() {
            @Override
            public void callbackGoodsInStockDetail(GoodsInStockDetailBean goodsInStockDetailBean) {
                goodsInStockActivityView.getGoodsInStockDetailData(goodsInStockDetailBean);
            }
        });

    }

    public void getPreSaleDetailInfo(String productId, String skuId, String cl_jiner) {
        IPreSaleDetailModel preSaleDetailModel = new PreSaleDetailModel(skuId, productId);
        preSaleDetailModel.getPreSaleDetailResult(new PreSaleDetailCallback() {
            @Override
            public void callbackPreSaleDetail(PreSaleDetailBean preSaleDetailBean,String sku) {
                goodsInStockActivityView.getPreSaleDetailData(preSaleDetailBean,sku);
            }
        });
    }



}
