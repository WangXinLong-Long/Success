package com.silianchuangye.sumao.success.fragments.myPlasticTrade.demandPlanning.PlanQueryMVP.adapter;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Parcelable;
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
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.SpotOrderMVP.view.SpotOrder;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.demandPlanning.PlanQueryMVP.bean.PlanBean;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.demandPlanning.PlanQueryMVP.bean.PlanQueryBean;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.demandPlanning.SearchplanActivity;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.demandPlanning.planQuery.ViewOriginalPlanActivity;

import java.io.Serializable;
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
    private RelativeLayout foot;
    private int number;
    String id;
    private TextView tv_seeplan_left_dingdannum;
    public PlanQueryAdapter(Context context,List<Map<String,Object>> list) {
        this.context = context;
        this.list=list;
        inflater = LayoutInflater.from(context);
    }

    public void addData(List<Map<String,Object>> data) {
        list.addAll(data);
    }

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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        //number=position;
        //if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.itemplan, null);
            //TextView tv_seeplan_left_dingdannum=(TextView) convertView.findViewById(R.id.tv_seeplan_left_dingdannum);
            foot= (RelativeLayout) convertView.findViewById(R.id.layout_foot);
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
            tv_seeplan_left_dingdannum= (TextView) convertView.findViewById(R.id.tv_seeplan_left_dingdannum);
            //viewHolder.view_original_plan = ((RelativeLayout) convertView.findViewById(R.id.view_original_plan));
            convertView.setTag(viewHolder);
        viewHolder.demand_date_num.setText(list.get(position).get("scheduleDate").toString());
        viewHolder.demand_or_order_number.setText(list.get(position).get("planID").toString());
        viewHolder.date_of_presentation_num.setText(list.get(position).get("registerDate").toString());
        if (list.get(position).containsKey("categoryName")) {
            viewHolder.classification_content.setText(list.get(position).get("categoryName").toString());
        }else{
            viewHolder.classification_content.setText(" ");
        }
        viewHolder.qiye.setText(list.get(position).get("sellerEnterpriseName").toString());
        viewHolder.product_name_content.setText( list.get(position).get("productName").toString());
        if (list.get(position).toString().contains("prePirce")&&!list.get(position).get("prePirce").toString().equals("")){
            viewHolder.price_content1.setText(list.get(position).get("prePirce").toString());
        }else{
        }
        viewHolder.number.setText(list.get(position).get("quantity").toString());
        if (list.get(position).toString().contains("preAmount")&&!list.get(position).get("preAmount").toString().equals("")){
            viewHolder.money.setText(list.get(position).get("preAmount").toString());
        }else{
            //viewHolder.money.setVisibility(View.GONE);
        }
        viewHolder.warehouse.setText(list.get(position).get("warehouseName").toString());
        viewHolder.wuliu.setText(list.get(position).get("logisticsName").toString());


            if (list.get(position).get("demandScheduleState").toString().equals("confirmed")){
                viewHolder.zhuangtai.setText("已确认");
                Log.d("已确认","已确认"+id);
                id=list.get(position).get("orderId").toString();
                Log.d("orderId100029000",id);
                viewHolder.demand_or_order.setText(list.get(position).get("orderId").toString());
                viewHolder.demand_or_order.setVisibility(View.VISIBLE);
                tv_seeplan_left_dingdannum.setVisibility(View.VISIBLE);
                viewHolder.demand_or_order.setTextColor(context.getResources().getColor(R.color.btn_blue_normal));
                tv_seeplan_left_dingdannum.setTextColor(context.getResources().getColor(R.color.btn_blue_normal));
                viewHolder.copy_button.setTextColor(context.getResources().getColor(R.color.btn_blue_normal));
                bt_search.setVisibility(View.VISIBLE);
//            final ViewHolder finalViewHolder = viewHolder;
                viewHolder.copy_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //获取剪贴板管理器：
                        ClipboardManager cm = (ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
                        // 创建普通字符型ClipData
                        ClipData mClipData = ClipData.newPlainText("Label", list.get(position).get("orderId").toString());
                        // 将ClipData内容放到系统剪贴板里。
                        cm.setPrimaryClip(mClipData);
                    }
                });
                viewHolder.demand_or_order.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(context, SpotOrder.class);
                        intent.putExtra("id",list.get(position).get("orderId").toString());
                        context.startActivity(intent);
                    }
                });
                bt_search.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("传过去的返回值",list.get(position).toString());

                        Intent intent=new Intent(context, SearchplanActivity.class);
                        // intent.putExtra("PlanOrderList", list.get(position).toString());
                        intent.putExtra("riqi",list.get(position).get("scheduleDate").toString());
                        intent.putExtra("name",list.get(position).get("productName").toString());
                        intent.putExtra("cangku",list.get(position).get("warehouseName").toString());
                        intent.putExtra("number",list.get(position).get("quantity").toString());
                        intent.putExtra("logisticsName",list.get(position).get("logisticsName").toString());
//                    if (list.get(position).toString().contains("orderId")&&!list.get(position).toString().equals("")){
//                        intent.putExtra("orderId",id);
//                    }else{
//                        intent.putExtra("orderId"," ");
//                    }
                        intent.putExtra("state",list.get(position).get("demandScheduleState").toString());
                        if (list.get(position).toString().contains("scheduleDateChange")&&!list.get(position).get("scheduleDateChange").toString().equals("")){
                            intent.putExtra("riqinew",list.get(position).get("scheduleDateChange").toString());
                        }else{
                            intent.putExtra("riqinew",list.get(position).get("scheduleDate").toString());
                        }

                        if (list.get(position).toString().contains("productNameChange")){
                            String nameNew=list.get(position).get("productNameChange").toString();
                            if (nameNew!=null&& nameNew!="" && !nameNew.equals("")) {
                                intent.putExtra("nameNew", nameNew);
                            }else{
                                intent.putExtra("nameNew",list.get(position).get("productName").toString());
                            }
                        }else{
                            intent.putExtra("nameNew",list.get(position).get("productName").toString());
                        }

                        if (!list.get(position).toString().contains("quantityChange")){
                            String shuliang=list.get(position).get("quantity").toString();
                            intent.putExtra("numberNew",shuliang);

                        }else{
                            String numberNew=list.get(position).get("quantityChange").toString();
//                viewHolder.number.setText(list.get(position).get("quantity").toString());
                            Log.d("變化后的值",numberNew);
                            if (!numberNew.equals("")||numberNew!=""||numberNew!=null){
                                Log.d("變化后的值",numberNew);
                                intent.putExtra("numberNew",numberNew);
                            }else{

                                intent.putExtra("numberNew",list.get(position).get("quantity").toString());
                            }
                        }

                        if (!list.get(position).toString().contains("warehouseChangeName")){
//                viewHolder.warehouse.setText(list.get(position).get("warehouseChangeName").toString());
                            intent.putExtra("cangkuNew",list.get(position).get("warehouseName").toString());
                        }else{
                            String cangkuNew=list.get(position).get("warehouseChangeName").toString();
//                viewHolder.warehouse.setText(list.get(position).get("warehouseName").toString());
                            if (cangkuNew!=null||!cangkuNew.equals("")||cangkuNew!=""){
                                intent.putExtra("cangkuNew",cangkuNew);
                            }else{
                                intent.putExtra("cangkuNew",list.get(position).get("warehouseName").toString());
                            }

                        }
                        if (!list.get(position).toString().contains("logisticsChangeName")){
                            intent.putExtra("wayNew",list.get(position).get("logisticsName").toString());
//                viewHolder.wuliu.setText(list.get(position).get("logisticsChangeName").toString());
                        }else {
//                viewHolder.wuliu.setText(list.get(position).get("logisticsName").toString());
                            String wayNew=list.get(position).get("logisticsChangeName").toString();
                            if (wayNew!=null||!wayNew.equals("")||wayNew!=""){
                                intent.putExtra("wayNew",wayNew);
                            }else{
                                intent.putExtra("wayNew",list.get(position).get("logisticsName").toString());
                            }


                        }


                        context.startActivity(intent);
                    }
                });

            }else if (list.get(position).get("demandScheduleState").toString().equals("Pending")){
                bt_search.setVisibility(View.GONE);
                viewHolder.zhuangtai.setText("待确认");
                foot.setVisibility(View.GONE);
            }else if (list.get(position).get("demandScheduleState").toString().equals ("canceled")||!list.get(position).toString().contains("demandScheduleState")){
                viewHolder.zhuangtai.setText("已取消");
                bt_search.setVisibility(View.GONE);
                foot.setVisibility(View.GONE);
            }



//        }else {
//            Log.d("进的事else","else");
//           // viewHolder.demand_or_order.setText(" ");
//          //  bt_search.setVisibility(View.GONE);
//        }


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
