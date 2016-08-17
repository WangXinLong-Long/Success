package com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleMVP.presenter;

import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.bean.GoodsInStockActivityBean;
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

    public   void getPreSaleInfo(String region2,String classification2,String application2,int Nrpp,int No){
        IPreSaleModel preSaleModel = new PreSaleModel(region2,classification2,application2,Nrpp,No);
        preSaleModel.getPreSaleResult(new PreSaleCallback() {
            @Override
            public void callbackPreSale(GoodsInStockActivityBean goodsInStockActivityBean) {
                preSaleView.setDataInActivity(goodsInStockActivityBean);
            }
        });
    }

}
