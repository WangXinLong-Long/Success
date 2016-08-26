package com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.FirmInfoPicture.FirmInfoPictureMVP.presenter;

import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.FirmInfoPicture.FirmInfoPictureMVP.model.FirmInfoPictureCallback;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.FirmInfoPicture.FirmInfoPictureMVP.model.FirmInfoPictureModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.FirmInfoPicture.FirmInfoPictureMVP.model.IFirmInfoPictureModel;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.companyInformations.firmInfomation.FirmInfoPicture.FirmInfoPictureMVP.view.IFirmInfoPictureView;

/**
 * Created by Administrator on 2016/8/24 0024.
 */
public class FirmInfoPicturePresenter {
    IFirmInfoPictureView firmInfoPictureView ;

    public FirmInfoPicturePresenter(IFirmInfoPictureView firmInfoPictureView) {
        this.firmInfoPictureView = firmInfoPictureView;
    }
    public void sendFirmInfoPictureInServer(String path){
        IFirmInfoPictureModel firmInfoPictureModel = new FirmInfoPictureModel(path);
        firmInfoPictureModel.getFirmInfoPictureInfo(new FirmInfoPictureCallback() {
            @Override
            public void callbackFirmInfoPicture(String info) {
                firmInfoPictureView.isPictureUploadInService(info);
            }
        });
    }
}
