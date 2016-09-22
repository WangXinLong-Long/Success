package com.silianchuangye.sumao.success.fragments.type;

import android.content.Context;
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
    private final Map<String, String> hashMap;

    public TypeInfoActivityAdapter(Context context, List<Cls> cl) {
        this.context = context;
        this.cl = cl;
        inflater = LayoutInflater.from(context);
        hashMap = new HashMap<>();
        hashMap.put("englishAuctionProduct","公开竞拍");
        hashMap.put("fixedProduct","现货");
        hashMap.put("forward-pricing-product","预售");
        hashMap.put("demandScheduleProduct","计划订单");
        hashMap.put("sealedAuctionProduct","密封竞拍");
        hashMap.put("groupProdut","团购");
    }

    @Override
    public int getCount() {
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

        holder.tv_name_type_two.setText(cl.get(position).getCl_mingcheng());
        holder.tv_number_type_two.setText(cl.get(position).getCl_shuliang());
        holder.tv_price_type_two .setText(cl.get(position).getCl_jine());
        holder.tv_address_type_two.setText(cl.get(position).getCl_qiye());
        holder.tv_cangku_type_two.setText(cl.get(position).getCl_cangku());
        holder.tv_state_type_two.setText(hashMap.get(cl.get(position).getCl_type()));
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
