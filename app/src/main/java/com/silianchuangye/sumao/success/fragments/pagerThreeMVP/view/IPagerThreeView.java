package com.silianchuangye.sumao.success.fragments.pagerThreeMVP.view;

/**
 * Created by Jobs Created on 2016/12/23.
 */
public interface IPagerThreeView {

    void setDataInPagerThree(String result);

//    删除商品
    void setDeleteGoodsData(String result,int position);

//    获取商品信息
    void setCarsUpdate(String result);

//    生成订单数据，传递到订单页
    void sendDataToOrderPage(  String result);
}
