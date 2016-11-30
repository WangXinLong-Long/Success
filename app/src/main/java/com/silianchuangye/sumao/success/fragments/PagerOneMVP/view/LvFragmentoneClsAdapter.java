package com.silianchuangye.sumao.success.fragments.PagerOneMVP.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleMVP.bean.Cl;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleMVP.bean.Forward;
import com.silianchuangye.sumao.success.fragments.homepage.preSale.PreSaleMVP.bean.Group;

import java.util.List;

/**
 * Created by Administrator on 2016/8/26 0026.
 */
public class LvFragmentoneClsAdapter extends BaseAdapter {
    List<Group> cls;
    Context context;
    LayoutInflater inflater;
    public LvFragmentoneClsAdapter(List<Group> cls, Context context) {
        this.cls = cls;
        this.context = context;
        inflater =  LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if (cls.size()!=0){

            return cls.size();
        }
        return 0;
    }

    @Override
    public Group getItem(int position) {
        return cls.get(position);
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
        holder.tvfragmentfordate.setText(cls.get(position).getCl_jine());
        holder.tvfragmentforcity.setVisibility(View.GONE);
        holder.tvFragmentforcom.setText(cls.get(position).getCl_mingcheng());
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
