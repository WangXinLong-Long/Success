package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.SelectCityAreaMVP.presenter;

import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.SelectCityAreaMVP.model.IcityAreaCallbackListener;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.SelectCityAreaMVP.model.IselecteCityAreaModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.SelectCityAreaMVP.model.SelecteCityAreaModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.SelectCityAreaMVP.view.ISelecteCityAreaView;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.SelectProvinceAreaMVP.bean.Area;

import java.util.List;

/**
 * Created by Administrator on 2016/7/28 0028.
 */
public class SelecteCityAreaPresenter {
    ISelecteCityAreaView selecteCityAreaView;

    public SelecteCityAreaPresenter(ISelecteCityAreaView selecteCityAreaView) {
        this.selecteCityAreaView = selecteCityAreaView;
    }

    public void getCityInfo(String level){
        IselecteCityAreaModel model = new SelecteCityAreaModel(level);
        model.requestCityInfo(new IcityAreaCallbackListener() {
            @Override
            public void callbackCityInfor(List<Area> areas) {
                selecteCityAreaView.initCityView(areas);
            }
        });
    }
    public  void jumpCounty(int position){
        selecteCityAreaView.jumpCountryActivity(position);
    }
}
