package com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.presenter;

import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.bean.GoodsInStockActivityBean;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.model.GoodsInStockActivityCallback;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.model.GoodsInStockActivityModel;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.model.IGoodsInStockActivityModel;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.view.IGoodsInStockActivityView;

/**
 * Created by Administrator on 2016/8/12 0012.
 */
public class GoodsInStockActivityPresenter {

IGoodsInStockActivityView goodsInStockActivityView;

    public GoodsInStockActivityPresenter(IGoodsInStockActivityView goodsInStockActivityView) {
        this.goodsInStockActivityView = goodsInStockActivityView;
    }

    public   void getGoodsInStockInfo(String region2,String classification2,String application2,int Nrpp,int No){
        IGoodsInStockActivityModel goodsInStockActivityModel = new GoodsInStockActivityModel(region2,classification2,application2,Nrpp,No);
        goodsInStockActivityModel.getGoodsInStockActivityResult(new GoodsInStockActivityCallback() {
            @Override
            public void callbackInGoodsInStockActivity(GoodsInStockActivityBean goodsInStockActivityBean) {
                goodsInStockActivityView.setDataInActivity(goodsInStockActivityBean);
            }
        });
    }

}
