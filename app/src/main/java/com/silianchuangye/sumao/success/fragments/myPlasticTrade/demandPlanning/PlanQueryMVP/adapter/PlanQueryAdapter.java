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
    private int number;
    String id;
    private TextView tv_seeplan_left_dingdannum;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
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
            tv_seeplan_left_dingdannum= (TextView) convertView.findViewById(R.id.tv_seeplan_left_dingdannum);
            viewHolder.view_original_plan = ((RelativeLayout) convertView.findViewById(R.id.view_original_plan));
            convertView.setTag(viewHolder);
        }else {
            viewHolder = ((ViewHolder) convertView.getTag());
        }

        viewHolder.demand_or_order_number.setText(list.get(position).get("planID").toString());
        viewHolder.demand_or_order.setTextColor(context.getResources().getColor(R.color.btn_blue_normal));
        tv_seeplan_left_dingdannum.setTextColor(context.getResources().getColor(R.color.btn_blue_normal));
        viewHolder.demand_date_num.setText(list.get(position).get("scheduleDateChange").toString());
        viewHolder.date_of_presentation_num.setText(list.get(position).get("scheduleDate").toString());
        viewHolder.classification_content.setText(" ");
        final String state=list.get(position).get("demandScheduleState").toString();
        viewHolder.qiye.setText(list.get(position).get("sellerEnterpriseName").toString());
        if (state.equals("confirmed")){
            viewHolder.zhuangtai.setText("已确认");
//

            viewHolder.copy_button.setTextColor(context.getResources().getColor(R.color.btn_blue_normal));
           // bt_search.setVisibility(View.VISIBLE);
            final ViewHolder finalViewHolder = viewHolder;
            viewHolder.copy_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //获取剪贴板管理器：
                    ClipboardManager cm = (ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
// 创建普通字符型ClipData
                    ClipData mClipData = ClipData.newPlainText("Label", id);
// 将ClipData内容放到系统剪贴板里。
                    cm.setPrimaryClip(mClipData);
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
        viewHolder.demand_or_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, SpotOrder.class);
                intent.putExtra("id",id);
                context.startActivity(intent);
            }
        });
        if (list.get(position).toString().contains("orderId")) {
            id=list.get(position).get("orderId").toString();
            viewHolder.demand_or_order.setText(id);
            Log.d("订单号的值",list.get(position).get("orderId").toString());
            Log.d("订单号的值",id);
            bt_search.setVisibility(View.VISIBLE);
            bt_search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("剪輯的值",""+list.get(position).toString());
//                    SharedPreferences sp=context.getSharedPreferences("sumao", Activity.MODE_PRIVATE);
//                    SharedPreferences.Editor editor=sp.edit();
//                    editor.put
                    Intent intent=new Intent(context, SearchplanActivity.class);
                   // intent.putExtra("PlanOrderList", list.get(position).toString());
                    intent.putExtra("riqi",list.get(number).get("scheduleDate").toString());
                    intent.putExtra("name",list.get(number).get("productName").toString());
                    intent.putExtra("cangku",list.get(number).get("warehouseName").toString());
                    intent.putExtra("number",list.get(number).get("quantity").toString());
                    intent.putExtra("logisticsName",list.get(number).get("logisticsName").toString());
                    if (list.get(position).toString().contains("orderId")&&!list.get(position).toString().equals("")){
                        intent.putExtra("orderId",id);
                    }else{
                        intent.putExtra("orderId"," ");
                    }
                    intent.putExtra("state",state);
                    if (list.get(position).toString().contains("scheduleDateChange")&&!list.get(position).get("scheduleDateChange").toString().equals("")){
                        intent.putExtra("riqinew",list.get(number).get("scheduleDateChange").toString());
                    }else{
                        intent.putExtra("riqinew",list.get(number).get("scheduleDate").toString());
                    }

                    if (list.get(position).toString().contains("productNameChange")){
                        String nameNew=list.get(position).get("productNameChange").toString();
                        Log.d("产品名称的值",list.get(number).get("productNameChange").toString());
                        if (nameNew!=null&& nameNew!="" && !nameNew.equals("")) {
                            // viewHolder.product_name_content.setText(list.get(position).get("productNameChange").toString());
                            intent.putExtra("nameNew", list.get(number).get("productNameChange").toString());
                        }else{
                            intent.putExtra("nameNew",list.get(number).get("productName").toString());
                        }
            }else{
                        intent.putExtra("nameNew",list.get(number).get("productName").toString());

            }

            if (!list.get(position).toString().contains("quantityChange")){
                String shuliang=list.get(number).get("quantity").toString();
                intent.putExtra("numberNew",shuliang);

            }else{
                String numberNew=list.get(position).get("quantityChange").toString();
//                viewHolder.number.setText(list.get(position).get("quantity").toString());
                Log.d("變化后的值",numberNew);
                if (!numberNew.equals("")||numberNew!=""||numberNew!=null){
                       Log.d("變化后的值",numberNew);
                      intent.putExtra("numberNew",numberNew);
                }else{

                    intent.putExtra("numberNew",list.get(number).get("quantity").toString());
                }
            }

            if (!list.get(position).toString().contains("warehouseChangeName")){
//                viewHolder.warehouse.setText(list.get(position).get("warehouseChangeName").toString());
                intent.putExtra("cangkuNew",list.get(number).get("warehouseName").toString());
            }else{
                String cangkuNew=list.get(position).get("warehouseChangeName").toString();
//                viewHolder.warehouse.setText(list.get(position).get("warehouseName").toString());
                if (cangkuNew!=null||!cangkuNew.equals("")||cangkuNew!=""){
                    intent.putExtra("cangkuNew",cangkuNew);
                }else{
                    intent.putExtra("cangkuNew",list.get(number).get("warehouseName").toString());
                }

            }
            if (!list.get(position).toString().contains("logisticsChangeName")){
                intent.putExtra("wayNew",list.get(number).get("logisticsName").toString());
//                viewHolder.wuliu.setText(list.get(position).get("logisticsChangeName").toString());
            }else {
//                viewHolder.wuliu.setText(list.get(position).get("logisticsName").toString());
                String wayNew=list.get(position).get("logisticsChangeName").toString();
                if (wayNew!=null||!wayNew.equals("")||wayNew!=""){
                    intent.putExtra("wayNew",wayNew);
                }else{
                    intent.putExtra("wayNew",list.get(number).get("logisticsName").toString());
                }


            }


                    context.startActivity(intent);
                }
            });
        }else {
            Log.d("进的事else","else");
            viewHolder.demand_or_order.setText(" ");
            bt_search.setVisibility(View.GONE);
        }


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
