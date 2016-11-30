package com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleMVP.view;

import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.bean.GoodsInStockActivityBean;

/**
 * Created by Administrator on 2016/8/15 0015.
 */
public interface IPreSaleView {
    //    把请求下来的数据存储到PreSaleActivity中
    void setDataInActivity(GoodsInStockActivityBean goodsInStockActivityBean);
}
