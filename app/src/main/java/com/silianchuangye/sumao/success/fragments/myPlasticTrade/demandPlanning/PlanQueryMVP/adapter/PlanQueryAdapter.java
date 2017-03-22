package com.silianchuangye.sumao.success.fragments.myPlasticTrade.demandPlanning.PlanQueryMVP.adapter;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.ShangYou.SearchSeePlan;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.demandPlanning.PlanQueryMVP.bean.PlanBean;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.demandPlanning.PlanQueryMVP.bean.PlanQueryBean;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.demandPlanning.SearchplanActivity;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.demandPlanning.planQuery.ViewOriginalPlanActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Jobs Created on 2017/2/10.
 */
public class PlanQueryAdapter extends BaseAdapter {
    private Context context;
    private List<Map<String,Object>> list;
    private LayoutInflater inflater;
    private Button bt_search;
    private int number;
//    private List<PlanBean.PlanOrderList> list;
    public PlanQueryAdapter(Context context,List<Map<String,Object>> list) {
        this.context = context;
        //list = new ArrayList<>();
        this.list=list;
        inflater = LayoutInflater.from(context);
    }

    public void addData(List<Map<String,Object>> data) {
        list.addAll(data);
    }

//       public void addData(List<PlanBean.PlanOrderList> data) {
//        list.addAll(data);
//   }

    public void clear() {
        list.clear();
    }

    @Override
    public int getCount() {
        Log.d("list的长度","list的长度"+list.size());
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        number=position;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.itemplan, null);
            bt_search = (Button) convertView.findViewById(R.id.btn_seeplan_seeplan);
//            需求或订单
            viewHolder.demand_or_order = ((TextView) convertView.findViewById(R.id.tv_seeplan_right_dingdannum));
//            需求或订单号
            viewHolder.demand_or_order_number = ((TextView) convertView.findViewById(R.id.demand_or_order_number1));
//            复制
            viewHolder.copy_button = ((Button) convertView.findViewById(R.id.copy_button1));
//            需求日期和提报日期的容器
           // viewHolder.demand = ((RelativeLayout) convertView.findViewById(R.id.demand1));
//            需求日期
            viewHolder.demand_date_num = ((TextView) convertView.findViewById(R.id.demand_date_num1));
//            提报日期
            viewHolder.date_of_presentation_num = ((TextView) convertView.findViewById(R.id.date_of_presentation_num1));
//            分类
            viewHolder.classification_content = ((TextView) convertView.findViewById(R.id.classification_content1));
//            产品名称
            viewHolder.product_name_content = ((TextView) convertView.findViewById(R.id.product_name_content1));
           //价格
            viewHolder.price_content1= (TextView) convertView.findViewById(R.id.price_content1);
            //数量
            viewHolder.number= (TextView) convertView.findViewById(R.id.number_content1);
            //金e
            viewHolder.money= (TextView) convertView.findViewById(R.id.amount_of_money_content1);
            //仓库
            viewHolder.warehouse= (TextView) convertView.findViewById(R.id.warehouse_content1);
            //物流
            viewHolder.wuliu= (TextView) convertView.findViewById(R.id.logistics_information_content1);
            //卖方企业
            viewHolder.qiye= (TextView) convertView.findViewById(R.id.seller_enterprise_content1);
            //状态
            viewHolder.zhuangtai= (TextView) convertView.findViewById(R.id.state_content1);
            viewHolder.view_original_plan = ((RelativeLayout) convertView.findViewById(R.id.view_original_plan));
            convertView.setTag(viewHolder);
        }else {
            viewHolder = ((ViewHolder) convertView.getTag());
        }

        viewHolder.demand_or_order_number.setText(list.get(position).get("planID").toString());
       if (list.get(position).toString().contains("orderId")) {
           viewHolder.demand_or_order.setText(list.get(position).get("orderId").toString());
           Log.d("订单号的值",list.get(position).get("orderId").toString());
           bt_search.setVisibility(View.VISIBLE);
           bt_search.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent intent=new Intent(context, SearchplanActivity.class);
                   intent.putExtra("PlanOrderList",list.get(number).toString());
                   context.startActivity(intent);
               }
           });
       }else {
           Log.d("进的事else","else");
           viewHolder.demand_or_order.setText(" ");
           bt_search.setVisibility(View.GONE);
       }

        viewHolder.demand_date_num.setText(list.get(position).get("scheduleDateChange").toString());
        viewHolder.date_of_presentation_num.setText(list.get(position).get("scheduleDate").toString());
        viewHolder.classification_content.setText(" ");
        String state=list.get(position).get("demandScheduleState").toString();
        viewHolder.qiye.setText(list.get(position).get("sellerEnterpriseName").toString());
        if (state.equals("confirmed")){
            viewHolder.zhuangtai.setText("已确认");
//            if (list.get(position).toString().contains("productNameChange")){
//                viewHolder.product_name_content.setText(list.get(position).get("productNameChange").toString());
//            }else{
//                viewHolder.product_name_content.setText( list.get(position).get("productName").toString());
//            }
//            if (list.get(position).toString().contains("price")){
//                viewHolder.price_content1.setText(list.get(position).get("price").toString());
//            }else{
//                if (list.get(position).toString().contains("prePirce")&&!list.get(position).get("prePirce").toString().equals("")){
//                    viewHolder.price_content1.setText(list.get(position).get("prePirce").toString());
//                }else{
//                   // viewHolder.price_content1.setText(" ");
//                   // viewHolder.price_content1.setVisibility(View.GONE);
//                }
//            }
//            if (list.get(position).toString().contains("quantityChange")){
//                if(!list.get(position).get("quantityChange").toString().equals("")){
//                    viewHolder.number.setText(list.get(position).get("quantityChange").toString());
//                }
//
//            }else{
//                viewHolder.number.setText(list.get(position).get("quantity").toString());
//            }
//            if (list.get(position).toString().contains("amount")){
//                viewHolder.money.setText(list.get(position).get("amount").toString());
//            }else{
//                if (list.get(position).toString().contains("preAmount")&&!list.get(position).get("preAmount").toString().equals("")){
//                    viewHolder.money.setText(list.get(position).get("preAmount").toString());
//                }else{
//                    //viewHolder.money.setVisibility(View.GONE);
//                }
//            }
//            if (list.get(position).toString().contains("warehouseChangeName")){
//                viewHolder.warehouse.setText(list.get(position).get("warehouseChangeName").toString());
//            }else{
//                viewHolder.warehouse.setText(list.get(position).get("warehouseName").toString());
//            }
//            if (list.get(position).toString().contains("logisticsChangeName")){
//                viewHolder.wuliu.setText(list.get(position).get("logisticsChangeName").toString());
//            }else{
//                viewHolder.wuliu.setText(list.get(position).get("logisticsName").toString());
//
//            }

            viewHolder.copy_button.setTextColor(context.getResources().getColor(R.color.btn_blue_normal));
           // bt_search.setVisibility(View.VISIBLE);
            final ViewHolder finalViewHolder = viewHolder;
            viewHolder.copy_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ClipboardManager copy = (ClipboardManager) context
                            .getSystemService(Context.CLIPBOARD_SERVICE);
                    copy.setText(finalViewHolder.demand_or_order.getText().toString());
                    Toast.makeText(context,"复制成功",Toast.LENGTH_SHORT).show();

                }
            });

        }else if (state.equals("Pending")){
            viewHolder.zhuangtai.setText("待确认");

           // bt_search.setVisibility(View.GONE);

            viewHolder.copy_button.setTextColor(context.getResources().getColor(R.color.gray));

        }else if (state.equals("canceled")){
            viewHolder.zhuangtai.setText("已取消");
            bt_search.setVisibility(View.GONE);

            viewHolder.copy_button.setTextColor(context.getResources().getColor(R.color.gray));
        }
        viewHolder.product_name_content.setText( list.get(position).get("productName").toString());
        if (list.get(position).toString().contains("prePirce")&&!list.get(position).get("prePirce").toString().equals("")){
            viewHolder.price_content1.setText(list.get(position).get("prePirce").toString());
        }else{
            // viewHolder.price_content1.setVisibility(View.GONE);
        }
        viewHolder.number.setText(list.get(position).get("quantity").toString());
        if (list.get(position).toString().contains("preAmount")&&!list.get(position).get("preAmount").toString().equals("")){
            viewHolder.money.setText(list.get(position).get("preAmount").toString());
        }else{
            //viewHolder.money.setVisibility(View.GONE);
        }
        viewHolder.warehouse.setText(list.get(position).get("warehouseName").toString());
        viewHolder.wuliu.setText(list.get(position).get("logisticsName").toString());


        return convertView;
    }

    class ViewHolder {
        TextView demand_or_order;
        TextView demand_or_order_number;
        Button copy_button;
        TextView demand_date_num;
        TextView date_of_presentation_num;
        TextView classification_content;
        TextView product_name_content;
        RelativeLayout demand;
        RelativeLayout view_original_plan;
        TextView price_content1;
        TextView number;
        TextView money;
        TextView warehouse;
        TextView wuliu;
        TextView qiye;
        TextView zhuangtai;
    }
}
