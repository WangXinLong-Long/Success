package com.silianchuangye.sumao.success.fragments.homepage.groupbuying.GroupBuyingMVP.view;

/**
 * Created by Jobs Created on 2017/1/11.
 */
public interface IGroupBuyingView {
//    获取列表页内容
    void setGroupBuyingResult(String result,boolean updateOrLoad);
//      获取详情页内容
    void setGroupBuyingDetailResult(String result,String state);
}
