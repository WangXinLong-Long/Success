package com.silianchuangye.sumao.success.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.model.CustomerApprovalModel;

import java.util.List;

/**
 * Created by Administrator on 2016/7/19 0014.
 * 卖家已支付订单详情Adapter
 */
public class HavaPaidSellerOrderDetailsAdapter extends BaseAdapter {
    private List<HavaPaidSellerOrderDetailsModel> mLists;
    private Context context;
    private LayoutInflater inflater;
    public HavaPaidSellerOrderDetailsAdapter(Context context, List<HavaPaidSellerOrderDetailsModel> lists) {
        this.context = context;
        this.mLists = lists;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mLists.size();
    }

    @Override
    public Object getItem(int position) {
        return mLists.get(position);
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
            convertView = inflater.inflate(R.layout.hava_paid_seller_order_details_item,null);
            holder.title = ((TextView) convertView.findViewById(R.id.title));
            holder.classification = ((TextView) convertView.findViewById(R.id.classification2));
            holder.productName = ((TextView) convertView.findViewById(R.id.productName2));
            holder.originalUnitPrice = ((TextView) convertView.findViewById(R.id.originalUnitPrice2));
            holder.number = ((TextView) convertView.findViewById(R.id.number2));
            holder.newUnitPrice = ((TextView) convertView.findViewById(R.id.newUnitPrice2));
            holder.totalProductPrice = ((TextView) convertView.findViewById(R.id.totalProductPrice2));
            holder.warehouse = ((TextView) convertView.findViewById(R.id.warehouse2));
            convertView.setTag(holder);
        }else {
            holder = ((ViewHolder) convertView.getTag());
        }
        holder.title.setText(mLists.get(position).getTitle());
        holder.classification.setText(mLists.get(position).getClassification());
        holder.productName.setText(mLists.get(position).getProductName());
        holder.originalUnitPrice.setText(mLists.get(position).getOriginalUnitPrice());
        holder.number.setText(mLists.get(position).getNumber());
        holder.newUnitPrice.setText(mLists.get(position).getNewUnitPrice());
        holder.totalProductPrice.setText(mLists.get(position).getTotalProductPrice());
        holder.warehouse.setText(mLists.get(position).getWarehouse());

        return convertView;
    }
    class ViewHolder{
        //    标题
         TextView title;
        //    分类
         TextView classification;
        //    产品名称
         TextView productName;
        //    原单价
         TextView originalUnitPrice;
        //    数量
         TextView number;
        //    新单价
         TextView newUnitPrice;
        //    产品总价
         TextView totalProductPrice;
        //    仓库
         TextView warehouse;

    }
}
