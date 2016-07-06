package com.silianchuangye.sumao.success.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

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
        PreSaleAdapter.ViewHolder holder;
        if (convertView==null)
        {

        }
        return null;
    }
    class ViewHolder{

    }
}
