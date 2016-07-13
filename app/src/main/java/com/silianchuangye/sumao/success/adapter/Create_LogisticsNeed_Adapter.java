package com.silianchuangye.sumao.success.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.bean.Create_Logistics_NeedInfo;

import java.util.List;

/**
 * Created by Administrator on 2016/7/11.
 */
public class Create_LogisticsNeed_Adapter extends BaseAdapter{
    private List<Create_Logistics_NeedInfo> list;
    private Context ctx;
    public Create_LogisticsNeed_Adapter(List<Create_Logistics_NeedInfo>list, Context ctx){
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
            convertView=View.inflate(ctx, R.layout.item_create_need,null);
            holder.tv_start= (TextView) convertView.findViewById(R.id.tv_item_create_need_start);
            holder.tv_second= (TextView) convertView.findViewById(R.id.tv_item_create_need_second);
            holder.tv_three= (TextView) convertView.findViewById(R.id.tv_item_create_need_three);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.tv_start.setText(list.get(position).tv_start);
        holder.tv_second.setText(list.get(position).tv_second);
        holder.tv_three.setText(list.get(position).tv_three);
        return convertView;
    }
    private class ViewHolder{
        TextView tv_start,tv_second,tv_three;
    }
}
