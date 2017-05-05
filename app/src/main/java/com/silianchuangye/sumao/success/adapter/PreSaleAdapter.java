package com.silianchuangye.sumao.success.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.bean.GoodsInStockActivityBean;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/30 0030.
 */
public class PreSaleAdapter extends BaseAdapter {
    private Context context;
    private  List<GoodsInStockActivityBean.InBean> preSaleModels;
    LayoutInflater inflater;
    private String xinhuo="";
    private TextView tv_icon;
    private  String state;
    private ListView listDemo;

    public PreSaleAdapter(Context context,String xianhuo) {
        preSaleModels = new ArrayList<>();
        this.context = context;
        this.xinhuo=xianhuo;
        this.listDemo=listDemo;
        inflater = LayoutInflater.from(context);
    }
    public PreSaleAdapter(Context context) {
        preSaleModels = new ArrayList<>();
        this.context = context;
        this.listDemo=listDemo;
        inflater = LayoutInflater.from(context);
    }

    public void addData( List<GoodsInStockActivityBean.InBean> data) {
        preSaleModels.addAll(data);
    }
public void clearData(){
    Log.d("走这个方法了","走这个方法了");
    preSaleModels.clear();
}
    @Override
    public int getCount() {
        return preSaleModels.size();
    }

    @Override
    public Object getItem(int position) {
        return preSaleModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {

            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.presale_item, null);
            tv_icon= (TextView) convertView.findViewById(R.id.icon);
            viewHolder.title = (TextView) convertView.findViewById(R.id.title_presale_item);
            viewHolder.number = (TextView) convertView.findViewById(R.id.number_presale_item);
            viewHolder.price = (TextView) convertView.findViewById(R.id.price_presale_item);
            viewHolder.company = (TextView) convertView.findViewById(R.id.company_presale_item);
            viewHolder.warehouse = (TextView) convertView.findViewById(R.id.warehouse_presale_item);
            viewHolder.productType = (TextView) convertView.findViewById(R.id.productType_presale_item);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.title.setText(preSaleModels.get(position).getIn_name());
        viewHolder.number.setText(preSaleModels.get(position).getIn_num());
       // DecimalFormat df = new DecimalFormat("0.00");

        BigDecimal b=new BigDecimal(preSaleModels.get(position).getIn_prix().toString());
        double  f1=b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
        viewHolder.price.setText(f1+"");

        viewHolder.company.setText(preSaleModels.get(position).getIn_qiye());
        viewHolder.warehouse.setText(preSaleModels.get(position).getIn_cangku());

        if (preSaleModels.get(position).getIn_leixing().equals("fixedProduct")){
            viewHolder.productType.setText("现货");
            viewHolder.productType.setVisibility(View.VISIBLE);
        }else if (preSaleModels.get(position).getIn_leixing().equals("forward-pricing-product")){
            viewHolder.productType.setText("预售");
            viewHolder.productType.setVisibility(View.VISIBLE);
        }
if (xinhuo.equals("现货直销")){
    viewHolder.productType.setVisibility(View.VISIBLE);
    viewHolder.price.setVisibility(View.GONE);
    tv_icon.setVisibility(View.GONE);
}


        return convertView;
    }

    class ViewHolder {

        TextView title;
        TextView number;
        TextView price;
        TextView company;
        TextView warehouse;
        TextView productType;


    }
}
