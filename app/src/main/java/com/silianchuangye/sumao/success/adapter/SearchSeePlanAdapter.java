package com.silianchuangye.sumao.success.adapter;

import android.content.ClipboardManager;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.ShangYou.SearchSeePlanInfo;

import java.util.List;

/**
 * Created by Administrator on 2016/9/6.
 */
public class SearchSeePlanAdapter extends BaseAdapter{
    private Context ctx;
    private List<SearchSeePlanInfo>list;
    public SearchSeePlanAdapter(Context ctx,List<SearchSeePlanInfo>list){
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
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if(convertView==null){
            convertView=View.inflate(ctx, R.layout.item_search_seeplan,null);
            holder=new ViewHolder();
            holder.tv_date= (TextView) convertView.findViewById(R.id.tv_seeplan_right_date);
            holder.tv_name= (TextView) convertView.findViewById(R.id.tv_seeplan_right_name);
            holder.tv_cangku= (TextView) convertView.findViewById(R.id.tv_seeplan_right_cangku);
            holder.tv_num= (TextView) convertView.findViewById(R.id.tv_seeplan_right_num);
            holder.tv_peisong= (TextView) convertView.findViewById(R.id.tv_seeplan_right_peisong);
            holder.tv_state= (TextView) convertView.findViewById(R.id.tv_seeplan_right_state);
            holder.tv_dingdannum= (TextView) convertView.findViewById(R.id.tv_seeplan_right_dingdannum);
            holder.btn_copy= (Button) convertView.findViewById(R.id.btn_seeplan_copy);
            holder.img= (ImageView) convertView.findViewById(R.id.img_item_search_seeplan);
            holder.relative= (RelativeLayout) convertView.findViewById(R.id.relative_final);
            holder.line_searchseeplan=convertView.findViewById(R.id.line_searchseeplan);
            holder.tv_leftstate= (TextView) convertView.findViewById(R.id.tv_seeplan_left_state);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.tv_date.setText(list.get(position).date);
        holder.tv_name.setText(list.get(position).name);
        holder.tv_cangku.setText(list.get(position).cangku);
        holder.tv_num.setText(list.get(position).num);
        holder.tv_peisong.setText(list.get(position).peisong);
        holder.tv_state.setText(list.get(position).state);
        holder.tv_dingdannum.setText(list.get(position).dingdannum);
        holder.img.setImageResource(list.get(position).img_plan);
        if(list.get(position).img_plan==R.mipmap.shangyouplan1){
            //原计划
            holder.line_searchseeplan.setVisibility(View.GONE);
            holder.relative.setVisibility(View.GONE);
            holder.tv_state.setVisibility(View.GONE);
            holder.tv_leftstate.setVisibility(View.GONE);
        }else{
            holder.tv_name.setTextColor(ctx.getResources().getColor(R.color.red));
            holder.tv_num.setTextColor(ctx.getResources().getColor(R.color.red));
        }
        //复制按钮
        holder.btn_copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager copy = (ClipboardManager) ctx
                        .getSystemService(Context.CLIPBOARD_SERVICE);
                copy.setText(holder.tv_dingdannum.getText().toString());
            }
        });
        return convertView;
    }
    private class ViewHolder{
        TextView tv_date,tv_name,tv_cangku,tv_num,tv_peisong,tv_state,tv_leftstate,tv_dingdannum;
        ImageView img;
        Button btn_copy;
        RelativeLayout relative;
        View line_searchseeplan;
    }
}
