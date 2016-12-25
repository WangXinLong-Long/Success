package com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.PayMentsOrderMVP.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.GoodsInStockDetailActivityMVP.bean.Orders;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jobs Created on 2016/12/19.
 */
public class PayMentsOrderListViewAdapter extends BaseAdapter {
    private Context context;
    List<Orders.OrderIdListBean> orderIdListBeans;

    public PayMentsOrderListViewAdapter(Context context) {
        this.context = context;
        orderIdListBeans = new ArrayList<>();
    }

    public void addLists( List<Orders.OrderIdListBean> orderIdListBean) {
        this.orderIdListBeans.addAll(orderIdListBean) ;
//        lists.addAll(this.orderIdListBean.getCommerceItem());
    }


    @Override
    public int getCount() {
        return orderIdListBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return orderIdListBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(this.context).inflate(R.layout.order_item, null);
            //订单编号
            viewHolder.product_order_number = (TextView) convertView.findViewById(R.id.product_order_number);
            //金额
            viewHolder.money_number = ((TextView) convertView.findViewById(R.id.money_number));
            //剩余时间
            viewHolder.residual_time = ((TextView) convertView.findViewById(R.id.residual_time));
            //产品名称
            viewHolder.tv_name = ((TextView) convertView.findViewById(R.id.tv_name));
            //品种
            viewHolder.surplus_amount_et = ((TextView) convertView.findViewById(R.id.surplus_amount_et));
            //产品牌号
            viewHolder.purchase_quantity_et = ((TextView) convertView.findViewById(R.id.purchase_quantity_et));
            //生产企业
            viewHolder.min_variable_et = ((TextView) convertView.findViewById(R.id.min_variable_et));
            //仓库
            viewHolder.delivery_time_et = ((TextView) convertView.findViewById(R.id.delivery_time_et));
            //产品单价
            viewHolder.classification_pre_sale_et = ((TextView) convertView.findViewById(R.id.classification_pre_sale_et));
            //数量
            viewHolder.warehouse_et = ((TextView) convertView.findViewById(R.id.warehouse_et));
            //产品总价
            viewHolder.region_et = ((TextView) convertView.findViewById(R.id.region_et));
            //公司
            viewHolder.company_et = ((TextView) convertView.findViewById(R.id.company_et));
            convertView.setTag(viewHolder);
        } else {
            viewHolder = ((ViewHolder) convertView.getTag());
        }
        Orders.OrderIdListBean orderIdListBean = this.orderIdListBeans.get(position);
        Orders.OrderIdListBean.CommerceItemBean commerceItemBean = orderIdListBean.getCommerceItem().get(0);
        //订单编号
        viewHolder.product_order_number.setText(orderIdListBean.getOrderId());
        //金额
        viewHolder.money_number.setText(commerceItemBean.getAmount());
        //剩余时间
        viewHolder.residual_time.setText(orderIdListBean.getRemainingTime());

        viewHolder.tv_name.setText(commerceItemBean.getProductName());
        //品种
        viewHolder.surplus_amount_et.setText(commerceItemBean.getParentCategories());
        //产品牌号
        viewHolder.purchase_quantity_et.setText(commerceItemBean.getGradeNumber());
        //生产企业
        viewHolder.min_variable_et.setText(commerceItemBean.getManufacturer());
        //仓库
        viewHolder.delivery_time_et.setText(commerceItemBean.getWarehouse());
        //产品单价
        viewHolder.classification_pre_sale_et.setText(commerceItemBean.getAmount());
        //数量
        viewHolder.warehouse_et.setText(commerceItemBean.getQuantity());
        //产品总价
        viewHolder.region_et.setText(orderIdListBean.getTotal());
        //公司
        viewHolder.company_et.setText(commerceItemBean.getSalesCompanyDisplayName());
        return convertView;
    }

    class ViewHolder {
        TextView product_order_number;//订单编号
        TextView money_number;//金额
        TextView residual_time;//剩余时间
        TextView tv_name;//
        TextView surplus_amount_et;//品种
        TextView purchase_quantity_et;//产品牌号
        TextView min_variable_et;//生产企业
        TextView delivery_time_et;//仓库
        TextView classification_pre_sale_et;//产品单价
        TextView warehouse_et;//数量
        TextView region_et;//产品总价
        TextView company_et;//公司
    }
}
