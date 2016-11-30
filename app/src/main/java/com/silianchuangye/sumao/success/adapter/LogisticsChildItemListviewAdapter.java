package com.silianchuangye.sumao.success.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.bean.LogisticsListChild;

import java.util.List;

/**
 * Created by Administrator on 2016/7/11 0011.
 * 查看物流需求列表中子列表的数据填充器
 */

public class LogisticsChildItemListviewAdapter extends BaseAdapter {
    Context context;
    List<LogisticsListChild> childrenlist;
    LayoutInflater inflater;
    public LogisticsChildItemListviewAdapter(Context context, List<LogisticsListChild> childrenlist) {
        this.context = context;
        this.childrenlist = childrenlist;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return childrenlist.size();
    }

    @Override
    public Object getItem(int position) {
        return childrenlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null)
        {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.logistics_child_item_listview_item,null);
            holder.logistics_child_item_listview_item_number2 = ((TextView) convertView.findViewById(R.id.logistics_child_item_listview_item_number2));
            holder.logistics_child_item_listview_item_classification1 = ((TextView) convertView.findViewById(R.id.logistics_child_item_listview_item_classification1));
            holder.product_order_number2 = ((TextView) convertView.findViewById(R.id.product_order_number2));
            holder.productModel2 = ((TextView) convertView.findViewById(R.id.productModel2));
            convertView.setTag(holder);
        }else {
            holder = ((ViewHolder) convertView.getTag());
        }
//        产品数量
        holder.logistics_child_item_listview_item_number2.setText(childrenlist.get(position).getNumber());
//        产品分类
        holder.logistics_child_item_listview_item_classification1.setText(childrenlist.get(position).getClassification());
//        产品订单编号
        holder.product_order_number2.setText(childrenlist.get(position).getProductOrderNumber());
//        产品名称
        holder.productModel2.setText(childrenlist.get(position).getProductName());

        return convertView;
    }
    class ViewHolder{
        TextView logistics_child_item_listview_item_number2;
        TextView logistics_child_item_listview_item_classification1;
        TextView product_order_number2;
        TextView productModel2;
    }
}
