package com.silianchuangye.sumao.success.adapter;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.ShangYou.CaiGouMVP.bean.PlanOrderList;
import com.silianchuangye.sumao.success.ShangYou.SearchSeePlan;
import com.silianchuangye.sumao.success.ShangYou.SeePlanInfo;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.PhysicalDistributionManagement.ViewLogisticsDemands.ViewLogisticsDemand;

import java.util.List;

/**
 * Created by Administrator on 2016/9/6.
 */
public class SeePlanAdapter extends BaseAdapter{
    private Context ctx;
    private List<PlanOrderList>  list;
    public SeePlanAdapter(Context ctx,List<PlanOrderList>  list){
        this.ctx=ctx;
        this.list=list;
    }
    @Override
    public int getCount() {
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
        final ViewHolder holder;
        if(convertView==null){
            convertView=View.inflate(ctx, R.layout.item_seeplan_lv,null);
            holder=new ViewHolder();
            holder.tv_bianhao= (TextView) convertView.findViewById(R.id.tv_seeplan_right_bianhao);
            holder.tv_date= (TextView) convertView.findViewById(R.id.tv_seeplan_right_date);
            holder.tv_name= (TextView) convertView.findViewById(R.id.tv_seeplan_right_name);
            holder.tv_cnagku= (TextView) convertView.findViewById(R.id.tv_seeplan_right_cangku);
            holder.tv_num= (TextView) convertView.findViewById(R.id.tv_seeplan_right_num);
            holder.tv_peisong= (TextView) convertView.findViewById(R.id.tv_seeplan_right_peisong);
            holder.tv_state= (TextView) convertView.findViewById(R.id.tv_seeplan_right_state);
            holder.tv_dingdangnum= (TextView) convertView.findViewById(R.id.tv_seeplan_right_dingdannum);
            holder.btn_copy= (Button) convertView.findViewById(R.id.btn_seeplan_copy);
            holder.btn_see= (Button) convertView.findViewById(R.id.btn_seeplan_seeplan);
            holder.tv_seeplan_left_dingdannum =(TextView) convertView.findViewById(R.id.tv_seeplan_left_dingdannum);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }/*
            productName = planOrderList.get(i).getProductName();//产品名称
            demandScheduleState = planOrderList.get(i).getDemandScheduleState();//订单状态
            logisticsName = planOrderList.get(i).getLogisticsName();//物流
            orderId = planOrderList.get(i).getOrderId();//订单ID
            planID = planOrderList.get(i).getPlanID();//计划ID
            quantity = planOrderList.get(i).getQuantity();//数量
            warehouseName = planOrderList.get(i).getWarehouseName();//仓库名
            scheduleDate = planOrderList.get(i).getScheduleDate();//计划日
            productNameChange = planOrderList.get(i).getProductNameChange();//修改后产品名字
            logisticsChangeName = planOrderList.get(i).getLogisticsChangeName();//修改后物流
            quantityChange = planOrderList.get(i).getQuantityChange();//修改后数量
            scheduleDateChange = planOrderList.get(i).getScheduleDateChange();//修改后计划日
            warehouseChangeName = planOrderList.get(i).getWarehouseChangeName();//修改后仓库名*/
        holder.tv_bianhao.setText(list.get(position).getPlanID());//
        holder.tv_date.setText(list.get(position).getScheduleDate());
        holder.tv_name.setText(list.get(position).getProductName());
        holder.tv_cnagku.setText(list.get(position).getWarehouseName());
        holder.tv_num.setText(list.get(position).getQuantity());
        holder.tv_peisong.setText(list.get(position).getLogisticsName());
        holder.tv_state.setText(list.get(position).getDemandScheduleState());
//        holder.tv_dingdangnum.setText(list.get(position).getOrderId());
        //复制按钮
        holder.btn_copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager copy = (ClipboardManager) ctx
                        .getSystemService(Context.CLIPBOARD_SERVICE);
                copy.setText(holder.tv_dingdangnum.getText().toString());
                Toast.makeText(ctx,"复制成功",Toast.LENGTH_SHORT).show();
            }
        });
        //查看原计划按钮
        holder.btn_see.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ctx, SearchSeePlan.class);
                intent.putExtra("PlanOrderList",list.get(position));
                ctx.startActivity(intent);
            }
        });
        if (null == list.get(position).getDemandScheduleState()){
            holder.tv_state.setText("已取消");
            holder.btn_see.setVisibility(View.INVISIBLE);
            holder.tv_dingdangnum.setVisibility(View.INVISIBLE);
            holder.tv_seeplan_left_dingdannum.setVisibility(View.INVISIBLE);
            holder.btn_copy.setTextColor(ctx.getResources().getColor(R.color.gray));
            holder.btn_copy.setClickable(false);
        }else if(list.get(position).getDemandScheduleState().equals("confirmed")){//Pending 待确定//null 已取消//confirmed 已确定
            holder.tv_state.setText("已确定");
            holder.btn_see.setVisibility(View.VISIBLE);
            holder.tv_dingdangnum.setVisibility(View.VISIBLE);
            holder.tv_seeplan_left_dingdannum.setVisibility(View.VISIBLE);
            holder.btn_copy.setTextColor(ctx.getResources().getColor(R.color.common_botton_bar_blue));
            holder.tv_dingdangnum.setText(list.get(position).getOrderId());
        }else{
            holder.tv_state.setText("待确定");
            holder.btn_see.setVisibility(View.INVISIBLE);
            holder.tv_dingdangnum.setVisibility(View.INVISIBLE);
            holder.tv_seeplan_left_dingdannum.setVisibility(View.INVISIBLE);
            holder.btn_copy.setTextColor(ctx.getResources().getColor(R.color.gray));
            holder.btn_copy.setClickable(false);
        }

        return convertView;
    }
    private class ViewHolder{
        TextView tv_bianhao,tv_date,tv_name,tv_cnagku,tv_num,tv_peisong,tv_state,tv_dingdangnum,tv_seeplan_left_dingdannum;
        Button btn_see,btn_copy;
    }
}
