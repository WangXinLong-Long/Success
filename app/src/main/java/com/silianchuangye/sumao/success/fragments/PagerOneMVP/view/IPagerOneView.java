package com.silianchuangye.sumao.success.fragments.PagerOneMVP.view;

import android.graphics.drawable.Drawable;

import com.silianchuangye.sumao.success.fragments.PagerOneMVP.bean.AnnounceBean;
import com.silianchuangye.sumao.success.fragments.PagerOneMVP.bean.BannerBean;

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
}
