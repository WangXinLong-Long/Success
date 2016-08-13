package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.SelectProvinceAreaMVP.presenter;

import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.SelectProvinceAreaMVP.bean.Area;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.SelectProvinceAreaMVP.model.IRequestProvinceResult;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.SelectProvinceAreaMVP.model.IResultCallbackListener;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.SelectProvinceAreaMVP.model.RequestProvinceResult;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.SelectProvinceAreaMVP.view.ISelectProvinceAreaView;

import java.util.List;

/**
 * Created by Administrator on 2016/7/28 0028.
 */
public class SelectProvinceAreaPresenter {
    ISelectProvinceAreaView selectProvinceAreaView;
    IRequestProvinceResult requestProvinceResult = new RequestProvinceResult();
    public SelectProvinceAreaPresenter(ISelectProvinceAreaView selectProvinceAreaView) {
        this.selectProvinceAreaView = selectProvinceAreaView;
    }

    public void putResultInView(){
        requestProvinceResult.requestRrovinceResult(new IResultCallbackListener() {
            @Override
            public void setProvinceData(List<Area> provs) {
                selectProvinceAreaView.initProvinceAreaView(provs);
            }
        });
    }
    public void JumpToCity(int position){
        selectProvinceAreaView.onListItemClick(position);
    }
}
