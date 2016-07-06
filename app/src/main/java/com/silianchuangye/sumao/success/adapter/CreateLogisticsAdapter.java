package com.silianchuangye.sumao.success.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.bean.Createlogistics_ExpandInfo;
import com.silianchuangye.sumao.success.fragments.bean.Createlogistics_ListInfo;

import java.util.List;

/**
 * Created by Administrator on 2016/7/6.
 */
public class CreateLogisticsAdapter extends BaseExpandableListAdapter{
    private Context ctx;
    private List<Createlogistics_ExpandInfo> expandList;
    public CreateLogisticsAdapter(Context ctx,List<Createlogistics_ExpandInfo>expandList){
        this.ctx=ctx;
        this.expandList=expandList;
    }
    @Override
    public int getGroupCount() {
        return expandList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return expandList.get(groupPosition).list.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return expandList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return expandList.get(groupPosition).list.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
       ViewHolder holder;
        if(convertView==null){
            convertView=View.inflate(ctx, R.layout.item_create_logistics,null);
            holder=new ViewHolder();
            holder.tv_expand_order_num= (TextView) convertView.findViewById(R.id.tv_item_create_logistics_order_num);
            holder.tv_expand_order_name= (TextView) convertView.findViewById(R.id.tv_item_create_logistics_name);
            holder.tv_expand_date= (TextView) convertView.findViewById(R.id.tv_item_create_logistic_date);
            holder.tv_expand_company= (TextView) convertView.findViewById(R.id.tv_item_create_logistics_company_name);
            holder.btn_expand_kefu= (Button) convertView.findViewById(R.id.btn_item_create_logistics_kefu);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_expand_order_num.setText(expandList.get(groupPosition).order_num);
        holder.tv_expand_order_name.setText(expandList.get(groupPosition).order_name);
        holder.tv_expand_company.setText(expandList.get(groupPosition).company_name);
        holder.tv_expand_date.setText(expandList.get(groupPosition).date);
        holder.btn_expand_kefu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ctx,"客服",Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if(convertView==null){
            convertView=View.inflate(ctx,R.layout.item_create_logistics_lv,null);
            holder=new ViewHolder();
            holder.tv_child_produce_top_name= (TextView) convertView.findViewById(R.id.tv_item_create_logistics_product_top_name);
            holder.img_child_select= (ImageView) convertView.findViewById(R.id.img_item_create_logistics_top_select);
            holder.tv_child_product_name= (TextView) convertView.findViewById(R.id.tv_item_create_logistics_lv_produce_name);
            holder.tv_child_logistics= (TextView) convertView.findViewById(R.id.tv_item_create_logistics_logistica_top_type);
            holder.tv_child_sub= (TextView) convertView.findViewById(R.id.tv_item_create_logistics_lv_sub);
            holder.edt_child_num= (EditText) convertView.findViewById(R.id.edt_item_create_logistics_num);
            holder.tv_child_add= (TextView) convertView.findViewById(R.id.tv_item_create_logistics_lv_add);
            holder.tv_child_sort= (TextView) convertView.findViewById(R.id.tv_item_create_logistics_lv_sort_name);
            holder.tv_child_product_name= (TextView) convertView.findViewById(R.id.tv_item_create_logistics_lv_produce_name);
            holder.tv_child_num= (TextView) convertView.findViewById(R.id.tv_item_create_logistics_lv_num_dun);
            holder.tv_child_price= (TextView) convertView.findViewById(R.id.tv_item_create_logistics_lv_produce_price_money);
            holder.tv_child_can_num= (TextView) convertView.findViewById(R.id.tv_item_create_logistics_lv_can_num);
            holder.tv_child_date= (TextView) convertView.findViewById(R.id.tv_item_create_logistics_lv_date);
            holder.tv_child_cangku= (TextView) convertView.findViewById(R.id.tv_item_create_logistics_lv_cangku_name);
            holder.liear_child_message= (LinearLayout) convertView.findViewById(R.id.linear_item_create_logistics_message);
            holder.img_child_logistics_top_in= (ImageView) convertView.findViewById(R.id.img_item_create_logistics_top_in);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.tv_child_produce_top_name.setText(expandList.get(groupPosition).list.get(childPosition).product_name);
        holder.tv_child_product_name.setText(expandList.get(groupPosition).list.get(childPosition).product_name);
        holder.tv_child_logistics.setText(expandList.get(groupPosition).list.get(childPosition).logistics_name);
        holder.tv_child_sort.setText(expandList.get(groupPosition).list.get(childPosition).sort);
        holder.tv_child_price.setText(expandList.get(groupPosition).list.get(childPosition).only_price+"");
        holder.tv_child_num.setText(expandList.get(groupPosition).list.get(childPosition).num+"吨");
        holder.tv_child_can_num.setText(expandList.get(groupPosition).list.get(childPosition).can_num+"吨");
        holder.tv_child_date.setText(expandList.get(groupPosition).list.get(childPosition).date);
        holder.tv_child_cangku.setText(expandList.get(groupPosition).list.get(childPosition).cangku_name);
        holder.img_child_logistics_top_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.liear_child_message.setVisibility(View.VISIBLE);
            }
        });
        holder.img_child_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.img_child_select.setImageResource(R.mipmap.cart_select);
            }
        });
        holder.tv_child_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=holder.edt_child_num.getText().toString();
                int i=Integer.valueOf(str);
                i--;
                holder.edt_child_num.setText(i+"");
            }
        });
        holder.tv_child_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=holder.edt_child_num.getText().toString();
                int i=Integer.valueOf(str);
                i++;
                holder.edt_child_num.setText(i+"");
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

     class ViewHolder{
        TextView tv_expand_order_num;
        TextView tv_expand_order_name;
        TextView tv_expand_date;
        TextView tv_expand_company;
        Button btn_expand_kefu;

        ImageView img_child_select,img_child_logistics_top_in;
        TextView tv_child_logistics;
        TextView tv_child_sub;
        TextView tv_child_add;
        EditText edt_child_num;
        LinearLayout liear_child_message;
        TextView tv_child_produce_top_name,tv_child_sort,tv_child_product_name,tv_child_num,tv_child_price
                ,tv_child_can_num,tv_child_date,tv_child_cangku;
    }
}
