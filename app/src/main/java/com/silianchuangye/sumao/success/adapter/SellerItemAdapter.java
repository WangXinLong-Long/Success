package com.silianchuangye.sumao.success.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.SellerManagementPlatform.SellerOrderManagement.ReNumAndPrice;
import com.silianchuangye.sumao.success.fragments.bean.SellerItemInfo;

import java.util.List;

/**
 * Created by Administrator on 2016/7/25.
 */
public class SellerItemAdapter extends BaseAdapter {
    private Context ctx;
    private List<SellerItemInfo> list;
    private SellerItemCall call;

    public SellerItemAdapter(Context ctx, List<SellerItemInfo> list, SellerItemCall call) {
        this.ctx = ctx;
        this.list = list;
        this.call=call;
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
        ViewHolder holder;
        if(convertView==null){
            convertView=View.inflate(ctx, R.layout.item_seller_lv,null);
            holder=new ViewHolder();
            holder.tielte= (TextView) convertView.findViewById(R.id.tv_item_seller_title);
            holder.xiugai= (TextView) convertView.findViewById(R.id.tv_item_seller_xiugai);
            holder.sort= (TextView) convertView.findViewById(R.id.tv_item_seller_sort);
            holder.name= (TextView) convertView.findViewById(R.id.tv_item_seller_product_name);
            holder.old_price= (TextView) convertView.findViewById(R.id.tv_item_seller_old_price);
            holder.new_price= (TextView) convertView.findViewById(R.id.tv_item_seller_new_price);
            holder.num= (TextView) convertView.findViewById(R.id.tv_item_seller_num);
            holder.new_price= (TextView) convertView.findViewById(R.id.tv_item_seller_new_price);
            holder.all_price= (TextView) convertView.findViewById(R.id.tv_item_seller_product_allprice);
            holder.cangku= (TextView) convertView.findViewById(R.id.tv_item_seller_cangku_name);
            holder.relative= (RelativeLayout) convertView.findViewById(R.id.relative_item_seller);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.tielte.setText(list.get(position).title);
        holder.xiugai.setText(list.get(position).xiugai);
        holder.sort.setText(list.get(position).sort);
        holder.name.setText(list.get(position).name);
        holder.old_price.setText(list.get(position).olr_price);
        holder.new_price.setText(list.get(position).new_price);
        holder.num.setText(list.get(position).num);
        holder.all_price.setText(list.get(position).all_price);
        holder.cangku.setText(list.get(position).cangku);
        holder.relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call.call(position);
            }
        });
        return convertView;
    }
    private class ViewHolder{
        TextView tielte,xiugai,sort,name,old_price,new_price,all_price,num,cangku;
        RelativeLayout relative;
    }
    public interface SellerItemCall{
        public void call(int position);
    }
}
