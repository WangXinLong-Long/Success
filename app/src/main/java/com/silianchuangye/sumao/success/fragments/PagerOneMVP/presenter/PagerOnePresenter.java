package com.silianchuangye.sumao.success.fragments.PagerOneMVP.presenter;

import android.graphics.drawable.Drawable;

import com.silianchuangye.sumao.success.fragments.PagerOneMVP.bean.ApplicationBean;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.bean.BannerBean;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.bean.ClassificationBean;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.model.GetPictureModel.GetPictureCallback;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.model.GetPictureModel.GetPictureModels;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.model.GetPictureModel.IGetPictureModel;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.model.IPagerOneModel;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.model.PagerOneCallback;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.model.PagerOneModel;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.model.applicationModel.ApplicationCallback;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.model.applicationModel.ApplicationModel;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.model.applicationModel.IApplicationModel;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.model.auctionModel.AuctionCallback;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.model.auctionModel.AuctionModel;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.model.auctionModel.IAuctionModel;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.model.classificationModel.ClassificationCallback;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.model.classificationModel.ClassificationModel;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.model.classificationModel.IClassificationModel;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.model.grideViewModel.GrideViewCallback;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.model.grideViewModel.GrideViewModel;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.model.grideViewModel.IGrideViewModel;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.view.IPagerOneView;
import com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.bean.UpstreamDirectorySellingBean;
import com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.model.IUpstreamDirectSellingModel;
import com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.model.UpstreamDirectSellingCallback;
import com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.model.UpstreamDirectSellingModel;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.bean.GoodsInStockActivityBean;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.model.GoodsInStockActivityCallback;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.model.GoodsInStockActivityModel;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.model.IGoodsInStockActivityModel;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleMVP.model.IPreSaleModel;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleMVP.model.PreSaleCallback;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleMVP.model.PreSaleModel;

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
//      现货
    public   void  getGoodsInStockInfo(String pattern, String regions, String provinces, String cities,
                                       String categoryNames, String appNames, String gradeNums, String pageNum,String updateOrLoad){
        IGoodsInStockActivityModel goodsInStockActivityModel = new GoodsInStockActivityModel(pattern,  regions,  provinces,  cities,
                categoryNames,  appNames,  gradeNums,  pageNum,updateOrLoad);
        goodsInStockActivityModel.getGoodsInStockActivityResult(new GoodsInStockActivityCallback() {
            @Override
            public void callbackInGoodsInStockActivity(GoodsInStockActivityBean goodsInStockActivityBean,String updateOrLoad) {
                pagerOne.setDataInActivity(goodsInStockActivityBean,updateOrLoad);
            }
        });
    }
//      预售
    public   void getPreSaleInfo(String pattern, String regions, String provinces, String cities,
                                 String categoryNames, String appNames, String gradeNums, String pageNum,String updateOrLoad){
        IPreSaleModel preSaleModel = new PreSaleModel( pattern,  regions,  provinces,  cities,
                 categoryNames,  appNames,  gradeNums,  pageNum,updateOrLoad);
        preSaleModel.getPreSaleResult(new PreSaleCallback() {
            @Override
            public void callbackPreSale(GoodsInStockActivityBean goodsInStockActivityBean,String updateOrLoad) {
                pagerOne.setPreSaleDataInActivity(goodsInStockActivityBean,updateOrLoad);
            }
        });
    }
//      上游直销
    public void getUpstreamDirectSellingInfo(String sessionId){
        IUpstreamDirectSellingModel upstreamDirectSellingModel = new UpstreamDirectSellingModel(sessionId);
        upstreamDirectSellingModel.getUpstreamDirectSellingInfo(new UpstreamDirectSellingCallback() {
            @Override
            public void callbackUpstreamDirectSelling(UpstreamDirectorySellingBean upstreamDirectorySellingBean) {
                pagerOne.setUpstreamDirectSellingInActivity(upstreamDirectorySellingBean);
            }
        });
    }
//    获取地区、分类、应用的列表值
    public void getGridViewData(){
        IGrideViewModel grideViewModel = new GrideViewModel();
        grideViewModel.getGrideViewData(new GrideViewCallback() {
            @Override
            public void callbackGrideViewData() {
                pagerOne.setGrideViewDataInActivity();
            }
        });
    }

    public void getClassification(String transactionType){
        IClassificationModel classificationModel = new ClassificationModel(transactionType);
        classificationModel.getClassificationData(new ClassificationCallback() {
            @Override
            public void callbackClassification(ClassificationBean classificationBean,String transactionType) {
                pagerOne.getClassificationData( classificationBean,transactionType);
            }
        });
    }

    public void getApplication(String transactionType){
        IApplicationModel classificationModel = new ApplicationModel(transactionType);
        classificationModel.getCalssificationResult(new ApplicationCallback() {
            @Override
            public void callbackClassificationModel( ApplicationBean applicationBean,String transactionType) {
                pagerOne.getApplicationData(  applicationBean,transactionType);
            }
        });
    }

//    获取竞拍数据
    public void getAuctionResult(String productId){
        IAuctionModel auctionModel = new AuctionModel(productId);
        auctionModel.getAuctionModel(new AuctionCallback() {
            @Override
            public void callbackAuction(String result) {
                pagerOne.getAuctionData( result);
            }
        });
    }
}
