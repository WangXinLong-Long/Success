package com.silianchuangye.sumao.success.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.bean.logisticsMessageInfo;

import java.util.List;

/**
 * Created by Administrator on 2016/9/10.
 */
public class TrackingDistributionDetailsAdapter extends BaseAdapter{
    private List<logisticsMessageInfo> list;
    private Context ctx;
    public TrackingDistributionDetailsAdapter(List<logisticsMessageInfo>list,Context ctx){
        this.list=list;
        this.ctx=ctx;
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
        ViewHolder holder;
        if(convertView==null){
            convertView=View.inflate(ctx, R.layout.item_deteails,null);
            holder=new ViewHolder();
            holder.tv_title= (TextView) convertView.findViewById(R.id.tv_item_deteails_title);
            holder.tv_date= (TextView) convertView.findViewById(R.id.tv_item_deteails_date);
            holder.tv_time1_left= (TextView) convertView.findViewById(R.id.tv_item_deteails_time1_left);
            holder.tv_time1_right= (TextView) convertView.findViewById(R.id.tv_item_deteails_time1_right);
            holder.tv_time2_left= (TextView) convertView.findViewById(R.id.tv_item_deteails_time2_left);
            holder.tv_time2_right= (TextView) convertView.findViewById(R.id.tv_item_deteails_time2_right);
            holder.tv_name= (TextView) convertView.findViewById(R.id.tv_item_deteails_name_right);
            holder.tv_phoneNum= (TextView) convertView.findViewById(R.id.tv_item_deteails_lianxi_right);
            holder.tv_date2= (TextView) convertView.findViewById(R.id.tv_item_deteails_date2);
            holder.tv_time3_left= (TextView) convertView.findViewById(R.id.tv_item_deteails_time3_left);
            holder.tv_time3_right= (TextView) convertView.findViewById(R.id.tv_item_deteails_time3_right);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.tv_title.setText(list.get(position).title);
        holder.tv_date.setText(list.get(position).date);
        holder.tv_time1_left.setText(list.get(position).time1_left+" : ");
        holder.tv_time1_right.setText(list.get(position).time1_right);
        holder.tv_time2_left.setText(list.get(position).time2_left+" : ");
        holder.tv_time2_right.setText(list.get(position).time2_right);
        holder.tv_name.setText(list.get(position).name);
        holder.tv_phoneNum.setText(list.get(position).phoneNum);
        holder.tv_date2.setText(list.get(position).date2);
        holder.tv_time3_right.setText(list.get(position).time3_right);
        holder.tv_time3_left.setText(list.get(position).time3_left+" : ");
        return convertView;
    }
     class ViewHolder{
        TextView tv_title,tv_date,tv_time1_left,tv_time1_right,
                tv_time2_left,tv_time2_right,tv_name,tv_phoneNum,
                tv_date2,tv_time3_left,tv_time3_right;
    }
}
