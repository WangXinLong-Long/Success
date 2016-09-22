package com.silianchuangye.sumao.success.fragments.PagerOneMVP.view;

import android.graphics.drawable.Drawable;

import com.silianchuangye.sumao.success.fragments.PagerOneMVP.bean.AnnounceBean;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.bean.BannerBean;
import com.silianchuangye.sumao.success.fragments.homepage.UpstreamDirectSellingMVP.bean.UpstreamDirectorySellingBean;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.bean.GoodsInStockActivityBean;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.bean.PreSaleDetailCalendarBean;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleMVP.bean.PreSaleBean;

/**
 * Created by Administrator on 2016/8/16 0016.
 */
public interface IPagerOneView {
//    从服务器获取图片的地址放置到PagerOne中，要用
    void setPagerOneBannerDataInFragment(BannerBean bannerBean);
//    将获取到的图片，存在PagerOne的集合中
    void savePictureInPagerOneCollection(Drawable result);
//    获取到公告栏的信息，并填充到数组中
    void saveAnnounceInAnnounceList(AnnounceBean announceBean);
//    获取首页的预售列表,现货列表，竞拍列表
    void saveHomeSaleInFragmentList(PreSaleBean preSaleBean);
//      点击现货按钮后，先进行网络请求
void setDataInActivity(GoodsInStockActivityBean goodsInStockActivityBean);
//    z点击预售按钮后，进行网络请求
void setPreSaleDataInActivity(GoodsInStockActivityBean goodsInStockActivityBean);
//    点击塑贸资讯按钮后，进行网络请求
    void setSuMaoConsultInActivity(AnnounceBean announceBean);
//    点击上游直销，进行网络请求
    void setUpstreamDirectSellingInActivity(UpstreamDirectorySellingBean upstreamDirectorySellingBean);

}
