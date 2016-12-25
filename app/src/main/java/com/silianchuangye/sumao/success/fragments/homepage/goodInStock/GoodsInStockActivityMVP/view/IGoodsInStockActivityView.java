package com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.view;

import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.bean.GoodsInStockActivityBean;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.GoodsInStockDetailActivityMVP.bean.GoodsInStockDetailBean;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleDetailActivityMVP.bean.PreSaleDetailBean;

/**
 * Created by Administrator on 2016/8/12 0012.
 */
public interface IGoodsInStockActivityView {
//    把请求下来的数据存储到IGoodsInStockActivity中
    void setDataInActivity(GoodsInStockActivityBean goodsInStockActivityBean,String updateOrLoad);
//获取详情页的信息
    void getGoodsInStockDetailData(GoodsInStockDetailBean goodsInStockDetailBean);
//    在现货列表中获取到预售商品的详情
    void  getPreSaleDetailData(PreSaleDetailBean preSaleDetailBean,String sku);
}
