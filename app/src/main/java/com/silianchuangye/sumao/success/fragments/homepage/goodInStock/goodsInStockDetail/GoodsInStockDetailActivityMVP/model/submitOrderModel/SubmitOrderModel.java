package com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.GoodsInStockDetailActivityMVP.model.submitOrderModel;

import com.google.gson.Gson;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.GoodsInStockDetailActivityMVP.bean.Orders;
import com.silianchuangye.sumao.success.utils.LogUtils;
import com.silianchuangye.sumao.success.utils.SuMaoConstant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Jobs Created on 2016/12/19.
 */
public class SubmitOrderModel implements ISubmitOrderModel{
    private String quantity;
    private String _dynSessConf;
    private String productId;
    private String skuId;

    public SubmitOrderModel(String quantity, String _dynSessConf, String productId, String skuId) {
        this.quantity = quantity;
        this._dynSessConf = _dynSessConf;
        this.productId = productId;
        this.skuId = skuId;
    }

    @Override
    public void getSubmitOrderResult(final SubmitOrderCallback callback) {
        /*OrderList orderList = new OrderList();
        List<Orders> lists = new ArrayList<>();
        Orders orders = new Orders();
        List<CommerceItems> commerceItem = new ArrayList<>();
        CommerceItems commerceItems = new CommerceItems();
        commerceItems.setAmount(13+"");
        commerceItems.setAmountUnitScale("元");
        commerceItems.setCommerceItemId("2021"+1);
        commerceItems.setGradeNumber(13+"");
        commerceItems.setManufacturer("制造商");
        commerceItems.setParentCategories("。。。");
        commerceItems.setProductName("产品名称");
        commerceItems.setQuantity("13");
        commerceItems.setSalePrice("100");
        commerceItems.setSalesCompanyDisplayName("销售公司"+1);
        commerceItems.setWarehouse("仓库"+1);
        commerceItem.add(commerceItems);
        orders.setCommerceItem(commerceItem);
        orders.setOrderId("2022"+1);
        orders.setRemainingTime(System.currentTimeMillis()+"");
        orders.setTotal("13");
        lists.add(orders);
        orderList.setOrderIdList(lists);
        callback.callbackSubmitOrder(orderList);*/
        String url = SuMaoConstant.SUMAO_IP + "/rest/model/atg/commerce/ShoppingCartActor/go";
        RequestParams requestParams = new RequestParams(url);
        requestParams.addParameter("quantity", this.quantity);
        requestParams.addParameter("_dynSessConf", this._dynSessConf);
        requestParams.addParameter("productId", this.productId);
        requestParams.addParameter("skuId", this.skuId);
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            private Orders orderList;
            @Override
            public void onSuccess(String result) {
                LogUtils.log("提交订单result-->" + result);

                callback.callbackSubmitOrder(result);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtils.log("提交订单onError-->" + ex);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                LogUtils.log("提交订单onCancelled-->" + cex);
            }

            @Override
            public void onFinished() {
                LogUtils.log("提交订单onFinished-->" );
            }
        });
    }
}
