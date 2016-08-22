package com.silianchuangye.sumao.success.fragments.PagerOneMVP.presenter;

import android.graphics.drawable.Drawable;

import com.silianchuangye.sumao.success.fragments.PagerOneMVP.bean.AnnounceBean;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.bean.BannerBean;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.model.AnnouncementModel.AnnouncementCallback;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.model.AnnouncementModel.AnnouncementModel;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.model.AnnouncementModel.IAnnouncementModel;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.model.GetPictureModel.GetPictureCallback;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.model.GetPictureModel.GetPictureModels;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.model.GetPictureModel.IGetPictureModel;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.model.HomeSaleModel.HomeSaleCallback;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.model.HomeSaleModel.HomeSaleModels;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.model.HomeSaleModel.IHomeSaleModel;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.model.IPagerOneModel;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.model.PagerOneCallback;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.model.PagerOneModel;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.view.IPagerOneView;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.view.PagerOne;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.bean.PreSaleDetailCalendarBean;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.model.CalendarModel.CalendarCallback;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.model.CalendarModel.CalendarModels;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.model.CalendarModel.ICalendarModel;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleMVP.bean.PreSaleBean;
import com.silianchuangye.sumao.success.utils.LogUtils;

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
    public void getAnnouncementInfoToPagerOneFragment(){
        IAnnouncementModel announcementModel = new AnnouncementModel();
        announcementModel.getAnnouncementInfo(new AnnouncementCallback() {
            @Override
            public void callbackAnnouncement(AnnounceBean announceBean) {
                pagerOne.saveAnnounceInAnnounceList(announceBean);
            }
        });
    }

    public void getHomeSaleInfoToPagerOneFragment(){
        IHomeSaleModel homeSaleModel = new HomeSaleModels();
        homeSaleModel.getHomeSaleInfo(new HomeSaleCallback() {
            @Override
            public void callbackHomeSale(PreSaleBean preSaleBean) {
                pagerOne.saveHomeSaleInFragmentList(preSaleBean);
            }
        });
    }


}
