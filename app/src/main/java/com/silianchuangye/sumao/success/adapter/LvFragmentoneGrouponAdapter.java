package com.silianchuangye.sumao.success.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleMVP.bean.Forward;

import java.util.List;

/**
 * Created by Administrator on 2016/8/19 0019.
 */
public class LvFragmentoneGrouponAdapter extends BaseAdapter {
    List<Forward> forwards;
    Context context;
    LayoutInflater inflater;
    public LvFragmentoneGrouponAdapter(List<Forward> forwards, Context context) {
        this.forwards = forwards;
        this.context = context;
            inflater =  LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return forwards.size();
    }

    @Override
    public Forward getItem(int position) {
        return forwards.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView== null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.ragmentoneitemfordate, null);
            holder.tvfragmentfordate = ((TextView) convertView.findViewById(R.id.tvfragmentfordate));
            holder.tvfragmentforcity = ((TextView) convertView.findViewById(R.id.tvfragmentforcity));
            holder.tvFragmentforcom = ((TextView) convertView.findViewById(R.id.tvFragmentforcom));
            holder.tvfragmentfornumber = ((TextView) convertView.findViewById(R.id.tvfragmentfornumber));
            holder.ivfragmenticon = ((ImageView) convertView.findViewById(R.id.ivfragmenticon));
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvfragmentfordate.setText(forwards.get(position).getCl_jine());
        holder.tvfragmentforcity.setText(forwards.get(position).getCl_diqu());
        holder.tvFragmentforcom.setText(forwards.get(position).getCl_mingcheng());
        holder.tvfragmentfornumber.setVisibility(View.GONE);
        holder.ivfragmenticon.setVisibility(View.INVISIBLE);
        return convertView;
    }
    class ViewHolder{
        TextView tvfragmentfordate;
        TextView tvFragmentforcom;
        TextView tvfragmentforcity;
        TextView tvfragmentfornumber;
        ImageView ivfragmenticon;
    }
}
