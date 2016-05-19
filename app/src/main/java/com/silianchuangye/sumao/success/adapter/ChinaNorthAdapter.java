package com.silianchuangye.sumao.success.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.homepage.theprice.ChinaNorthInfo;

import java.util.List;

/**
 * Created by Administrator on 2016/5/17.
 * 点价下的
 */
public class ChinaNorthAdapter extends BaseAdapter{
    private List<ChinaNorthInfo> list;
    private Context ctx;
    public ChinaNorthAdapter(List<ChinaNorthInfo> list,Context ctx){
        this.list = list;
        this.ctx=ctx;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            convertView=View.inflate(ctx, R.layout.item_chiannorth,null);
            holder=new ViewHolder();
            holder.tv_itemchinanorth_title= (TextView) convertView.findViewById(R.id.tv_itemchinanorth_title);
            holder.tv_itemchinanorth_name= (TextView) convertView.findViewById(R.id.tv_itemchinanorth_name);
            holder.tv_itemchinanorth_telnum= (TextView) convertView.findViewById(R.id.tv_itemchinanorth_telnum);
            holder.btn_itemchinanorth_my= (Button) convertView.findViewById(R.id.btn_itemchiannorth);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.tv_itemchinanorth_title.setText(list.get(position).title);
        holder.tv_itemchinanorth_name.setText(list.get(position).name);
        holder.tv_itemchinanorth_telnum.setText(list.get(position).telnum);
        return convertView;
    }
    private class ViewHolder{
        TextView tv_itemchinanorth_title,
        tv_itemchinanorth_name,
        tv_itemchinanorth_telnum;
        Button btn_itemchinanorth_my;

    }
}
