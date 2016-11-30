package com.silianchuangye.sumao.success.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.bean.ChinaNorth_Margin_info;

import java.util.List;

/**
 * Created by Administrator on 2016/5/20.
 */
public class ChinaNorth_Margin_Adapter extends BaseAdapter {
    private List<ChinaNorth_Margin_info> list;
    private Context ctx;
    public ChinaNorth_Margin_Adapter(List<ChinaNorth_Margin_info> list,Context ctx){
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
            convertView=View.inflate(ctx, R.layout.item_chinanorth_margin, null);
            holder=new ViewHolder();
            holder.BankName= (TextView) convertView.findViewById(R.id.tv_item_margin_bank);
            holder.MarginMoney= (TextView) convertView.findViewById(R.id.tv_item_margin_price);
            holder.img= (ImageView) convertView.findViewById(R.id.radio_item_margin_bank);
          convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.BankName.setText(list.get(position).bank);
        holder.MarginMoney.setText(list.get(position).money);
        if(list.get(position).Flag){
            holder.img.setImageResource(R.mipmap.cart_select);
        }else{
            holder.img.setImageResource(R.mipmap.cart_select_null);
        }
        return convertView;
    }
    private class ViewHolder{
        TextView BankName,MarginMoney;
        ImageView img;
    }
}
