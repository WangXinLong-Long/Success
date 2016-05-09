package com.silianchuangye.sumao.success.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.model.SpotOrderModel;

import java.util.List;


/**
 * Created by Administrator on 2016/5/4 0004.
 */
public class SpotOrderAdapter extends BaseAdapter {
    Context context;
    SpotOrderModel model;
    List<SpotOrderModel> list;
    LayoutInflater inflater ;
    public SpotOrderAdapter(Context context, List<SpotOrderModel>  list) {
            this.context = context;
            this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if (list!=null)
        {
//            Log.i("test",list.size()+"");
            return list.size();
        }
        return 0;
    }

    @Override
    public SpotOrderModel getItem(int position) {
        return list.get(position);

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null)
        {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.spot_order_item,null);
            holder.productModel = ((TextView) convertView.findViewById(R.id.productModel));
            holder.univalent = ((TextView) convertView.findViewById(R.id.univalent2));
            holder.number = ((TextView) convertView.findViewById(R.id.number2));
            holder.enterprise = ((TextView) convertView.findViewById(R.id.enterprise2));
            holder.totalMoney = ((TextView) convertView.findViewById(R.id.totalMoney2));
            holder.warehouse = ((TextView) convertView.findViewById(R.id.warehouse2));
            holder.company = ((TextView) convertView.findViewById(R.id.company2));
            convertView.setTag(holder);
        }else {
            holder = ((ViewHolder) convertView.getTag());
        }
        holder.productModel.setText(list.get(position).getProductModel());

        holder.univalent.setText(list.get(position).getUnivalent()+"");
        holder.number.setText(list.get(position).getNumber()+"");
        holder.enterprise.setText(list.get(position).getEnterprise()+"");
        holder.totalMoney.setText(list.get(position).getTotalMoney()+"");
        holder.warehouse.setText(list.get(position).getWarehouse()+"");
        holder.company.setText(list.get(position).getCompany()+"");
        return convertView;
    }
    class ViewHolder
    {
        TextView productModel;
        TextView univalent;
        TextView number;
        TextView enterprise;
        TextView totalMoney;
        TextView warehouse;
        TextView company;
    }
}
