package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.SelectCityAreaMVP.view;

import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.SelectProvinceAreaMVP.bean.Area;

import java.util.List;

/**
 * Created by Administrator on 2016/7/28 0028.
 */
public interface ISelecteCityAreaView {
    void initCityView(List<Area> areas);
    void jumpCountryActivity(int position);
}
