package com.silianchuangye.sumao.success.adapter;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.bean.CartItemInfo;

import java.util.List;

/**
 * Created by Administrator on 2016/7/4.
 */
public class CartItemAdapter extends BaseAdapter{
    public List<CartItemInfo> list;
    public List<Integer>imglist;
    public Context ctx;
    public CartItemAdapter(List<CartItemInfo>list,Context ctx,List<Integer>imglist){
        this.ctx=ctx;
        this.list=list;
        this.imglist=imglist;
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
            convertView=View.inflate(ctx, R.layout.item_cart_price,null);
            holder=new ViewHolder();
            holder.tv= (TextView) convertView.findViewById(R.id.item_cart_tv);
            holder.img= (ImageView) convertView.findViewById(R.id.item_cart_select_img);
            holder.item_cart_img= (ImageView) convertView.findViewById(R.id.item_cart_img);
            convertView.setTag(holder);
        }
        holder= (ViewHolder) convertView.getTag();
        holder.item_cart_img.setImageResource(imglist.get(position));
        holder.tv.setText(list.get(position).bank_name);
        if(list.get(position).flag){
            holder.img.setImageResource(R.mipmap.cart_select);
        }else{
            holder.img.setImageResource(R.mipmap.cart_select_null);
        }
        return convertView;
    }
    private class ViewHolder {
        TextView tv;
        ImageView img, item_car,item_cart_img;
    }
}
