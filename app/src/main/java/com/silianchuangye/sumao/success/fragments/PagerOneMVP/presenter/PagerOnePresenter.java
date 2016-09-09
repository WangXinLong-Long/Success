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
import com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.model.IUpstreamDirectSellingModel;
import com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.model.UpstreamDirectSellingCallback;
import com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.model.UpstreamDirectSellingModel;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.bean.GoodsInStockActivityBean;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.model.GoodsInStockActivityCallback;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.model.GoodsInStockActivityModel;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.model.IGoodsInStockActivityModel;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.bean.PreSaleDetailCalendarBean;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.model.CalendarModel.CalendarCallback;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.model.CalendarModel.CalendarModels;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.model.CalendarModel.ICalendarModel;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleMVP.bean.PreSaleBean;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleMVP.model.IPreSaleModel;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleMVP.model.PreSaleCallback;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleMVP.model.PreSaleModel;
import com.silianchuangye.sumao.success.fragments.homepage.sumaoconsultMVP.model.ISuMaoConsultModel;
import com.silianchuangye.sumao.success.fragments.homepage.sumaoconsultMVP.model.SuMaoConsultCallback;
import com.silianchuangye.sumao.success.fragments.homepage.sumaoconsultMVP.model.SuMaoConsultModel;
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
//      滚动条
    public void getPictureFromServerToPagerOneFragment(String url){
        IGetPictureModel getPictureModel = new GetPictureModels(url);
        getPictureModel.getImageFromServer(new GetPictureCallback() {
            @Override
            public void callbackGetPicture(Drawable result) {
                pagerOne.savePictureInPagerOneCollection(result);
            }
        });

    }
//    公告
    public void getAnnouncementInfoToPagerOneFragment(){
        IAnnouncementModel announcementModel = new AnnouncementModel();
        announcementModel.getAnnouncementInfo(new AnnouncementCallback() {
            @Override
            public void callbackAnnouncement(AnnounceBean announceBean) {
                pagerOne.saveAnnounceInAnnounceList(announceBean);
            }
        });
    }
//      预售信息
    public void getHomeSaleInfoToPagerOneFragment(){
        IHomeSaleModel homeSaleModel = new HomeSaleModels();
        homeSaleModel.getHomeSaleInfo(new HomeSaleCallback() {
            @Override
            public void callbackHomeSale(PreSaleBean preSaleBean) {
                pagerOne.saveHomeSaleInFragmentList(preSaleBean);
            }
        });
    }
//      现货
    public   void getGoodsInStockInfo(String region2,String classification2,String application2,int Nrpp,int No){
        IGoodsInStockActivityModel goodsInStockActivityModel = new GoodsInStockActivityModel(region2,classification2,application2,Nrpp,No);
        goodsInStockActivityModel.getGoodsInStockActivityResult(new GoodsInStockActivityCallback() {
            @Override
            public void callbackInGoodsInStockActivity(GoodsInStockActivityBean goodsInStockActivityBean) {
                pagerOne.setDataInActivity(goodsInStockActivityBean);
            }
        });
    }
//      预售
    public   void getPreSaleInfo(String region2,String classification2,String application2,int Nrpp,int No){
        IPreSaleModel preSaleModel = new PreSaleModel(region2,classification2,application2,Nrpp,No);
        preSaleModel.getPreSaleResult(new PreSaleCallback() {
            @Override
            public void callbackPreSale(GoodsInStockActivityBean goodsInStockActivityBean) {
                pagerOne.setPreSaleDataInActivity(goodsInStockActivityBean);
            }
        });
    }
//      塑贸资讯
    public void getSuMaoConsultInfo(){
        ISuMaoConsultModel suMaoConsultModel = new SuMaoConsultModel();
        suMaoConsultModel.getSuMaoConsultInfo(new SuMaoConsultCallback() {
            @Override
            public void callbackSuMaoConsult(AnnounceBean announceBean) {
                pagerOne.setSuMaoConsultInActivity(announceBean);
            }
        });
    }
//      上游直销
    public void getUpstreamDirectSellingInfo(){
        IUpstreamDirectSellingModel upstreamDirectSellingModel = new UpstreamDirectSellingModel();
        upstreamDirectSellingModel.getUpstreamDirectSellingInfo(new UpstreamDirectSellingCallback() {
            @Override
            public void callbackUpstreamDirectSelling() {
                pagerOne.setUpstreamDirectSellingInActivity();
            }
        });
    }
}
