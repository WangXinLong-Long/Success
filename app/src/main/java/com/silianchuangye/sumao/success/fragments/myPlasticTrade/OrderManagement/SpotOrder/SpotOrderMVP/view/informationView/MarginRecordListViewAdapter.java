package com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.SpotOrderMVP.view.informationView;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.myPlasticTrade.OrderManagement.SpotOrder.SpotOrderMVP.MarginRecordItemBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Jobs Created on 2017/2/8.
 */
public class MarginRecordListViewAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<Map<String,Object>> lists;

    public MarginRecordListViewAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        lists = new ArrayList<>();
    }

    public void addData(List<Map<String,Object>> data){
        lists.addAll(data);
    }

    public void clearData(){
    lists.clear();
}
    @Override
    public int getCount() {
        Log.d("list的size",lists.size()+"");
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //        margin_record_item
        ViewHolder viewHolder = null;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView =  inflater.inflate(R.layout.margin_record_item,null);
            //        显示 已解冻或已冻结 两种状态
            viewHolder.frozen_tv = ((TextView) convertView.findViewById(R.id.frozen_tv));
            //        冻结资金
            viewHolder.capital = ((TextView) convertView.findViewById(R.id.capital));
            //        元
            viewHolder.capital_yuan = ((TextView) convertView.findViewById(R.id.capital_yuan));
            //        冻结日期或解冻日期
            viewHolder.frozen_date_number = ((TextView) convertView.findViewById(R.id.frozen_date_number));
            //        冻结时间或解冻时间
            viewHolder.frozen_time_number = ((TextView) convertView.findViewById(R.id.frozen_time_number));
            convertView.setTag(viewHolder);
        }else {
            viewHolder = ((ViewHolder) convertView.getTag());
        }
        if (lists.get(position).get("type").equals("freeze")){
            viewHolder.frozen_tv.setTextColor(Color.RED);
            viewHolder.capital_yuan.setTextColor(Color.RED);
            viewHolder.capital.setTextColor(Color.RED);
            viewHolder.frozen_tv.setText("已冻结");
        }else if (lists.get(position).get("type").equals("force")){
            viewHolder.frozen_tv.setTextColor(Color.YELLOW);
            viewHolder.capital_yuan.setTextColor(Color.YELLOW);
            viewHolder.capital.setTextColor(Color.YELLOW);
            viewHolder.frozen_tv.setText("已强扣");
        }else {
            viewHolder.frozen_tv.setTextColor(Color.GREEN);
            viewHolder.capital_yuan.setTextColor(Color.GREEN);
            viewHolder.capital.setTextColor(Color.GREEN);
            viewHolder.frozen_tv.setText("已解冻");
        }
        //viewHolder.frozen_tv.setText(lists.get(position).get("type").toString());
        viewHolder.capital.setText(lists.get(position).get("amount").toString());
        viewHolder.frozen_date_number.setText(new SimpleDateFormat("yyyy-MM-dd").
                format(Double.parseDouble(lists.get(position).get("time").toString())));
        viewHolder.frozen_time_number.setText(new SimpleDateFormat("HH:mm:ss").format(Double.parseDouble(lists.get(position).get("time").toString())));
        return convertView;
    }
    class ViewHolder{
//        显示 已解冻或已冻结 两种状态
        TextView frozen_tv;
//        冻结资金
        TextView capital;
//        元（改变“元” 字体颜色用）
        TextView capital_yuan;
//        冻结日期或解冻日期
        TextView frozen_date_number;
//        冻结时间或解冻时间
        TextView frozen_time_number;
    }
}
