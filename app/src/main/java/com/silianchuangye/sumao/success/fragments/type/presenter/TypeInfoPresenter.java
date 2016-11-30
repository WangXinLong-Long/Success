package com.silianchuangye.sumao.success.fragments.type.presenter;

import com.silianchuangye.sumao.success.fragments.SearchActivityMVP.bean.SearchActivityBean;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.bean.GoodsInStockActivityBean;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.model.GoodsInStockActivityCallback;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.model.GoodsInStockActivityModel;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.model.IGoodsInStockActivityModel;
import com.silianchuangye.sumao.success.fragments.type.model.TypeInfoCallback;
import com.silianchuangye.sumao.success.fragments.type.model.TypeInfoModel;
import com.silianchuangye.sumao.success.fragments.type.view.ITypeInfoView;

/**
 * Created by Administrator on 2016/8/29 0029.
 */
public class TypeInfoPresenter {
    ITypeInfoView typeInfoView;

    public TypeInfoPresenter(ITypeInfoView typeInfoView) {
        this.typeInfoView = typeInfoView;
    }

    public void getSearchFromSever(String region2,String classification2,String application2,String tradingMethod,int Nrpp,int No,String Ntt) {
        TypeInfoModel typeInfoModel = new TypeInfoModel(region2, classification2, application2,tradingMethod, Nrpp, No,Ntt);
        typeInfoModel.searchMessageFromServer(new TypeInfoCallback() {
            @Override
            public void callbackTypeInfo(SearchActivityBean searchActivityBean) {
                typeInfoView.getTypeInfoInActivity( searchActivityBean);
            }
        });

    }
}
