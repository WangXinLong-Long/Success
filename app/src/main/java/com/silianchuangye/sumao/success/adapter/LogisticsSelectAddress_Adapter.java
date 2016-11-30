package com.silianchuangye.sumao.success.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.bean.Logistics_SelectAddress_Info;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.PhysicalDistributionManagement.logistics.Logistics_SelectAddress;

import java.util.List;

/**
 * Created by Administrator on 2016/7/11.
 */
public class LogisticsSelectAddress_Adapter extends BaseAdapter{
    private List<Logistics_SelectAddress_Info> list;
    private Context ctx;
    public LogisticsSelectAddress_Adapter(List<Logistics_SelectAddress_Info>list,Context ctx){
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            holder=new ViewHolder();
            convertView=View.inflate(ctx, R.layout.item_loginstics_selsectaddress,null);
            holder.tv_select_address_title= (TextView) convertView.findViewById(R.id.tv_item_select_address);
            holder.tv_select_address_message= (TextView) convertView.findViewById(R.id.tv_item_select_address_message);
            holder.tv_select_address_name= (TextView) convertView.findViewById(R.id.tv_item_select_address_name);
            holder.tv_select_address_num= (TextView) convertView.findViewById(R.id.tv_item_select_address_phone_num);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.tv_select_address_title.setText(list.get(position).tv_address_title);
        holder.tv_select_address_message.setText(list.get(position).tv_address_message);
        holder.tv_select_address_num.setText(list.get(position).tv_address_phone_num);
        holder.tv_select_address_name.setText(list.get(position).tv_address_name);
        return convertView;
    }
    private class ViewHolder{
        TextView tv_select_address_title,
                tv_select_address_name,
                tv_select_address_message,
                tv_select_address_num;
    }
}
