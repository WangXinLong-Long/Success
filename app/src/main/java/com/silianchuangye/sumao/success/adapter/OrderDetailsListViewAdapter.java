package com.silianchuangye.sumao.success.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.model.OrderDeatilsModel;
import com.silianchuangye.sumao.success.model.SpotOrderModel;

import java.util.List;

/**
 * Created by Administrator on 2016/7/6 0006.
 */
public class OrderDetailsListViewAdapter extends BaseAdapter{
    private OrderDeatilsModel orderDeatilsModel;
private Context context;
    private List<OrderDeatilsModel> list;
    LayoutInflater inflater ;


    public OrderDetailsListViewAdapter(Context context, List<OrderDeatilsModel> list) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if (list!=null)
        {
            return list.size();
        }
        return 0;
    }

    @Override
    public OrderDeatilsModel getItem(int position) {
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
            convertView = inflater.inflate(R.layout.order_details_activity_item,null);
//            分类
            holder.classification2 =(TextView) convertView.findViewById(R.id.classification2);
//            产品单价(元/吨)
            holder.product_unit_price2 =(TextView) convertView.findViewById(R.id.product_unit_price2);
//            数量(吨)
            holder.enterprise2 =(TextView) convertView.findViewById(R.id.enterprise2);
//            产品总价
            holder.totalMoney2 =(TextView) convertView.findViewById(R.id.totalMoney2);
//            配送方式
            holder.distributionMode2 =(TextView) convertView.findViewById(R.id.distributionMode2);
//            交货时间
            holder.delivery_time2 =(TextView) convertView.findViewById(R.id.delivery_time2);
//            仓库
            holder.warehouse2 =(TextView) convertView.findViewById(R.id.warehouse2);
            holder.productModel= (TextView) convertView.findViewById(R.id.productModel);
            convertView.setTag(holder);
        }else {
            holder = ((ViewHolder) convertView.getTag());
        }
        //            分类
        holder.classification2.setText(list.get(position).getClassification()+"");
        //            产品单价(元/吨)
        holder.product_unit_price2.setText(list.get(position).getProductUnitPrice()+"");
        //            数量(吨)
        holder.enterprise2.setText(list.get(position).getNumber()+"");
        //            产品总价
        holder.totalMoney2.setText(list.get(position).getTotalProductPrice()+"");
        //            配送方式
        holder.distributionMode2.setText(list.get(position).getDistributionMode()+"");
        //            交货时间
        holder.delivery_time2.setText(list.get(position).getDeliveryTime()+"");
        //            仓库
        holder.warehouse2.setText(list.get(position).getWarehouse()+"");
        holder.productModel.setText(list.get(position).getTitle());
        return convertView;
    }
    class ViewHolder{
        TextView classification2,productModel;
        TextView product_unit_price2;
        TextView enterprise2;
        TextView totalMoney2;
        TextView distributionMode2;
        TextView delivery_time2;
        TextView warehouse2;
    }
}
