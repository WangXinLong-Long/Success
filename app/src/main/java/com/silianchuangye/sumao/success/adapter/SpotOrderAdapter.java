package com.silianchuangye.sumao.success.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.preSell.presellallFragmentMVP.bean.PreSellAllBean;
import com.silianchuangye.sumao.success.model.SpotOrderModel;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2016/5/4 0004.
 */
public class SpotOrderAdapter extends BaseAdapter {
    Context context;
    SpotOrderModel model;
    List<Map<String,Object>> list;
    LayoutInflater inflater;
    PreSellAllBean.OrderBean data;
    private String cl_fangshi;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public SpotOrderAdapter(Context context,List<Map<String,Object>>list,String cl_fangshi) {
        this.context = context;
        this.list=list;
        this.cl_fangshi=cl_fangshi;
        inflater = LayoutInflater.from(context);
    }



    public void clearData(){
        this.list.clear();
    }
    @Override
    public int getCount() {
        if (list != null) {
//            Log.i("test",list.size()+"");
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.spot_order_item, null);
            holder.productModel = ((TextView) convertView.findViewById(R.id.productModel));
            holder.univalent = ((TextView) convertView.findViewById(R.id.univalent2));
            holder.number = ((TextView) convertView.findViewById(R.id.number2));
//            holder.enterprise = ((TextView) convertView.findViewById(R.id.enterprise2));
            holder.totalMoney = ((TextView) convertView.findViewById(R.id.totalMoney2));
            holder.warehouse = ((TextView) convertView.findViewById(R.id.warehouse2));
            holder.company = ((TextView) convertView.findViewById(R.id.company2));
//            holder.type = (TextView) convertView.findViewById(R.id.type2);
            holder.fenlei = (TextView) convertView.findViewById(R.id.fenlei2);
//            物流信息
            holder.date = (TextView) convertView.findViewById(R.id.date2);
            convertView.setTag(holder);
        } else {
            holder = ((ViewHolder) convertView.getTag());
        }
        holder.productModel.setText(list.get(position).get("cl_mingcheng").toString());
        Float price=Float.parseFloat(list.get(position).get("cl_jine").toString());
        DecimalFormat df = new DecimalFormat("#.##");
        //all_price.setText(df.format(x) + "");
        holder.univalent.setText(df.format(price)+"元");
        holder.number.setText(list.get(position).get("cl_shuliang").toString());
//        holder.enterprise.setText(list.get(position).getEnterprise()+"");
        Float totle=Float.parseFloat(list.get(position).get("cl_amount").toString());
        holder.totalMoney.setText(df.format(totle)+"元");
        holder.warehouse.setText(list.get(position).get("cl_cangku") + "");

        //holder.company.setText(simpleDateFormat.format(Double.parseDouble(list.get(position).get("deliveryStartDate").toString())+"至"+ simpleDateFormat.format(Double.parseDouble(list.get(position).get("deliveryEndDate").toString())))+"");
          holder.company.setText(new SimpleDateFormat("yyyy-MM-dd ").format(Double.parseDouble(list.get(position).get("deliveryStartDate").toString()))+"至"+new SimpleDateFormat("yyyy-MM-dd ").format(Double.parseDouble(list.get(position).get("deliveryEndDate").toString())));
        //            物流信息
        holder.date.setText(cl_fangshi);
        holder.fenlei.setText(list.get(position).get("cl_fenlei") + "");
//        String type = data.getType();
//        if (type.equals("fixedPricingOrder")){//现货
//            holder.type.setText("现货");
//        }else if (type.equals("openAuctionOrder")){//公开竞拍
//            holder.type.setText("公开竞拍");
//        }else if (type.equals("sealedAuctionOrder")){//密封竞拍
//            holder.type.setText("密封竞拍");
//        }else if (type.equals("integratePurchaseOrder")){//团购
//            holder.type.setText("团购");
//        }else if (type.equals("forwardPricingOrder")){//预售
//            holder.type.setText("预售");
//        }else if (type.equals("scheduleOrder")){//计划订单
//            holder.type.setText("计划订单");
//        }
        return convertView;
    }

    class ViewHolder {
        TextView productModel;
        TextView univalent;
        TextView number;
        TextView enterprise;
        TextView totalMoney;
        TextView warehouse;
        TextView company;
        TextView type;
        TextView date;
        TextView fenlei;
    }
}
