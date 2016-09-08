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

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.ShangYou.SearchSeePlan;
import com.silianchuangye.sumao.success.ShangYou.SeePlanInfo;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.PhysicalDistributionManagement.ViewLogisticsDemands.ViewLogisticsDemand;

import java.util.List;

/**
 * Created by Administrator on 2016/9/6.
 */
public class SeePlanAdapter extends BaseAdapter{
    private Context ctx;
    private List<SeePlanInfo> list;
    public SeePlanAdapter(Context ctx,List<SeePlanInfo>list){
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
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.tv_bianhao.setText(list.get(position).bianhao);
        holder.tv_date.setText(list.get(position).date);
        holder.tv_name.setText(list.get(position).name);
        holder.tv_cnagku.setText(list.get(position).cnagku);
        holder.tv_num.setText(list.get(position).num);
        holder.tv_peisong.setText(list.get(position).peisong);
        holder.tv_state.setText(list.get(position).state);
        holder.tv_dingdangnum.setText(list.get(position).dingdannum);
        //复制按钮
        holder.btn_copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager copy = (ClipboardManager) ctx
                        .getSystemService(Context.CLIPBOARD_SERVICE);
                copy.setText(holder.tv_dingdangnum.getText().toString());
            }
        });
        //查看原计划按钮
        holder.btn_see.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ctx, SearchSeePlan.class);
                intent.putExtra("bianhao",holder.tv_bianhao.getText().toString());
                ctx.startActivity(intent);
            }
        });
        if(list.get(position).state.equals("待确定")){

        }else{
            holder.tv_name.setTextColor(ctx.getResources().getColor(R.color.common_top_bar_blue));
            holder.tv_num.setTextColor(ctx.getResources().getColor(R.color.red));
        }

        return convertView;
    }
    private class ViewHolder{
        TextView tv_bianhao,tv_date,tv_name,tv_cnagku,tv_num,tv_peisong,tv_state,tv_dingdangnum;
        Button btn_see,btn_copy;
    }
}
