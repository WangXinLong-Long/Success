package com.silianchuangye.sumao.success.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.bean.SaleSearchInfo;

import java.util.List;

/**
 * Created by Administrator on 2016/7/19.
 */
public class SaleSearh_Adapter extends BaseAdapter{
    private List<SaleSearchInfo> list;
    private Context ctx;
    public SaleSearh_Adapter(List<SaleSearchInfo>list,Context ctx){
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
            convertView=View.inflate(ctx, R.layout.item_sale_search_lv,null);
            holder=new ViewHolder();
            holder.title= (TextView) convertView.findViewById(R.id.tv_item_sale_search_title);
            holder.sort= (TextView) convertView.findViewById(R.id.tv_item_sale_search_sort);
            holder.num= (TextView) convertView.findViewById(R.id.tv_item_sale_search_num);
            holder.product_name= (TextView) convertView.findViewById(R.id.tv_item_sale_search_product_name);
            holder.pai_num= (TextView) convertView.findViewById(R.id.tv_item_sale_search_pai_num);
            holder.price= (TextView) convertView.findViewById(R.id.tv_item_sale_search_price);
            holder.sheng_num= (TextView) convertView.findViewById(R.id.tv_item_sale_search_sheng_num);
            holder.cangku_name= (TextView) convertView.findViewById(R.id.tv_item_sale_search_cangku_name);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.title.setText(list.get(position).title);
        holder.num.setText(list.get(position).num);
        holder.sort.setText(list.get(position).sort);
        holder.product_name.setText(list.get(position).product_name);
        holder.pai_num.setText(list.get(position).pai_num);
        holder.price.setText(list.get(position).price);
        holder.sheng_num.setText(list.get(position).sheng_num);
        holder.cangku_name.setText(list.get(position).cangku_name);
        return convertView;
    }
    private class ViewHolder{
        TextView title,num,sort,product_name,pai_num,
                price,sheng_num,cangku_name;
    }
}
