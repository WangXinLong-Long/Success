package com.silianchuangye.sumao.success.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.silianchuangye.sumao.success.R;
import com.silianchuangye.sumao.success.fragments.bean.CartInfo;
import com.silianchuangye.sumao.success.fragments.shoppingCart.dialog.Cart_MyDialog;

import java.util.List;

/**
 * Created by Administrator on 2016/5/20.
 * 购物车列表适配器
 */
public class CartAdapter extends BaseAdapter{
private List<CartInfo> list;
    private Context ctx;
    private Cart_MyDialog dialog;
    private SelectCallBack call;
    private String temp;
    public CartAdapter(Context ctx,List<CartInfo>list,SelectCallBack call,Cart_MyDialog dialog){
        this.ctx=ctx;
        this.list=list;
        this.call=call;
        this.dialog=dialog;
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
        final ViewHolder holder;
        if(convertView==null){
            convertView=View.inflate(ctx, R.layout.item_cart_activity,null);
            holder=new ViewHolder();
            holder.Tv_all_price= (TextView) convertView.findViewById(R.id.tv_item_cart_all_price);
            holder.Edt_buy_num= (EditText) convertView.findViewById(R.id.tv_item_cart_buy_num);
            holder.Tv_cangku_name= (TextView) convertView.findViewById(R.id.tv_item_cart_cangku_name);
            holder.Tv_company= (TextView) convertView.findViewById(R.id.tv_item_cart_cangku_conmpany_name);
            holder.Tv_name= (TextView) convertView.findViewById(R.id.tv_item_cart_name);
            holder.Tv_pai_num= (TextView) convertView.findViewById(R.id.tv_item_cart_paihao_num);
            holder.Tv_price= (TextView) convertView.findViewById(R.id.tv_item_cart_cangku_danjia_price);
            holder.Tv_qiye= (TextView) convertView.findViewById(R.id.tv_item_cart_shengchan_name);
            holder.Tv_sort_name= (TextView) convertView.findViewById(R.id.tv_item_cart_pinzhong_name);
            holder.Img_select= (ImageView) convertView.findViewById(R.id.img_item_cart_select);
            holder.Img_del= (ImageView) convertView.findViewById(R.id.img_item_cart_del);
            holder.Tv_buy_Sub= (TextView) convertView.findViewById(R.id.img_item_cart_buy_sub);
            holder.Tv_buy_Add= (TextView) convertView.findViewById(R.id.img_item_cart_buy_add);
            convertView.setTag(holder);
        }else{
           holder= (ViewHolder) convertView.getTag();
        }
        holder.Tv_name.setText(list.get(position).name);
        holder.Tv_sort_name.setText(list.get(position).sort_name);
        holder.Tv_cangku_name.setText(list.get(position).cangku_name);
        holder.Tv_pai_num.setText(list.get(position).pai_num);
        holder.Tv_price.setText(list.get(position).price);
        holder.Tv_qiye.setText(list.get(position).qiye);
        holder.Tv_company.setText(list.get(position).company);
        holder.Edt_buy_num.setText(list.get(position).buy_num);
        holder.Tv_all_price.setText(list.get(position).all_price+"元");
        //选中与不选中
        if(list.get(position).Selsct_Flag){
            holder.Img_select.setImageResource(R.mipmap.cart_select);
        }else {
            holder.Img_select.setImageResource(R.mipmap.cart_select_null);
        }
        holder.Img_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call.Call(position);}
        });
        //删除按钮
        holder.Img_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(list.get(position));
                notifyDataSetChanged();
            }
        });

        //增加或减少购买数
        holder.Tv_buy_Sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=holder.Edt_buy_num.getText().toString();
                int num=Integer.valueOf(str);
                num--;
                if(num>=1) {
                    holder.Edt_buy_num.setText("" + num);
                }
            }
        });
        holder.Tv_buy_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=holder.Edt_buy_num.getText().toString();
                int num=Integer.valueOf(str);
                num++;
                if(num>=15){
                    dialog.show();
                }
                if(num<15&&num>=0) {
                    holder.Edt_buy_num.setText("" + num);
                }
            }
        });
        holder.Edt_buy_num.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                temp=holder.Edt_buy_num.getText().toString();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str=holder.Edt_buy_num.getText().toString();
                if(str.equals("")){
                    Toast.makeText(ctx, "购买数量不能为空", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                String str=holder.Edt_buy_num.getText().toString();
                if(str.equals("")){
                    str=temp;
                    holder.Edt_buy_num.setText(str);
                }
                int i=Integer.valueOf(str);
                if(i>=15){
                   holder.Edt_buy_num.setText(temp);
                    dialog.show();
                }
                if(i<1){
                    holder.Edt_buy_num.setText(temp);
                }

            }
        });
        return convertView;
    }
    public class ViewHolder{
        TextView Tv_name;
        TextView Tv_sort_name;
        TextView Tv_cangku_name;
        TextView Tv_pai_num;
        TextView Tv_price;
        TextView Tv_qiye;
        TextView Tv_company;
        EditText Edt_buy_num;
        TextView Tv_buy_Sub,Tv_buy_Add;
        TextView Tv_all_price;
        ImageView Img_select,Img_del;
    }

    public interface SelectCallBack{
        public void Call(int index);
    }
}
