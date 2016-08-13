package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.SelectCountyAreaMVP.presenter;

import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.SelectCountyAreaMVP.model.IcountyAreaCallbackListener;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.SelectCountyAreaMVP.model.IselectCountyAreaModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.SelectCountyAreaMVP.model.SelectCountyAreaModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.SelectCountyAreaMVP.view.ISelectCountyAreaView;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.receiptAddress.SelectProvinceAreaMVP.bean.Area;

import java.util.List;

/**
 * Created by Administrator on 2016/7/28 0028.
 */
public class SelectCountyAreaPresenter  {
    ISelectCountyAreaView selectCountyAreaView;

    public SelectCountyAreaPresenter(ISelectCountyAreaView selectCountyAreaView) {
        this.selectCountyAreaView = selectCountyAreaView;
    }
    public void getCountyInfo(String level){
        IselectCountyAreaModel model = new SelectCountyAreaModel(level);
        model.requestCountyInfo(new IcountyAreaCallbackListener() {
            @Override
            public void callbackCountyInfor(List<Area> areas) {
                selectCountyAreaView.initCountyView(areas);
            }
        });
    }
    public  void jumpVillage(int position){
        selectCountyAreaView.jumpVillageActivity(position);
    }
}
