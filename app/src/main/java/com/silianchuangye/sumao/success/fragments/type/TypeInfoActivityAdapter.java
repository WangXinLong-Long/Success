package com.silianchuangye.sumao.success.fragments.type;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.SearchActivityMVP.bean.Cls;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/9/9 0009.
 */
public class TypeInfoActivityAdapter extends BaseAdapter {
    private Context context;
    private List<Cls> cl;////////////////
    private LayoutInflater inflater;


    public TypeInfoActivityAdapter(Context context, List<Cls> cl) {
        this.context = context;
        this.cl = cl;
        inflater = LayoutInflater.from(context);

    }

    public void addData(List<Cls> data){
        cl.addAll(data);
    }
    public void clearData(){
        cl.clear();
    }

    @Override
    public int getCount() {
        Log.d("list的size",cl.size()+"");
        return cl.size();

    }

    @Override
    public Object getItem(int position) {
        return cl.get(position);
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
            convertView = inflater.inflate(R.layout.item_type_two, null);
            holder.tv_name_type_two = (TextView) convertView.findViewById(R.id.tv_name_type_two);
            holder.tv_number_type_two = (TextView) convertView.findViewById(R.id.tv_number_type_two);
            holder.tv_price_type_two = (TextView) convertView.findViewById(R.id.tv_price_type_two);
            holder.tv_address_type_two = (TextView) convertView.findViewById(R.id.tv_address_type_two);
            holder.tv_cangku_type_two = (TextView) convertView.findViewById(R.id.tv_cangku_type_two);
            holder.tv_state_type_two = (TextView) convertView.findViewById(R.id.tv_state_type_two);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }

        holder.tv_name_type_two.setText(cl.get(position).getIn_name());
        holder.tv_number_type_two.setText(cl.get(position).getIn_num());
        holder.tv_price_type_two .setText(cl.get(position).getIn_prix());
        holder.tv_address_type_two.setText(cl.get(position).getIn_qiye());
        holder.tv_cangku_type_two.setText(cl.get(position).getIn_cangku());

        if(cl.get(position).getIn_leixing().equals("englishAuctionProduct")){
            holder.tv_state_type_two.setText("公开竞拍");
        }else if (cl.get(position).getIn_leixing().equals("fixedProduct")){
            holder.tv_state_type_two.setText("现货");
        }else if(cl.get(position).getIn_leixing().equals("forward-pricing-product")){
            holder.tv_state_type_two.setText("预售");
        }else if (cl.get(position).getIn_leixing().equals("demandScheduleProduct")){
            holder.tv_state_type_two.setText("计划订单");
        }else if (cl.get(position).getIn_leixing().equals("sealedAuctionProduct")){
            holder.tv_state_type_two.setText("密封竞拍");
        }else if(cl.get(position).getIn_leixing().equals("groupProdut")){
            holder.tv_state_type_two.setText("团购");
        }

        return convertView;
    }

    class ViewHolder {
        TextView tv_name_type_two;
        TextView tv_number_type_two;
        TextView tv_price_type_two;
        TextView tv_address_type_two;
        TextView tv_cangku_type_two;
        TextView tv_state_type_two;
    }
}
