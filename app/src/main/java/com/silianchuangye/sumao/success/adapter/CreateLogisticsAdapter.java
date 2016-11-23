package com.silianchuangye.sumao.success.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.HX.Constant;
import com.silianchuangye.sumao.success.HX.ui.LoginActivity;
import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.bean.Createlogistics_ExpandInfo;
import com.silianchuangye.sumao.success.fragments.bean.Createlogistics_ListInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/6.
 */
public class CreateLogisticsAdapter extends BaseExpandableListAdapter{
    private Context ctx;
    private List<Createlogistics_ExpandInfo> expandList;
    private boolean showFlag;
    boolean expandFlag;
    private LogisticsCall call;
    boolean falg=true;
    String  cangku,maijia,peisong;
View view,childView;
    public CreateLogisticsAdapter(Context ctx,List<Createlogistics_ExpandInfo>expandList,LogisticsCall call){
        this.ctx=ctx;
        this.expandList=expandList;
        this.call=call;
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
    ExpandHolder expand;
    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView=View.inflate(ctx, R.layout.item_create_logistics,null);
            view=convertView;
            expand=new ExpandHolder();
            expand.relative_group_item= (RelativeLayout) convertView.findViewById(R.id.relative_group_item);
            expand.tv_expand_order_num= (TextView) convertView.findViewById(R.id.tv_item_create_logistics_order_num);
            expand.tv_expand_order_name= (TextView) convertView.findViewById(R.id.tv_item_create_logistics_name);
            expand.tv_expand_date= (TextView) convertView.findViewById(R.id.tv_item_create_logistic_date);
            expand.tv_expand_company= (TextView) convertView.findViewById(R.id.tv_item_create_logistics_company_name);
            expand.btn_expand_kefu= (Button) convertView.findViewById(R.id.btn_item_create_logistics_kefu);
//            Jobs Created 最大可发货数量
            expand.tv_item_create_logistic_vailable_quantity2 = ((TextView) convertView.findViewById(R.id.tv_item_create_logistic_vailable_quantity2));
            convertView.setTag(expand);
        }else {
            expand = (ExpandHolder) convertView.getTag();
        }
        expand.tv_expand_order_num.setText(expandList.get(groupPosition).order_num);
        expand.tv_expand_order_name.setText(expandList.get(groupPosition).order_name);
        expand.tv_expand_company.setText(expandList.get(groupPosition).company_name);
        expand.tv_expand_date.setText(expandList.get(groupPosition).date);
        expand.tv_item_create_logistic_vailable_quantity2.setText(expandList.get(groupPosition).list.get(0).can_num);
        expand.btn_expand_kefu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHX = new Intent();
                intentHX.setClass(ctx, LoginActivity.class);
                intentHX.putExtra(Constant.MESSAGE_TO_INTENT_EXTRA, Constant.MESSAGE_TO_DEFAULT);
//                传入卖家id
                intentHX.putExtra(Constant.IM_SERVICE_NUMBER, expandList.get(groupPosition).cl_gongsiId);
                ctx.startActivity(intentHX);
            }
        });
        return convertView;
    }

    ViewHolder holder;

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, final ViewGroup parent) {
        if(convertView==null){
            convertView=View.inflate(ctx,R.layout.item_create_logistics_lv,null);
            childView=convertView;
            holder=new ViewHolder();
            holder.relative_child_item= (RelativeLayout) convertView.findViewById(R.id.relative1_child_item);
            holder.tv_child_produce_top_name= (TextView) convertView.findViewById(R.id.tv_item_create_logistics_product_top_name);
            holder.img_child_select= (ImageView) convertView.findViewById(R.id.img_item_create_logistics_top_select);
            holder.tv_child_product_name= (TextView) convertView.findViewById(R.id.tv_item_create_logistics_lv_produce_name);
            holder.tv_child_logistics= (TextView) convertView.findViewById(R.id.tv_item_create_logistics_logistica_top_type);
//            holder.tv_child_sub= (TextView) convertView.findViewById(R.id.tv_item_create_logistics_lv_sub);
//            holder.edt_child_num= (EditText) convertView.findViewById(R.id.edt_item_create_logistics_num);
//            holder.tv_child_add= (TextView) convertView.findViewById(R.id.tv_item_create_logistics_lv_add);
            holder.edt_child_num= (TextView) convertView.findViewById(R.id.edt_item_create_logistics_num);

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
        holder.edt_child_num.setText(expandList.get(groupPosition).list.get(childPosition).edt_num);
        holder.edt_child_num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(expandList.get(groupPosition).list.get(childPosition).SelectFlag) {
                    call.callNum(groupPosition, childPosition, expandList.get(groupPosition).list.get(childPosition).can_num);
                }else{
                    Toast.makeText(ctx,"请先选中该条数据",Toast.LENGTH_SHORT).show();
                }
            }
        });
        if(expandList.get(groupPosition).list.get(childPosition).visiableFlag){
            holder.liear_child_message.setVisibility(View.VISIBLE);
            holder.img_child_logistics_top_in.setImageResource(R.mipmap.my_sumao_iv_order_down);
        }else{
            holder.liear_child_message.setVisibility(View.GONE);
            holder.img_child_logistics_top_in.setImageResource(R.mipmap.my_sumao_iv_order);
        }
        //展开详情与关闭
        holder.tv_child_logistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call.callVisiable(groupPosition,childPosition);
            }
        });
        //展开详情与关闭
        holder.img_child_logistics_top_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call.callVisiable(groupPosition,childPosition);
            }
        });
        if(expandList.get(groupPosition).list.get(childPosition).SelectFlag){
            holder.img_child_select.setImageResource(R.mipmap.cart_select);
        }else{
            if (cangku==null&&maijia==null&&peisong==null){
                holder.img_child_select.setImageResource(R.mipmap.cart_select_null);
            }else {
                if (cangku.equals(expandList.get(groupPosition).list.get(childPosition).cangku_name)
                        && maijia.equals(expandList.get(groupPosition).list.get(childPosition).gongsi)
                        && peisong.equals(expandList.get(groupPosition).list.get(childPosition).logistics_name)) {
                    holder.img_child_select.setImageResource(R.mipmap.cart_select_null);
                } else {
                    holder.img_child_select.setImageResource(R.mipmap.cart_no);
                }
            }
        }

        notifyDataSetChanged();
        holder.img_child_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //根据卖家，仓库，和配送方式选择是否可以打勾，目前还不完善，
                if (cangku != null&&maijia!=null&&peisong!=null) {
                    if (cangku.equals(expandList.get(groupPosition).list.get(childPosition).cangku_name)
                            &&maijia.equals(expandList.get(groupPosition).list.get(childPosition).gongsi)
                            &&peisong.equals(expandList.get(groupPosition).list.get(childPosition).logistics_name)) {
                        call.call(groupPosition, childPosition,holder.edt_child_num.getText().toString(),expand.relative_group_item,holder.relative_child_item);
                    }else{
                        Toast.makeText(ctx,"同一个卖家、同一个仓库、同一个配送方式的可以创建一个物流订单",Toast.LENGTH_SHORT).show();
                    }
                } else {
                    cangku=expandList.get(groupPosition).list.get(childPosition).cangku_name;
                    peisong=expandList.get(groupPosition).list.get(childPosition).logistics_name;
                    maijia=expandList.get(groupPosition).list.get(childPosition).gongsi;
                    call.call(groupPosition, childPosition,holder.edt_child_num.getText().toString(),expand.relative_group_item,holder.relative_child_item);
                }
                int k = 0;
                for (int i = 0; i < expandList.size(); i++) {
                    for (int j = 0; j < expandList.get(i).list.size(); j++) {
                        if (!expandList.get(i).list.get(j).SelectFlag) {
                            k++;
                        }
                    }
                }
                int sum = 0;
                for (int i = 0; i < expandList.size(); i++) {
                    int all = expandList.get(i).list.size();
                    sum += all;
                }
                if(k==sum){
                    cangku=null;
                    maijia=null;
                    peisong=null;
                    notifyDataSetChanged();
                }
            }
         });
//        holder.tv_child_sub.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String str=holder.edt_child_num.getText().toString();
//                int i=Integer.valueOf(str);
//                i--;
//                if(i<1){
//                    i=1;
//                }
//                holder.edt_child_num.setText(i+"");
//            }
//        });
//        holder.tv_child_add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String str=holder.edt_child_num.getText().toString();
//                int i=Integer.valueOf(str);
//                i++;
//                holder.edt_child_num.setText(i+"");
//            }
//        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
    class ExpandHolder{
        TextView tv_expand_order_num;
        TextView tv_expand_order_name;
        TextView tv_expand_date;
        TextView tv_expand_company;
        Button btn_expand_kefu;
        RelativeLayout relative_group_item;
        //            Jobs Created 最大可发货数量
        TextView tv_item_create_logistic_vailable_quantity2;
    }
     class ViewHolder{
        ImageView img_child_select,img_child_logistics_top_in;
        TextView tv_child_logistics;
        TextView tv_child_sub;
        TextView tv_child_add;
         TextView edt_child_num;
        LinearLayout liear_child_message;
        TextView tv_child_produce_top_name,tv_child_sort,tv_child_product_name,tv_child_num,tv_child_price
                ,tv_child_can_num,tv_child_date,tv_child_cangku;
         RelativeLayout relative_child_item;
    }
    public interface LogisticsCall{
        public void call(int groupPosition, int childPosition, String num,RelativeLayout view,RelativeLayout childView);
        public void callNum(int groupPosition,int childPosition,String id);
        public void callVisiable(int groupPosition,int childPosition);
    }
}
