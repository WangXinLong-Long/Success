package com.silianchuangye.sumao.success.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.bean.BuyInfo;

import java.util.List;

/**
 * Created by Administrator on 2016/7/15.
 */
public class BuyAdapter extends BaseAdapter{
    private List<BuyInfo> list;
    private Context ctx;
    public BuyAdapter(List<BuyInfo>list,Context ctx){
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
            convertView=View.inflate(ctx, R.layout.item_buy_lv,null);
            holder=new ViewHolder();
            holder.tv= (TextView) convertView.findViewById(R.id.tv_item_buy);
            holder.img= (ImageView) convertView.findViewById(R.id.img_item_buy_selsect);
            convertView.setTag(holder);
        }
        holder= (ViewHolder) convertView.getTag();
        holder.tv.setText(list.get(position).tv);
        if(list.get(position).flag){
            holder.img.setImageResource(R.mipmap.cart_select);
        }else{
            holder.img.setImageResource(R.mipmap.cart_select_null);
        }
        return convertView;
    }

    private class ViewHolder{
        TextView tv;
        ImageView img;
    }
}
