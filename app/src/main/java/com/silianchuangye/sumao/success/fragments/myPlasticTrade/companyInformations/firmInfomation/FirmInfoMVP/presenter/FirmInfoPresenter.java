package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.FirmInfoMVP.presenter;

import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.addressDisplayMVP.model.AddressDisplayModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.addressDisplayMVP.model.IAddressDisplayCallback;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.addressDisplayMVP.model.IAddressDisplayModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.FirmInfoMVP.view.IFirmInfoView;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.FirmInfoUpdateMVP.bean.FirmInfoUpdateActivityBean;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.FirmInfoUpdateMVP.model.FirmInfoUpdateCallback;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.FirmInfoUpdateMVP.model.FirmInfoUpdateModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.FirmInfoUpdateMVP.model.IFirmInfoUpdateModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.FirmInfoUpdateMVP.model.valueModel.IValueModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.FirmInfoUpdateMVP.model.valueModel.ValueCallback;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.FirmInfoUpdateMVP.model.valueModel.ValueModel;
import com.silianchuangye.sumao.success.model.DifferentTypes;

/**
 * Created by Administrator on 2016/9/7 0007.
 */
public class FirmInfoPresenter {
    IFirmInfoView firmInfoView ;

    public FirmInfoPresenter(IFirmInfoView firmInfoView) {
        this.firmInfoView = firmInfoView;
    }

    //获取企业信息
    public void getFirmInfoUpdatePresenterInActivity() {
        IFirmInfoUpdateModel firmInfoUpdateModel = new FirmInfoUpdateModel();
        firmInfoUpdateModel.getFirmInfoUpdateInfo(new FirmInfoUpdateCallback() {
            @Override
            public void callbackFirmInfoUpdate(FirmInfoUpdateActivityBean firmInfoUpdateActivityBean) {
                firmInfoView.setInfoInActivity(firmInfoUpdateActivityBean);
            }
        });
    }

    //设置对应的地址
    public void setDetailAddress(String province,String city,String county){
        IAddressDisplayModel addressDisplayModel = new AddressDisplayModel(province,city,county,0);
        addressDisplayModel.getAddressDisplayInfo(new IAddressDisplayCallback() {
            @Override
            public void callbackAddressDisplayInfo(String address,int position) {
                firmInfoView.setStringInText(address);

            }
        });
    }

    //值列表
    public void setDataInView() {
        IValueModel userRegisterModel = new ValueModel();
        userRegisterModel.getEntInfo(new ValueCallback() {
            @Override
            public void setData(DifferentTypes differentTypes) {
                firmInfoView.initFirmInfoTypeActivityView(differentTypes);
            }
        });
    }
}
