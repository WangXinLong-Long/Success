package com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.PayMentsOrderMVP.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.GoodsInStockDetailActivityMVP.bean.Orders;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.goodsInStockDetail.PayMentsOrderMVP.view.PaymentsOrder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

/**
 * Created by Jobs Created on 2016/12/19.
 */
public class PayMentsOrderListViewAdapter extends BaseAdapter {
    private Context context;
    List<Orders.OrderIdListBean> orderIdListBeans;
    private Map<TextView, TextViewTimeCountUtil> leftTimeMap = new HashMap<TextView, TextViewTimeCountUtil>();

    public PayMentsOrderListViewAdapter(Context context) {
        this.context = context;
        orderIdListBeans = new ArrayList<>();
    }

    public void addLists(List<Orders.OrderIdListBean> orderIdListBean) {
        this.orderIdListBeans.addAll(orderIdListBean);
//        lists.addAll(this.orderIdListBean.getCommerceItem());
    }

    public void clear() {
        orderIdListBeans.clear();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
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
//            离家支付
            viewHolder.pay_button = (Button) convertView.findViewById(R.id.pay_button);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = ((ViewHolder) convertView.getTag());
        }
        Orders.OrderIdListBean orderIdListBean = this.orderIdListBeans.get(position);
        Orders.OrderIdListBean.CommerceItemBean commerceItemBean = orderIdListBean.getCommerceItem().get(0);
        //订单编号
        viewHolder.product_order_number.setText("订单编号:" + orderIdListBean.getOrderId());
        //金额
        viewHolder.money_number.setText(commerceItemBean.getAmount());
        //剩余时间
//        ("剩余支付时间：" + new SimpleDateFormat("HH:mm:ss").format());
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
        viewHolder.pay_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((PaymentsOrder) context).payImmediately(position);
            }
        });
        Log.d("时间的值",orderIdListBean.getRemainingTime().toString());
        TextViewTimeCountUtil tc = leftTimeMap.get(viewHolder.residual_time);
        if (tc != null) {
            tc.cancel();
            tc = null;
        }
        long time = Long.parseLong(this.orderIdListBeans.get(position).getRemainingTime().toString())*1000;


        //启动倒计时
        TextViewTimeCountUtil tcu = new TextViewTimeCountUtil(
                time, 1000,
                viewHolder.residual_time);
        tcu.start();
        //保存当前到时任务
        leftTimeMap.put(viewHolder.residual_time, tcu);


        return convertView;
    }

    public void cancelAllTimers() {
        Set<Map.Entry<TextView, TextViewTimeCountUtil>> s = leftTimeMap
                .entrySet();
        Iterator it = s.iterator();
        while (it.hasNext()) {
            try {
                Map.Entry pairs = (Map.Entry) it.next();
                TextViewTimeCountUtil cdt = (TextViewTimeCountUtil) pairs
                        .getValue();
                cdt.cancel();
                cdt = null;
            } catch (Exception e) {
            }
        }
        it = null;
        s = null;
        leftTimeMap.clear();
    }




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
        Button pay_button;
    }





