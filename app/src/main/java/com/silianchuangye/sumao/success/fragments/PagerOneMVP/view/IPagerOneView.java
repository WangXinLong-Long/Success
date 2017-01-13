package com.silianchuangye.sumao.success.fragments.PagerOneMVP.view;

import android.graphics.drawable.Drawable;

import com.silianchuangye.sumao.success.fragments.PagerOneMVP.bean.ApplicationBean;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.bean.BannerBean;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.bean.ClassificationBean;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.bean.SupplierBean;
import com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.bean.UpstreamDirectorySellingBean;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.bean.GoodsInStockActivityBean;

/**
 * Created by Administrator on 2016/8/16 0016.
 */
public interface IPagerOneView {
//    从服务器获取图片的地址放置到PagerOne中，要用
    void setPagerOneBannerDataInFragment(BannerBean bannerBean);
//    将获取到的图片，存在PagerOne的集合中
    void savePictureInPagerOneCollection(Drawable result);
//      点击现货按钮后，先进行网络请求
void setDataInActivity(GoodsInStockActivityBean goodsInStockActivityBean,String updateOrLoad);
//    z点击预售按钮后，进行网络请求
void setPreSaleDataInActivity(GoodsInStockActivityBean goodsInStockActivityBean,String updateOrLoad);
//    点击上游直销，进行网络请求
    void setUpstreamDirectSellingInActivity(UpstreamDirectorySellingBean upstreamDirectorySellingBean);
//    将GrideView请求下来的值，放置到List中
    void setGrideViewDataInActivity();
    //    获取分类列表
    void getClassificationData(ClassificationBean classificationBean,String transactionType);
    //    获取应用列表
    void getApplicationData( ApplicationBean applicationBean,String transactionType);
    //    获取供应商列表
    void getSupplier(SupplierBean supplierBean,String transactionType);

//    获取竞拍的数据
    void getAuctionData(String result);

//    获取竞拍列表的数据
    void setAuctionListResult(String result,String updateOrLoad);

//    根据首页的筛选条件，进行筛选
    void setDataInSearchActivity(GoodsInStockActivityBean goodsInStockActivityBean, String updateOrLoad,String searchField);

}
