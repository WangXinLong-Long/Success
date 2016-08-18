package com.silianchuangye.sumao.success.fragments.homepage.AnnouncementDetailMVP.presenter;

import com.silianchuangye.sumao.success.fragments.homepage.AnnouncementDetailMVP.bean.AnnouncementDetailBean;
import com.silianchuangye.sumao.success.fragments.homepage.AnnouncementDetailMVP.model.AnnouncementDetailCallback;
import com.silianchuangye.sumao.success.fragments.homepage.AnnouncementDetailMVP.model.AnnouncementDetailModel;
import com.silianchuangye.sumao.success.fragments.homepage.AnnouncementDetailMVP.model.IAnnouncementDetailModel;
import com.silianchuangye.sumao.success.fragments.homepage.AnnouncementDetailMVP.view.IAnnouncementDetailView;

/**
 * Created by Administrator on 2016/8/18 0018.
 */
public class AnnouncementDetailPresenter {
    IAnnouncementDetailView announcementDetailView;

    public AnnouncementDetailPresenter(IAnnouncementDetailView announcementDetailView) {
        this.announcementDetailView = announcementDetailView;
    }
    public void getAnnouncementDetailInfoInAnnouncementDetailActivity(String id){
        IAnnouncementDetailModel announcementDetailModel = new AnnouncementDetailModel(id);
        announcementDetailModel.getAnnouncementDetailInfo(new AnnouncementDetailCallback() {
            @Override
            public void callbackAnnouncementDetail(AnnouncementDetailBean announcementDetailBean1) {
                announcementDetailView.getHtmlCodeInActivity(announcementDetailBean1);
            }
        });
    }
}
