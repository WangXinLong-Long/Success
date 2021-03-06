package com.silianchuangye.sumao.success.adapter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.support.v4.app.INotificationSideChannel;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.customermanger.CustomerMessage;
import com.silianchuangye.sumao.success.customermanger.CustomerType;
import com.silianchuangye.sumao.success.fragments.bean.AllCustomInfo;

import java.util.List;

/**
 * Created by Administrator on 2016/7/12.
 */
public class AllManagerAdapter extends BaseAdapter{
    private List<AllCustomInfo> list;
    private Context ctx;
//    private My my=new My();
    private int i;
    String str;
    private CallBackType call;
    public AllManagerAdapter(List<AllCustomInfo>list,Context ctx,CallBackType call){
        this.ctx=ctx;
        this.list=list;
        this.call=call;

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
    public View getView(final int position, View convertView, ViewGroup parent) {
        i=position;
        ViewHolder holder;
        if(convertView==null){
            convertView=View.inflate(ctx, R.layout.item_all_manager,null);
            holder=new ViewHolder();
            holder.title= (TextView) convertView.findViewById(R.id.tv_item_all_manager_title);
            holder.num= (TextView) convertView.findViewById(R.id.tv_item_all_manager_num);
            holder.name= (TextView) convertView.findViewById(R.id.tv_item_all_manager_name);
            holder.type= (TextView) convertView.findViewById(R.id.tv_item_all_manager_type);
            holder.address= (TextView) convertView.findViewById(R.id.tv_item_all_manager_address);
            holder.buy= (TextView) convertView.findViewById(R.id.tv_item_all_manager_buy);
            holder.person= (TextView) convertView.findViewById(R.id.tv_item_all_manager_person);
//            holder.zhuangtai= (TextView) convertView.findViewById(R.id.tv_item_all_manager_zhuangtai);
            holder.relative= (RelativeLayout) convertView.findViewById(R.id.relative_item_all_manager);
            holder.relative_title= (RelativeLayout) convertView.findViewById(R.id.relative_item_all_manager_title);
            holder.state= (TextView) convertView.findViewById(R.id.tv_item_all_managet_state);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.title.setText(list.get(position).title);
        holder.num.setText(list.get(position).num);
        holder.name.setText(list.get(position).name);
        holder.type.setText(list.get(position).type);
        holder.address.setText(list.get(position).address);
        holder.buy.setText(list.get(position).buy);
        holder.person.setText(list.get(position).person);
        holder.state.setText(list.get(position).state);
        final String str=holder.state.getText().toString();
        if(str.equals("未通过")){
            holder.state.setTextColor(ctx.getResources().getColor(R.color.red));
            holder.relative.setVisibility(View.GONE);
        }else if(str.equals("已通过")){
            holder.state.setTextColor(ctx.getResources().getColor(R.color.green));
            holder.relative.setVisibility(View.GONE);
        }else if(str.equals("待审批")){
            holder.state.setTextColor(ctx.getResources().getColor(R.color.orange));
        }else{
            holder.relative.setVisibility(View.GONE);
        }
//        holder.zhuangtai.setText(list.get(position).zhuangtai);
//        holder.relative.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                call.call(position);
//            }
//        });

        holder.relative_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(str.equals("已通过")) {
                    Intent intent = new Intent(ctx, CustomerMessage.class);
                    intent.putExtra("title", list.get(position).title);
                    ctx.startActivity(intent);
                }
            }
        });
        return convertView;
    }
    private class ViewHolder{
        TextView title,num,name,type,address,buy,person,state;
        RelativeLayout relative,relative_title;
    }
//    private class My extends BroadcastReceiver {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//           str =intent.getStringExtra("type");
//            call.call(i);
//        }
//    }
    public interface CallBackType{
        public void call(int poistion);
    }
}
