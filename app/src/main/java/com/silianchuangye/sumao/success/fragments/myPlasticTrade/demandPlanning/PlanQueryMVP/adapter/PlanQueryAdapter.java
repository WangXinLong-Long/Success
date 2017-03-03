package com.silianchuangye.sumao.success.fragments.myPlasticTrade.demandPlanning.PlanQueryMVP.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.demandPlanning.PlanQueryMVP.bean.PlanQueryBean;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.demandPlanning.planQuery.ViewOriginalPlanActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jobs Created on 2017/2/10.
 */
public class PlanQueryAdapter extends BaseAdapter {
    private Context context;
    private List<PlanQueryBean> list;
    private LayoutInflater inflater;

    public PlanQueryAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
        inflater = LayoutInflater.from(context);
    }

    public void addData(List<PlanQueryBean> data) {
        list.addAll(data);
    }

    public void clear() {
        list.clear();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.planz_query_item, null);
//            需求或订单
            viewHolder.demand_or_order = ((TextView) convertView.findViewById(R.id.demand_or_order1));
//            需求或订单号
            viewHolder.demand_or_order_number = ((TextView) convertView.findViewById(R.id.demand_or_order_number1));
//            复制
            viewHolder.copy_button = ((Button) convertView.findViewById(R.id.copy_button1));
//            需求日期和提报日期的容器
            viewHolder.demand = ((RelativeLayout) convertView.findViewById(R.id.demand1));
//            需求日期
            viewHolder.demand_date_num = ((TextView) convertView.findViewById(R.id.demand_date_num1));
//            提报日期
            viewHolder.date_of_presentation_num = ((TextView) convertView.findViewById(R.id.date_of_presentation_num1));
//            分类
            viewHolder.classification_content = ((TextView) convertView.findViewById(R.id.classification_content1));
//            产品名称
            viewHolder.product_name_content = ((TextView) convertView.findViewById(R.id.product_name_content1));

            viewHolder.view_original_plan = ((RelativeLayout) convertView.findViewById(R.id.view_original_plan));
            convertView.setTag(viewHolder);
        }else {
            viewHolder = ((ViewHolder) convertView.getTag());
        }
        viewHolder.demand_or_order.setText(list.get(position).getOrderOrdemand());
        viewHolder.demand_or_order_number.setText(list.get(position).getOrderOrdemandNum());
        if (list.get(position).getOrderOrdemand().equals("订单编号:")){
            viewHolder.demand_or_order_number.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
            viewHolder.demand_or_order_number.setTextColor(context.getResources().getColor(R.color.common_botton_bar_blue));
            viewHolder.demand.setVisibility(View.GONE);
            viewHolder.view_original_plan.setVisibility(View.VISIBLE);
        }
        viewHolder.demand_date_num.setText(list.get(position).getDemand_date_num());
        viewHolder.date_of_presentation_num.setText(list.get(position).getDate_of_presentation_num());
        viewHolder.classification_content.setText(list.get(position).getClassification_content());
        viewHolder.product_name_content.setText(list.get(position).getProduct_name_content());
        viewHolder.view_original_plan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewOriginalPlanActivity.class);
                context.startActivity(intent);
            }
        });
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
    }
}
