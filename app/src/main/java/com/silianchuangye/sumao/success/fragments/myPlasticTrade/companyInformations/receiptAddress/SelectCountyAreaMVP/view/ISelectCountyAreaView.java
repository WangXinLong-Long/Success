package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.SelectCountyAreaMVP.view;

import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.SelectProvinceAreaMVP.bean.Area;

import java.util.List;

/**
 * Created by Administrator on 2016/7/28 0028.
 */
public interface ISelectCountyAreaView {
  void  initCountyView(List<Area> areas);
    void jumpVillageActivity(int position);

}
