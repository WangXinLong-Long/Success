package com.silianchuangye.sumao.success.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.ShangYou.DayPlanInfo;

import java.util.List;

/**
 * Created by Administrator on 2016/9/6.
 */
public class DayPlanAdapter extends BaseAdapter{
    private Context ctx;
    private List<DayPlanInfo> list;
    public DayPlanAdapter(Context ctx,List<DayPlanInfo>list){
        this.ctx=ctx;
        this.list=list;
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
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            convertView=View.inflate(ctx, R.layout.item_lv_dayplan,null);
            holder=new ViewHolder();
            holder.tv_date= (TextView) convertView.findViewById(R.id.tv_lv_dayplan_date_right);
            holder.tv_name= (TextView) convertView.findViewById(R.id.tv_lv_dayplan_name_right);
            holder.tv_num= (TextView) convertView.findViewById(R.id.tv_lv_dayplan_num_right);
            holder.tv_cangku= (TextView) convertView.findViewById(R.id.tv_lv_dayplan_cangku_right);
            holder.tv_peisong= (TextView) convertView.findViewById(R.id.tv_lv_dayplan_peisong_right);
            holder.tv_beizhu= (TextView) convertView.findViewById(R.id.tv_lv_dayplan_beizhu_right);
            holder.relative_delete= (RelativeLayout) convertView.findViewById(R.id.relative_delete);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.tv_date.setText(list.get(position).date);
        holder.tv_name.setText(list.get(position).nam);
        holder.tv_num.setText(list.get(position).num);
        holder.tv_cangku.setText(list.get(position).cangku);
        holder.tv_peisong.setText(list.get(position).peisong);
        holder.tv_beizhu.setText(list.get(position).beizhu);
        holder.relative_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ctx,"删除计划",Toast.LENGTH_SHORT).show();
                list.remove(position);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }
    private class ViewHolder{
        TextView tv_date,tv_name,tv_num,tv_cangku,tv_peisong,tv_beizhu;
        RelativeLayout relative_delete;
    }
}
