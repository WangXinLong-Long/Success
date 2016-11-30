package com.silianchuangye.sumao.success.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.homepage.goodInStock.GoodsInStockActivityMVP.bean.SMCl;
import com.silianchuangye.sumao.success.model.PreSaleModel;
import com.silianchuangye.sumao.success.utils.LogUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/5/30 0030.
 */
public class PreSaleAdapter extends BaseAdapter {
    private  Context context;
    private  List<SMCl> preSaleModels;
    LayoutInflater inflater;

    public PreSaleAdapter(Context context,  List<SMCl> preSaleModels) {

        this.context = context;
        this.preSaleModels = preSaleModels;
        inflater = LayoutInflater.from(context);
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
        if (convertView==null)
        {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.presale_item,null);
            viewHolder.title = (TextView) convertView.findViewById(R.id.title_presale_item);
            viewHolder.number = (TextView) convertView.findViewById(R.id.number_presale_item);
            viewHolder.price = (TextView) convertView.findViewById(R.id.price_presale_item);
            viewHolder.company = (TextView) convertView.findViewById(R.id.company_presale_item);
            viewHolder.warehouse = (TextView) convertView.findViewById(R.id.warehouse_presale_item);
//            viewHolder.productType = (TextView) convertView.findViewById(R.id.productType_presale_item);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.title.setText(preSaleModels.get(position).getCl_mingcheng());
        viewHolder.number.setText(preSaleModels.get(position).getCl_shuliang());
        viewHolder.price.setText(preSaleModels.get(position).getCl_jine());
        viewHolder.company.setText(preSaleModels.get(position).getCl_qiye());
        viewHolder.warehouse.setText(preSaleModels.get(position).getCl_cangku());
//        viewHolder.productType.setText(preSaleModels.get(position).getProductType());

        return convertView;
    }
    class ViewHolder
    {
        TextView title;
        TextView number;
        TextView price;
        TextView company;
        TextView warehouse;
        TextView productType;

    }
}
