package com.silianchuangye.sumao.success.fragments.homepage.groupbuying.GroupBuyingMVP.presenter;

import com.silianchuangye.sumao.success.fragments.homepage.groupbuying.GroupBuyingMVP.model.groupBuyingDetailResultModel.GroupBuyingDetailResultCallback;
import com.silianchuangye.sumao.success.fragments.homepage.groupbuying.GroupBuyingMVP.model.groupBuyingDetailResultModel.GroupBuyingDetailResultModel;
import com.silianchuangye.sumao.success.fragments.homepage.groupbuying.GroupBuyingMVP.model.groupBuyingDetailResultModel.IGroupBuyingDetailResultModel;
import com.silianchuangye.sumao.success.fragments.homepage.groupbuying.GroupBuyingMVP.model.groupBuyingModel.GroupBuyingCallback;
import com.silianchuangye.sumao.success.fragments.homepage.groupbuying.GroupBuyingMVP.model.groupBuyingModel.GroupBuyingModel;
import com.silianchuangye.sumao.success.fragments.homepage.groupbuying.GroupBuyingMVP.model.groupBuyingModel.IGroupBuyingModel;
import com.silianchuangye.sumao.success.fragments.homepage.groupbuying.GroupBuyingMVP.view.IGroupBuyingView;

/**
 * Created by Jobs Created on 2017/1/11.
 */
public class GroupBuyingPresenter {
    private IGroupBuyingView groupBuyingView;

    public GroupBuyingPresenter(IGroupBuyingView groupBuyingView) {
        this.groupBuyingView = groupBuyingView;
    }

    public void getGroupBuyingResult(boolean updateOrLoad){
        IGroupBuyingModel groupBuyingModel = new GroupBuyingModel( updateOrLoad);
        groupBuyingModel.getGroupBuyingResult(new GroupBuyingCallback() {
            @Override
            public void callbackGroupBuying(String result,boolean updateOrLoad) {
                groupBuyingView.setGroupBuyingResult(result, updateOrLoad);
            }
        });
    }

    public void getGroupBuyingDetailResult(String productId,String state){
        IGroupBuyingDetailResultModel groupBuyingDetailResultModel = new GroupBuyingDetailResultModel( productId,state);
        groupBuyingDetailResultModel.getGroupBuyingDetailResultResult(new GroupBuyingDetailResultCallback() {
            @Override
            public void callbackGroupBuyingDetailResult(String result,String state) {
                groupBuyingView.setGroupBuyingDetailResult(result,state);
            }
        });
    }
}
