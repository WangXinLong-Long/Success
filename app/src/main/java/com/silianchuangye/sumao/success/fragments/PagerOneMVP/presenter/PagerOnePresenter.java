package com.silianchuangye.sumao.success.fragments.PagerOneMVP.presenter;

import android.graphics.drawable.Drawable;

import com.silianchuangye.sumao.success.fragments.PagerOneMVP.bean.BannerBean;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.model.GetPictureModel.GetPictureCallback;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.model.GetPictureModel.GetPictureModels;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.model.GetPictureModel.IGetPictureModel;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.model.IPagerOneModel;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.model.PagerOneCallback;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.model.PagerOneModel;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.view.IPagerOneView;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.view.PagerOne;

/**
 * Created by Administrator on 2016/8/16 0016.
 */
public class PagerOnePresenter {
    IPagerOneView pagerOne;

    public PagerOnePresenter(IPagerOneView pagerOne) {
        this.pagerOne = pagerOne;
    }
    public void getPagerOneInfoToPagerOneFragment(){
        IPagerOneModel pagerOneModel = new PagerOneModel();
        pagerOneModel.getPagerOneInfor(new PagerOneCallback() {
            @Override
            public void callbackPagerOne(BannerBean bannerBean) {
                pagerOne.setPagerOneBannerDataInFragment(bannerBean);
            }
        });
    }

    public void getPictureFromServerToPagerOneFragment(String url){
        IGetPictureModel getPictureModel = new GetPictureModels(url);
        getPictureModel.getImageFromServer(new GetPictureCallback() {
            @Override
            public void callbackGetPicture(Drawable result) {
                pagerOne.savePictureInPagerOneCollection(result);
            }
        });

    }
}
